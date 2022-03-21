/*
logout(): This method clears the session storage of username
*/

import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { AppService } from '../services/app.service';

import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService, private appService:AppService,
    private router: Router) { }

  ngOnInit(): void {
    this.appService.setUserLoggedIn(false);
    this.authenticationService.logout();
    this.router.navigate(['home']);
  }

}
