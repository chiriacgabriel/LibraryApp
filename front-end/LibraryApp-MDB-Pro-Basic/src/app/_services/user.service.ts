import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {User} from '../model/User';

const API_URL = 'http://localhost:8080/api/users/';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}

  getUsers(): Observable<any> {
    return this.http.get(API_URL);
  }

  editUserById(id: number, user): Observable<User>{
    return this.http.put<User>(API_URL + id, {
      name: user.name,
      lastName: user.lastName,
      email: user.email,
      password: user.password,
      roleSet: user.roleSet
    }, httpOptions);
  }

  getUserById(id: number){
    this.http.get(API_URL + id);
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete(API_URL + id);
  }

  uploadPhoto(id: number, user){
    this.http.post(API_URL + id, {
      profilePhoto: user.profilePhoto
    }, httpOptions);
  }

}
