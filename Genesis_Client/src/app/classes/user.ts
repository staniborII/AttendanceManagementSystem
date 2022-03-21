
import {UserGender} from './gender';
import { TimeSheet } from './TimeSheet';
import { UserLogin } from './userLogin';
import { Role } from './role';
import { Address } from './address';

export class User {
    employeeId: number;
    firstName: string;
    lastName: string;
    emailId: string;
    phoneNumber: number;
    //active:boolean;
    designation: string;
    rfid:number;
    userGender: UserGender;
    role: Role;
    regDate:string;
    timeSheets:Array<TimeSheet>;
    userLogin:UserLogin;
    address:Address;

}
