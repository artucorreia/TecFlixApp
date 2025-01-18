import { inject, Injectable } from '@angular/core';
import {
    HttpClient,
    HttpErrorResponse,
    HttpHeaders,
} from '@angular/common/http';
import { catchError, map, Observable, of } from 'rxjs';

// interfaces
import { Login } from '../../interfaces/resquest/login';
import { Register } from '../../interfaces/resquest/register';
import { ApiError } from '../../interfaces/response/api-error';
import { Token } from '../../interfaces/response/token';
import { GenericResponse } from '../../interfaces/response/generic-response';

// environments
import { environment } from '../../../environments/environment';

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    private _http = inject(HttpClient);
    private _baseHeaders = new HttpHeaders().set(
        'X-API-KEY',
        environment.apiKey
    );

    ngOnInit() {
        fetch(environment.apiUrl);
        fetch(environment.apiKey);
    }

    public clearStorage(): void {
        localStorage.removeItem('token');
        sessionStorage.removeItem('token');
    }

    private getToken(): Token | null {
        const stringToken: string | null =
            localStorage.getItem('token') || sessionStorage.getItem('token');

        if (stringToken) return JSON.parse(stringToken);
        return null;
    }

    public extractAccessToken(): string {
        let token: Token | null = this.getToken();
        return token?.accessToken || '';
    }

    public saveToken(token: Token, rememberMe: boolean | null): void {
        const storage: Storage = rememberMe ? localStorage : sessionStorage;
        storage.setItem('token', JSON.stringify(token));
    }

    public login(data: Login): Observable<Token | ApiError> {
        return this._http
            .post<Token>(`${environment.apiUrl}auth/login`, data, {
                headers: this._baseHeaders,
            })
            .pipe(
                map((response: Token) => response),
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

    public register(data: Register): Observable<GenericResponse | ApiError> {
        return this._http
            .post<GenericResponse>(`${environment.apiUrl}auth/register`, data, {
                headers: this._baseHeaders,
            })
            .pipe(
                map((response: GenericResponse) => response),
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

    public sendEmailCode(
        userId: string,
        resetPassword?: boolean
    ): Observable<GenericResponse | ApiError> {
        return this._http
            .post<GenericResponse>(
                `${environment.apiUrl}auth/send-code/${userId}?resetPassword=${
                    resetPassword || false
                }`,
                null,
                {
                    headers: this._baseHeaders,
                }
            )
            .pipe(
                map((response: GenericResponse) => response),
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

    public validateEmailCode(
        code: string,
        userId: string
    ): Observable<GenericResponse | ApiError> {
        return this._http
            .post<GenericResponse>(
                `${environment.apiUrl}auth/validate-code?code=${code}&userId=${userId}`,
                null,
                { headers: this._baseHeaders }
            )
            .pipe(
                map((response: GenericResponse) => response),
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

    public changePassword(data: {
        newPassword: string;
    }): Observable<GenericResponse | ApiError> {
        const authenticatedHeaders: HttpHeaders = new HttpHeaders()
            .set('X-API-KEY', environment.apiKey)
            .set('Authorization', `Bearer ${this.extractAccessToken()}`);
        return this._http
            .post<GenericResponse>(
                `${environment.apiUrl}auth/change-password`,
                data,
                { headers: authenticatedHeaders }
            )
            .pipe(
                map((response: GenericResponse) => response),
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

    public resetPassword(
        code: string,
        userId: string,
        data: {
            newPassword: string;
        }
    ): Observable<GenericResponse | ApiError> {
        return this._http
            .post<GenericResponse>(
                `${environment.apiUrl}auth/reset-password?code=${code}&userId=${userId}`,
                data,
                { headers: this._baseHeaders }
            )
            .pipe(
                map((response: GenericResponse) => response),
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
