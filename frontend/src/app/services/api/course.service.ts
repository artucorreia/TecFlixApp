import {
    HttpClient,
    HttpErrorResponse,
    HttpHeaders,
} from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, map, Observable, of } from 'rxjs';

// services
import { AuthService } from '../auth/auth.service';

// interfaces
import { Course } from '../../interfaces/response/course';
import { ApiError } from '../../interfaces/response/api-error';
import { Pagination } from '../../interfaces/response/pagination';

// environments
import { environment } from '../../../environments/environment';
import { Review } from '../../interfaces/response/review';

@Injectable({
    providedIn: 'root',
})
export class CourseService {
    private _http: HttpClient = inject(HttpClient);
    private _authService: AuthService = inject(AuthService);

    private _baseHeaders: HttpHeaders = new HttpHeaders()
        .set('X-API-KEY', environment.apiKey)
        .set(
            'Authorization',
            `Bearer ${this._authService.extractAccessToken()}`
        );

    ngOnInit() {
        fetch(environment.apiUrl);
        fetch(environment.apiKey);
    }

    constructor() {}

    public findById(id: string): Observable<Course | ApiError> {
        return this._http
            .get<Course>(`${environment.apiUrl}v1/courses/${id}`, {
                headers: this._baseHeaders,
            })
            .pipe(
                map((response: Course) => response),
                catchError((error: HttpErrorResponse) => {
                    const apiError: ApiError = {
                        title: error.error.title,
                        timestamp: error.error.timestamp,
                        details: error.error.details,
                        status: error.status,
                    };
                    return of(apiError);
                })
            );
    }

    public findAll(paged: {
        page?: number;
        size?: number;
        direction?: string;
    }): Observable<Pagination<Course> | ApiError> {
        const pageOptions = this.createPageOptions(paged);
        return this._http
            .get<Pagination<Course>>(
                `${environment.apiUrl}v1/courses?${pageOptions}`,
                {
                    headers: this._baseHeaders,
                }
            )
            .pipe(
                map((response: Pagination<Course>) => response),
                catchError((error: HttpErrorResponse) => {
                    const apiError: ApiError = {
                        title: error.error.title,
                        timestamp: error.error.timestamp,
                        details: error.error.details,
                        status: error.status,
                    };
                    return of(apiError);
                })
            );
    }

    public search(
        paged: {
            page?: number;
            size?: number;
            direction?: string;
        },
        search: {
            term?: string;
            tags?: string;
        }
    ): Observable<Pagination<Course> | ApiError> {
        const pageOptions: string = this.createPageOptions(paged);
        const searchOptions: string = this.createSearchOptions(search);
        return this._http
            .get<Pagination<Course>>(
                `${environment.apiUrl}v1/courses/search?${pageOptions}${searchOptions}`,
                {
                    headers: this._baseHeaders,
                }
            )
            .pipe(
                map((response: Pagination<Course>) => response),
                catchError((error: HttpErrorResponse) => {
                    const apiError: ApiError = {
                        title: error.error.title,
                        timestamp: error.error.timestamp,
                        details: error.error.details,
                        status: error.status,
                    };
                    return of(apiError);
                })
            );
    }

    public findReviewsByCourseId(id: string): Observable<Review[] | ApiError> {
        return this._http
            .get<Review[]>(`${environment.apiUrl}v1/courses/${id}/reviews`, {
                headers: this._baseHeaders,
            })
            .pipe(
                map((response: Review[]) => response),
                catchError((error: HttpErrorResponse) => {
                    const apiError: ApiError = {
                        title: error.error.title,
                        timestamp: error.error.timestamp,
                        details: error.error.details,
                        status: error.status,
                    };
                    return of(apiError);
                })
            );
    }

    private createPageOptions(paged: {
        page?: number;
        size?: number;
        direction?: string;
    }): string {
        const sort = paged.direction || 'averageScore,DESC';
        return `page=${paged.page || 0}&size=${
            paged.size || 10
        }&direction=${sort}`;
    }

    private createSearchOptions(search: {
        term?: string;
        tags?: string;
    }): string {
        let result: string = '';
        if (search.term) result += `&term=${search.term}`;
        if (search.tags) result += `&tags=${search.tags}`;
        return result;
    }
}
