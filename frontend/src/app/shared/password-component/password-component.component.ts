import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';

// form
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-password-component',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './password-component.component.html',
  styleUrl: './password-component.component.scss'
})
export class PasswordComponentComponent {
  @Output() public passwordEmitter = new EventEmitter<string>();
  public password: string = "";

  public passwordVisible: boolean = false;
  public inputType: string = "password";

  public toggleVisibility() {
    this.passwordVisible = !this.passwordVisible;
    this.toggleInputType();
  }

  private toggleInputType() {
    if (this.passwordVisible) {
      this.inputType = "text";
    } else {
      this.inputType = "password"
    }
  }

  public sendPassword(): void {
    this.passwordEmitter.emit(this.password);
  }
}
