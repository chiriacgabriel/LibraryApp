import {Book} from './Book';
import {User} from './User';
import {Client} from './Client';

export class Reservation {
  id: number;
  bookList: Book[];
  userList: User[];
  clientList: Client[];
  startDate: string;
  endDate: string;

}
