import { Injectable } from '@angular/core';
import { ApiError } from '../../../interfaces/response/api-error';

@Injectable({
    providedIn: 'root',
})
export class ApiUtilService {
    constructor() {}

    public isApiError<T>(response: T | ApiError): response is ApiError {
        return (response as ApiError).details !== undefined;
    }
}
