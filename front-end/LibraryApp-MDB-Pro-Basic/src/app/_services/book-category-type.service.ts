import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Fictional} from '../model/Fictional';
import {Nonfictional} from '../model/Nonfictional';
import {BookCategory} from '../model/BookCategory';

const API_URL_FICTIONAL = 'http://localhost:8080/api/fictional/';
const API_URL_NONFICTIONAL = 'http://localhost:8080/api/nonfictional/';
const API_URL_BOOK_CATEGORY = 'http://localhost:8080/api/book-category/';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class BookCategoryTypeService {

  constructor(private http: HttpClient) {
  }

  getAllFictional(): Observable<Fictional> {
    return this.http.get<Fictional>(API_URL_FICTIONAL);
  }

  getAllNonfictional(): Observable<Nonfictional> {
    return this.http.get<Nonfictional>(API_URL_NONFICTIONAL);
  }

  getAllBookCategories(): Observable<BookCategory> {
    return this.http.get<BookCategory>(API_URL_BOOK_CATEGORY);
  }
}
