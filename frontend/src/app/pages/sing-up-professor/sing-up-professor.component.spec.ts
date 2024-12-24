import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingUpProfessorComponent } from './sing-up-professor.component';

describe('SingUpProfessorComponent', () => {
  let component: SingUpProfessorComponent;
  let fixture: ComponentFixture<SingUpProfessorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SingUpProfessorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SingUpProfessorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
