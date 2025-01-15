import { Component, inject, signal, WritableSignal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

// services
import { ApiUtilService } from '../../services/api/api-util.service';
import { CourseService } from '../../services/api/course.service';

// components
import { CourseDetailsComponent } from '../../components/course-details/course-details.component';
import { CourseModulesComponent } from '../../components/course-modules/course-modules.component';
import { CourseReviewsComponent } from '../../components/course-reviews/course-reviews.component';

// interfaces
import { Course } from '../../interfaces/response/course';

@Component({
    selector: 'app-course',
    imports: [
        CourseDetailsComponent,
        CourseModulesComponent,
        CourseReviewsComponent,
    ],
    templateUrl: './course.component.html',
    styleUrl: './course.component.scss',
})
export class CourseComponent {
    private _http: ActivatedRoute = inject(ActivatedRoute);
    private _apiUtil: ApiUtilService = inject(ApiUtilService);
    private _courseService: CourseService = inject(CourseService);

    public course: WritableSignal<Course> = signal({
        id: '',
        title: '',
        description: '',
        capeImage: '',
        modules: [],
        tags: [],
        professor: {
            id: '',
            name: '',
            email: null,
            role: null,
            createdAt: null,
            active: null,
            professorData: null,
            enrolledCourses: null,
            socials: null,
            coursesTaught: null,
        },
        totalScoreReviews: 0,
        totalReviews: 0,
        createdAt: new Date(),
        active: false,
    });

    public details: { reviews: number; average: number } = {
        reviews: 0,
        average: 0,
    };

    ngOnInit() {
        this._courseService.findById(this.getCourseId()).subscribe({
            next: (response) => {
                if (this._apiUtil.isApiError(response)) {
                    console.log(response);
                    return;
                }
                this.course.set(response);
                if (response.totalReviews != 0) {
                    this.details = {
                        reviews: response.totalReviews,
                        average:
                            response.totalScoreReviews / response.totalReviews,
                    };
                }
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
