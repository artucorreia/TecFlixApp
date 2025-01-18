import { Component, inject, signal } from '@angular/core';
import {
    FormBuilder,
    FormControl,
    FormGroup,
    ReactiveFormsModule,
    Validators,
} from '@angular/forms';
import { RouterLink } from '@angular/router';

// services
import { ApiUtilService } from '../../../services/api/api-util.service';
import { AuthService } from '../../../services/auth/auth.service';
import { UserService } from '../../../services/api/user.service';
import { MessageUtilService } from '../../../services/util/message-util.service';

// primeng
import { ProgressSpinner } from 'primeng/progressspinner';
import { FloatLabel } from 'primeng/floatlabel';
import { InputTextModule } from 'primeng/inputtext';

@Component({
    selector: 'app-forgot-password',
    imports: [
        ReactiveFormsModule,
        RouterLink,

        // primeng
        InputTextModule,
        FloatLabel,
        ProgressSpinner,
    ],
    templateUrl: './forgot-password.component.html',
    styleUrl: './forgot-password.component.scss',
})
export class ForgotPasswordComponent {
    private _fb: FormBuilder = inject(FormBuilder);
    private _apiUtil: ApiUtilService = inject(ApiUtilService);
    private _authService: AuthService = inject(AuthService);
    private _userService: UserService = inject(UserService);
    private _messageService: MessageUtilService = inject(MessageUtilService);

    public form: FormGroup<{
        email: FormControl<string | null>;
    }>;
    public isLoading = signal(false);

    constructor() {
        this.form = this._fb.group({
            email: ['', [Validators.email, Validators.required]],
        });
    }

    public submit() {
        if (this.form.invalid) {
            this._messageService.display({
                severity: 'warn',
                summary: 'Warn',
                detail: 'Formulário inválido. Verifique seu e-mail e tente novamente',
                life: 3000,
            });
            return;
        }
        this.closeForm();
        const email = this.form.controls['email'].value;
        if (email) this.findUserByEmail(email);

        this.resetForm();
        // this.isLoading.set(false);
    }

    private findUserByEmail(email: string) {
        this._userService.findByEmail(email).subscribe({
            next: (response) => {
                if (this._apiUtil.isApiError(response)) {
                    this._messageService.display({
                        severity: 'error',
                        summary: 'Error',
                        detail: response.title,
                        life: 3000,
                    });
                    console.log(response);
                    this.isLoading.set(false);
                    return;
                }
                this.sendEmailCode(response.id);
            },
            error: (error) => console.log('unexpected error', error),
        });
    }

    private sendEmailCode(userId: string) {
        this._authService.sendEmailCode(userId, true).subscribe({
            next: (response) => {
                if (this._apiUtil.isApiError(response)) {
                    this._messageService.display({
                        severity: 'error',
                        summary: 'Error',
                        detail: response.title,
                        life: 3000,
                    });
                    this.isLoading.set(false);
                    return;
                }
                this._messageService.display({
                    severity: 'success',
                    summary: 'Success',
                    detail: response.message,
                    life: 3000,
                });
                this.isLoading.set(false);
                return;
            },
        });
    }

    private closeForm(): void {
        this.form.disable();
        this.isLoading.set(true);
    }

    private resetForm(): void {
        this.form.reset();
        this.form.enable();
    }
}
