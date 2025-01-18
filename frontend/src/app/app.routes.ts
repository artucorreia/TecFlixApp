import { Routes } from '@angular/router';

// guards
import { authGuard } from './guards/auth.guard';

// layouts
import { LoggedLayoutComponent } from './layouts/logged-layout.component';
import { UnloggedLayoutComponent } from './layouts/unlogged-layout.component';

// pages
import { HomeComponent } from './pages/home/home.component';
import { SingInComponent } from './pages/auth/sing-in/sing-in.component';
import { SingUpComponent } from './pages/auth/sing-up/sing-up.component';
import { ValidateEmailComponent } from './pages/auth/validate-email/validate-email.component';
import { SearchComponent } from './pages/search/search.component';
import { CourseComponent } from './pages/course/course.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { AccountComponent } from './pages/account/account.component';
import { ForgotPasswordComponent } from './pages/auth/forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './pages/auth/reset-password/reset-password.component';

export const routes: Routes = [
    {
        path: 'sing-in',
        component: UnloggedLayoutComponent,
        children: [
            {
                path: '',
                loadComponent: () =>
                    import('./pages/auth/sing-in/sing-in.component').then(
                        (c) => SingInComponent
                    ),
            },
        ],
    },
    {
        path: 'sing-up',
        component: UnloggedLayoutComponent,
        children: [
            {
                path: '',
                loadComponent: () =>
                    import('./pages/auth/sing-up/sing-up.component').then(
                        (c) => SingUpComponent
                    ),
            },
        ],
    },
    {
        path: 'sing-up/authenticate-code',
        component: UnloggedLayoutComponent,
        children: [
            {
                path: '',
                loadComponent: () =>
                    import(
                        './pages/auth/validate-email/validate-email.component'
                    ).then((c) => ValidateEmailComponent),
            },
        ],
    },
    {
        path: 'sing-in/forgot-password',
        component: UnloggedLayoutComponent,
        children: [
            {
                path: '',
                loadComponent: () =>
                    import(
                        './pages/auth/forgot-password/forgot-password.component'
                    ).then((c) => ForgotPasswordComponent),
            },
        ],
    },
    {
        path: 'sing-in/reset-password',
        component: UnloggedLayoutComponent,
        children: [
            {
                path: '',
                loadComponent: () =>
                    import(
                        './pages/auth/reset-password/reset-password.component'
                    ).then((c) => ResetPasswordComponent),
            },
        ],
    },

    {
        path: 'home',
        component: LoggedLayoutComponent,
        children: [
            {
                path: '',
                loadComponent: () =>
                    import('./pages/home/home.component').then(
                        (c) => HomeComponent
                    ),
            },
        ],
        canActivate: [authGuard],
    },
    {
        path: 'course/:id',
        component: LoggedLayoutComponent,
        children: [
            {
                path: '',
                loadComponent: () =>
                    import('./pages/course/course.component').then(
                        (c) => CourseComponent
                    ),
            },
        ],
        canActivate: [authGuard],
    },
    {
        path: 'profile/:id',
        component: LoggedLayoutComponent,
        children: [
            {
                path: '',
                loadComponent: () =>
                    import('./pages/profile/profile.component').then(
                        (c) => ProfileComponent
                    ),
            },
        ],
        canActivate: [authGuard],
    },
    {
        path: 'search',
        component: LoggedLayoutComponent,
        children: [
            {
                path: '',
                loadComponent: () =>
                    import('./pages/search/search.component').then(
                        (c) => SearchComponent
                    ),
            },
        ],
        canActivate: [authGuard],
    },
    {
        path: 'account',
        component: LoggedLayoutComponent,
        children: [
            {
                path: '',
                loadComponent: () =>
                    import('./pages/account/account.component').then(
                        (c) => AccountComponent
                    ),
            },
        ],
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
