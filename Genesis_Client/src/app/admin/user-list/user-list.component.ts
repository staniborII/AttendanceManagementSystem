import { Component, OnInit } from '@angular/core';
import { User } from './../../classes/user';
import { Observable } from "rxjs";
import { UserService } from "./../../services/user.service";
import { Router } from '@angular/router';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  employeeList: Observable<User>;
  x:boolean;

  constructor(private userService: UserService,private router: Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.employeeList = this.userService.getUserList();
  }

  deleteUser(id: number) {
    this.x = confirm("Are you sure you want to delete this user?");
    if(this.x){
      this.userService.deleteUser(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
    }
    
  }

  userDetails(id: number){
    this.router.navigate(['details', id]);
  }

  userTimeSheet(id: number){
    this.router.navigate(['timeSheet', id]);
  }

  updateUser(id: number){
    this.router.navigate(['update', id]);
  }

  back(){
    this.router.navigate(['admin-home']);
  }

}
