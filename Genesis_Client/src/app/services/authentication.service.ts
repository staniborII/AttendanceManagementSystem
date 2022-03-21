/*
This will check if the user name and password is correct. If correct, the username and password is set in session storage.
The sessionStorage object stores data for only one session. The data is deleted if the browser is closed. 
This service will have the following methods:
1. authenticate(): Authenticate username and password
2. isUserLoggedIn(): checks the session storage if username exis
3. logout(): This method clears the session storage of username
*/

import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { UserLogin } from '../classes/userLogin';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  baseUrl = 'http://localhost:3557/genesis/login';
  //retValue: any;
  constructor(private http: HttpClient) { }
  
  /*
  authenticate(userLogin:UserLogin){
    if(userLogin.userName === "admin" && userLogin.password === "admin"){
      sessionStorage.setItem('username', userLogin.userName);
      return true;
    }else{return false;}
  }*/
  
  
  authenticate(userLogin: UserLogin): Observable<any>{
    //return this.http.get(`${this.baseUrl}/${userLogin}`);
    
    //response type is set to text as i am expecting a string ADMIN or USER
    let retValue: any = this.http.post(`${this.baseUrl}`,userLogin,{responseType: 'json'}).pipe(
      catchError(this.handleError));
    return retValue;
  }

  isUserLoggedIn(){
    let user = sessionStorage.getItem('username')
    console.log(!(user===null))
    return !(user === null)
  }

  checkUserRole(){
    let userRole = sessionStorage.getItem('userRole')
    console.log(userRole == "ADMIN")
    return userRole == "ADMIN"
  }

  logout(){
    sessionStorage.removeItem('username')
    sessionStorage.removeItem('userRole')
  }
  
  private handleError(err: HttpErrorResponse) {
    console.error(err.error.message);
    //console.error(err.headers);
    //console.error(err.name);
    //console.error(err.status);
    //return Observable.throw(err.error() || 'Server error');
    return throwError(err.error.message || "Invalid Credentials!");
  }
  
}
