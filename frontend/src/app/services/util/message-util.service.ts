import { Injectable, signal, WritableSignal } from '@angular/core';

@Injectable({
    providedIn: 'root',
})
export class MessageUtilService {
    public message: WritableSignal<{
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
    }> = signal({
        severity: '',
        summary: '',
        detail: '',
        life: 3000,
    });

    public display(data: {
        severity:
            | 'success'
            | 'info'
            | 'warn'
            | 'error'
            | 'contrast'
            | 'secondary';
        summary:
            | 'Success'
            | 'Info'
            | 'Warn'
            | 'Error'
            | 'Contrast'
            | 'Secondary';
        detail: string;
        life: 3000;
    }) {
        this.message.set(data);
    }
}
