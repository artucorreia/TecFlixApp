import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MessageComponent } from './components/message/message.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MessageComponent],
  template: `
    <app-message></app-message>
    <router-outlet />
  `
})
export class AppComponent { }