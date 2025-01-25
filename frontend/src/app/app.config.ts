import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { providePrimeNG } from 'primeng/config';
import Aura from '@primeng/themes/aura';
import { provideHttpClient } from '@angular/common/http';
import { routes } from './app.routes';
import { initializeApp, provideFirebaseApp } from '@angular/fire/app';
import { getStorage, provideStorage } from '@angular/fire/storage';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({
      eventCoalescing: true,
    }),
    provideRouter(routes),
    provideHttpClient(),
    provideAnimationsAsync(),
    provideAnimationsAsync(),
    providePrimeNG({
      theme: {
        preset: Aura,
      },
    }),
    provideFirebaseApp(() =>
      initializeApp({
        projectId: 'tecflix-academy',
        appId: '1:378803860813:web:dd6e0b4946351959c23304',
        storageBucket: 'tecflix-academy.firebasestorage.app',
        apiKey: 'AIzaSyDo9XoMs12ydULY6fpKduukz8XQxi2I-ZM',
        authDomain: 'tecflix-academy.firebaseapp.com',
        messagingSenderId: '378803860813',
        measurementId: 'G-CD2BW4QN33',
      }),
    ),
    provideStorage(() => getStorage()),
  ],
};
