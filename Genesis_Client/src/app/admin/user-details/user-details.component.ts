import { Component, OnInit, Input } from '@angular/core';
import { User } from './../../classes/user';
import { UserService } from './../../services/user.service';
import { UserListComponent } from '../user-list/user-list.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Address } from 'src/app/classes/address';


@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  id: number;
  user: User;
  address:Address;

  constructor(private router: Router,private route: ActivatedRoute, private userService: UserService/*, private listComponent: UserListComponent*/) { }
  
  ngOnInit() {
    this.user = new User();

    this.id = this.route.snapshot.params['id'];//get id from the route's url
    
    this.userService.getUser(this.id)
      .subscribe(
        data => {
        console.log(data);
        this.user = data;
        this.address = this.user.address;
        //console.log(this.address.streetName);
      }, 
      error => console.log(error));
  }

  list(){
    this.router.navigate(['users']);
  }

}
