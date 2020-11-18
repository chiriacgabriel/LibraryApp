import {Component, OnInit} from '@angular/core';
import {BookImageUrlService} from '../../_services/book-image-url.service';
import {BookCategoryTypeService} from '../../_services/book-category-type.service';
import {BookService} from '../../_services/book.service';
import {ModalDirective} from 'ng-uikit-pro-standard';
import {FormControl, FormGroup} from '@angular/forms';
import {AuthorService} from "../../_services/author.service";
import {isEmpty} from "rxjs/operators";
import { Author } from 'src/app/model/Author';
import {Role} from "../../model/Role";

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.scss']
})
export class BookComponent implements OnInit {

  bookImages = [];
  bookCategories = [];
  fictionals = [];
  nonfictionals = [];
  books = [];
  authors = [];
  addBookForm: FormGroup;
  ifFictional = false;

  constructor(private bookImageUrlService: BookImageUrlService,
              private bookCategoryTypeService: BookCategoryTypeService,
              private bookService: BookService,
              private authorService: AuthorService) {
  }

  ngOnInit(): void {
    this.getAllBookImages();
    this.getAllBooks();
    this.getAllAuthors();
    this.formBook();
  }

  formBook(){
    this.addBookForm = new FormGroup({
      title: new FormControl(''),
      author: new FormControl(''),
      stock: new FormControl(''),
      bookImageUrl: new FormControl(this.bookImages)
    });
  }

  getAllBookImages() {
    this.bookImageUrlService.getAllImageBook().subscribe((data: any) => {
        this.bookImages = data;
      },
      err => {
        this.bookImages = JSON.parse(err.message).message;
      }
    );
  }

  getAllAuthors(){
    this.authorService.getAllAuthors().subscribe((data: any) => {
      this.authors = data;
    }, error => {
      this.authors = JSON.parse(error.message).message;
    });
  }

  getAllBooks(){
    this.bookService.getAllBooks().subscribe((data: any) => {
      this.books = data;
      console.log(this.books)
    }, error => {
      this.books = JSON.parse(error.message).message;
    });
  }

  addBook(modalDirective: ModalDirective){
    this.addBookForm.value.author.id
    this.bookService.addBook(this.addBookForm.value).subscribe(response => {
      this.ngOnInit();
    },
      error => {
      console.log(error);
      });
    modalDirective.toggle();
  }

}
