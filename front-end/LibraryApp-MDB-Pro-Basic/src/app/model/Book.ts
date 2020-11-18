import {Author} from './Author';
import {BookImageUrl} from './BookImageUrl';
import {BookCategory} from './BookCategory';
import {Fictional} from './Fictional';
import {Nonfictional} from './Nonfictional';

export class Book{
  id: string;
  title: string;
  author: Author;
  clients: any;
  stock: number;
  reservationList: any;
  reviewList: any;
  bookCategory: BookCategory;
  fictional: Fictional;
  nonfictional: Nonfictional;
  bookImageUrl: BookImageUrl;
}
