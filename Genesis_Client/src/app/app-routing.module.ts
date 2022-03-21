import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UserListComponent } from './admin/user-list/user-list.component';
import { UserDetailsComponent } from './admin/user-details/user-details.component';
import { CreateUserComponent } from './admin/create-user/create-user.component';
import { UpdateUserComponent } from './admin/update-user/update-user.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';

import { AuthGuardService } from './services/auth-guard.service';
import { NonAdminComponent } from './non-admin-components/non-admin/non-admin.component';
import { HomeComponent } from './home/home.component';
import { AdminGaurdService } from './services/admin-gaurd.service';
import { RegisterUserComponent } from './admin/register-user/register-user.component';
import { UserCredentialListComponent } from './admin/user-credential-list/user-credential-list.component';
import { UserTimeSheetComponent } from './admin/user-time-sheet/user-time-sheet.component';
import { ViewDetailsComponent } from './non-admin-components/view-details/view-details.component';
import { TimeSheetComponent } from './non-admin-components/time-sheet/time-sheet.component';
import { ChangeCredentialsComponent } from './non-admin-components/change-credentials/change-credentials.component';
import { UpdateMyDetailsComponent } from './non-admin-components/update-my-details/update-my-details.component';

import { NonAdminHomeComponent } from './non-admin-components/non-admin-home/non-admin-home.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { AboutComponent } from './about/about.component';
import { SignUpComponent } from './sign-up/sign-up.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'login', component: LoginComponent },
  { path: 'reset-password', component: ResetPasswordComponent }, 
  { path: 'sign-up', component: SignUpComponent },

  { path: 'newUser', component: RegisterUserComponent, canActivate:[AuthGuardService, AdminGaurdService] },
  { path: 'userCredentialList', component: UserCredentialListComponent, canActivate:[AuthGuardService, AdminGaurdService] },
  { path: 'admin-home', component: AdminHomeComponent, canActivate:[AuthGuardService, AdminGaurdService] },
  { path: 'users', component: UserListComponent,canActivate:[AuthGuardService, AdminGaurdService] },
  { path: 'add', component: CreateUserComponent ,canActivate:[AuthGuardService, AdminGaurdService]},
  { path: 'update/:id', component: UpdateUserComponent ,canActivate:[AuthGuardService, AdminGaurdService]},
  { path: 'details/:id', component: UserDetailsComponent,canActivate:[AuthGuardService, AdminGaurdService] },
  { path: 'timeSheet/:id', component: UserTimeSheetComponent,canActivate:[AuthGuardService, AdminGaurdService] },

  /*The path 'details/:id' where you specified the route parameter id which will receive different values based on the employee selected*/
  
  { path: 'logout', component: LogoutComponent,canActivate:[AuthGuardService] },
  { path: 'non-admin-home', component: NonAdminHomeComponent,canActivate:[AuthGuardService] },

  { path: 'non-admin/:empId', component: NonAdminComponent,canActivate:[AuthGuardService] },
  { path: 'view-details/:empId', component: ViewDetailsComponent,canActivate:[AuthGuardService] },
  { path: 'time-sheet/:empId', component: TimeSheetComponent,canActivate:[AuthGuardService] },
  { path: 'change-credentials/:empId', component: ChangeCredentialsComponent,canActivate:[AuthGuardService] },
  { path: 'update-my-details/:empId', component: UpdateMyDetailsComponent,canActivate:[AuthGuardService] },
  
  { path: '**', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
