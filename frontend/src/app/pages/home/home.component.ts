import { Component, inject, signal, WritableSignal } from '@angular/core';

// components
import { CarouselComponent } from '../../components/carousel/carousel.component';

// services
import { CourseService } from '../../services/api/course.service';
import { ApiUtilService } from '../../services/api/api-util.service';

// interfaces
import { Pagination } from '../../interfaces/response/pagination';
import { Course } from '../../interfaces/response/course';

@Component({
    selector: 'app-home',
    imports: [CarouselComponent],
    templateUrl: './home.component.html',
    styleUrl: './home.component.scss',
})
export class HomeComponent {
    private _courseService: CourseService = inject(CourseService);
    private _apiUtil: ApiUtilService = inject(ApiUtilService);

    public carousels: WritableSignal<
        { title: string; pagination: WritableSignal<Pagination<Course>> }[]
    > = signal([]);

    ngOnInit() {
        // find more top rated courses
        this.findTopRatedCourses();

        // find latest courses
        this.findLatestCourses();

        // find courses about development
        this.findCoursesAboutTheme();
    }

    private findTopRatedCourses(): void {
        this._courseService.findAll({ size: 7 }).subscribe({
            next: (response) => {
                if (this._apiUtil.isApiError(response)) {
                    console.log(response);
                    return;
                }
                const carousel = {
                    title: 'Melhor avaliados',
                    pagination: signal(response),
                };
                this.carousels.update((values) => [...values, carousel]);
            },
            error: (error) => {
                console.log('unexpected error', error);
            },
        });
    }

    private findLatestCourses(): void {
        this._courseService
            .findAll({ direction: 'createdAt,desc', size: 7 })
            .subscribe({
                next: (response) => {
                    if (this._apiUtil.isApiError(response)) {
                        console.log(response);
                        return;
                    }
                    const carousel = {
                        title: 'Mais Recentes',
                        pagination: signal(response),
                    };
                    this.carousels.update((values) => [...values, carousel]);
                },
                error: (error) => {
                    console.log('unexpected error', error);
                },
            });
    }

    private findCoursesAboutTheme(): void {
        this._courseService
            .search({ size: 7 }, { tags: '1,2,3,4,5,6' })
            .subscribe({
                next: (response) => {
                    if (this._apiUtil.isApiError(response)) {
                        console.log(response);
                        return;
                    }
                    const carousel = {
                        title: 'Desenvolvimento',
                        pagination: signal(response),
                    };
                    this.carousels.update((values) => [...values, carousel]);
                },
                error: (error) => {
                    console.log('unexpected error', error);
                },
            });
    }
}
