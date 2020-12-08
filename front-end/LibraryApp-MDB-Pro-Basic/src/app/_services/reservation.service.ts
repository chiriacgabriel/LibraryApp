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

  getAllReservation(): Observable<Reservation> {
    return this.http.get<Reservation>(API_URL);
  }

  getReservationById(id: number): Observable<Reservation> {
    return this.http.get<Reservation>(API_URL + id);
  }

  addReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.post<Reservation>(API_URL, {
      bookList: reservation.bookList,
      user: reservation.user,
      client: reservation.client,
      startDate: reservation.startDate,
      endDate: reservation.endDate,
      reservationState: reservation.reservationState
    }, httpOptions);
  }

  editReservationById(id: number, reservation: Reservation): Observable<Reservation> {
    return this.http.put<Reservation>(API_URL + id, {
      bookList: reservation.bookList,
      user: reservation.user,
      client: reservation.client,
      startDate: reservation.startDate,
      endDate: reservation.endDate,
      reservationState: reservation.reservationState
    }, httpOptions);
  }

  deleteReservationById(id: number): Observable<Reservation> {
    return this.http.delete<Reservation>(API_URL + id);
  }


}
