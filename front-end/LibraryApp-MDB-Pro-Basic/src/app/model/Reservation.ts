import {Book} from './Book';
import {User} from './User';
import {Client} from './Client';
import {ReservationState} from './ReservationState';

export class Reservation {
  id: number;
  bookList: Book[];
  user: User;
  client: Client;
  startDate: Date;
  endDate: Date;
  reservationState: ReservationState;
  processedDate: Date;

}
