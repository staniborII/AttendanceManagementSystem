import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private clockInUrl = 'http://localhost:3557/genesis/employees/clockIn';
  private clockOutUrl = 'http://localhost:3557/genesis/employees/clockOut';
  private baseUrl = 'http://localhost:3557/genesis/employees';
  private newCredentialsUrl = 'http://localhost:3557/genesis/newUser';
  private credentialsUrl = 'http://localhost:3557/genesis/userCredentials';
  constructor(private http: HttpClient) { }

  clockIn(employeeId: number){
    return this.http.put(`${this.clockInUrl}/${employeeId}`, {responseType: 'text'}).pipe(
      catchError(this.handleError));
  }

  clockOut(employeeId:number){
    return this.http.post(`${this.clockOutUrl}`, {responseType: 'text'}).pipe(
      catchError(this.handleError));
  }

  getUser(employeeId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${employeeId}`).pipe(
      catchError(this.handleError));
  }

  createUser (employee: Object): Observable<any> {
    return this.http.post(`${this.baseUrl}`, employee,{responseType: 'text'}).pipe(
      catchError(this.handleError));
  }

  updateUser(id: number, value: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, value).pipe(
      catchError(this.handleError));
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' }).pipe(
      catchError(this.handleError));
  }

  getUserList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`).pipe(
      catchError(this.handleError));
  }

  private handleError(err: HttpErrorResponse) {
    console.error(err.error.message);
    //console.error(err.headers);
    //console.error(err.name);
    //console.error(err.status);
    //return Observable.throw(err.error() || 'Server error');
    return throwError(err.error.message || "Server Error!");
  }

  /* Methods For User Credentials */
  registerUser(user:Object): Observable<any> {
    return this.http.post(`${this.newCredentialsUrl}`, user,{responseType: 'text'}).pipe(
      catchError(this.handleError));
  }

  getAllUserCredentialList(): Observable<any> {
    return this.http.get(`${this.credentialsUrl}`).pipe(
      catchError(this.handleError));
  }

  deleteUserCredentials(id: number): Observable<any> {
    return this.http.delete(`${this.credentialsUrl}/${id}`, { responseType: 'text' }).pipe(
      catchError(this.handleError));
  }

}