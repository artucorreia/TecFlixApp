import { Component, inject } from '@angular/core';

// service
import { AuthService } from '../../service/auth/auth.service';

// router
import { Router, RouterModule } from '@angular/router';

// form
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

// components
import { HeaderVersionOneComponent } from '../../shared/header-version-one/header-version-one.component';
import { PasswordComponentComponent } from '../../shared/password-component/password-component.component';

@Component({
  selector: 'app-sing-in',
  standalone: true,
  imports: [ReactiveFormsModule, HeaderVersionOneComponent, RouterModule, PasswordComponentComponent],
  templateUrl: './sing-in.component.html',
  styleUrl: './sing-in.component.scss'
})
export class SingInComponent {
  #authService = inject(AuthService);
  #router = inject(Router);
  #formBuilder = inject(FormBuilder);

  public loginForm: FormGroup = this.#formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required]]
  })

  public getPassword(event: string) {
    this.loginForm.get("password")?.setValue(
      this.#authService.hashCode(event)
    );
  }

  private setLoginData(): void {
    const email: string = this.loginForm.get("email")?.value ?? '';
    const password: string = this.loginForm.get("password")?.value ?? '';

    sessionStorage.setItem("password", password);
    sessionStorage.setItem("email", email);
  }

  submitForm() {
    if (this.loginForm.valid) {
      this.setLoginData();
      this.#router.navigate(['in'])
    }
  }
}
