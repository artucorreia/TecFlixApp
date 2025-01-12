import { inject, Injectable } from '@angular/core';
import {
    HttpClient,
    HttpErrorResponse,
    HttpHeaders,
} from '@angular/common/http';
import { Login } from '../../interfaces/resquest/login';
import { Register } from '../../interfaces/resquest/register';
import { catchError, map, Observable, of } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ApiError } from '../../interfaces/response/api-error';
import { Token } from '../../interfaces/response/token';
import { GenericResponse } from '../../interfaces/response/generic-response';

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

    public extractToken(): Token {
        const token =
            localStorage.getItem('token') || sessionStorage.getItem('token');
        return token ? JSON.parse(token) : null;
    }

    public saveToken(token: Token, rememberMe: boolean | null): void {
        const storage: Storage = rememberMe ? localStorage : sessionStorage;
        storage.setItem('token', JSON.stringify(token));
    }

    public login(data: Login): Observable<Token | ApiError> {
        return this._http
            .post<Token>(`${environment.apiUrl}/auth/login`, data, {
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
            .post<GenericResponse>(
                `${environment.apiUrl}/auth/register`,
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

    public sendEmailCode(
        userId: string
    ): Observable<GenericResponse | ApiError> {
        return this._http
            .post<GenericResponse>(
                `${environment.apiUrl}/auth/send-code/${userId}`,
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

    public validateEmailCode(
        code: string
    ): Observable<GenericResponse | ApiError> {
        return this._http
            .post<GenericResponse>(
                `${environment.apiUrl}/auth/validate-code/${code}`,
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
}
