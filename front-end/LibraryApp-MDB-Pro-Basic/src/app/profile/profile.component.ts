import {Component, HostListener, OnInit, ViewChild} from '@angular/core';
import {MdbTableDirective, ModalDirective} from 'ng-uikit-pro-standard';
import {ReservationService} from '../_services/reservation.service';
import {TokenStorageService} from '../_services/token-storage.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {User} from "../model/User";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  @ViewChild(MdbTableDirective, {static: true}) mdbTable: MdbTableDirective;

  elements: any = [];
  isElements = false;

  headElements = ['Book', 'Client', 'StartDate', 'EndDate', 'ReservationState'];
  searchText = '';
  previous: string;

  reservations = [];

  userForm: FormGroup;

  currentUser: any;

  constructor(private reservationService: ReservationService,
              private token: TokenStorageService) {

  }

  @HostListener('input') oninput() {
    this.searchItems();
  }

  ngOnInit() {
    this.getAllReservationsByUserId();

    this.mdbTable.setDataSource(this.elements);
    this.previous = this.mdbTable.getDataSource();

    this.currentUser = this.token.getUser();
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
    return this.reservationService.getReservationsByUserId(this.token.getUser().id).subscribe((data: any) => {
      this.reservations = data;
      this.populateTable(this.reservations);
    }, error => {
      this.reservations = JSON.parse(error.message).message;
    });
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

  editUser(user: User) {
    this.userForm = new FormGroup({
      id: new FormControl(user.id),
      name: new FormControl(user.name),
      lastName: new FormControl(user.lastName),
      email: new FormControl(user.email),
      password: new FormControl('', Validators.required),

    });
  }

  onFocusOutEvent(event: Event){
    console.log(event);
  }

}
