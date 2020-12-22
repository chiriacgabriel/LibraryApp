import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {ModalDirective} from 'ng-uikit-pro-standard';
import {Observable, Subject} from 'rxjs';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {BookService} from '../../_services/book.service';
import {UserService} from '../../_services/user.service';
import {ClientService} from '../../_services/client.service';
import {map, startWith} from 'rxjs/operators';
import {Client} from 'src/app/model/Client';
import {TokenStorageService} from '../../_services/token-storage.service';
import {User} from '../../model/User';
import {ReservationStateService} from '../../_services/reservation-state.service';
import {ReservationService} from '../../_services/reservation.service';
import {ReloadPageService} from '../../_services/reload-page.service';
import {AlertsService} from '../../_services/alerts.service';
import {Reservation} from '../../model/Reservation';

@Component({
  selector: 'app-reservation',
  changeDetection: ChangeDetectionStrategy.Default,
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.scss']
})
export class ReservationComponent implements OnInit {
  reservationForm: FormGroup;
  bookForm: FormGroup;
  bookTableRows: FormArray;
  editReservationForm: FormGroup;

  bookList = [];
  selectedBook = [];
  userList = [];
  clientList = [];
  filteredOptionsClient: Observable<Client[]>;
  searchClient = new Subject();
  reservationStateList = [];
  reservationList = [];

  stockBook: number;
  currentUser: User;

  constructor(private bookService: BookService,
              private userService: UserService,
              private clientService: ClientService,
              private tokenService: TokenStorageService,
              private formBuilder: FormBuilder,
              private reservationService: ReservationService,
              private alertService: AlertsService,
              private reservationStateService: ReservationStateService,
              private reloadPageService: ReloadPageService
  ) {
    this.bookForm = this.formBuilder.group({
      items: [null, Validators.required],
    });
    this.bookTableRows = this.formBuilder.array([]);
  }

  ngOnInit(): void {
    this.formReservation();
    this.getAllBooks();
    this.getAllClients();
    this.getAllReservationState();
    this.getAllUser();
    this.getAllReservations();
    this.currentUser = this.tokenService.getUser();
    this.bookForm.addControl('bookTableRows', this.bookTableRows);
  }

  onAddRow() {
    this.bookTableRows.push(this.createItemFormGroup());
  }

  onRemoveRow(rowIndex: number) {
    this.bookTableRows.removeAt(rowIndex);
  }

  createItemFormGroup(): FormGroup {
    return this.formBuilder.group({
      bookList: new FormControl('')
    });
  }

  formReservation() {
    this.reservationForm = new FormGroup({
      bookList: new FormControl(this.selectedBook),
      user: new FormControl(''),
      client: new FormControl(''),
      startDate: new FormControl(''),
      endDate: new FormControl(''),
      reservationState: new FormControl('')
    });
  }

  editFormReservation(reservation: Reservation, modalDirective: ModalDirective) {

    this.editReservationForm = new FormGroup({
      id: new FormControl(reservation.id),
      bookList: new FormControl(reservation.bookList),
      client: new FormControl(reservation.client),
      startDate: new FormControl(reservation.startDate),
      endDate: new FormControl(reservation.endDate),
      reservationState: new FormControl(reservation.reservationState)
    });

    modalDirective.toggle();
  }

  getAllBooks() {
    this.bookService.getAllBooks().subscribe((data: any) => {
      this.bookList = data;
    }, error => {
      this.bookList = JSON.parse(error.message).message;
    });
  }

  getAllUser() {
    return this.userService.getUsers().subscribe((data: any) => {
      this.userList = data;
    }, error => {
      this.userList = JSON.parse(error.message).message;
    });
  }

  getAllClients() {
    this.clientService.getAllClients().subscribe((data: any) => {
      this.clientList = data;
      this.filteredOptionsClient = this.searchClient.pipe(startWith(''), map((text: string) => this.filterClient(text)));
    }, error => {
      this.clientList = JSON.parse(error.message).message;
    });
  }

  getAllReservations() {
    this.reservationService.getAllReservationsFromDb().subscribe((data: any) => {
      this.reservationList = data;
    }, error => {
      this.reservationList = JSON.parse(error.message).message;
    });
  }

  filterClient(value: string) {
    const filterValue = value.toLowerCase();
    return this.clientList.filter((client: any) => client.firstName.toLowerCase().includes(filterValue) ||
      client.lastName.toLowerCase().includes(filterValue));
  }

  getAllReservationState() {
    this.reservationStateService.getAllStates().subscribe((data: any) => {
      this.reservationStateList = data;
    }, error => {
      this.reservationStateList = JSON.parse(error.message).message;
    });
  }

  addReservation(modalDirective: ModalDirective) {
    for (let i = 0; i < this.bookTableRows.length; i++) {
      this.selectedBook.push(this.bookForm.value.bookTableRows[i].bookList);
    }
    for (let j = 0; j < this.userList.length; j++) {
      if (this.userList[j].email === this.currentUser.email) {
        this.reservationForm.get('user').setValue(this.userList[j]);
      }
    }
    this.reservationService.addReservation(this.reservationForm.value)
                           .subscribe(response => {
      this.alertService.success();
      modalDirective.toggle();
      this.reloadPageService.reload();
    }, error => {
      console.log(error.message);
    });
  }

  updateReservation(modalDirective: ModalDirective){
    const index = this.reservationList.findIndex(reservation => reservation.id == this.editReservationForm.value.id);
    this.reservationList[index] = this.editReservationForm.value;
    const id = this.reservationList[index].id;

    this.reservationService.editReservationById(id, this.reservationList[index]).subscribe(response => {
      this.reloadPageService.reload();
      this.alertService.success();
      modalDirective.toggle();
    }, error => {
      console.log(error);
    });
  }
}
