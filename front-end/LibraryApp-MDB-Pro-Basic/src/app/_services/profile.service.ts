import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../model/User';

const API_URL = 'http://localhost:8080/api/users/';
const API_URL_PROFILE_IMAGE = 'http://localhost:8080/api/profile-image/';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) {
  }

  editUserByIdProfileImage(id: number, user): Observable<User> {
    return this.http.put<User>(API_URL + id, {
      name: user.name,
      lastName: user.lastName,
      email: user.email,
      password: user.password,
      roleSet: user.roleSet,
      profilePhoto: user.profilePhoto
    }, httpOptions);
  }

  uploadProfileImage(file: File, id: number): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file);


    const req = new HttpRequest('POST', API_URL_PROFILE_IMAGE + id, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  getProfileImageByUserId(id: number) {
    return this.http.get(API_URL_PROFILE_IMAGE + id);
  }

}
