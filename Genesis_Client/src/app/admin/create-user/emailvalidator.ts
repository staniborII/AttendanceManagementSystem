import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';

export class EmailValidator{

    validateEmail(c: FormControl) {
        let regexpEmail = 
        new RegExp('^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$');        
        return regexpEmail.test(c.value) ? null : {
            emailError: {
                message: "Email is invalid"
            }
        };
      }

      
}