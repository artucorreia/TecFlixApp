import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

// primeng
import { ButtonModule } from 'primeng/button';
import { StepperModule } from 'primeng/stepper';

@Component({
  selector: 'app-teaching',
  imports: [
    CommonModule,
    RouterLink,

    // primeng
    ButtonModule,
    StepperModule,
  ],
  templateUrl: './teaching.component.html',
  styleUrl: './teaching.component.scss',
})
export class TeachingComponent {}
