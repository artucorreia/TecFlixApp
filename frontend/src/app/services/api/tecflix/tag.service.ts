import {
    HttpClient,
    HttpErrorResponse,
    HttpHeaders,
} from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { AuthService } from '../../auth/auth.service';
import { environment } from '../../../../environments/environment';
import { catchError, map, Observable, of } from 'rxjs';
import { Tag } from '../../../interfaces/response/tag';
import { ApiError } from '../../../interfaces/response/api-error';

@Injectable({
    providedIn: 'root',
})
export class TagService {
    private _http: HttpClient = inject(HttpClient);
    private _authService: AuthService = inject(AuthService);
    private _baseHeaders: HttpHeaders = new HttpHeaders()
        .set('X-API-KEY', environment.apiKey)
        .set(
            'Authorization',
            `Bearer ${this._authService.extractToken().accessToken}`
        );

    constructor() {}

    ngOnInit() {
        fetch(environment.apiUrl);
        fetch(environment.apiKey);
    }

    public findAllTags(): Observable<Tag[] | ApiError> {
        return this._http
            .get<Tag[]>(`${environment.apiUrl}v1/tags`, {
                headers: this._baseHeaders,
            })
            .pipe(
                map((response: Tag[]) => response),
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
}
