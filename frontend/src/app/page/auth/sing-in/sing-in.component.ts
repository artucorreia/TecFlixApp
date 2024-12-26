// angular
import { Component, inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

// primeng
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { PasswordModule } from 'primeng/password';
import { DividerModule } from 'primeng/divider'
import { InputTextModule } from 'primeng/inputtext';
import { FloatLabel } from 'primeng/floatlabel';

import { AuthService } from '../../../service/auth/auth.service';
import { Login } from '../../../interface/resquest/login';
import { TecFlixApiUtilService } from '../../../service/util/api/tec-flix-api-util.service';


@Component({
  selector: 'app-sing-in',
  imports: [
    ReactiveFormsModule,
    RouterModule,
    
    // primeng
    ButtonModule,
    InputTextModule,
    FloatLabel,
    PasswordModule,
    DividerModule,
    ToastModule
  ],
  providers: [MessageService],
  templateUrl: './sing-in.component.html',
  styleUrl: '../sing-up/sing-up.component.scss'
})
export class SingInComponent {
  private _router: Router = inject(Router);
  private _messageService: MessageService = inject(MessageService);
  private _authService: AuthService = inject(AuthService);
  private _apiUtil: TecFlixApiUtilService = inject(TecFlixApiUtilService);
  public rember: boolean = false;


  public singInForm: FormGroup<{
    email: FormControl<string | null>,
    password: FormControl<string | null>,
    rememberMe: FormControl<boolean | null>
  }>;
  
  constructor(
    private _fb: FormBuilder
  ) {
    this.singInForm = _fb.group({
      email: ['', [Validators.email, Validators.required]],
      password: ['', [Validators.maxLength(50), Validators.minLength(8), Validators.required]],
      rememberMe: [false, [Validators.required]]
    });
  }

  singIn() {
    if (this.singInForm.invalid) throw new Error("Formulario invalido");

    const login: Login = {
      email: this.singInForm.controls.email.value, 
      password: this.singInForm.controls.password.value
    };
    this._authService.login(login).subscribe({
      next: (response) => {
        if (this._apiUtil.isApiError(response)) {
          this._messageService.add({ severity: 'error', summary: 'Error', detail: response.title, life: 3000 });
          return;
        };
        this._authService.clearStorage();
        this._authService.saveToken(response, this.singInForm.controls.rememberMe.value)
        this._router.navigate(['/home']);
        return;
      },
      error: (error) => console.log("unexpected error", error)
    });
  }
}
