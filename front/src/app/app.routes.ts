import { Routes } from '@angular/router';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => 
            import('./pages/home/home.component').then(p => p.HomeComponent),
        pathMatch: 'full'
    },
    {
        path: 'course/:id',
        loadComponent: () => 
            import('./pages/course-details/course-details.component').then(p => p.CourseDetailsComponent),
    },
    {
        path: 'join',
        children: [
            {
                path: 'login',
                loadComponent: () => 
                    import('./pages/sing-in/sing-in.component').then(p => p.SingInComponent)
            },
            {
                path: 'singup',
                loadComponent: () => 
                    import('./pages/sing-up/sing-up.component').then(p => p.SingUpComponent)
            }
        ]
    },
    {
        path: 'in',
        canActivate: [authGuard],
        children: [
            {
                path: '',
                loadComponent: () => 
                    import('./dashboard/home/home.component').then(p => p.HomeComponent)
            },
            {
                path: 'course/:id',
                loadComponent: () => 
                    import('./dashboard/course-details/course-details.component').then(p => p.CourseDetailsComponent)               
            }
        ]
    },
];
