import {
    Component,
    inject,
    Input,
    signal,
    WritableSignal,
} from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

// interfaces
import { Course } from '../../interfaces/response/course';

// primeng
import { ButtonModule } from 'primeng/button';
import { SkeletonModule } from 'primeng/skeleton';

@Component({
    selector: 'app-course-details',
    imports: [
        CommonModule,

        // primeng
        ButtonModule,
        SkeletonModule,
    ],
    templateUrl: './course-details.component.html',
    styleUrl: './course-details.component.scss',
})
export class CourseDetailsComponent {
    private _router: Router = inject(Router);

    @Input() public course: WritableSignal<Course> = signal({
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
        totalScore: 0,
        totalReviews: 0,
        averageScore: 0,
        createdAt: new Date(),
        active: false,
    });

    public navigate(path: string, id: number | string) {
        if (path === '/profile') {
            return this._router.navigate([`/${path}/${id}`]);
        }

        return this._router.navigate([`/${path}`], {
            queryParams: {
                tags: id,
                page: 0,
                size: 10,
                direction: 'totalReviews,desc',
            },
        });
    }
}
