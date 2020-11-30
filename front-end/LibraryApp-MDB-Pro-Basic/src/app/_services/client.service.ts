import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Client} from '../model/Client';

const API_URL = 'http://localhost:8080/api/clients/';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) {
  }

  getAllClients(): Observable<Client>{
    return this.http.get<Client>(API_URL);
  }

  addClient(client: Client): Observable<Client>{
    return this.http.post<Client>(API_URL,{
      firstName: client.firstName,
      lastName: client.lastName,
      email: client.email,
      phoneNumber: client.phoneNumber
    }, httpOptions);
  }

  editClientById(id: number, client: Client): Observable<Client>{
    return this.http.put<Client>(API_URL + id,{
      firstName: client.firstName,
      lastName: client.lastName,
      email: client.email,
      phoneNumber: client.phoneNumber
    },httpOptions);
  }

  getClientById(id: number){
    return this.http.get<Client>(API_URL + id);
  }

  deleteClientById(id: number): Observable<Client> {
    return this.http.delete<Client>(API_URL + id);
  }
}
