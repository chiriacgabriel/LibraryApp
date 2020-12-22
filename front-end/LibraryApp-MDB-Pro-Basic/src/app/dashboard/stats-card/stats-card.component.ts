import {Component, OnInit} from '@angular/core';
import {StatsService} from '../../_services/stats.service';

@Component({
  selector: 'app-stats-card',
  templateUrl: './stats-card.component.html',
  styleUrls: ['./stats-card.component.scss']
})
export class StatsCardComponent implements OnInit {

  countAuthors: number;
  countUsers: number;
  countBooks: number;
  countReservations: number;

  constructor(private statsService: StatsService) {
  }

  ngOnInit(): void {
    this.getCountOfAuthors();
    this.getCountOfUsers();
    this.getCountOfBooks();
    this.getCountOfReservations();

  }

  getCountOfAuthors() {
    this.statsService.getAuthorsCount().subscribe((data: any) => {
      this.countAuthors = data;
    }, error => {
      this.countAuthors = JSON.parse(error.message).message;
    });
  }

  getCountOfUsers() {
    this.statsService.getUsersCount().subscribe((data: any) => {
      this.countUsers = data;
    }, error => {
      this.countUsers = JSON.parse(error.message).message;
    });
  }

  getCountOfBooks() {
    this.statsService.getBooksCount().subscribe((data: any) => {
      this.countBooks = data;
    }, error => {
      this.countBooks = JSON.parse(error.message).message;
    });
  }

  getCountOfReservations() {
    this.statsService.getReservationsCount().subscribe((data: any) => {
      this.countReservations = data;
    }, error => {
      this.countReservations = JSON.parse(error.message).message;
    });
  }


}
