import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderVersionOneComponent } from './header-version-one.component';

describe('HeaderVersionOneComponent', () => {
  let component: HeaderVersionOneComponent;
  let fixture: ComponentFixture<HeaderVersionOneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HeaderVersionOneComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HeaderVersionOneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
