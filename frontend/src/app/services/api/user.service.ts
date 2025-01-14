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
import { User } from '../../interfaces/response/user';
import { ApiError } from '../../interfaces/response/api-error';

// environments
import { environment } from '../../../environments/environment';

@Injectable({
    providedIn: 'root',
})
export class UserService {
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

    public findMe(): Observable<User | ApiError> {
        return this._http
            .get<User>(`${environment.apiUrl}v1/users/me`, {
                headers: this._baseHeaders,
            })
            .pipe(
                map((response: User) => response),
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

    public findProfile(id: string): Observable<User | ApiError> {
        return this._http
            .get<User>(`${environment.apiUrl}v1/users/profile/${id}`, {
                headers: this._baseHeaders,
            })
            .pipe(
                map((response: User) => response),
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

    public findById(id: string): Observable<User | ApiError> {
        return this._http
            .get<User>(`${environment.apiUrl}v1/courses/${id}`, {
                headers: this._baseHeaders,
            })
            .pipe(
                map((response: User) => response),
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
