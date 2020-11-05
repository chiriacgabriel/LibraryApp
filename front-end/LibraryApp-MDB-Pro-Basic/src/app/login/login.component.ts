import {Component, OnInit} from '@angular/core';
import {AuthService} from '../_services/auth.service';
import {TokenStorageService} from '../_services/token-storage.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MDBModalRef} from 'ng-uikit-pro-standard';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    form: any = {};
    isLoggedIn = false;
    isLoginFailed = false;
    errorMessage = '';
    roles: string[] = [];

    validatingForm: FormGroup;

    constructor(private authService: AuthService,
                private tokenStorage: TokenStorageService,
                public modalRef: MDBModalRef) {
    }

    ngOnInit(): void {
        if (this.tokenStorage.getToken()) {
            this.isLoggedIn = true;
            this.roles = this.tokenStorage.getUser().roles;

        }

        this.validatingForm = new FormGroup({
            modalFormElegantEmail: new FormControl('', Validators.email),
            modalFormElegantPassword: new FormControl('', Validators.required)
        });
    }

    onSubmit(): void {
        this.authService.login(this.form).subscribe(
            data => {
                this.tokenStorage.saveToken(data.token);
                this.tokenStorage.saveUser(data);

                this.isLoginFailed = false;
                this.isLoggedIn = true;
                this.roles = this.tokenStorage.getUser().roles;
                this.reloadPage();
            },
            err => {
                this.errorMessage = err.error.message;
                this.isLoginFailed = true;
            }
        );
    }

    reloadPage(): void {
        window.location.assign('/dashboard');
    }

    get modalFormElegantEmail() {
        return this.validatingForm.get('modalFormElegantEmail');
    }

    get modalFormElegantPassword() {
        return this.validatingForm.get('modalFormElegantPassword');
    }

}
