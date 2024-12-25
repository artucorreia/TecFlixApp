import { Routes } from '@angular/router';
import { HomeComponent } from './module/landing/home/home.component';
import { authGuard } from './guard/auth/auth.guard';
import { SingInComponent } from './module/auth/sing-in/sing-in.component';
import { SingUpComponent } from './module/auth/sing-up/sing-up.component';

export const routes: Routes = [
    {
        path: 'sing-in',
        loadComponent: () => import('./module/auth/sing-in/sing-in.component').then(c => SingInComponent),
    },
    {
        path: 'sing-up',
        loadComponent: () => import('./module/auth/sing-up/sing-up.component').then(c => SingUpComponent),
    },
    {
        path: 'sing-up/authenticate-code',
        loadComponent: () => import('./module/auth/sing-up/sing-up.component').then(c => SingUpComponent),
    },
    {
        path: 'home',
        loadComponent: () => import('./module/landing/home/home.component').then(c => HomeComponent),
        canActivate: [authGuard]
    },
    {
        path: '',
        pathMatch: 'full',
        redirectTo: 'home'
    },
    {
        path: '**',
        redirectTo: 'sing-in'
    }
];
