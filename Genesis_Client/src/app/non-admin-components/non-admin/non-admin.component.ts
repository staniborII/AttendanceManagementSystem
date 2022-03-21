import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/classes/user';

@Component({
  selector: 'app-non-admin',
  templateUrl: './non-admin.component.html',
  styleUrls: ['./non-admin.component.css']
})
export class NonAdminComponent implements OnInit {

  successMessage:string;
  errorMessage:string;
  submitted:boolean = true;
  user: User = new User();
  empId:number;
  userName:string;

  constructor(private router:Router, private route: ActivatedRoute, private userService:UserService) { }

  ngOnInit(): void {
    this.empId = this.route.snapshot.params['empId'];
    this.reloadData();
    //this.userName = sessionStorage.getItem('username');
  }

  reloadData(){
    this.getEmployee();
  }

  getEmployee(){
    this.submitted = false;
    this.userService.getUser(this.empId)
      .subscribe(data => {
        console.log(data);
        this.user = data;
        this.empId = this.user.employeeId;
        this.userName = this.user.firstName;
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

  updateMyDetails(empId: number){
    this.router.navigate(['update-my-details', empId]);
  }

  back(){
    this.router.navigate(['non-admin']);
  }

}
