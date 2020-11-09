import {Book} from './Book';
import {AuthorImageUrl} from './AuthorImageUrl';

export class Author{
  id: number;
  name: string;
  lastName: string;
  dateOfBirth: string;
  nationality: string;
  description: string;
  type: string;
  authorImageUrl: AuthorImageUrl;
  books: Book[];
}
