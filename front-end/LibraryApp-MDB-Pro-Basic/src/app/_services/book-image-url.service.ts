import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthorImageUrl} from '../model/AuthorImageUrl';
import {BookImageUrl} from '../model/BookImageUrl';

const API_URL = 'http://localhost:8080/api/book-image/';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class BookImageUrlService {

  constructor(private http: HttpClient) {
  }

  getAllImageBook(): Observable<BookImageUrl> {
    return this.http.get<BookImageUrl>(API_URL);
  }

  addBookImageUrl(bookImageUrl: BookImageUrl): Observable<BookImageUrl> {
    return this.http.post<BookImageUrl>(API_URL, {
      title: bookImageUrl.title,
      imageUrl: bookImageUrl.imageUrl,
    }, httpOptions);
  }

  deleteBookImageById(id: number): Observable<BookImageUrl>{
    return this.http.delete<BookImageUrl>(API_URL + id);
  }
}
