/*
What will happen if the user directly tries to access a page without login. 
For example if a user directly navigates to localhost:4200 He will be able to view the page. 
But this should not be the case as the user is not logged in. 
So we should first check if the user is logged in and only then allow the user to view the page. 
We achieve this using the CanActivate interface.
*/
  /*This service will activate a particular route only if the user is logged in.
      This service will implement the CanActivate interface. Overriding the canActivate method, we will specify that a route should only be
      activated only if a user is logged in.
      Upon successful log in, in the app.routing.ts module, we activate a route only if the user is logged in using the AuthGuardService
    */

import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(private router: Router,
    private authService: AuthenticationService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | import("@angular/router").UrlTree | import("rxjs").Observable<boolean | import("@angular/router").UrlTree> | Promise<boolean | import("@angular/router").UrlTree> {
    if(this.authService.isUserLoggedIn())
      return true;

    this.router.navigate (['login']);
    return false;
  
  }
}
