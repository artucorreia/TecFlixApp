import { Component, inject } from '@angular/core';

// Components
import { HeaderLoggedComponent } from '../../shared/header-logged/header-logged.component';
import { CarouselAdsComponent } from '../../shared/carousel-ads/carousel-ads.component';
import { CarouselKeepWatchingComponent } from '../../shared/carousel-keep-watching/carousel-keep-watching.component';
import { Carousel } from '../../shared/carousel/carousel.component';
import { FooterComponent } from '../../shared/footer/footer.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: 
  [
    HeaderLoggedComponent, 
    CarouselAdsComponent, 
    CarouselKeepWatchingComponent, 
    Carousel, 
    FooterComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  // #service = inject(AuthService);

  ngOnInit(): void {
  }
}
