import { Routes } from '@angular/router';

// guards
import { authGuard } from './guard/auth/auth.guard';

// pages
import { HomeComponent } from './page/home/home.component';
import { SingInComponent } from './page/auth/sing-in/sing-in.component';
import { SingUpComponent } from './page/auth/sing-up/sing-up.component';
import { ValidateEmailComponent } from './page/auth/validate-email/validate-email.component';
import { SearchComponent } from './page/search/search.component';
import { CourseComponent } from './page/course/course.component';
import { ProfileComponent } from './page/profile/profile.component';

export const routes: Routes = [
    {
        path: 'sing-in',
        loadComponent: () =>
            import('./page/auth/sing-in/sing-in.component').then(
                (c) => SingInComponent
            ),
    },
    {
        path: 'sing-up',
        loadComponent: () =>
            import('./page/auth/sing-up/sing-up.component').then(
                (c) => SingUpComponent
            ),
    },
    {
        path: 'sing-up/authenticate-code',
        loadComponent: () =>
            import('./page/auth/validate-email/validate-email.component').then(
                (c) => ValidateEmailComponent
            ),
    },

    {
        path: 'course/:id',
        loadComponent: () =>
            import('./page/course/course.component').then(
                (c) => CourseComponent
            ),
        canActivate: [authGuard],
    },
    {
        path: 'profile/:id',
        loadComponent: () =>
            import('./page/profile/profile.component').then(
                (c) => ProfileComponent
            ),
        canActivate: [authGuard],
    },
    {
        path: 'search',
        loadComponent: () =>
            import('./page/search/search.component').then(
                (c) => SearchComponent
            ),
        canActivate: [authGuard],
    },
    {
        path: 'home',
        loadComponent: () =>
            import('./page/home/home.component').then((c) => HomeComponent),
        canActivate: [authGuard],
    },
    {
        path: '',
        pathMatch: 'full',
        redirectTo: 'home',
    },
    {
        path: '**',
        redirectTo: 'sing-in',
    },
];
