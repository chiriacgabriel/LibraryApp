import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Author} from '../model/Author';

const API_URL = 'http://localhost:8080/api/authors/';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  constructor(private http: HttpClient) {
  }

  getAllAuthors(): Observable<Author> {
    return this.http.get<Author>(API_URL);
  }

  addAuthor(author: Author): Observable<Author> {
    return this.http.post<Author>(API_URL, {
      firstName: author.firstName,
      lastName: author.lastName,
      dateOfBirth: author.dateOfBirth,
      nationality: author.nationality,
      description: author.description,
      type: author.type,
      authorImageUrl: author.authorImageUrl
    }, httpOptions);
  }

  editAuthorById(id: number, author): Observable<Author> {
    return this.http.put<Author>(API_URL + id, {
      firstName: author.firstName,
      lastName: author.lastName,
      dateOfBirth: author.dateOfBirth,
      nationality: author.nationality,
      description: author.description,
      type: author.type,
      authorImageUrl: author.authorImageUrl
    }, httpOptions);
  }

  getAuthorById(id: number) {
    return this.http.get<Author>(API_URL + id);
  }

  deleteAuthorById(id: number): Observable<Author> {
    return this.http.delete<Author>(API_URL + id);
  }
}
