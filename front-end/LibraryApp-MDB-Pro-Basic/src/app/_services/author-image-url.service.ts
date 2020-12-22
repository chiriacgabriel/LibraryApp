import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpRequest} from '@angular/common/http';
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

  createImageUrl(title: string, file: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('title', title);
    formData.append('file', file);

    const req = new HttpRequest('POST', API_URL, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  deleteImageById(id: number): Observable<AuthorImageUrl> {
    return this.http.delete<AuthorImageUrl>(API_URL + id);
  }
}
