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
import { ProfessorRegister } from '../../interfaces/request/professor-register';
import { GenericResponse } from '../../interfaces/response/generic-response';
import { ApiUtilService } from './api-util.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private _http: HttpClient = inject(HttpClient);
  private _authService: AuthService = inject(AuthService);
  private _apiUtil: ApiUtilService = inject(ApiUtilService);

  private _baseHeaders: HttpHeaders = new HttpHeaders()
    .set('X-API-KEY', environment.apiKey)
    .set('Authorization', `Bearer ${this._authService.extractAccessToken()}`);
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
        }),
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
        }),
      );
  }

  public findByEmail(email: string): Observable<User | ApiError> {
    // NOTE: Setting headers without token
    const headers: HttpHeaders = new HttpHeaders().set(
      'X-API-KEY',
      environment.apiKey,
    );

    return this._http
      .get<User>(`${environment.apiUrl}v1/users/find?email=${email}`, {
        headers: headers,
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
        }),
      );
  }

  public registerProfessor(
    data: ProfessorRegister,
  ): Observable<GenericResponse | ApiError> {
    return this._http
      .post<GenericResponse>(
        `${environment.apiUrl}v1/users/professor-register`,
        data,
        { headers: this._baseHeaders },
      )
      .pipe(
        map((response: GenericResponse) => response),
        catchError((error: HttpErrorResponse) =>
          this._apiUtil.catchApiError(error),
        ),
      );
  }
}
