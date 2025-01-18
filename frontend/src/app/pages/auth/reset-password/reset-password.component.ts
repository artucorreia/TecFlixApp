import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';

// components
import { ChangePasswordComponent } from '../../../components/change-password/change-password.component';

// services
import { ApiUtilService } from '../../../services/api/api-util.service';
import { AuthService } from '../../../services/auth/auth.service';
import { MessageUtilService } from '../../../services/util/message-util.service';

// interfaces
interface QueryParams {
    userId: string;
    code: string;
}

@Component({
    selector: 'app-reset-password',
    imports: [RouterLink, ChangePasswordComponent],
    templateUrl: './reset-password.component.html',
    styleUrl: './reset-password.component.scss',
})
export class ResetPasswordComponent {
    private _apiUtil: ApiUtilService = inject(ApiUtilService);
    private _authService: AuthService = inject(AuthService);
    private _http: ActivatedRoute = inject(ActivatedRoute);
    private _messageService: MessageUtilService = inject(MessageUtilService);

    public apiCallIsOver = signal(false);
    private _queryParams: QueryParams = {
        code: '',
        userId: '',
    };

    ngOnInit(): void {
        this.setQueryParams();
    }

    private setQueryParams(): void {
        this._http.queryParams.subscribe((params) => {
            this._queryParams.code = params['code'];
            this._queryParams.userId = params['userId'];
        });
    }

    public resetPassword(newPassword: string): void {
        this._authService
            .resetPassword(this._queryParams.code, this._queryParams.userId, {
                newPassword: newPassword,
            })
            .subscribe({
                next: (response) => {
                    if (this._apiUtil.isApiError(response)) {
                        this._messageService.display({
                            severity: 'error',
                            summary: 'Error',
                            detail: response.title,
                            life: 3000,
                        });
                        this.apiCallIsOver.set(true);
                        return;
                    }

                    this._messageService.display({
                        severity: 'success',
                        summary: 'Success',
                        detail: response.message,
                        life: 3000,
                    });
                    this.apiCallIsOver.set(true);
                    return;
                },
                error: (error) => {
                    this.apiCallIsOver.set(true);
                    console.log('unexpected error', error);
                },
            });
    }
}
