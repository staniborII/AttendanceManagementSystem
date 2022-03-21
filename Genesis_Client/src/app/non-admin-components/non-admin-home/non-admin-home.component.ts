import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/classes/user';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-non-admin-home',
  templateUrl: './non-admin-home.component.html',
  styleUrls: ['./non-admin-home.component.css']
})
export class NonAdminHomeComponent implements OnInit {

  successMessage:string;
  errorMessage:string;
  submitted:boolean = true;
  user: User;
  empId:number;

  constructor(private router:Router, private route: ActivatedRoute, private userService:UserService) { }

  ngOnInit(): void {
    //this.empId = this.route.snapshot.params['empId'];
    
  }

  nonAdmin(){
    console.log(this.empId);
    this.router.navigate(['non-admin', this.empId]);
  }
  /*
  getEmployee(){
    this.submitted = false;
    this.userService.getUser(4)
      .subscribe(data => {
        console.log(data);
        this.user = data;
        this.empId = this.user.employeeId;
      }, error => {console.log(error); this.errorMessage=error}
      
      );
  }

  viewDetails(empId: number){
    this.router.navigate(['view-details', empId]);
  }

  viewTimeSheet(empId: number){
    this.router.navigate(['time-sheet', empId]);
  }

  changeCredentials(empId: number){
    this.router.navigate(['change-credentials', empId]);
  }

  back(){
    this.router.navigate(['non-admin']);
  }
  */
}


