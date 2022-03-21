/* User idle time out is implemented in this class with the aid of libraries from:
- @angular/core
- @ng-idle/keepalive

The dialog box that appears is with the ad of libraries from:
- ngx-bootstrap/modal

*/

import { Component,  ViewChild } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';

import { Idle, DEFAULT_INTERRUPTSOURCES } from '@ng-idle/core';
import { Keepalive } from '@ng-idle/keepalive';

import { AppService } from './services/app.service';
import { BsModalService, ModalDirective, BsModalRef } from 'ngx-bootstrap/modal';
//import { BsModalRef } from 'ngx-bootstrap/modal';
//import { ModalDirective } from 'ngx-bootstrap/modal';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  idleState = 'Not started.';
  timedOut = false;
  lastPing?: Date = null;
 
  title = 'Welcome to Genesis';

  public modalRef: BsModalRef;

  @ViewChild('childModal', { static: false }) childModal: ModalDirective;

  constructor(private authenticationService:AuthenticationService, private idle: Idle, private keepalive: Keepalive, 
    private appService:AppService, private router: Router, private modalService: BsModalService,){
      // sets an idle timeout of 5 seconds, for testing purposes.
    idle.setIdle(1200);
    // sets a timeout period of 5 seconds. after 10 seconds of inactivity, the user will be considered timed out.
    idle.setTimeout(20);
    // sets the default interrupts, in this case, things like clicks, scrolls, touches to the document
    idle.setInterrupts(DEFAULT_INTERRUPTSOURCES);

    idle.onIdleEnd.subscribe(() => { 
      this.idleState = 'No longer idle.'
      console.log(this.idleState);
      this.reset();
    });
    
    idle.onTimeout.subscribe(() => {
      this.childModal.hide();
      this.idleState = 'Timed out!';
      this.timedOut = true;
      console.log(this.idleState);
      //this is the page we navigate to if the timer runs out
      this.logout();
      //this.router.navigate(['login']);
    });
    
    idle.onIdleStart.subscribe(() => {
        this.idleState = 'You\'ve gone idle!'
        console.log(this.idleState);
        this.childModal.show();
    });
    
    idle.onTimeoutWarning.subscribe((countdown) => {
      this.idleState = 'You will time out in ' + countdown + ' seconds!'
      console.log(this.idleState);
    });

    // sets the ping interval to 15 seconds
    keepalive.interval(15);

    keepalive.onPing.subscribe(() => this.lastPing = new Date());

    this.appService.getUserLoggedIn().subscribe(userLoggedIn => {
      if (userLoggedIn) {
        idle.watch()
        this.timedOut = false;
      } else {
        idle.stop();
      }
    })

    // this.reset();
  }

  reset() {
    this.idle.watch();
    //xthis.idleState = 'Started.';
    this.timedOut = false;
  }

  hideChildModal(): void {
    this.childModal.hide();
  }

  stay() {
    this.childModal.hide();
    this.reset();
  }

  logout() {
    this.childModal.hide();
    this.appService.setUserLoggedIn(false);
    this.authenticationService.logout();
    //on log out, navigate to log out component
    this.router.navigate(['login']);
  }


  isUserLoggedIn(){
    return this.authenticationService.isUserLoggedIn();
  }



  /* Set the width of the side navigation to 250px */
  /* Set the width of the side navigation to 250px and the left margin of the page content to 250px and add a black background color to body */
  openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
  }

  /* Set the width of the side navigation to 0 and the left margin of the page content to 0, and the background color of body to white */
  closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
    document.body.style.backgroundColor = "white";
  }
}
