import { Component, Input, Signal, signal } from '@angular/core';

@Component({
  selector: 'app-message',
  imports: [],
  template: `
    @if (display()) {
      <main class="container fadeInRight">
        <section class="icon_container">
            @switch (type()) {
                @case ('negative') {
                  <i class="pi pi-times-circle" style="font-size: 2em; color: #da2c00;"></i>
                }
                @case ('alert') {
                  <i class="pi pi-exclamation-circle" style="font-size: 2em; color:rgb(218, 160, 0);"></i>
                }
                @default {
                  <i class="pi pi-check-circle" style="font-size: 2em; color: #00da00;"></i>
                }
            }
        </section>

        <section class="content_container">
            <p class="content">{{ content() }}</p>
        </section>
      </main>
    }
  `,
  styles: `
    @use '../../../dist/scss/variables.scss' as variables;
    @use '../../../dist/scss/animations.scss';

    .container {
        z-index: 1;

        position: fixed;
        top: 4rem;
        right: 1.5rem;

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
  `
})
export class MessageComponent {
  public display = signal(false);
  public content = signal('');
  public type = signal('');

  public show(content: string, type: string): void {
    this.content.set(content);
    this.type.set(type);
    this.display.update(display => !display);

    setTimeout(() => {this.display.update(display => !display);}, 4000)
  }
}
