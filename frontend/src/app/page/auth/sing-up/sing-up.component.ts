// angular
import { Component, inject, signal } from '@angular/core'
import {
    FormBuilder,
    FormControl,
    FormGroup,
    ReactiveFormsModule,
    Validators,
} from '@angular/forms'
import { RouterModule } from '@angular/router'

// primeng
import { ButtonModule } from 'primeng/button'
import { PasswordModule } from 'primeng/password'
import { DividerModule } from 'primeng/divider'
import { InputTextModule } from 'primeng/inputtext'
import { ToastModule } from 'primeng/toast'
import { FloatLabel } from 'primeng/floatlabel'
import { MessageService } from 'primeng/api'
import { ProgressSpinner } from 'primeng/progressspinner'

import { AuthService } from '../../../service/auth/auth.service'
import { Register } from '../../../interface/resquest/register'
import { TecFlixApiUtilService } from '../../../service/util/api/tec-flix-api-util.service'
import { MessageUtilService } from '../../../service/util/message/message-util.service'

@Component({
    selector: 'app-sing-up',
    imports: [
        ReactiveFormsModule,
        RouterModule,

        // primeng
        ButtonModule,
        InputTextModule,
        FloatLabel,
        PasswordModule,
        DividerModule,
        ProgressSpinner,
        ToastModule,
    ],
    providers: [MessageService],
    templateUrl: './sing-up.component.html',
    styleUrl: './sing-up.component.scss',
})
export class SingUpComponent {
    private _messageUtilService: MessageUtilService = inject(MessageUtilService)
    private _apiUtil: TecFlixApiUtilService = inject(TecFlixApiUtilService)
    private _authService: AuthService = inject(AuthService)

    public loading = signal(false)

    public singUpForm: FormGroup<{
        name: FormControl<string | null>
        email: FormControl<string | null>
        password: FormControl<string | null>
    }>

    constructor(private _fb: FormBuilder) {
        this.singUpForm = _fb.group({
            name: [
                '',
                [
                    Validators.required,
                    Validators.minLength(3),
                    Validators.maxLength(30),
                ],
            ],
            email: [
                '',
                [
                    Validators.email,
                    Validators.required,
                    Validators.maxLength(50),
                ],
            ],
            password: [
                '',
                [
                    Validators.maxLength(50),
                    Validators.minLength(8),
                    Validators.required,
                ],
            ],
        })
    }

    public singUp(): void {
        if (this.singUpForm.invalid) throw new Error('Formulario invalido')

        this.closeForm()

        const register: Register = {
            name: this.singUpForm.controls.name.value,
            email: this.singUpForm.controls.email.value,
            password: this.singUpForm.controls.password.value,
        }

        this._authService.register(register).subscribe({
            next: (response) => {
                if (this._apiUtil.isApiError(response)) {
                    this._messageUtilService.display({
                        severity: 'error',
                        summary: 'Errror',
                        detail: response.title,
                        life: 3000,
                    })
                    this.resetForm()
                    return
                }

                this.sendCode(response.id || '')
            },
            error: (error) => {
                this.resetForm()
                console.log('unexpected error', error)
            },
        })
    }

    public sendCode(userId: string): void {
        this._authService.sendEmailCode(userId).subscribe({
            next: (response) => {
                this.resetForm()
                if (this._apiUtil.isApiError(response)) {
                    this._messageUtilService.display({
                        severity: 'error',
                        summary: 'Errror',
                        detail: response.title,
                        life: 3000,
                    })
                    this.resetForm()
                    return
                }
                this._messageUtilService.display({
                    severity: 'success',
                    summary: 'Success',
                    detail: response.message,
                    life: 3000,
                })
            },
            error: (error) => {
                this.resetForm()
                console.log('unexpected error', error)
            },
        })
    }

    private resetForm(): void {
        this.singUpForm.reset()
        this.singUpForm.enable()
        this.loading.update((value) => (value = !value))
    }

    private closeForm(): void {
        this.singUpForm.disable()
        this.loading.update((value) => (value = !value))
    }
}
