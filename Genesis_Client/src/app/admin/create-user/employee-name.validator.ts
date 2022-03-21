import { AbstractControl } from "@angular/forms";

//Validator class to validate the employeeName
export class EmployeeNameValidator {
  /*method to check employeeName can contain multiple words 
    separated by spaces and each word should contain more than one character*/
  static checkName(name: AbstractControl): { 'checkName': true } | null {
    let value = "" + name.value;
    if (value.match('^[A-Za-z ]+$')) {
      let names = value.split(" ");
      for (let i = 0; i < names.length; i++) {
        if (names[i].trim().length == 1) {
          return { "checkName": true };
        }
      }
    }
    else {
      return { "checkName": true };
    }
    return null;
  }
}
