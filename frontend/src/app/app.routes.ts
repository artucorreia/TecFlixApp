import { Routes } from '@angular/router';

// guards
import { authGuard } from './guards/auth.guard';

// pages
import { HomeComponent } from './pages/home/home.component';
import { SingInComponent } from './pages/auth/sing-in/sing-in.component';
import { SingUpComponent } from './pages/auth/sing-up/sing-up.component';
import { ValidateEmailComponent } from './pages/auth/validate-email/validate-email.component';
import { SearchComponent } from './pages/search/search.component';
import { CourseComponent } from './pages/course/course.component';
import { ProfileComponent } from './pages/profile/profile.component';

export const routes: Routes = [
    {
        path: 'sing-in',
        loadComponent: () =>
            import('./pages/auth/sing-in/sing-in.component').then(
                (c) => SingInComponent
            ),
    },
    {
        path: 'sing-up',
        loadComponent: () =>
            import('./pages/auth/sing-up/sing-up.component').then(
                (c) => SingUpComponent
            ),
    },
    {
        path: 'sing-up/authenticate-code',
        loadComponent: () =>
            import('./pages/auth/validate-email/validate-email.component').then(
                (c) => ValidateEmailComponent
            ),
    },

    {
        path: 'course/:id',
        loadComponent: () =>
            import('./pages/course/course.component').then(
                (c) => CourseComponent
            ),
        canActivate: [authGuard],
    },
    {
        path: 'profile/:id',
        loadComponent: () =>
            import('./pages/profile/profile.component').then(
                (c) => ProfileComponent
            ),
        canActivate: [authGuard],
    },
    {
        path: 'search',
        loadComponent: () =>
            import('./pages/search/search.component').then(
                (c) => SearchComponent
            ),
        canActivate: [authGuard],
    },
    {
        path: 'home',
        loadComponent: () =>
            import('./pages/home/home.component').then((c) => HomeComponent),
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
