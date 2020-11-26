import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Reservation} from '../model/Reservation';

const API_URL = 'http://localhost:8080/api/reservations/';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) {
  }

  getAllReservations(): Observable<Reservation> {
    return this.http.get<Reservation>(API_URL);
  }

  getReservationById(id: number): Observable<Reservation> {
    return this.http.get<Reservation>(API_URL + id);
  }

  addReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.post<Reservation>(API_URL, {
      bookList: reservation.bookList,
      userList: reservation.userList,
      clientList: reservation.clientList,
      startDate: reservation.startDate,
      endDate: reservation.endDate
    }, httpOptions);
  }

  editReservationById(id: number, reservation: Reservation): Observable<Reservation> {
    return this.http.put<Reservation>(API_URL + id, {
      bookList: reservation.bookList,
      userList: reservation.userList,
      clientList: reservation.clientList,
      startDate: reservation.startDate,
      endDate: reservation.endDate
    }, httpOptions);
  }

  deleteReservationById(id: number): Observable<Reservation> {
    return this.http.delete<Reservation>(API_URL + id);
  }


}
