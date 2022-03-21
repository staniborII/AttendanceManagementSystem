import { Component, OnInit } from '@angular/core';
import { UserService } from './../../services/user.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators, EmailValidator } from '@angular/forms';
import { UserLogin } from 'src/app/classes/userLogin';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  successMessage:string;
  errorMessage:string;

  submitted = false;
  userLogin:UserLogin = new UserLogin;

  timeout:number = 4000;
  time_out:number = this.timeout/1000;

  registerUserForm:FormGroup;

  constructor(private formBuilder:FormBuilder, private userService:UserService, private router:Router) { }

  ngOnInit(): void {
    this.registerUserForm = this.formBuilder.group({
      employeeId: ['',{updateOn: 'blur', validators: [Validators.required]}],
      userName: ['',{updateOn: 'blur', validators: [Validators.required]}],
      password: ['',{updateOn: 'blur', validators: [Validators.required]}],
      /*confirmPassword: ['',{updateOn: 'blur', validators: [Validators.required]}],*/
      role: ['',{updateOn: 'blur', validators: [Validators.required]}]

    });
  }

  save() {
    this.userService.registerUser(this.registerUserForm.value)
      .subscribe(data => {
        console.log(data);
        this.successMessage = data; 
        console.log(this.successMessage)
      }, error => console.log(error));
    this.userLogin = new UserLogin();
    
    //setTimeout redirects to admin-home after x seconds
    setTimeout;
    
    setTimeout(() => {
      this.router.navigate(['/admin-home']);
    }, this.timeout);
    
  }
  
  onSubmit() {
    
    this.submitted = true;
    this.save();
  }

  back(){
    this.router.navigate(['admin-home']);
  }

}
