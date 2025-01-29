import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';

// components
import { ProfessorDataFormComponent } from '../../../components/professor-data-form/professor-data-form.component';

// primeng
import { ButtonModule } from 'primeng/button';
import { StepperModule } from 'primeng/stepper';
import { InputTextModule } from 'primeng/inputtext';
import { UserService } from '../../../services/api/user.service';
import { ApiUtilService } from '../../../services/api/api-util.service';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MessageUtilService } from '../../../services/util/message-util.service';
import { Social } from '../../../interfaces/response/social';
import { ProfessorRegister } from '../../../interfaces/request/professor-register';
import { ProfessorSocialsFormComponent } from '../../../components/professor-socials-form/professor-socials-form.component';

// local interface
interface IEnum {
  name: string;
  value: string | undefined;
}

@Component({
  selector: 'app-professor-register',
  imports: [
    CommonModule,

    // components
    ProfessorDataFormComponent,
    ProfessorSocialsFormComponent,

    // pimeng
    ButtonModule,
    StepperModule,
    InputTextModule,
  ],
  templateUrl: './professor-register.component.html',
  styleUrl: './professor-register.component.scss',
})
export class ProfessorRegisterComponent {
  private _userService: UserService = inject(UserService);
  private _apiUtil: ApiUtilService = inject(ApiUtilService);
  private _fb: FormBuilder = inject(FormBuilder);
  private _messageService: MessageUtilService = inject(MessageUtilService);

  activeStep: number = 1;
  public professorDataForm: FormGroup<{
    birthDate: FormControl<Date | null>;
    gender: FormControl<IEnum | null>;
    contact: FormControl<string | null>;
    occupation: FormControl<IEnum | null>;
    biography: FormControl<string | null>;
    profileImage: FormControl<File | null>;
    socials: FormControl<Social[] | null>;
  }>;

  constructor() {
    this.professorDataForm = this._fb.group({
      birthDate: new FormControl<Date | null>(null, [Validators.required]),
      gender: new FormControl<IEnum | null>(null, [Validators.required]),
      contact: ['', [Validators.required, Validators.minLength(16)]],
      occupation: new FormControl<IEnum | null>(null, [Validators.required]),
      biography: [
        '',
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(2000),
        ],
      ],
      profileImage: new FormControl<File | null>(null),
      socials: new FormControl<Social[] | null>([]),
    });
  }

  registerProfessor() {
    let data: ProfessorRegister = this.mapProfessorRegister('');
    console.log(data);
    console.log('registrando professor');
  }

  private mapProfessorRegister(profileImage: string): ProfessorRegister {
    const controls = this.professorDataForm.controls;
    const gender: string | null = controls['gender'].value?.value || null;
    const occupation: string | null =
      controls['occupation'].value?.value || null;

    return {
      professorData: {
        birthdate: this.formatDate(controls['birthDate'].value),
        gender: gender,
        contact: controls['contact'].value,
        occupation: occupation,
        biography: controls['biography'].value,
        profileImage: profileImage,
      },
      socials: controls['socials'].value,
    };
  }

  private formatDate(birthDate: Date | null): string {
    if (!birthDate) return '';
    return birthDate.toISOString().split('T')[0];
  }

  private register(data: ProfessorRegister) {
    this._userService.registerProfessor(data).subscribe({
      next: (response) => {
        if (this._apiUtil.isApiError(response)) {
          this._messageService.display({
            summary: 'Error',
            severity: 'error',
            detail: response.title,
          });
          return;
        }
        this._messageService.display({
          summary: 'Success',
          severity: 'success',
          detail: response.message,
        });
        return;
      },

      error: (error) => console.log('unexpected error', error),
    });
  }
}
