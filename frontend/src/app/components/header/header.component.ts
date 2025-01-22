import { Component, inject, signal, WritableSignal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ActivatedRoute, Router } from '@angular/router';

// services
import { AuthService } from '../../services/auth/auth.service';
import { ApiUtilService } from '../../services/api/api-util.service';
import { TagService } from '../../services/api/tag.service';

// interfaces
import { Tag } from '../../interfaces/response/tag';

// primeng
import { MenuItem } from 'primeng/api';
import { InputTextModule } from 'primeng/inputtext';
import { Menubar } from 'primeng/menubar';
import { BadgeModule } from 'primeng/badge';
import { AvatarModule } from 'primeng/avatar';
import { Ripple } from 'primeng/ripple';
import { MenuModule } from 'primeng/menu';
import { IconField } from 'primeng/iconfield';
import { InputIcon } from 'primeng/inputicon';

@Component({
  selector: 'app-header',
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,

    // primeng
    Menubar,
    BadgeModule,
    AvatarModule,
    InputTextModule,
    Ripple,
    MenuModule,
    IconField,
    InputIcon,
  ],
  templateUrl: './header.component.html',
  styles: `
    .avatar:hover {
      cursor: pointer;
    }
  `,
})
export class HeaderComponent {
  private _router: Router = inject(Router);
  private _http: ActivatedRoute = inject(ActivatedRoute);
  private _authService: AuthService = inject(AuthService);
  private _tagsService: TagService = inject(TagService);
  private _apiUtil: ApiUtilService = inject(ApiUtilService);

  public term = signal('');

  public items: WritableSignal<MenuItem[]> = signal([]);
  public userMenuItems: MenuItem[] = [];

  constructor() {}

  ngOnInit() {
    this.setItems();
    this.findAllTags();
    this.setUserMenuItems();
    this.getTerm();
  }

  private getTerm(): void {
    this._http.queryParams.subscribe((params) => {
      this.term.set(params['term']);
    });
  }

  private setItems(): void {
    this.items.set([
      {
        label: 'Inicio',
        route: '/home',
      },
      {
        label: 'Todos os Cursos',
        command: () =>
          this._router.navigate(['/search'], {
            queryParams: {
              page: 0,
              size: 10,
              direction: 'averageScore,desc',
            },
          }),
      },
    ]);
  }

  private setUserMenuItems(): void {
    this.userMenuItems = [
      {
        label: 'Profile',
        items: [
          {
            label: 'Conta',
            icon: 'pi pi-cog',
            routerLink: '/account',
          },
          {
            label: 'Aprendizado',
            icon: 'pi pi-video',
            badge: '2',
            command: () => console.log('Aprendizado'),
          },
          {
            separator: true,
          },
          {
            label: 'Sair',
            icon: 'pi pi-sign-out',
            command: () => this.logout(),
          },
        ],
      },
    ];
  }

  // NOTE: listen event key in search input
  // if event key is 'enter' search for that
  public listener(event: KeyboardEvent): void {
    if (event.key == 'Enter') {
      this.search();
    }
  }

  public clearTerm() {
    this.term.set('');
    this.search();
  }

  private search(): void {
    this._router.navigate(['/search'], {
      queryParams: this.getQueryParams({
        term: this.term(),
        page: 0,
        size: 10,
        direction: 'averageScore,desc',
      }),
    });
  }

  private findAllTags(): void {
    this._tagsService.findAllTags().subscribe({
      next: (response) => {
        if (this._apiUtil.isApiError(response)) {
          console.log(response);
          return;
        }
        this.mapTags(response);
        return;
      },
      error: (error) => {
        console.log('unexpected error', error);
      },
    });
  }

  private mapTags(tags: Tag[]): void {
    let result: MenuItem[] = [];
    for (let tag of tags) {
      result.push({
        id: tag.id.toString(),
        label: tag.name,
        command: (item) => {
          if (item.item) {
            this._router.navigate(['/search'], {
              queryParams: this.getQueryParams({
                tags: item.item.id,
              }),
            });
          }
        },
      });
    }
    this.items.update((values) => [
      ...values,
      {
        label: 'Tags',
        items: result,
      },
    ]);
  }

  private logout(): void {
    this._authService.clearStorage();
    this._router.navigate(['/sing-in']);
  }

  private getQueryParams(defaultParams: {
    term?: string;
    tags?: string;
    page?: number;
    size?: number;
    direction?: string;
  }) {
    let queryParams = defaultParams;
    this._http.queryParams.subscribe((params) => {
      if (typeof queryParams.term == undefined)
        queryParams.term = params['term'];
      if (typeof queryParams.tags == undefined)
        queryParams.tags = params['tags'];
      if (typeof queryParams.page == undefined)
        queryParams.page = params['page'];
      if (typeof queryParams.size == undefined)
        queryParams.size = params['size'];
      if (typeof queryParams.direction == undefined)
        queryParams.direction = params['direction'];
    });
    return queryParams;
  }
}
