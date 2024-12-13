import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from './service/auth/auth.service';


@NgModule({
  declarations: [],
  imports: [
    BrowserModule,
    AppComponent,
    HttpClientModule
  ],
  providers: [AuthService],
  bootstrap: []
})
export class AppModule { }
