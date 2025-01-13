import { Component, effect, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';

// services
import { MessageUtilService } from './services/util/message-util.service';

// primeng
import { MessageService } from 'primeng/api';
import { Toast } from 'primeng/toast';

@Component({
    selector: 'app-root',
    imports: [
        RouterOutlet,

        // primeng
        Toast,
    ],
    template: `
        <p-toast />
        <router-outlet />
    `,
    providers: [MessageService],
})
export class AppComponent {
    private _primeApiMessageService: MessageService = inject(MessageService);
    private _messageService: MessageUtilService = inject(MessageUtilService);

    constructor() {
        effect(() => {
            this._primeApiMessageService.add({
                severity: this._messageService.message().severity,
                summary: this._messageService.message().summary,
                detail: this._messageService.message().detail,
                life: this._messageService.message().life,
            });
        });
    }
}
