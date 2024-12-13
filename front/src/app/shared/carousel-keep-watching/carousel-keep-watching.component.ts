import { Component, inject } from '@angular/core';

// service
import { TecflixApiService } from '../../service/tecflix-api/tecflix-api.service';

@Component({
  selector: 'app-carousel-keep-watching',
  standalone: true,
  imports: [],
  templateUrl: './carousel-keep-watching.component.html',
  styleUrl: './carousel-keep-watching.component.scss'
})
export class CarouselKeepWatchingComponent {
  #tecflixApiService = inject(TecflixApiService);

  ngOnInit() {
    let teste: string | null = sessionStorage.getItem("email");
    console.log(teste)
    // const courses = this.#tecflixApiService.getUserCouses('104');
    // for (let c of courses) {
    //   console.log(c);
    // }
  }
}
