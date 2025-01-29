import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorSocialsFormComponent } from './professor-socials-form.component';

describe('ProfessorSocialsFormComponent', () => {
  let component: ProfessorSocialsFormComponent;
  let fixture: ComponentFixture<ProfessorSocialsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfessorSocialsFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfessorSocialsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
