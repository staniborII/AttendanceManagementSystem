import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateUserComponent } from './admin/create-user/create-user.component';
import { UserListComponent } from './admin/user-list/user-list.component';
import { UserDetailsComponent } from './admin/user-details/user-details.component';

import { UserService } from './services/user.service';
import { AuthGuardService } from './services/auth-guard.service';
import { AuthenticationService } from './services/authentication.service';

import { HttpClientModule } from '@angular/common/http';

import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { UpdateUserComponent } from './admin/update-user/update-user.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { NonAdminComponent } from './non-admin-components/non-admin/non-admin.component';
import { HomeComponent } from './home/home.component';
import { RegisterUserComponent } from './admin/register-user/register-user.component';
import { UserCredentialListComponent } from './admin/user-credential-list/user-credential-list.component';
import { UserTimeSheetComponent } from './admin/user-time-sheet/user-time-sheet.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { ChangeCredentialsComponent } from './non-admin-components/change-credentials/change-credentials.component';
import { TimeSheetComponent } from './non-admin-components/time-sheet/time-sheet.component';
import { ViewDetailsComponent } from './non-admin-components/view-details/view-details.component';
import { NonAdminHomeComponent } from './non-admin-components//non-admin-home/non-admin-home.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';


import { NgIdleKeepaliveModule } from '@ng-idle/keepalive'; // this includes the core NgIdleModule but includes keepalive providers for easy wireup
import { ModalModule } from 'ngx-bootstrap/modal';
import { AboutComponent } from './about/about.component';
import { UpdateMyDetailsComponent } from './non-admin-components/update-my-details/update-my-details.component';



@NgModule({
  declarations: [
    AppComponent,
    CreateUserComponent,
    UserListComponent,
    UserDetailsComponent,
    UpdateUserComponent,
    LoginComponent,
    LogoutComponent,
    AdminHomeComponent,
    NonAdminComponent,
    HomeComponent,
    RegisterUserComponent,
    UserCredentialListComponent,
    UserTimeSheetComponent,
    SignUpComponent,
    ChangeCredentialsComponent,
    TimeSheetComponent,
    ViewDetailsComponent,
    NonAdminHomeComponent,
    ResetPasswordComponent,
    AboutComponent,
    UpdateMyDetailsComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    NoopAnimationsModule,
    AppRoutingModule,
    NgIdleKeepaliveModule.forRoot(),
    ModalModule.forRoot()
  ],
  providers: [UserService, AuthGuardService, AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
