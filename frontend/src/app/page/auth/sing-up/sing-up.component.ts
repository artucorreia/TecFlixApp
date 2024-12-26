import { Component, inject, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { PasswordModule } from 'primeng/password';
import { DividerModule } from 'primeng/divider'
import { InputTextModule } from 'primeng/inputtext';
import { FloatLabel } from 'primeng/floatlabel';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../service/auth/auth.service';
import { Register } from '../../../interface/resquest/register';
import { TecFlixApiUtilService } from '../../../service/util/api/tec-flix-api-util.service';
import { MessageType } from '../../../enums/message-type';
import { MessageService } from '../../../service/util/message/message.service';

@Component({
  selector: 'app-sing-up',
  imports: [
    ReactiveFormsModule,
    RouterModule,
    // MessageComponent,

    // primeng
    ButtonModule,
    InputTextModule,
    FloatLabel,
    PasswordModule,
    DividerModule
  ],
  templateUrl: './sing-up.component.html',
  styleUrl: './sing-up.component.scss'
})
export class SingUpComponent {
  private _messageService: MessageService = inject(MessageService);
  private _apiUtil: TecFlixApiUtilService = inject(TecFlixApiUtilService);
  private _authService: AuthService = inject(AuthService);
  
  public loading: boolean = false;
  
  public singUpForm: FormGroup<{
    name: FormControl<string | null>,
    email: FormControl<string | null>,
    password: FormControl<string | null>
  }>;
  
  constructor(
    private _fb: FormBuilder,
  ) {
    this.singUpForm = _fb.group({
      name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(30)]],
      email: ['', [Validators.email, Validators.required, Validators.maxLength(50)]],
      password: ['', [Validators.maxLength(50), Validators.minLength(8), Validators.required]]
    });
  }

  public singUp(): void {
    if (this.singUpForm.invalid) throw new Error("Formulario invalido");

    this.closeForm();

    const register: Register = {
      name: this.singUpForm.controls.name.value, 
      email: this.singUpForm.controls.email.value, 
      password: this.singUpForm.controls.password.value
    };
    
    this._authService.register(register)
    .subscribe({
      next: response => {
        if (this._apiUtil.isApiError(response)) {
          this._messageService.show(response.title, MessageType.NEGATIVE);
          this.resetForm();
          return;
        }
        
        this.sendCode(response.id || "");
      },
      error: error => {
        this.resetForm();
        console.log("unexpected error", error)
      }
    });
  }

  public sendCode(userId: string): void {
    this._authService.sendEmailCode(userId).subscribe({
      next: response => {
        this.resetForm();
        if (this._apiUtil.isApiError(response)) {
          this._messageService.show(response.title, MessageType.NEGATIVE);
          this.resetForm();
          return;
        }
        this._messageService.show(response.message, MessageType.POSITIVE);
      },
      error: error => {
        this.resetForm();
        console.log("unexpected error", error);
      }
    });
  }

  private resetForm(): void {
    this.singUpForm.reset();
    this.singUpForm.enable();
    this.loading = !this.loading;
  }

  private closeForm(): void {
    this.singUpForm.disable();
    this.loading = !this.loading;
  }
}
