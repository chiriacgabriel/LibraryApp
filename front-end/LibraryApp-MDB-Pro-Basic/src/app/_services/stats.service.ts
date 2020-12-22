import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

const API_URL_AUTHOR = 'http://localhost:8080/api/count-data/author';
const API_URL_USER = 'http://localhost:8080/api/count-data/user';
const API_URL_BOOK = 'http://localhost:8080/api/count-data/book';
const API_URL_RESERVATION = 'http://localhost:8080/api/count-data/reservation';

@Injectable({
  providedIn: 'root'
})
export class StatsService {

  constructor(private http: HttpClient) {
  }

  getAuthorsCount(): Observable<any> {
    return this.http.get(API_URL_AUTHOR);
  }

  getUsersCount(): Observable<any> {
    return this.http.get(API_URL_USER);
  }

  getBooksCount(): Observable<any> {
    return this.http.get(API_URL_BOOK);
  }

  getReservationsCount(): Observable<any> {
    return this.http.get(API_URL_RESERVATION);
  }
}
