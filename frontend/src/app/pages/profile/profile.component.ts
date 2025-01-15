import { Component, inject, signal, WritableSignal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';

// services
import { UserService } from '../../services/api/user.service';
import { ApiUtilService } from '../../services/api/api-util.service';
import { MessageUtilService } from '../../services/util/message-util.service';

// componentes
import { CarouselComponent } from '../../components/carousel/carousel.component';

// interfaces
import { User } from '../../interfaces/response/user';
import { Pagination } from '../../interfaces/response/pagination';
import { Course } from '../../interfaces/response/course';

// primeng
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { ButtonModule } from 'primeng/button';
import { SkeletonModule } from 'primeng/skeleton';

@Component({
    selector: 'app-profile',
    imports: [
        CommonModule,
        CarouselComponent,
        DatePipe,

        // primeng
        ScrollPanelModule,
        ButtonModule,
        SkeletonModule,
    ],
    templateUrl: './profile.component.html',
    styleUrl: './profile.component.scss',
})
export class ProfileComponent {
    private _userService: UserService = inject(UserService);
    private _messageService: MessageUtilService = inject(MessageUtilService);
    private _apiUtil: ApiUtilService = inject(ApiUtilService);
    private _http: ActivatedRoute = inject(ActivatedRoute);

    public user: User | null = null;
    public coursesTaught: WritableSignal<Pagination<Course>> = signal({
        content: [],
        links: null,
        page: null,
    });
    ngOnInit() {
        this.findProfileById(this.getUserId());
    }

    private getUserId() {
        let userId: string = '';
        this._http.params.subscribe((params) => {
            if (params['id']) userId = params['id'];
        });
        return userId;
    }

    private findProfileById(id: string) {
        this._userService.findProfile(id).subscribe({
            next: (response) => {
                if (this._apiUtil.isApiError(response)) {
                    this._messageService.display({
                        severity: 'error',
                        summary: 'Error',
                        detail: response.title,
                        life: 3000,
                    });
                    return;
                }
                this.user = response;
                if (response.coursesTaught) {
                    this.coursesTaught.set({
                        content: response.coursesTaught,
                        links: null,
                        page: null,
                    });
                }
            },
            error: (error) => {
                console.log('unexpected error', error);
            },
        });
    }
}
