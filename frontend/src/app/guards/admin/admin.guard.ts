import { inject } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivateFn,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { TokenService } from '../../services/token/token.service';

export const adminGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const router: Router = inject(Router);
  const tokenService: TokenService = inject(TokenService);
  let token: string = '';
  const alowedRole: string = 'ROLE_ADMIN';
  let userDetails: any = {};

  if (tokenService.isLogedIn()) {
    token = JSON.stringify(sessionStorage.getItem('token'));
    userDetails = tokenService.getRolesAndIdFromToken(token);

    if (userDetails.roles.includes(alowedRole)) {
      return true;
    } else {
      return router.navigateByUrl('/');
    }
  } else {
    return router.navigateByUrl('login');
  }
};
