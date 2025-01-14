import { Component, Input, signal, WritableSignal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

// interfaces
import { Course } from '../../interfaces/response/course';
import { Pagination } from '../../interfaces/response/pagination';

// primeng
import { CarouselModule } from 'primeng/carousel';
import { ButtonModule } from 'primeng/button';
import { Rating } from 'primeng/rating';
import { TagModule } from 'primeng/tag';
import { SkeletonModule } from 'primeng/skeleton';

@Component({
    selector: 'app-carousel',
    imports: [
        FormsModule,
        RouterLink,
        CommonModule,

        // primang
        CarouselModule,
        ButtonModule,
        TagModule,
        Rating,
        SkeletonModule,
    ],
    templateUrl: './carousel.component.html',
    styleUrl: './carousel.component.scss',
})
export class CarouselComponent {
    @Input() public pagination: WritableSignal<Pagination<Course>> = signal({
        content: [],
        links: null,
        page: null,
    });
    public responsiveOptions: any[] | undefined;
    public value: number = 5;

    constructor() {}

    ngOnInit() {
        this.responsiveOptions = [
            {
                breakpoint: '1400px',
                numVisible: 2,
                numScroll: 1,
            },
            {
                breakpoint: '1199px',
                numVisible: 3,
                numScroll: 1,
            },
            {
                breakpoint: '767px',
                numVisible: 2,
                numScroll: 1,
            },
            {
                breakpoint: '575px',
                numVisible: 1,
                numScroll: 1,
            },
        ];
    }

    public getScore(totalScore: number, totalReviews: number) {
        if (totalScore === null || totalReviews === null)
            return (this.value = 0);

        return (this.value = totalScore / totalReviews);
    }
}
