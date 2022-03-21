import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/classes/user';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-update-my-details',
  templateUrl: './update-my-details.component.html',
  styleUrls: ['./update-my-details.component.css']
})
export class UpdateMyDetailsComponent implements OnInit {

  empId: number;
  user: User;
  submitted:boolean = false;

  confirmation:boolean;

  updateForm: FormGroup;
  

  constructor(private formBuilder:FormBuilder, private route: ActivatedRoute,private router: Router,
    private userService: UserService) { }

  ngOnInit() {
    this.user = new User();

    this.empId = this.route.snapshot.params['empId'];
    
    this.userService.getUser(this.empId)
      .subscribe(data => {
        console.log(data)
        this.user = data;
      }, error => console.log(error));
  }

  updateUser() {
    this.confirmation = confirm("Are you sure you want to update your details?");

    if(this.confirmation){
      this.userService.updateUser(this.empId, this.user/*this.updateForm.value*/)
        .subscribe(data => console.log(data), error => console.log(error));
      this.user = new User();
      this.goback();
      this.submitted = true;
    }else{this.submitted =false;}
  }

  onSubmit() {
    this.updateUser(); 
    //this.submitted =true;   
  }

  goback() {
    this.router.navigate(['non-admin', this.empId]);
  }

  back(){
    this.router.navigate(['non-admin', this.empId]);
  }
}
