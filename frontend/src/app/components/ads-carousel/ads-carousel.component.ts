import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

// primeng
import { CarouselModule } from 'primeng/carousel';

interface Announcement {
    title: string;
    image: string;
    details: string;
    positionRight?: boolean;
}

@Component({
    selector: 'app-announcement-carousel',
    imports: [
        CommonModule,

        // primang
        CarouselModule,
    ],
    templateUrl: './ads-carousel.component.html',
    styleUrl: './ads-carousel.component.scss',
})
export class AdsCarouselComponent {
    public responsiveOptions: any[] | undefined;

    public ads: Announcement[] = [];
    public adMobile: Announcement = {
        title: '',
        image: '',
        details: '',
    };

    ngOnInit(): void {
        this.setAds();
        this.setAdMobile();
    }

    private setAds(): void {
        this.ads = [
            {
                title: 'Domine a tecnologia. Liderando o futuro.',
                image: './ads/hardware.jpg',
                details:
                    'Seja o profissional que todas as empresas procuram. Cursos práticos e atualizados para te ajudar a brilhar na área de TI.',
                positionRight: true,
            },
            {
                title: 'Seu sonho na tecnologia começa aqui.',
                image: './ads/server.jpg',
                details:
                    'Oferecemos os cursos certos para você aprender o que precisa. Do básico ao avançado, domine a TI e alcance seus objetivos.',
                positionRight: false,
            },
            {
                title: 'Descubra seu potencial na TI',
                image: './ads/programming.jpg',
                details:
                    'Com a TecFlix, você pode transformar seu conhecimento em grandes oportunidades. Aprenda, cresça e conquiste o futuro que você merece.',
                positionRight: true,
            },
        ];
    }

    private setAdMobile(): void {
        this.adMobile = {
            title: 'Na TI, o céu é o limite',
            image: './ads/world.jpg',
            details:
                'Com dedicação e os cursos certos, você pode chegar onde quiser. Aprenda, evolua e seja reconhecido no mercado.',
        };
    }
}
