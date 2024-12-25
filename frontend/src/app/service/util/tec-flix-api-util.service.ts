import { Injectable } from '@angular/core';
import { ApiError } from '../../interface/response/api-error';

@Injectable({
  providedIn: 'root'
})
export class TecFlixApiUtilService {

  constructor() { }

  public isApiError<T>(response: T | ApiError ): response is ApiError {
    return (response as ApiError).details !== undefined;
  }
}
