import { Component, inject, signal } from '@angular/core';
import {
    FormBuilder,
    FormControl,
    FormGroup,
    ReactiveFormsModule,
    Validators,
} from '@angular/forms';

// services
import { MessageUtilService } from '../../services/util/message-util.service';
import { AuthService } from '../../services/auth/auth.service';
import { ApiUtilService } from '../../services/api/api-util.service';

// primeng
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { FloatLabel } from 'primeng/floatlabel';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { ProgressSpinner } from 'primeng/progressspinner';

@Component({
    selector: 'app-change-password',
    imports: [
        ReactiveFormsModule,

        // primeng
        FloatLabel,
        InputTextModule,
        ButtonModule,
        PasswordModule,
        DividerModule,
        ProgressSpinner,
    ],
    templateUrl: './change-password.component.html',
    styleUrl: './change-password.component.scss',
})
export class ChangePasswordComponent {
    private _fb: FormBuilder = inject(FormBuilder);
    private _authService: AuthService = inject(AuthService);
    private _apiUtil: ApiUtilService = inject(ApiUtilService);
    private _messageService: MessageUtilService = inject(MessageUtilService);

    public form: FormGroup<{
        password: FormControl<string | null>;
        confirmation: FormControl<string | null>;
    }>;
    public isLoading = signal(false);

    constructor() {
        this.form = this._fb.group({
            password: [
                '',
                [
                    Validators.minLength(8),
                    Validators.maxLength(30),
                    Validators.required,
                ],
            ],
            confirmation: [
                '',
                [
                    Validators.minLength(8),
                    Validators.maxLength(50),
                    Validators.required,
                ],
            ],
        });
    }

    public submit() {
        if (this.form.invalid) {
            this._messageService.display({
                severity: 'warn',
                summary: 'Warn',
                detail: 'Formulário inválido. Verifique o dados e tente novamente',
                life: 3000,
            });
            return;
        }

        this.closeForm();
        if (!this.passwordIsValid()) {
            this.form.enable();
            return;
        }

        let newPassword: string | null = this.form.controls.confirmation.value;
        if (newPassword) {
            this.changePassword({ newPassword: newPassword });
        }
        this.form.enable();
    }

    private changePassword(data: { newPassword: string }) {
        this._authService.changePassword(data).subscribe({
            next: (response) => {
                this.resetForm();
                if (this._apiUtil.isApiError(response)) {
                    this._messageService.display({
                        severity: 'error',
                        summary: 'Error',
                        detail: response.title,
                        life: 3000,
                    });
                    return;
                }

                this._messageService.display({
                    severity: 'success',
                    summary: 'Success',
                    detail: response.message,
                    life: 3000,
                });
            },
            error: (error) => {
                this.resetForm();
                console.log('unexpected error', error);
            },
        });
    }

    private passwordIsValid(): boolean {
        let password = this.form.controls.password.value;
        let confirmation = this.form.controls.confirmation.value;

        if (password !== confirmation) {
            this._messageService.display({
                severity: 'error',
                summary: 'Error',
                detail: 'As senhas não coincidem. Verfique e tente novamente',
                life: 3000,
            });
            return false;
        }
        return true;
    }

    private closeForm(): void {
        this.form.disable();
        this.isLoading.update((value) => (value = !value));
    }

    private resetForm(): void {
        this.form.reset();
        this.form.enable();
        this.isLoading.update((value) => (value = !value));
    }
}
