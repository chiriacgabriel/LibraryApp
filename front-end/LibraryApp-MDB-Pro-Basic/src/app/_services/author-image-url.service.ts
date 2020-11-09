import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthorImageUrl} from '../model/AuthorImageUrl';

const API_URL = 'http://localhost:8080/api/author-image/';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthorImageUrlService {

  constructor(private http: HttpClient) {
  }

  getAllImages(): Observable<AuthorImageUrl> {
    return this.http.get<AuthorImageUrl>(API_URL);
  }

  createImageUrl(authorImageUrl: AuthorImageUrl): Observable<AuthorImageUrl> {
    return this.http.post<AuthorImageUrl>(API_URL, {
      title: authorImageUrl.title,
      imageUrl: authorImageUrl.imageUrl,
    }, httpOptions);
  }
}
