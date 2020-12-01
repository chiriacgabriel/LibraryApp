import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-reservation-header',
  templateUrl: './reservation-header.component.html',
  styleUrls: ['./reservation-header.component.scss']
})
export class ReservationHeaderComponent implements OnInit {

  @Input() view: string;
  @Input() viewDate: Date;
  @Input() locale = 'en';
  @Output() viewChange: EventEmitter<string> = new EventEmitter();
  @Output() viewDateChange: EventEmitter<Date> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

}
