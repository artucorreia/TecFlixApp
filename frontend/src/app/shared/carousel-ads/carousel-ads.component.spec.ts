import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarouselAdsComponent } from './carousel-ads.component';

describe('CarouselAdsComponent', () => {
  let component: CarouselAdsComponent;
  let fixture: ComponentFixture<CarouselAdsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CarouselAdsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CarouselAdsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
