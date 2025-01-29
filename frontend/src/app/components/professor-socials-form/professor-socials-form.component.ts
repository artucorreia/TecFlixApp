import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { InputNumberModule } from 'primeng/inputnumber';

// primeng
import { IftaLabelModule } from 'primeng/iftalabel';
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
@Component({
  selector: 'app-professor-socials-form',
  imports: [
    FormsModule,

    // primeng
    IftaLabelModule,
    InputGroupAddonModule,
    InputGroupModule,
    SelectModule,
    InputTextModule,
  ],
  templateUrl: './professor-socials-form.component.html',
  styleUrl: './professor-socials-form.component.scss',
})
export class ProfessorSocialsFormComponent {}
