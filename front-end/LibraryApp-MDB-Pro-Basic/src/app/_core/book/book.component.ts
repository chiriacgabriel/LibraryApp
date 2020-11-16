import { Component, OnInit } from '@angular/core';
import {BookImageUrlService} from '../../_services/book-image-url.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.scss']
})
export class BookComponent implements OnInit {

  bookImages = [];

  constructor(private bookImageUrlService: BookImageUrlService) { }

  ngOnInit(): void {
    this.getAllBookImages();
  }

  getAllBookImages(){
    this.bookImageUrlService.getAllImageBook().subscribe((data: any) => {
        this.bookImages = data;
      },
      err =>
        this.bookImages = JSON.parse(err.message).message
    );
  }
}
