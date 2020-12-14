import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../_services/token-storage.service';
import {Router} from '@angular/router';
import {ProfileService} from '../_services/profile.service';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  email: string;
  id: number;
  currentUser: any;
  profileImageAvatar: any;
  private roles: string[];

  constructor(private tokenStorageService: TokenStorageService,
              private profileService: ProfileService,
              private sanitizer: DomSanitizer,
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
      this.id = user.id;
    }

    if (this.tokenStorageService.getToken() == null) {
      this.router.navigateByUrl('/home');
    }
    this.getImageProfileAvatar();
  }

  logout(): void {
    this.tokenStorageService.signOut();
    this.router.navigateByUrl('/home');
  }

  sendToProfile(id: number) {
    this.router.navigateByUrl('dashboard/profile/' + id);
  }

  getImageProfileAvatar() {
    this.profileService.getProfileImageByUserId(this.tokenStorageService.getUser().id).subscribe((data: any) => {
      this.profileImageAvatar = this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/png;base64, ${data.image}`);
    }, error => {
      this.profileImageAvatar = JSON.parse(error.message).message;
    });
  }
}
