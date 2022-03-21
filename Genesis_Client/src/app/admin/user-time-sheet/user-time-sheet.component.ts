import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/classes/user';
import { TimeSheet } from 'src/app/classes/TimeSheet';

@Component({
  selector: 'app-user-time-sheet',
  templateUrl: './user-time-sheet.component.html',
  styleUrls: ['./user-time-sheet.component.css']
})
export class UserTimeSheetComponent implements OnInit {
  user: User;
  id:number;
  userTimeSheets: any;
  

  constructor(private router: Router,private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit(): void {
    this.user = new User();

    this.id = this.route.snapshot.params['id'];//get id from the route's url
    
    this.userService.getUser(this.id)
      .subscribe(data => {
        console.log(data)
        this.user = data;
        this.userTimeSheets = this.user.timeSheets;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['users']);
  }

}
