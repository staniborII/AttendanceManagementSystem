import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  clockInForm:FormGroup;
  successMessage:any;
  errorMessage:string;
  id:number;
  constructor(private formBuilder:FormBuilder, private userService:UserService, private router:Router) { }

  ngOnInit(): void {
    this.clockInForm = this.formBuilder.group({
      employeeId: ['',{updateOn: 'blur', validators: [Validators.required]}]

    });
  }

  onSubmit() {
    this.id = this.clockInForm.value;
    this.userService.clockIn(this.id)
      .subscribe(data => {
        console.log(data);
        this.successMessage = data; 
        console.log(this.successMessage)
      }, error => console.log(error));
  }

  back(){
    this.router.navigate(['/']);
  }

}
