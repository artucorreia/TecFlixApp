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
import { UploadFile } from '../../interfaces/response/upload-file';
import { ApiError } from '../../interfaces/response/api-error';

// environments
import { environment } from '../../../environments/environment';
import { ApiUtilService } from './api-util.service';

@Injectable({
  providedIn: 'root',
})
export class FileService {
  private _http: HttpClient = inject(HttpClient);
  private _apiUtil: ApiUtilService = inject(ApiUtilService);
  private _authService: AuthService = inject(AuthService);
  private _baseHeaders: HttpHeaders = new HttpHeaders()
    .set('X-API-KEY', environment.apiKey)
    .set('Authorization', `Bearer ${this._authService.extractAccessToken()}`);

  constructor() {}

  ngOnInit() {
    fetch(environment.apiUrl);
    fetch(environment.apiKey);
  }

  public uploadFile(file: File): Observable<UploadFile | ApiError> {
    const formData = new FormData();
    formData.set('file', file);

    return this._http
      .post<UploadFile>(`${environment.apiUrl}v1/files/upload`, formData, {
        headers: this._baseHeaders,
      })
      .pipe(
        map((response: UploadFile) => response),
        catchError((error: HttpErrorResponse) =>
          this._apiUtil.catchApiError(error),
        ),
      );
  }
}
