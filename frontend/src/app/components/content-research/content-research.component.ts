import {
    Component,
    inject,
    Input,
    signal,
    WritableSignal,
} from '@angular/core';

// interfaces
import {
    Links,
    PageMetadata,
    Pagination,
} from '../../interface/response/pagination';
import { Course } from '../../interface/response/course';
import { ActivatedRoute, RouterLink } from '@angular/router';

// primeng
import { CarouselModule } from 'primeng/carousel';
import { ButtonModule } from 'primeng/button';
import { Rating } from 'primeng/rating';
import { TagModule } from 'primeng/tag';
import { SkeletonModule } from 'primeng/skeleton';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PaginatorComponent } from '../paginator/paginator.component';

@Component({
    selector: 'app-content-research',
    imports: [
        CommonModule,
        RouterLink,
        FormsModule,
        PaginatorComponent,

        // primang
        CarouselModule,
        ButtonModule,
        TagModule,
        Rating,
        SkeletonModule,
    ],
    templateUrl: './content-research.component.html',
    styleUrl: './content-research.component.scss',
})
export class ContentResearchComponent {
    private _http: ActivatedRoute = inject(ActivatedRoute);

    @Input() public data!: Pagination<Course>;

    public pagination: WritableSignal<Pagination<Course>> = signal({
        content: [],
        links: null,
        page: null,
    });

    public pageMetadata: WritableSignal<PageMetadata> = signal({
        size: 0,
        totalElements: 0,
        totalPages: 0,
        number: 0,
    });

    public value = 0;
    public term = signal('');

    ngOnChanges() {
        this.pagination.set(this.data);
        if (this.data.page) this.pageMetadata.set(this.data.page);
    }

    public getScore(totalScore: number, totalReviews: number) {
        if (totalScore === null || totalReviews === null)
            return (this.value = 0);

        return (this.value = totalScore / totalReviews);
    }
}
