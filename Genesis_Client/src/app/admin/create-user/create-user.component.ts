/*
This class creates a new Employee and adds to the employee database
*/

import { Component, OnInit } from '@angular/core';
import { UserService } from './../../services/user.service';
import { User } from './../../classes/user';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators, EmailValidator } from '@angular/forms';


@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  user: User = new User();
  submitted = false;
  successMessage:string;
  redirection:string;
  

  errorMessage:string;
  timeout:number = 4000;
  time_out:number = this.timeout/1000;

  registrationForm: FormGroup;
  flag:boolean;
  //new code 04.10

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) { }

  ngOnInit() {
      this.registrationForm = this.formBuilder.group({
        employeeId: ['', {updateOn: 'blur', validators: [Validators.required]}],
        firstName: ['', {updateOn: 'blur', validators: [Validators.required, Validators.pattern('[a-zA-Z]+')]}],
        lastName: ['', {updateOn: 'blur', validators: [Validators.required, Validators.pattern('[a-zA-Z]+')]}],
        
        address: this.formBuilder.group({
          streetName: ['', {updateOn: 'blur', validators: [Validators.required, Validators.pattern('[a-zA-Z]+')]}],
          streetNo: ['',{updateOn: 'blur', validators: [Validators.required, Validators.pattern('[0-9]{1,5}')]}],
          city: ['', {updateOn: 'blur', validators: [Validators.required, Validators.pattern('[a-zA-Z]+')]}],
          state: ['', {updateOn: 'blur', validators: [Validators.required, Validators.pattern('[a-zA-Z]+')]}],
          zip: ['',{updateOn: 'blur', validators: [Validators.required, Validators.pattern('[0-9]{5}')]}]
        }),

        emailId: ['', {updateOn: 'blur', validators: [Validators.required, Validators.email]}],

        designation: ['', {updateOn: 'blur', validators: [Validators.required]}],
        rfid: ['', {updateOn: 'blur', validators: [Validators.required, Validators.pattern('[0-9]{5}')]}],
        userGender: ['', {updateOn: 'blur', validators: [Validators.required]}],
        phoneNumber: ['', {updateOn: 'blur', validators: [Validators.required, Validators.pattern('[0-9]{11}')]}],
        role: ['', {updateOn: 'blur', validators: [Validators.required]}]

      });
  }
  /*
  validateEmail(emailId: FormControl) {
    let EMAIL_REGEXP = new RegExp("/^[\w._]+@[A-Za-z]+\.(com|co\.in|org)$/");
    return EMAIL_REGEXP.test(emailId.value) ? null : {
        emailError: {
            message: "Email is invalid"
        }
    };
  }*/

  /*
  get method below retrieves and returns the address as a form  group,
  so that in the html file, the properties can be accessed in *ngif condition through 
  *ngif = !address.controls['streetName'].valid for example
  it solves the problem of "Property controls does not exist on type Abstract control"
  */
  get address(){
    return this.registrationForm.controls.address as FormGroup;
  }

  newUser(): void {
    this.submitted = false;
    this.user = new User();
  }
  
  /*
  save() {
    this.userService.createUser(this.user)
      .subscribe(data => {
        console.log(data);
        this.successMessage = data; 
        console.log(this.successMessage)
      }, error => console.log(error));
    this.user = new User();
    
    //setTimeout redirects to admin-home after x seconds
    setTimeout;
    
    setTimeout(() => {
      this.router.navigate(['/admin-home']);
    }, this.timeout);
    
  }*/



  save() {
    
    this.userService.createUser(this.registrationForm.value)
      .subscribe(data => {
        console.log(data);
        this.successMessage = data; 
        console.log(this.successMessage)
      }, error => {this.errorMessage = error; console.log(error)});
    this.user = new User();
    
    //setTimeout redirects to admin-home after x seconds
    setTimeout;
    
    setTimeout(() => {
      this.redirection = "Redirecting to Admin Homepage";
      alert(this.successMessage);
      this.router.navigate(['/admin-home']);
    }, this.timeout);   
  }
  
  onSubmit() {
    
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/users']);
  }

  back(){
    this.router.navigate(['admin-home']);
  }
}
