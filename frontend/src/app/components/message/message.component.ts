import { Component, effect, inject, signal } from '@angular/core';
import { MessageType } from '../../enums/message-type';
import { MessageService } from '../../service/util/message/message.service';
import { animate, state, style, transition, trigger } from '@angular/animations';

@Component({
  selector: 'app-message',
  imports: [],
  template: `
    <main [@openClose]='state' class="container">
      <section class="icon_container">
        <i [class]="iconConfig[type].class" [style]="{'font-size': '2em', 'color': iconConfig[type].color}"></i>              
      </section>

      <section class="content_container">
          <p class="content"> {{ content() }} </p>
      </section>
    </main>
  `,
  styles: `
    @use '../../../dist/scss/variables.scss' as variables;

    .container {
      z-index: 1;

      position: fixed;
      top: 4rem;
      left: 100%;

      text-align: center;

      width: 20rem;
      padding: 1rem 2rem 3rem 2rem;
      
      background-color: variables.$bg-color-sccondary;
      border-radius: 15px;

      box-shadow: variables.$box-shadow;

      font-size: 0.9em;
      font-weight: 500;
      color: white;
    }

    .icon_container {
        display: flex;
        justify-content: center;

        padding-bottom: 1.5rem;
    }
  `,
  animations: [
    trigger('openClose', [
      state('open', style({ transform: 'translateX(-102%)' })),
      state('closed', style({ transform: 'translateX(120%)' })),
      transition('closed => open', [animate('0.5s ease-out')]),
      transition('open => closed', [animate('0.5s ease-in')])
    ])
  ]
})
export class MessageComponent {
  private _service: MessageService = inject(MessageService);
  public iconConfig = {
    'alert': {
      class: 'pi pi-exclamation-circle',
      color: '#daa000'
    },
    'positive': {
      class: 'pi pi-check-circle',
      color: '#00da00'
    },
    'negative': {
      class: 'pi pi-times-circle',
      color: '#da2c00'
    }
  };

  public state: string = 'closed'; 
  public content = signal('');
  public type: MessageType = MessageType.POSITIVE;

  constructor() {
    effect(() => {
      this.content.set(this._service.content());
      this.state = this._service.state();
      this.type = this._service.type();
    })
  }
}
