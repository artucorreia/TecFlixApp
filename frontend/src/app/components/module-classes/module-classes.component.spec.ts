import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModuleClassesComponent } from './module-classes.component';

describe('ModuleClassesComponent', () => {
  let component: ModuleClassesComponent;
  let fixture: ComponentFixture<ModuleClassesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModuleClassesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModuleClassesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
