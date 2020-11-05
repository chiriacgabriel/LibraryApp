import {Component, OnInit} from '@angular/core';
import {AuthService} from '../_services/auth.service';
import {MDBModalRef} from 'ng-uikit-pro-standard';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

    form: any = {};
    isSuccessful = false;
    isSignUpFailed = false;
    errorMessage = '';

    validatingForm: FormGroup;

    constructor(private authService: AuthService,
                public modalRefRegister: MDBModalRef) {
    }

    ngOnInit(): void {
        this.validatingForm = new FormGroup({
            modalFormElegantEmail: new FormControl('', Validators.email),
            modalFormElegantPassword: new FormControl('', Validators.required),
            modalFormElegantName: new FormControl('', Validators.required),
            modalFormElegantLastName: new FormControl('', Validators.required)
        });
    }

    onSubmit(): void {
        this.authService.register(this.form).subscribe(
            data => {
                this.isSuccessful = true;
                this.isSignUpFailed = false;
            },
            err => {
                this.errorMessage = err.error.message;
                this.isSignUpFailed = true;
            }
        );
    }


    get modalFormElegantEmail() {
        return this.validatingForm.get('modalFormElegantEmail');
    }

    get modalFormElegantPassword() {
        return this.validatingForm.get('modalFormElegantPassword');
    }

    get modalFormElegantLastName() {
        return this.validatingForm.get('modalFormElegantLastName');
    }

    get modalFormElegantName() {
        return this.validatingForm.get('modalFormElegantName');
    }
}
