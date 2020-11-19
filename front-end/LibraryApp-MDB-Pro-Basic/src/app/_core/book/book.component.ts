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
import {dashCaseToCamelCase} from "@angular/compiler/src/util";
import {BookCategory} from "../../model/BookCategory";

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

  isFictional = false;
  isNonfictional = false;

  constructor(private bookImageUrlService: BookImageUrlService,
              private bookCategoryTypeService: BookCategoryTypeService,
              private bookService: BookService,
              private authorService: AuthorService) {
  }

  ngOnInit(): void {
    this.getAllBookImages();
    this.getAllBooks();
    this.getAllAuthors();
    this.getAllBookCategories();
    this.getAllFictionals();
    this.getAllNonfictionals();
    this.formBook();
  }

  formBook(){
    this.addBookForm = new FormGroup({
      title: new FormControl(''),
      author: new FormControl(''),
      stock: new FormControl(''),
      bookImageUrl: new FormControl(this.bookImages),
      bookCategory: new FormControl(this.bookCategories),
      fictional: new FormControl(this.fictionals),
      nonfictional: new FormControl(this.nonfictionals)
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
    }, error => {
      this.books = JSON.parse(error.message).message;
    });
  }

  getAllBookCategories(){
    this.bookCategoryTypeService.getAllBookCategories().subscribe((data: any) => {
      this.bookCategories = data;
    }, error => {
      this.bookCategories = JSON.parse(error.message).message;
    });
  }

  getAllFictionals(){
    this.bookCategoryTypeService.getAllFictional().subscribe((data: any) => {
      this.fictionals = data
    }, error => {
      this.fictionals = JSON.parse(error.message).message;
    });
  }

  getAllNonfictionals(){
    this.bookCategoryTypeService.getAllNonfictional().subscribe((data: any) => {
      this.nonfictionals = data;
    },
      error => {
      this.nonfictionals = JSON.parse(error.message).message;
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

  isFictionalSelected(bookCategory: BookCategory){
    if (bookCategory.nameOfBookCategory == 'Fiction'){
      this.isFictional = true;
      this.isNonfictional = false;
    }

    if (bookCategory.nameOfBookCategory == 'Nonfiction'){
      this.isFictional = false;
      this.isNonfictional = true;
    }
  }
}
