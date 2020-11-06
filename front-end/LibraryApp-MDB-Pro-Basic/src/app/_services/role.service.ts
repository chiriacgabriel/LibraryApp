import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Role} from '../model/Role';

const API_URL = 'http://localhost:8080/api/users/roles';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private http: HttpClient) { }

  getRole(): Observable<Role>{
    return this.http.get<Role>(API_URL);
  }

}
