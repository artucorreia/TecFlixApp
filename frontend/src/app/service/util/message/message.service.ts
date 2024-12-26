import { Injectable, signal } from '@angular/core';
import { MessageType } from '../../../enums/message-type';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  public state = signal('');
  public content = signal('');
  public type = signal(MessageType.POSITIVE);
  private timeOutId: any;
  
  public show(content: string, type: MessageType): void {
    if (this.state() === 'open') clearTimeout(this.timeOutId);
    this.state.set('open')
    this.content.set(content);
    this.type.set(type);
    this.timeOutId = setTimeout(() => {this.state.set('closed');}, 4000)
  }
}
