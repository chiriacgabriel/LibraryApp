import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ReservationState} from '../model/ReservationState';

const API_URL = 'http://localhost:8080/api/state/';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ReservationStateService {

  constructor(private http: HttpClient) {
  }

  getAllStates(): Observable<ReservationState> {
    return this.http.get<ReservationState>(API_URL);
  }
}
