import { Component, inject, signal, WritableSignal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

// services
import { ApiUtilService } from '../../services/api/tecflix/api-util.service';
import { CourseService } from '../../services/api/tecflix/course.service';

// components
import { CourseDetailsComponent } from '../../components/course-details/course-details.component';

// interfaces
import { Course } from '../../interfaces/response/course';

@Component({
    selector: 'app-course',
    imports: [CourseDetailsComponent],
    templateUrl: './course.component.html',
    styleUrl: './course.component.scss',
})
export class CourseComponent {
    private _http: ActivatedRoute = inject(ActivatedRoute);
    private _apiUtil: ApiUtilService = inject(ApiUtilService);
    private _courseService: CourseService = inject(CourseService);

    private _courseId: string = '';

    public course: WritableSignal<Course> = signal({
        id: '',
        title: '',
        description: '',
        capeImage: '',
        modules: [],
        tags: [],
        professor: { id: '', name: '' },
        totalScoreReviews: 0,
        totalReviews: 0,
        createdAt: new Date(),
        active: false,
    });

    ngOnInit() {
        this.getCourseId();

        this._courseService.findById(this._courseId).subscribe({
            next: (response) => {
                if (this._apiUtil.isApiError(response)) {
                    console.log(response);
                    return;
                }
                this.course.set(response);
            },
            error: (error) => {
                console.log('unexpected error', error);
            },
        });
    }

    getCourseId() {
        return this._http.params.subscribe(
            (res) => (this._courseId = res['id'])
        );
    }
}
