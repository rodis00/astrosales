import {
  ActivatedRouteSnapshot,
  CanActivateFn,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { TokenService } from '../../services/token/token.service';
import { inject } from '@angular/core';

export const userGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const tokenService: TokenService = inject(TokenService);
  const router: Router = inject(Router);

  const protectedRoutes: string[] = ['/account'];

  return protectedRoutes.includes(state.url) && tokenService.isLogedIn()
    ? true
    : router.navigateByUrl('login');
};
