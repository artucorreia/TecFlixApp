import {
    Component,
    EventEmitter,
    inject,
    Input,
    Output,
    signal,
} from '@angular/core';
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
    private _messageService: MessageUtilService = inject(MessageUtilService);

    public form: FormGroup<{
        password: FormControl<string | null>;
        confirmation: FormControl<string | null>;
    }>;

    public isLoading = signal(false);
    @Input() public apiCallIsOver = signal(false);
    @Output() public output = new EventEmitter<string>();

    constructor() {
        this.form = this._fb.group({
            password: [
                '',
                [
                    Validators.minLength(8),
                    Validators.maxLength(50),
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

    public ngDoCheck() {
        if (this.apiCallIsOver()) this.resetForm();
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

        if (newPassword) this.output.emit(newPassword);
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
        this.isLoading.set(true);
    }

    private resetForm(): void {
        this.form.reset();
        this.form.enable();
        this.isLoading.set(false);
    }
}
