import { Component, OnInit } from '@angular/core';
import { User } from './../../classes/user';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from './../../services/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  id: number;
  user: User;
  submitted:boolean = false;

  confirmation:boolean;

  updateForm: FormGroup;
  

  constructor(private formBuilder:FormBuilder, private route: ActivatedRoute,private router: Router,
    private userService: UserService) { }

  ngOnInit() {
    this.user = new User();

    this.id = this.route.snapshot.params['id'];
    
    this.userService.getUser(this.id)
      .subscribe(data => {
        console.log(data)
        this.user = data;
      }, error => console.log(error));
  }

  updateUser() {
    this.confirmation = confirm("Are you sure you want to update this user?");

    if(this.confirmation){
      this.userService.updateUser(this.id, this.user/*this.updateForm.value*/)
        .subscribe(data => console.log(data), error => console.log(error));
      this.user = new User();
      this.gotoList();
      this.submitted = true;
    }else{this.submitted =false;}
  }

  onSubmit() {
    this.updateUser(); 
    //this.submitted =true;   
  }

  gotoList() {
    this.router.navigate(['/users']);
  }

  back(){
    this.router.navigate(['/users']);
  }

}
