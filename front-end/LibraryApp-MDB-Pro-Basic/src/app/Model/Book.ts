import {Author} from './Author';

export class Book{
  id: string;
  title: string;
  author: Author;
  clients: any;
  stock: number;
  reservationList: any;
  reviewList: any;
  bookCategory: string;
  typeOkBook: string;
}
