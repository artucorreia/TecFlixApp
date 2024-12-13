import { Component } from '@angular/core';

// primeng
import { CarouselModule } from 'primeng/carousel';

// interfaces
import { Ads } from '../../model/ads';


@Component({
  selector: 'app-carousel-ads',
  standalone: true,
  imports: [CarouselModule],
  templateUrl: './carousel-ads.component.html',
  styleUrl: './carousel-ads.component.scss'
})
export class CarouselAdsComponent {
  adverts: Ads[] = [];

  public colorIsBlack: boolean = true;

  responsiveOptions: any[] | undefined;

  ngOnInit() {
      this.adverts = [
        {
            id: '1000',
            name: 'Anúncio 1',
            description: 'Lorem ipsum dolor sit amet consectetur, adipisicing elit. Necessitatibus exercitationem iste porro voluptates nesciunt, deserunt voluptatum, eos illo impedit unde obcaecati pariatur illum delectus ipsa beatae ullam in nostrum fugit?',
            backgroundColor: '#0047AB'
        },
        {
            id: '1001',
            name: 'Anúncio 2',
            description: 'Lorem ipsum dolor sit amet consectetur, adipisicing elit. Repellendus molestiae, dolorem, labore harum commodi cum voluptatum facere eligendi nihil at blanditiis dicta impedit aliquid dolorum? Accusantium autem alias dolores velit!',
            backgroundColor: '#4B0082'
        },
        {
            id: '1002',
            name: 'Anúncio 3',
            description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nostrum recusandae ipsum inventore voluptate sint ipsam dolores debitis, eum dolorem distinctio rem cumque a ut nulla beatae odio, nam corrupti commodi!',
            backgroundColor: '#000080'
        }
      ];
  }
}
