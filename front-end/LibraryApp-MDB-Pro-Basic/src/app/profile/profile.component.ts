import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import {MDBModalRef} from 'ng-uikit-pro-standard';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  currentUser: any;

  constructor(private token: TokenStorageService,
              public modalRef: MDBModalRef) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
  }

}
