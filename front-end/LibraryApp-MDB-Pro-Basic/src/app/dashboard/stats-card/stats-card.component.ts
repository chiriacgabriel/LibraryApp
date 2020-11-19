import {Component, OnInit} from '@angular/core';
import {AuthorService} from '../../_services/author.service';
import {UserService} from '../../_services/user.service';
import {BookService} from "../../_services/book.service";

@Component({
  selector: 'app-stats-card',
  templateUrl: './stats-card.component.html',
  styleUrls: ['./stats-card.component.scss']
})
export class StatsCardComponent implements OnInit {
  authors = [];
  users = [];
  books = [];

  constructor(private authorService: AuthorService,
              private userService: UserService,
              private bookService: BookService) {
  }

  ngOnInit(): void {
    this.getAuthorsForStatCard();
    this.getUsersForStatCard();
    this.getBookForStatCard();
  }

  getAuthorsForStatCard() {
    this.authorService.getAllAuthors().subscribe((data: any) => {
        this.authors = data;
      },
      error => {
        this.authors = JSON.parse(error.message).message;
      });

  }

  getUsersForStatCard() {
    this.userService.getUsers().subscribe((data: any) => {
        this.users = data;
      },
      error => {
        this.users = JSON.parse(error.message).message;
      });
  }

  getBookForStatCard() {
    this.bookService.getAllBooks().subscribe((data: any) => {
        this.books = data;
      },
      error => {
        this.books = JSON.parse(error.message).message;
      });
  }
}
