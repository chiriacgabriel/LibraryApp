import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../_services/token-storage.service';
import {MDBModalRef, MDBModalService} from 'ng-uikit-pro-standard';
import {ProfileComponent} from '../profile/profile.component';
import {AuthorService} from '../_services/author.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  private roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  email: string;
  currentUser: any;

  modalRefProfile: MDBModalRef;

  constructor(private tokenStorageService: TokenStorageService,
              private modalService: MDBModalService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');

      this.email = user.email;
    }

    if (this.tokenStorageService.getToken() == null) {
      this.router.navigateByUrl('/home');
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    this.router.navigateByUrl('/home');
  }

  openModalProfile() {
    this.modalRefProfile = this.modalService.show(ProfileComponent);
  }

}
