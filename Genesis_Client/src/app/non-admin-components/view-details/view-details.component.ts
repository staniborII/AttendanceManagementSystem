import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from 'src/app/classes/user';
import { UserService } from 'src/app/services/user.service';
import { Address } from 'src/app/classes/address';

@Component({
  selector: 'app-view-details',
  templateUrl: './view-details.component.html',
  styleUrls: ['./view-details.component.css']
})
export class ViewDetailsComponent implements OnInit {
  empId:number;
  user:User;
  address:Address;

  constructor(private router:Router,private route: ActivatedRoute, private userService: UserService ) { }

  ngOnInit(): void {
    this.empId = this.route.snapshot.params['empId'];
    this.user = new User();

    this.userService.getUser(this.empId)
      .subscribe(data => {
        console.log(data)
        this.user = data;
        this.address = this.user.address;
        //console.log(this.address.streetName);
      }, error => console.log(error));
  }

  back(){
    this.router.navigate(['non-admin', this.empId]);
  }

}
