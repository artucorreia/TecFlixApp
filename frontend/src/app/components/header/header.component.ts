import { Component, inject, signal, WritableSignal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ActivatedRoute, Router } from '@angular/router';

// services
import { AuthService } from '../../service/auth/auth.service';
import { TecFlixApiUtilService } from '../../service/util/api/tec-flix-api-util.service';

// interfaces
import { Tag } from '../../interface/response/tag';
import { TagsService } from '../../service/api/tecflix/tags.service';

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
    private _tagsService: TagsService = inject(TagsService);
    private _apiUtil: TecFlixApiUtilService = inject(TecFlixApiUtilService);

    public term = signal('');

    public items: WritableSignal<MenuItem[]> = signal([]);
    public userMenuItems: MenuItem[] = [];

    constructor() {}

    ngOnInit() {
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
                            direction: 'totalReviews,desc',
                        },
                    }),
            },
        ]);

        this.findAllTags();

        this.userMenuItems = [
            {
                label: 'Profile',
                items: [
                    {
                        label: 'Conta',
                        icon: 'pi pi-cog',
                        command: () => console.log('Conta'),
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

    public search(event: KeyboardEventInit): void {
        if (event.key == 'Enter')
            this._router.navigate(['/search'], {
                queryParams: this.getQueryParams({
                    term: this.term(),
                    page: 0,
                    size: 10,
                    direction: 'totalReviews,desc',
                }),
            });
    }

    // TODO: add cache
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
            if (!queryParams.term) queryParams.term = params['term'];
            if (!queryParams.tags) queryParams.tags = params['tags'];
            if (!queryParams.page) queryParams.page = params['page'];
            if (!queryParams.size) queryParams.size = params['size'];
            if (!queryParams.direction)
                queryParams.direction = params['direction'];
        });
        return queryParams;
    }
}
