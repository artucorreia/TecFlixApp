import { ComponentFixture, TestBed } from '@angular/core/testing';

import ProfessorRegisterFormComponent from './professor-register-form.component';

describe('ProfessorRegisterFormComponent', () => {
  let component: ProfessorRegisterFormComponent;
  let fixture: ComponentFixture<ProfessorRegisterFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfessorRegisterFormComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ProfessorRegisterFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
