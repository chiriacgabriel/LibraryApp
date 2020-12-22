import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

const API_URL = 'http://localhost:8080/api/book-image/';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class BookImageUrlService {

  constructor(private http: HttpClient) {
  }

  getAllImageBook(): Observable<any> {
    return this.http.get(API_URL);
  }

  addBookImageUrl(title: string, file: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('title', title);

    const req = new HttpRequest('POST', API_URL, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  deleteBookImageById(id: number): Observable<any> {
    return this.http.delete(API_URL + id);
  }

}
