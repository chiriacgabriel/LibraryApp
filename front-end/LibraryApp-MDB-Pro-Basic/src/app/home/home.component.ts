import {Component, OnInit} from '@angular/core';
import {UserService} from '../_services/user.service';
import {MDBModalRef, MDBModalService} from 'ng-uikit-pro-standard';
import {LoginComponent} from '../login/login.component';
import {RegisterComponent} from '../register/register.component';
import {TokenStorageService} from '../_services/token-storage.service';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
    modalRef: MDBModalRef;
    modalRefRegister: MDBModalRef;

    constructor(private userService: UserService,
                private tokenStorage: TokenStorageService,
                private modalService: MDBModalService) {
    }

    ngOnInit(): void {
        if (this.tokenStorage.getToken() != null) {
            window.location.assign('/dashboard');
        }
    }

    openModalLogin() {
        this.modalRef = this.modalService.show(LoginComponent);
    }

    openModalRegister() {
        this.modalRefRegister = this.modalService.show(RegisterComponent);

    }

}
