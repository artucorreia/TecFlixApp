import { Routes } from '@angular/router';
import { HomeComponent } from './page/home/home.component';
import { authGuard } from './guard/auth/auth.guard';
import { SingInComponent } from './page/auth/sing-in/sing-in.component';
import { SingUpComponent } from './page/auth/sing-up/sing-up.component';
import { ValidateEmailComponent } from './page/auth/validate-email/validate-email.component';

export const routes: Routes = [
    {
        path: 'sing-in',
        loadComponent: () => import('./page/auth/sing-in/sing-in.component').then(c => SingInComponent),
    },
    {
        path: 'sing-up',
        loadComponent: () => import('./page/auth/sing-up/sing-up.component').then(c => SingUpComponent),
    },
    {
        path: 'sing-up/authenticate-code',
        loadComponent: () => import('./page/auth/validate-email/validate-email.component').then(c => ValidateEmailComponent),
    },
    {
        path: 'home',
        loadComponent: () => import('./page/home/home.component').then(c => HomeComponent),
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
