import {Component, HostListener, OnInit, ViewChild} from '@angular/core';
import {MdbTableDirective} from "ng-uikit-pro-standard";

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.scss'],
})
export class ReservationComponent implements OnInit {
  @ViewChild(MdbTableDirective, {static: true}) mdbTable: MdbTableDirective;
  elements: any = [];
  headElements = ['ID', 'First', 'Last', 'Handle'];
  searchText: string = '';
  previous: string;

  constructor() { }

  ngOnInit(): void {
    for (let i = 1; i <= 10; i++) {
      this.elements.push({
        id:i.toString(),
        first: 'Wpis' + (Math.floor(Math.random() * i * 10)).toString(),
        last: 'Last' + (Math.floor(Math.random() * i * 10)).toString(),
        handle: 'Handle' + (Math.floor(Math.random() * i * 10)).toString()
      });
    }
    this.mdbTable.setDataSource(this.elements);
    this.previous = this.mdbTable.getDataSource();
  }

  @HostListener('input') oninput() {
    this.searchItems();
  }

  searchItems() {
    const prev = this.mdbTable.getDataSource();
    if (!this.searchText) {
      this.mdbTable.setDataSource(this.previous);
      this.elements = this.mdbTable.getDataSource();
    }
    if (this.searchText) {
      this.elements = this.mdbTable.searchLocalDataByMultipleFields(this.searchText, ['first', 'last']);
      this.mdbTable.setDataSource(prev);
    }
  }

}
