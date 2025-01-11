import { Component, inject, signal, WritableSignal } from '@angular/core';

// components
import { HeaderComponent } from '../../components/header/header.component';
import { CarouselComponent } from '../../components/carousel/carousel.component';
import { FooterComponent } from '../../components/footer/footer.component';

// services
import { TecflixService } from '../../service/api/tecflix/tecflix.service';
import { TecFlixApiUtilService } from '../../service/util/api/tec-flix-api-util.service';

// interfaces
import { Pagination } from '../../interface/response/pagination';
import { Course } from '../../interface/response/course';

@Component({
    selector: 'app-home',
    imports: [HeaderComponent, CarouselComponent, FooterComponent],
    templateUrl: './home.component.html',
    styleUrl: './home.component.scss',
})
export class HomeComponent {
    private _tecflixApi: TecflixService = inject(TecflixService);
    private _apiUtil: TecFlixApiUtilService = inject(TecFlixApiUtilService);

    public carousels: WritableSignal<
        { title: string; pagination: WritableSignal<Pagination<Course>> }[]
    > = signal([]);

    ngOnInit() {
        // find more top rated courses
        this._tecflixApi
            .findAllCourses({ direction: 'totalReviews,desc' })
            .subscribe({
                next: (response) => {
                    if (this._apiUtil.isApiError(response)) {
                        console.log(response);
                        return;
                    }
                    const carousel = {
                        title: 'Mais Avaliados',
                        pagination: signal(response),
                    };
                    this.carousels.update((values) => [...values, carousel]);
                },
                error: (error) => {
                    console.log('unexpected error', error);
                },
            });

        // find latest courses
        this._tecflixApi
            .findAllCourses({ direction: 'createdAt,desc' })
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

        // find courses about development
        this._tecflixApi
            .search({ direction: 'totalReviews,desc' }, { tags: '1,2,3,4,5,6' })
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
