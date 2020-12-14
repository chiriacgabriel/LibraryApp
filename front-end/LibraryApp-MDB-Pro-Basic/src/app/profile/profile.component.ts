import {Component, HostListener, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {MdbTableDirective} from 'ng-uikit-pro-standard';
import {ReservationService} from '../_services/reservation.service';
import {TokenStorageService} from '../_services/token-storage.service';

import {ProfileService} from '../_services/profile.service';
import {AlertsService} from '../_services/alerts.service';
import {DomSanitizer} from '@angular/platform-browser';
import {ReloadPageService} from '../_services/reload-page.service';
import {NavigationEnd, Router} from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit, OnDestroy {

  @ViewChild(MdbTableDirective, {static: true}) mdbTable: MdbTableDirective;
  elements: any = [];
  isElements = false;
  headElements = ['Book', 'Client', 'StartDate', 'EndDate', 'ReservationState'];
  searchText = '';
  previous: string;
  reservations = [];
  currentUser: any;
  selectedFiles: FileList;
  currentFile: File;
  imageUser: any;
  imageId: number;
  page = 1;
  count: any;
  pageSize = 8;

  profileImageSubscription: any;
  navigationSubscription;

  constructor(private reservationService: ReservationService,
              private token: TokenStorageService,
              private profileService: ProfileService,
              private alertService: AlertsService,
              private sanitizer: DomSanitizer,
              private reloadPageService: ReloadPageService,
              private router: Router) {
    // TO PREVENT MEMORY LEAK
  this.navigationSubscription = this.router.events.subscribe((e: any) => {
    if (e instanceof NavigationEnd){
      this.getImageProfile();
    }
  });
  }

  @HostListener('input') oninput() {
    this.searchItems();
  }

  ngOnInit() {
    this.getAllReservationsByUserId();

    this.mdbTable.setDataSource(this.elements);
    this.previous = this.mdbTable.getDataSource();

    this.currentUser = this.token.getUser();

    this.getImageProfile();

  }

  // PART OF MEMORY LEAK
  ngOnDestroy() {
    if (this.profileImageSubscription){
      this.profileImageSubscription.unsubscribe();
    }
  }

  searchItems() {
    const prev = this.mdbTable.getDataSource();
    if (!this.searchText) {
      this.mdbTable.setDataSource(this.previous);
      this.elements = this.mdbTable.getDataSource();
    }
    if (this.searchText) {
      this.elements = this.mdbTable.searchLocalDataBy(this.searchText);
      this.mdbTable.setDataSource(prev);
    }
  }

  getAllReservationsByUserId() {
    const params = this.getRequestParams(this.page, this.pageSize);
    this.reservationService.getReservationsByUserId(this.token.getUser().id, params).subscribe((data: any) => {

      this.reservations = data.content;

      this.count = data.totalElements;

      this.populateTable(data.content);

    }, error => {
      this.reservations = JSON.parse(error.message).message;
    });
  }

  getRequestParams(page, pageSize) {
    let params = {};

    if (page) {
      params[`page`] = page - 1;
    }
    if (pageSize) {
      params[`size`] = pageSize;
    }

    return params;
  }

  handlePageChange(event) {
    if (this.elements.length > 0) {
      this.elements = [];
    }

    this.page = event;
    this.getAllReservationsByUserId();
  }

  populateTable(data: any) {
    for (let i = 0; i < data.length; i++) {
      for (let j = 0; j < data[i].bookList.length; j++) {
        this.elements.push({
          book: data[i].bookList[j].title,
          client: data[i].client.email,
          startDate: data[i].startDate,
          endDate: data[i].endDate,
          reservationState: data[i].reservationState.nameOfState
        });
      }
    }
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  update() {
    this.currentFile = this.selectedFiles.item(0);
    this.profileImageSubscription = this.profileService.updateProfileImage(this.currentFile, this.imageId).subscribe(event => {

      // THIS WILL PRODUCE MEMORY LEAK IF UNSUBSCRIBED AND DESTROYED
      this.reloadPageService.reload();

    }, error => {
      this.profileService = JSON.parse(error.message).message;
    });
    this.selectedFiles = undefined;
  }

  getImageProfile() {
    this.profileService.getProfileImageByUserId(this.token.getUser().id).subscribe((data: any) => {
      this.imageUser = this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/png;base64, ${data.image}`);
      this.imageId = data.id;
    }, error => {
      this.imageUser = JSON.parse(error.message).message;
    });
  }

}
