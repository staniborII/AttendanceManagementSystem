/*
In this component a user can register themselves
The role is set to user in the backend
*/

import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { User } from '../classes/user';


@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

 

  successMessage:string;
  errorMessage:string;
  role:string;

  isSubmitted = false;
  redirection:string;
  user: User = new User();
  empId: number;


  timeout:number = 4000;
  time_out:number = this.timeout/1000;

  registrationForm:FormGroup;
  constructor(private formBuilder:FormBuilder, private userService:UserService, private router:Router) { }

  ngOnInit(): void {
    this.registrationForm = this.formBuilder.group({
      employeeId: ['', {updateOn: 'blur', validators: [Validators.required]}],
      firstName: ['', {updateOn: 'blur', validators: [Validators.required, Validators.pattern('[a-zA-Z]+')]}],
      lastName: ['', {updateOn: 'blur', validators: [Validators.required, Validators.pattern('[a-zA-Z]+')]}],
      emailId: ['', {updateOn: 'blur', validators: [Validators.required, Validators.email]}],

      designation: ['', {updateOn: 'blur', validators: [Validators.required]}],
      rfid: ['', {updateOn: 'blur', validators: [Validators.required, Validators.pattern('[0-9]{5}')]}],
      userGender: ['', {updateOn: 'blur', validators: [Validators.required]}],
      phoneNumber: ['', {updateOn: 'blur', validators: [Validators.required, Validators.pattern('[0-9]{11}')]}],
      //role: ['', {updateOn: 'blur', validators: [Validators.required]}]
      address: this.formBuilder.group({
        streetName: ['', {updateOn: 'blur', validators: [Validators.required, Validators.pattern('[a-zA-Z]+')]}],
        streetNo: ['',{updateOn: 'blur', validators: [Validators.required, Validators.pattern('[0-9]{5}')]}],
        city: ['', {updateOn: 'blur', validators: [Validators.required, Validators.pattern('[a-zA-Z]+')]}],
        state: ['', {updateOn: 'blur', validators: [Validators.required, Validators.pattern('[a-zA-Z]+')]}],
        zip: ['',{updateOn: 'blur', validators: [Validators.required, Validators.pattern('[0-9]{5}')]}]
      }),

    });

    
  }

  newUser(): void {
    this.isSubmitted = false;
    this.user = new User();
  }

  
  save() {
    //this.registrationForm.get('role').setValue('USER');
    this.empId = this.registrationForm.get('employeeId').value;
    //this.role = this.registrationForm.get('role').value;

    this.userService.createUser(this.registrationForm.value)
      .subscribe(data => {
        console.log(data);
        this.successMessage = data; 
        console.log(this.successMessage)
      }, error => {this.errorMessage = error; console.log(error)});
    this.user = new User();

    console.log(this.isSubmitted);
    
    //setTimeout redirects to credentials form after x seconds
    setTimeout;
    
    setTimeout(() => {
      this.router.navigate(['/user-credentials', this.isSubmitted]);
    }, this.timeout);

    //this.redirection = "Redirecting to Admin Homepage";
    
  }
  
  onSubmit() {
    
    this.isSubmitted = true;
    this.save();
    console.log(this.isSubmitted);
  }

  /*
  gotoList() {
    this.router.navigate(['/users']);
  }*/

  back(){
    this.router.navigate(['login']);
  }

}
