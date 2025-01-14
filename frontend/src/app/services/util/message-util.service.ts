import { Injectable, signal, WritableSignal } from '@angular/core';

// interfaces
interface Message {
    severity:
        | 'success'
        | 'info'
        | 'warn'
        | 'error'
        | 'contrast'
        | 'secondary'
        | '';
    summary:
        | 'Success'
        | 'Info'
        | 'Warn'
        | 'Error'
        | 'Contrast'
        | 'Secondary'
        | '';
    detail: string;
    life: number;
}
@Injectable({
    providedIn: 'root',
})
export class MessageUtilService {
    public message: WritableSignal<Message> = signal({
        severity: '',
        summary: '',
        detail: '',
        life: 3000,
    });

    public display(data: Message) {
        this.message.set(data);
    }
}
