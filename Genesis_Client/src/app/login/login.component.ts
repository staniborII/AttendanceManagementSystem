import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './../services/authentication.service';
import { UserLogin } from './../classes/userLogin';
import { Observable } from 'rxjs';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AppService } from '../services/app.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userLogin: UserLogin = new UserLogin();
  invalidLogin = false;
  userRole:string;

  successMessage:string;
  errorMessage:string;

  loginForm:FormGroup;


  constructor(private appService: AppService ,private formBuilder:FormBuilder, private router: Router, private loginservice: AuthenticationService) { }


  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      userName: ['', [Validators.required]],
      password: ['', Validators.required],
    });
  }
  
  
  /*
  checkLogin() {
    if (this.loginservice.authenticate(this.userLogin.userName, this.userLogin.password)
    ) {
      this.router.navigate([''])
      this.invalidLogin = false
    } else
      this.invalidLogin = true
  }
  
  checkLogin() {
    if (this.loginservice.authenticate(this.userLogin)) {
      this.router.navigate([''])
      this.invalidLogin = false
    } else
      this.invalidLogin = true
  }*/

  /*
  checkLogin() method checks if the user logged in is an admin or normal user
  and based on that shows the respective view
  */
  /*
  checkLogin() {
    this.loginservice.authenticate(this.loginForm.value)
      .subscribe(data => {
        console.log(data);
        this.userRole = data;
        console.log(this.userRole);
        this.storSession(this.userRole);}, 
        error => {console.log(error); this.errorMessage=error}
      
      );
      //find a way to handle error and print on page
  }*/


  checkLogin() {
    this.appService.setUserLoggedIn(true);

    this.loginservice.authenticate(this.loginForm.value)
      .subscribe(data => {
        console.log(data);
        this.userLogin = data;
        console.log(this.userLogin.role);
        this.storSession(this.userLogin);}, 
        error => {console.log(error); this.errorMessage=error;}
      
      );
      //find a way to handle error and print on page
  }


  storSession(userLogin:UserLogin){
    var roleStr:string = userLogin.role.toString();
    var userNameStr:string = userLogin.userName.toString();
    var empId:number = userLogin.employeeId;
    console.log(roleStr);
    console.log(userNameStr);
    console.log(empId);
    //console.log(userLogin.role.toString());

    if(roleStr == "ADMIN"){
      sessionStorage.setItem('username', userNameStr);
      sessionStorage.setItem('userRole', roleStr);
      //sessionStorage.setItem('empId', empIdStr)
      this.router.navigate(['admin-home'])
      this.invalidLogin = false
      return true;
    }
    if(roleStr == "USER"){
      sessionStorage.setItem('username', userLogin.userName);
      sessionStorage.setItem('userRole', roleStr);
      //sessionStorage.setItem('empId', userLogin.userId.toString())
      this.router.navigate(['non-admin', empId])
      this.invalidLogin = false
      return true;
    }else{this.invalidLogin = true; return false;}  
  }
}
