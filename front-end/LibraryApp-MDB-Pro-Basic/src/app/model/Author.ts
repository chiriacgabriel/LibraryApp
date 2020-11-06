import {Book} from './Book';
import {AuthorImageUrl} from './AuthorImageUrl';

export class Author{
  id: number;
  name: string;
  lastName: string;
  dateOfBirth: string;
  authorImageUrl: AuthorImageUrl;
  books: Book[];
}
