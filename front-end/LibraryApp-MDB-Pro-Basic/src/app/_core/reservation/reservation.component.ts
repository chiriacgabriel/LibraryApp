import {ChangeDetectionStrategy, Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
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
import {ToastrModule} from "ngx-toastr";
import {ReservationStateService} from "../../_services/reservation-state.service";
import {UserService} from "../../_services/user.service";
import {ReservationService} from "../../_services/reservation.service";
import {AlertsService} from "../../_services/alerts.service";

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

  @ViewChild('modalContent', {static: true}) modalContent: TemplateRef<any>;

  reservationForm: FormGroup;

  bookList = [];
  selectedBook = [];
  bookForm: FormGroup;
  bookTableRows: FormArray;


  clientList = [];
  filteredOptionsClient: Observable<Client[]>;
  searchClient = new Subject();

  currentUser: User;
  userList = [];

  reservationStateList = [];


  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  actions: CalendarEventAction[] = [
    // {
    //   label: '<i class="fas fa-fw fa-pencil-alt"></i>',
    //   a11yLabel: 'Edit',
    //   onClick: ({event}: { event: CalendarEvent }): void => {
    //     this.handleEvent('Edited', event);
    //   },
    // },
    {
      label: '<i class="fas fa-fw fa-trash-alt"></i>',
      a11yLabel: 'Delete',
      onClick: ({event}: { event: CalendarEvent }): void => {
        this.events = this.events.filter((iEvent) => iEvent !== event);
        this.handleEvent('Deleted', event);
      },
    },
  ];

  refresh: Subject<any> = new Subject();

  events: CalendarEvent[] = [
    // {
    //   start: subDays(startOfDay(new Date()), 1),
    //   end: addDays(new Date(), 1),
    //   title: 'A 3 day event',
    //   color: colors.red,
    //   actions: this.actions,
    //   allDay: true,
    //   resizable: {
    //     beforeStart: true,
    //     afterEnd: true,
    //   },
    //   draggable: true,
    // },
  ];

  activeDayIsOpen = false;

  constructor(private bookService: BookService,
              private clientService: ClientService,
              private userService: UserService,
              private formBuilder: FormBuilder,
              private reservationService: ReservationService,
              private reservationStateService: ReservationStateService,
              private token: TokenStorageService,
              private alertService: AlertsService) {

    this.bookForm = this.formBuilder.group({
      items: [null, Validators.required],
      items_value: ['no', Validators.required]
    });

    this.bookTableRows = this.formBuilder.array([]);

  }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    this.formReservation();
    this.getAllBooks();
    this.getAllClients();
    this.allStateReservations;
    this.allUsers;

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
      bookList: new FormControl(''),
    });
  }


  dayClicked({date, events}: { date: Date; events: CalendarEvent[] }, modalDirective: ModalDirective): void {
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
      modalDirective.toggle();
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

  addEvent(modalDirective: ModalDirective): void {
    this.events = [
      ...this.events,
      {
        title: this.reservationForm.value.client.firstName + ' ' + this.reservationForm.value.client.lastName,
        start: startOfDay(parseISO(this.reservationForm.value.startDate)),
        end: endOfDay(parseISO(this.reservationForm.value.endDate)),
        color: colors.red,
        draggable: true,
        resizable: {
          beforeStart: true,
          afterEnd: true,
        },
      },
    ];
    modalDirective.toggle();
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

  getAllClients() {
    this.clientService.getAllClients().subscribe((data: any) => {
      this.clientList = data;
      this.filteredOptionsClient = this.searchClient.pipe(startWith(''), map((text: string) => this.filterClient(text)));
    }, error => {
      this.clientList = JSON.parse(error.message).message;
    });
  }

  filterClient(value: string): Client[] {
    const filterValue = value.toLowerCase();
    return this.clientList.filter((client: any) => client.firstName.toLowerCase().includes(filterValue) || client.lastName.toLowerCase().includes(filterValue));
  }

  get allStateReservations(){
    return this.reservationStateService.getAllStates().subscribe((data: any) => {
      this.reservationStateList = data;
    }, error => {
      this.reservationStateList = JSON.parse(error.message).message;
    });
  }

  get allUsers(){
    return this.userService.getUsers().subscribe((data: any) => {
      this.userList = data;
    }, error => {
      this.userList = JSON.parse(error.message).message;
    });
  }

  addReservation(modalDirective: ModalDirective) {
    for(let i = 0; i < this.bookTableRows.length; i++){
      this.selectedBook.push(this.bookForm.value.bookTableRows[i].bookList);
    }

    this.userList.forEach(item => {
      if (item.email === this.currentUser.email){
        this.reservationForm.get('user').setValue(item);
      }
    });

    this.reservationService.addReservation(this.reservationForm.value).subscribe(response => {
      this.ngOnInit();
      this.alertService.alertShowSuccess();
      modalDirective.toggle();
    }, err => {
      console.log(err.message);
    })
  }
}
