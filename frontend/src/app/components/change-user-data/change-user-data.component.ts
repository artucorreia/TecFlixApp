import { CommonModule, DatePipe } from '@angular/common';
import { Component, inject, signal, WritableSignal } from '@angular/core';
import {
    FormBuilder,
    FormControl,
    FormGroup,
    ReactiveFormsModule,
    Validators,
} from '@angular/forms';

// services
import { UserService } from '../../services/api/user.service';
import { MessageUtilService } from '../../services/util/message-util.service';
import { ApiUtilService } from '../../services/api/api-util.service';

// interfaces
import { User } from '../../interfaces/response/user';

// primeng
import { ButtonModule } from 'primeng/button';
import { FloatLabel } from 'primeng/floatlabel';
import { InputTextModule } from 'primeng/inputtext';

@Component({
    selector: 'app-change-user-data',
    imports: [
        CommonModule,
        ReactiveFormsModule,
        DatePipe,

        // primeng
        FloatLabel,
        InputTextModule,
        ButtonModule,
    ],
    templateUrl: './change-user-data.component.html',
    styleUrl: './change-user-data.component.scss',
})
export class ChangeUserDataComponent {
    private _fb: FormBuilder = inject(FormBuilder);
    private _userService: UserService = inject(UserService);
    private _messageService: MessageUtilService = inject(MessageUtilService);
    private _apiUtil: ApiUtilService = inject(ApiUtilService);

    public form: FormGroup<{
        name: FormControl<string | null>;
        email: FormControl<string | null>;
    }>;

    public user: WritableSignal<User | null> = signal(null);

    constructor() {
        this.form = this._fb.group({
            name: ['', [Validators.minLength(3), Validators.maxLength(50)]],
            email: ['', [Validators.email, Validators.maxLength(50)]],
        });
    }

    ngOnInit() {
        this.findMe();
    }

    private findMe(): void {
        this._userService.findMe().subscribe({
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
                this.user.set(response);
                if (response.name != null)
                    this.form.controls.name.setValue(response.name);
                if (response.email != null)
                    this.form.controls.email.setValue(response.email);
                return;
            },
            error: (error) => {
                console.log('unexpected error', error);
            },
        });
    }

    // TODO: add email change
    public submit() {
        console.log('submit user form');
    }
}
