import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarouselKeepWatchingComponent } from './carousel-keep-watching.component';

describe('CarouselKeepWatchingComponent', () => {
  let component: CarouselKeepWatchingComponent;
  let fixture: ComponentFixture<CarouselKeepWatchingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CarouselKeepWatchingComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CarouselKeepWatchingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
