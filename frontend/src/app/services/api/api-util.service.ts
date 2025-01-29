import { Injectable } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

// interfaces
import { ApiError } from '../../interfaces/response/api-error';

@Injectable({
  providedIn: 'root',
})
export class ApiUtilService {
  constructor() {}

  public isApiError<T>(response: T | ApiError): response is ApiError {
    return (response as ApiError).status !== undefined;
  }

  public catchApiError(error: HttpErrorResponse): Observable<ApiError> {
    const apiError: ApiError = {
      title: error.error.title,
      timestamp: error.error.timestamp,
      details: error.error.details,
      status: error.status,
    };
    return of(apiError);
  }
}
