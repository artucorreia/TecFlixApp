import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
    selector: 'app-layout',
    imports: [RouterOutlet],
    template: `
        <main>
            <router-outlet></router-outlet>
        </main>
    `,
})
export class UnloggedLayoutComponent {}
