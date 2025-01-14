// angular
import { Component, inject } from '@angular/core';
import {
    FormBuilder,
    FormControl,
    FormGroup,
    ReactiveFormsModule,
    Validators,
} from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

// services
import { AuthService } from '../../../services/auth/auth.service';
import { ApiUtilService } from '../../../services/api/tecflix/api-util.service';

// interfaces
import { Login } from '../../../interfaces/resquest/login';

// primeng
import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import { FloatLabel } from 'primeng/floatlabel';
import { MessageUtilService } from '../../../services/util/message-util.service';

@Component({
    selector: 'app-sing-in',
    imports: [
        ReactiveFormsModule,
        RouterModule,

        // primeng
        InputTextModule,
        FloatLabel,
        PasswordModule,
    ],
    providers: [],
    templateUrl: './sing-in.component.html',
    styleUrl: '../sing-up/sing-up.component.scss',
})
export class SingInComponent {
    private _fb: FormBuilder = inject(FormBuilder);
    private _router: Router = inject(Router);
    private _messageService: MessageUtilService = inject(MessageUtilService);
    private _authService: AuthService = inject(AuthService);
    private _apiUtil: ApiUtilService = inject(ApiUtilService);

    public singInForm: FormGroup<{
        email: FormControl<string | null>;
        password: FormControl<string | null>;
        rememberMe: FormControl<boolean | null>;
    }>;

    constructor() {
        this.singInForm = this._fb.group({
            email: ['', [Validators.email, Validators.required]],
            password: [
                '',
                [
                    Validators.maxLength(50),
                    Validators.minLength(8),
                    Validators.required,
                ],
            ],
            rememberMe: [false, [Validators.required]],
        });
    }

    singIn() {
        if (this.singInForm.invalid) throw new Error('Formulario invalido');

        const login: Login = {
            email: this.singInForm.controls.email.value,
            password: this.singInForm.controls.password.value,
        };
        this._authService.login(login).subscribe({
            next: (response) => {
                if (this._apiUtil.isApiError(response)) {
                    this._messageService.display({
                        severity: 'error',
                        summary: 'Error',
                        detail: response.title,
                        life: 3000,
                    });
                    return;
                }
                this._authService.clearStorage();
                this._authService.saveToken(
                    response,
                    this.singInForm.controls.rememberMe.value
                );
                this._router.navigate(['/home']);
                return;
            },
            error: (error) => console.log('unexpected error', error),
        });
    }
}
