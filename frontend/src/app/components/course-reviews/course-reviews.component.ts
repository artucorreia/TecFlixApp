import {
    Component,
    inject,
    Input,
    signal,
    WritableSignal,
} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';

// services
import { ApiUtilService } from '../../services/api/api-util.service';
import { CourseService } from '../../services/api/course.service';

// interfaces
import { Review } from '../../interfaces/response/review';

// primeng
import { Rating } from 'primeng/rating';
import { SkeletonModule } from 'primeng/skeleton';
import { DividerModule } from 'primeng/divider';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-course-reviews',
    imports: [
        DatePipe,
        CommonModule,
        FormsModule,

        // primeng
        Rating,
        SkeletonModule,
        DividerModule,
    ],
    templateUrl: './course-reviews.component.html',
    styleUrl: './course-reviews.component.scss',
})
export class CourseReviewsComponent {
    private _http: ActivatedRoute = inject(ActivatedRoute);
    private _apiUtil: ApiUtilService = inject(ApiUtilService);
    private _courseService: CourseService = inject(CourseService);

    public reviews: WritableSignal<Review[]> = signal([]);

    @Input() public details: {
        reviews: number;
        average: number;
    } = {
        reviews: 0,
        average: 0,
    };

    ngOnInit() {
        this._courseService
            .findReviewsByCourseId(this.getCourseId())
            .subscribe({
                next: (response) => {
                    if (this._apiUtil.isApiError(response)) {
                        console.log(response);
                        return;
                    }
                    this.reviews.set(response);
                },
                error: (error) => {
                    console.log('unexpected error', error);
                },
            });
    }

    private getCourseId(): string {
        let courseId = '';
        this._http.params.subscribe((res) => (courseId = res['id']));
        return courseId;
    }
}
