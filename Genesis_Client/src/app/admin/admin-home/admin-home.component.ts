import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './../../services/authentication.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {
  userName:string;
  constructor(private loginService:AuthenticationService) { }

  ngOnInit(): void {
    this.userName = sessionStorage.getItem('username')
  }

  isUserLoggedIn(){
    return this.loginService.isUserLoggedIn;
  }

}
