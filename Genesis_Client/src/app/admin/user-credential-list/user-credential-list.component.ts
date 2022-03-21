import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserLogin } from 'src/app/classes/userLogin';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-credential-list',
  templateUrl: './user-credential-list.component.html',
  styleUrls: ['./user-credential-list.component.css']
})
export class UserCredentialListComponent implements OnInit {
  
  userLogin: Observable<UserLogin>
  successMessage:string;
  errorMessage:string;

  confirmation:boolean;

  constructor(private userService:UserService, private router:Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.userLogin = this.userService.getAllUserCredentialList();
  }

  deleteUserCredentials(id: number) {
    this.confirmation = confirm("Are you sure you want to delete this user?");

    if(this.confirmation){
      this.userService.deleteUserCredentials(id)
      .subscribe(
        data => {
          this.successMessage = data;
          console.log(data);
          this.reloadData();
        },
        error => {this.errorMessage = error; console.log(error)});
    }
    
  }

  /*
  userDetails(id: number){
    this.router.navigate(['detailsUserCredentials', id]);
  }*/

  /*
  updateUser(id: number){
    this.router.navigate(['updateUserCredentials', id]);
  }*/

  back(){
    this.router.navigate(['admin-home']);
  }

}
