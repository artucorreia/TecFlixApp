import {
    HttpClient,
    HttpErrorResponse,
    HttpHeaders,
} from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, map, Observable, of } from 'rxjs';

// services
import { AuthService } from '../auth/auth.service';

// interfaes
import { Tag } from '../../interfaces/response/tag';
import { ApiError } from '../../interfaces/response/api-error';

// environments
import { environment } from '../../../environments/environment';

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
            `Bearer ${this._authService.extractAccessToken()}`
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
