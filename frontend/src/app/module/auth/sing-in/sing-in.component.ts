import { Component, inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { PasswordModule } from 'primeng/password';
import { DividerModule } from 'primeng/divider'
import { InputTextModule } from 'primeng/inputtext';
import { FloatLabel } from 'primeng/floatlabel';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../service/auth/auth.service';
import { Login } from '../../../interface/resquest/login';
import { Token } from '../../../interface/response/token';
import { TecFlixApiUtilService } from '../../../service/util/tec-flix-api-util.service';

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
    DividerModule
  ],
  templateUrl: './sing-in.component.html',
  styleUrl: './sing-in.component.scss'
})
export class SingInComponent {
  private _router: Router = inject(Router);
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
        if (this._apiUtil.isApiError(response)) return console.log("Errorrrr");
        this.clearStorage();
        return this.saveToken(response, this.singInForm.controls.rememberMe.value)
      },
      error: (error) => console.log("unexpected error", error)
    });
  }

  private clearStorage() {
    localStorage.removeItem("token");
    sessionStorage.removeItem("token");
  }

  saveToken(token: Token, rememberMe: boolean | null): void {
    const storage: Storage = rememberMe ? localStorage : sessionStorage;    
    storage.setItem("token", JSON.stringify(token));
    this._router.navigate(['/home']);
  }
}
