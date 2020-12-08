import {ChangeDetectionStrategy, Component, NgZone, OnInit} from '@angular/core';
import {endOfDay, isSameDay, isSameMonth, parseISO, startOfDay,} from 'date-fns';
import {Observable, Subject} from 'rxjs';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarView,
} from 'angular-calendar';
import {ModalDirective} from 'ng-uikit-pro-standard';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {BookService} from '../../_services/book.service';
import {ClientService} from '../../_services/client.service';
import {map, startWith} from 'rxjs/operators';
import {Client} from "../../model/Client";
import {TokenStorageService} from "../../_services/token-storage.service";
import {User} from "../../model/User";
import {ReservationStateService} from "../../_services/reservation-state.service";
import {UserService} from "../../_services/user.service";
import {ReservationService} from "../../_services/reservation.service";
import {AlertsService} from "../../_services/alerts.service";
import {Router} from "@angular/router";
import {ReloadPageService} from "../../_services/reload-page.service";

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
};

@Component({
  selector: 'app-reservation',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.scss'],
})
export class ReservationComponent implements OnInit {
  view: CalendarView = CalendarView.Month;
  CalendarView = CalendarView;
  viewDate: Date = new Date();
  reservationForm: FormGroup;
  bookList = [];
  stockBook: number;
  bookForm: FormGroup;
  bookTableRows: FormArray;
  selectedBook = [];
  userList = [];
  currentUser: User;
  clientList = [];
  filteredOptionsClient: Observable<Client[]>;
  searchClient = new Subject();
  reservationStateList = [];
  reservationList = [];
  actions: CalendarEventAction[] = [
    {
      label: '<i class="fas fa-fw fa-pencil-alt text-warning"></i>',
      a11yLabel: 'Edit',
      onClick: ({event}: { event: CalendarEvent }): void => {
        this.handleEvent('Edited', event);
      },
    },
    {
      label: '<i class="far fa-trash-alt text-danger"></i>',
      a11yLabel: 'Delete',
      onClick: ({event}: { event: CalendarEvent }): void => {
        this.events = this.events.filter((iEvent) => iEvent !== event);
        this.handleEvent('Deleted', event);
      },
    },
  ];
  refresh: Subject<any> = new Subject();
  events: CalendarEvent[] = [];
  activeDayIsOpen = false;

  constructor(private bookService: BookService,
              private userService: UserService,
              private clientService: ClientService,
              private tokenService: TokenStorageService,
              private formBuilder: FormBuilder,
              private reservationService: ReservationService,
              private alertService: AlertsService,
              private reservationStateService: ReservationStateService,
              private reloadPageService: ReloadPageService) {
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
    this.events;
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

  dayClicked({date, events}: { date: Date; events: CalendarEvent[] }, modal: ModalDirective): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    }
    if (events.length === 0) {
      modal.toggle();
    }
  }

  eventTimesChanged({
                      event,
                      newStart,
                      newEnd,
                    }: CalendarEventTimesChangedEvent): void {
    this.events = this.events.map((iEvent) => {
      if (iEvent === event) {
        return {
          ...event,
          start: newStart,
          end: newEnd,
        };
      }
      return iEvent;
    });
    this.handleEvent('Dropped or resized', event);
  }

  handleEvent(action: string, event: CalendarEvent): void {
  }

  deleteEvent(eventToDelete: CalendarEvent) {
    this.events = this.events.filter((event) => event !== eventToDelete);
  }

  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }

  populateCalendarEvents(data) {
    for (let i = 0; i < data.length; i++) {
      for (let j = 0; j < data[i].bookList.length; j++) {
        this.events.push(
          {
            start: startOfDay(parseISO(data[i].startDate)),
            end: endOfDay(parseISO(data[i].endDate)),
            title: data[i].client.firstName + ' ' + data[i].client.lastName + '<br>' + 'Book: ' + data[i].bookList[j].title,
            color: colors.yellow,
            actions: this.actions,
            resizable: {
              beforeStart: false,
              afterEnd: false,
            },
            draggable: false,
          }
        );
      }
    }
    this.refresh.next();
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
    })
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
    this.reservationService.getAllReservation().subscribe((data: any) => {
      this.reservationList = data;
      this.populateCalendarEvents(data);
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
    })
  }

  addReservation(modalDirective: ModalDirective) {
    for (let i = 0; i < this.bookTableRows.length; i++) {
      this.selectedBook.push(this.bookForm.value.bookTableRows[i].bookList);
    }
    for (let j = 0; j < this.userList.length; j++) {
      if (this.userList[j].email == this.currentUser.email) {
        this.reservationForm.get('user').setValue(this.userList[j]);
      }
    }
    this.reservationService.addReservation(this.reservationForm.value).subscribe(response => {
      this.alertService.alertShowSuccess();
      modalDirective.toggle();
      this.reloadPageService.reload();
    }, error => {
      console.log(error.message);
    })
  }

}
