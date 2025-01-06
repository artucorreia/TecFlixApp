import { Injectable, signal } from '@angular/core'

@Injectable({
    providedIn: 'root',
})
export class MessageUtilService {
    public message = signal({
        severity: '',
        summary: '',
        detail: '',
        life: 3000,
    })

    public display(data: {
        severity: string
        summary: string
        detail: string
        life: 3000
    }) {
        this.message.set(data)
    }
}
