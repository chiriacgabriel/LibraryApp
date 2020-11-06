import {Author} from './Author';
import {BookImageUrl} from './BookImageUrl';

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
  bookImageUrl: BookImageUrl;
}
