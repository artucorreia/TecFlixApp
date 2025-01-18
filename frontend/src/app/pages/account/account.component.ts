import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';

// primeng
import { TabsModule } from 'primeng/tabs';
import { ChangePasswordComponent } from '../../components/change-password/change-password.component';
import { ChangeUserDataComponent } from '../../components/change-user-data/change-user-data.component';
import { AuthService } from '../../services/auth/auth.service';
import { ApiUtilService } from '../../services/api/api-util.service';
import { MessageUtilService } from '../../services/util/message-util.service';

@Component({
    selector: 'app-account',
    imports: [
        CommonModule,
        ChangePasswordComponent,
        ChangeUserDataComponent,

        // primeng
        TabsModule,
    ],
    templateUrl: './account.component.html',
    styleUrl: './account.component.scss',
})
export class AccountComponent {
    private _authService: AuthService = inject(AuthService);
    private _apiUtil: ApiUtilService = inject(ApiUtilService);
    private _messageService: MessageUtilService = inject(MessageUtilService);

    public apiCallIsOver = signal(false);

    public changePassword(newPassword: string) {
        this._authService
            .changePassword({ newPassword: newPassword })
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
