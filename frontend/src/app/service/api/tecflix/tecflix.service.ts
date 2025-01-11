import {
    HttpClient,
    HttpErrorResponse,
    HttpHeaders,
} from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { catchError, map, Observable, of } from 'rxjs';
import { Course } from '../../../interface/response/course';
import { ApiError } from '../../../interface/response/api-error';
import { Pagination } from '../../../interface/response/pagination';
import { AuthService } from '../../auth/auth.service';

@Injectable({
    providedIn: 'root',
})
export class TecflixService {
    private _http: HttpClient = inject(HttpClient);
    private _authService: AuthService = inject(AuthService);

    private _baseHeaders: HttpHeaders = new HttpHeaders()
        .set('X-API-KEY', environment.apiKey)
        .set(
            'Authorization',
            `Bearer ${this._authService.extractToken().accessToken}`
        );

    ngOnInit() {
        fetch(environment.apiUrl);
        fetch(environment.apiKey);
    }
    constructor() {}

    public findAllCourses(paged: {
        page?: number;
        size?: number;
        direction?: string;
    }): Observable<Pagination<Course> | ApiError> {
        const pageOptions = this.createPageOptions(paged);
        return this._http
            .get<Pagination<Course>>(
                `${environment.apiUrl}/api/v1/courses?${pageOptions}`,
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
                `${environment.apiUrl}/api/v1/courses/search?${pageOptions}${searchOptions}`,
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

    private createPageOptions(paged: {
        page?: number;
        size?: number;
        direction?: string;
    }): string {
        const sort = paged.direction || 'totalReviews,ASC';
        return `page=${paged.page || 0}&size=${
            paged.size || 7
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
