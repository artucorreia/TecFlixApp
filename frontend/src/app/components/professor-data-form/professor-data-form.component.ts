import {
  Component,
  inject,
  Input,
  signal,
  WritableSignal,
} from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

// services
import { MessageUtilService } from '../../services/util/message-util.service';
import { UserService } from '../../services/api/user.service';
import { ApiUtilService } from '../../services/api/api-util.service';

// interfaces
import { ProfessorRegister } from '../../interfaces/request/professor-register';
import { Social } from '../../interfaces/response/social';

// enums
import { Gender } from '../../enums/gender';
import { Occupation } from '../../enums/occupation';

// primeng
import { ButtonModule } from 'primeng/button';
import { DatePickerModule } from 'primeng/datepicker';
import { InputMask } from 'primeng/inputmask';
import { SelectModule } from 'primeng/select';
import { IftaLabelModule } from 'primeng/iftalabel';
import { TextareaModule } from 'primeng/textarea';
import { RadioButtonModule } from 'primeng/radiobutton';
import { FileUploadEvent, FileUploadModule } from 'primeng/fileupload';

interface IEnum {
  name: string;
  value: string | undefined;
}

@Component({
  selector: 'app-professor-data-form',
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    // primeng
    DatePickerModule,
    ButtonModule,
    InputMask,
    SelectModule,
    IftaLabelModule,
    RadioButtonModule,
    TextareaModule,
    FileUploadModule,
  ],
  templateUrl: './professor-data-form.component.html',
  styleUrl: './professor-data-form.component.scss',
})
export class ProfessorDataFormComponent {
  public minDate!: Date;
  public maxDate!: Date;

  public genders = Object.values(Gender).map((gender) => ({
    name: gender,
    value: this.findEnumKeyByValue(Gender, gender),
  }));

  public ocupations = Object.values(Occupation).map((occupation) => ({
    name: occupation.toUpperCase(),
    value: this.findEnumKeyByValue(Occupation, occupation),
  }));

  @Input() public form!: FormGroup<{
    birthDate: FormControl<Date | null>;
    gender: FormControl<IEnum | null>;
    contact: FormControl<string | null>;
    occupation: FormControl<IEnum | null>;
    biography: FormControl<string | null>;
    profileImage: FormControl<File | null>;
    socials: FormControl<Social[] | null>;
  }>;

  ngOnInit(): void {
    // NOTE: Setting date limits
    const year: number = new Date().getFullYear();
    const maxAge: number = 70;
    this.maxDate = new Date();
    this.minDate = new Date(year - maxAge, 0);
  }

  private findEnumKeyByValue(
    enumToSearch: any,
    value: string,
  ): string | undefined {
    return Object.keys(enumToSearch).find((key) => enumToSearch[key] === value);
  }

  // NOTE: Setting the form's profileImage field
  public upload(event: FileUploadEvent): void {
    this.form.controls['profileImage'].setValue(event.files[0]);
  }
}
