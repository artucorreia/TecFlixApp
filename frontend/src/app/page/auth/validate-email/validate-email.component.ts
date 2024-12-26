import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { AuthService } from '../../../service/auth/auth.service';
import { TecFlixApiUtilService } from '../../../service/util/api/tec-flix-api-util.service';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';

@Component({
  selector: 'app-validate-email',
  imports: [
    RouterModule,
      
    // primeng
    ButtonModule,
    ToastModule
  ],
  providers: [MessageService],
  templateUrl: './validate-email.component.html',
  styleUrl: './validate-email.component.scss'
})
export class ValidateEmailComponent {
  private _apiUtil: TecFlixApiUtilService = inject(TecFlixApiUtilService);
  private _authService: AuthService = inject(AuthService);
  private _http: ActivatedRoute = inject(ActivatedRoute);
  private _messageService: MessageService = inject(MessageService);

  private _code: string = '';
  private _userId: string = '';
  private _time: number = 45;


  public loading = signal(true);
  public codeIsValid = signal(false);
  public timerIsActive = signal(false);
  public buttonMessage = signal('Reenviar Código');
  public message = signal('Estamos validando seu email...');
  public intervalId: any;

  ngOnInit(): void {
    this._http.queryParams.subscribe(params => {
      this._code = params['code'];
      this._userId = params['userId'];
      if (this._code !== null) this.validateEmailCode(this._code);
    });
  }

  private validateEmailCode(code: string) {
    this._authService.validateEmailCode(code).subscribe({
      next: response => {
        if (this._apiUtil.isApiError(response)) {
          this._messageService.add({ severity: 'error', summary: 'Error', detail: response.title, life: 3000 });
          this.codeIsValid.set(false);
          this.loading.set(false)
          if (response.title === "Usuário já está ativo"){ 
            this.codeIsValid.set(true);
            this.message.set("Seu usuário já está ativo, entre agora e comece a explorar")
            return;
          };
          this.message.set("Ocorreu um erro ao validar seu e-mail. Por favor envie o código novamente")
          return;
        }
        this._messageService.add({ severity: 'success', summary: 'Success', detail: response.message, life: 3000 });
        this.codeIsValid.set(true);
        this.loading.set(false)
        this.message.set("Seu e-mail foi validado com sucesso, entre agora e comece a explorar")
        return;
      },
      error: error => {
        console.log("unexpected error", error);
        this.loading.set(false)
      }
    });
  }

  public resendCode(): void {
    this.startTimer();

    this._authService.sendEmailCode(this._userId).subscribe({
      next: response => {
        if (this._apiUtil.isApiError(response)) {
          this._messageService.add({ severity: 'error', summary: 'Error', detail: response.title, life: 3000 });
          if (response.title === "Usuário já está ativo"){ 
            this.codeIsValid.set(true);
            this.message.set("Seu usuário já está ativo, entre agora e comece a explorar")
            return;
          };
          this.message.set("Ocorreu um erro ao validar seu e-mail. Por favor envie o código novamente")
          this.codeIsValid.set(false);
          return;
        }
        this._messageService.add({ severity: 'success', summary: 'Success', detail: response.message, life: 3000 });
      },
      error: error => {
        console.log("unexpected error", error);
      }
    });;
  }

  private startTimer(): void {
    this.timerIsActive.set(true);
    this.intervalId = setInterval(() => {
      const currentTime = this._time;
      
      if (currentTime > 1) {
        this._time -= 1;
        this.buttonMessage.set(`${this._time} segundos`);
      } else {
        this.resetButton();
      }
    }, 1000);
  }

  private resetButton(): void {
    clearInterval(this.intervalId);
    this.buttonMessage.set('Reenviar Código');
    this.timerIsActive.set(false);
  }
}
