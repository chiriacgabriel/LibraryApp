import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Book} from '../model/Book';

const API_URL = 'http://localhost:8080/api/books/';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) {
  }

  getAllBooks(): Observable<Book> {
    return this.http.get<Book>(API_URL);
  }

  getBookById(id: number): Observable<Book>{
    return this.http.get<Book>(API_URL + id);
  }
  addBook(book: Book): Observable<Book> {
    return this.http.post<Book>(API_URL, {
      title: book.title,
      author: book.author,
      stock: book.stock,
      bookImageUrl: book.bookImageUrl,
      bookCategory: book.bookCategory,
      fictional: book.fictional,
      nonfictional: book.nonfictional
    }, httpOptions);
  }

  editBookById(id: number, book: Book): Observable<Book>{
    return this.http.put<Book>(API_URL + id, {
      title: book.title,
      author: book.author,
      stock: book.stock,
      bookImageUrl: book.bookImageUrl,
      bookCategory: book.bookCategory,
      fictional: book.fictional,
      nonfictional: book.nonfictional
    }, httpOptions);
  }

  deleteBookById(id: number): Observable<Book>{
    return this.http.delete<Book>(API_URL + id);
  }
}
