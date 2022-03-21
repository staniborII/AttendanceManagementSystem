import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/classes/user';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { TimeSheet } from 'src/app/classes/TimeSheet';

@Component({
  selector: 'app-time-sheet',
  templateUrl: './time-sheet.component.html',
  styleUrls: ['./time-sheet.component.css']
})
export class TimeSheetComponent implements OnInit {
  user:User;
  empId:number;
  userTimeSheets:any;

  constructor(private router: Router,private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit(): void {

    this.user = new User();

    this.empId = this.route.snapshot.params['empId'];//get id from the route's url
    

    this.userService.getUser(this.empId)
      .subscribe(data => {
        console.log(data)
        this.user = data;
        this.userTimeSheets = this.user.timeSheets;
      }, error => console.log(error));
  }

  back(){
    this.router.navigate(['non-admin', this.empId]);
  }

}
