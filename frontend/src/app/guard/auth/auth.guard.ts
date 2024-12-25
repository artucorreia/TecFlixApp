import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { Token } from '../../interface/response/token';

export const authGuard: CanActivateFn = (route, state) => {
  const router: Router =  inject(Router);  

  let value: string | null = localStorage.getItem("token") || sessionStorage.getItem("token");
  if (value === null) {
    router.navigate(['/sing-in'])
    return false;
  }

  const token: Token = JSON.parse(value);
  if (
    new Date(token.expiresAt) < new Date()
  ) {
    router.navigate(['/sing-in'])
    return false;
  }
  
  return true;
};
