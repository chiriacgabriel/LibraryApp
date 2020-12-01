import {ChangeDetectionStrategy, Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {endOfDay, isSameDay, isSameMonth, parseISO, startOfDay, } from 'date-fns';
import {Subject} from 'rxjs';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarView,
} from 'angular-calendar';
import {ModalDirective} from 'ng-uikit-pro-standard';
import {FormControl, FormGroup} from '@angular/forms';

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

  reservationForm: FormGroup;

  @ViewChild('modalContent', {static: true}) modalContent: TemplateRef<any>;

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  modalData: {
    action: string;
    event: CalendarEvent;
  };

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

  activeDayIsOpen = true;

  constructor() {
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
    this.modalData = {event, action};
    // this.modal.open(this.modalContent, { size: 'lg' });
  }

  addEvent(modalDirective: ModalDirective): void {
    this.events = [
      ...this.events,
      {
        title: this.reservationForm.value.clientList,
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
    console.log(this.events);
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

  ngOnInit(): void {
    this.formReservation();
  }

  formReservation() {
    this.reservationForm = new FormGroup({
      bookList: new FormControl(''),
      userList: new FormControl(''),
      clientList: new FormControl(''),
      startDate: new FormControl(''),
      endDate: new FormControl('')
    });
  }
}
