import {Reservation} from './Reservation';

export class Client {
  id: number;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  email: string;
  reservationList: Reservation;
}
