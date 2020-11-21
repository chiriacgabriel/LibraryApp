import {Component, OnInit} from '@angular/core';
import {BookImageUrlService} from '../../_services/book-image-url.service';
import {BookCategoryTypeService} from '../../_services/book-category-type.service';
import {BookService} from '../../_services/book.service';
import {ModalDirective, ToastService} from 'ng-uikit-pro-standard';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthorService} from "../../_services/author.service";
import {isEmpty} from "rxjs/operators";
import { Author } from 'src/app/model/Author';
import {Role} from "../../model/Role";
import {dashCaseToCamelCase} from "@angular/compiler/src/util";
import {BookCategory} from "../../model/BookCategory";
import {BookImageUrl} from "../../model/BookImageUrl";
import {AlertsService} from "../../_services/alerts.service";
import { Book } from 'src/app/model/Book';

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
  editBookForm: FormGroup;

  isFictional = false;
  isNonfictional = false;

  preSelectedTitle = ''

  constructor(private bookImageUrlService: BookImageUrlService,
              private bookCategoryTypeService: BookCategoryTypeService,
              private bookService: BookService,
              private authorService: AuthorService,
              private alertsService: AlertsService) {
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
      author: new FormControl(),
      stock: new FormControl('',Validators.required),
      bookImageUrl: new FormControl(),
      bookCategory: new FormControl(),
      fictional: new FormControl(),
      nonfictional: new FormControl()
    });
  }

  editForm(book: Book, modalDirective: ModalDirective){
    modalDirective.toggle();

    this.editBookForm = new FormGroup({
      id: new FormControl(book.id),
      title: new FormControl(book.title),
      author: new FormControl(book.author),
      stock: new FormControl(book.stock),
      bookImageUrl: new FormControl(book.bookImageUrl.title),
      bookCategory: new FormControl(book.bookCategory),
      fictional: new FormControl(book.fictional),
      nonfictional: new FormControl(book.nonfictional)
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
    this.bookService.addBook(this.addBookForm.value).subscribe(response => {
      this.ngOnInit();
      this.alertsService.alertShowSuccess();
      modalDirective.toggle();
    },
      error => {
      console.log(error);
      });
  }

  updateBook(modalDirective: ModalDirective): void{
    modalDirective.toggle();

  }

  deleteBook(book: Book){
    if (window.confirm("Are you sure you want to delete " + String(book.title) + " ?")){
      this.bookService.deleteBookById(Number(book.id)).subscribe(response => {
        this.ngOnInit();
        this.alertsService.alertShowWarning();
      },
        error => {
        console.log(error);
        });
    }
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
  getPreSelectedTitle(bookImageUrl: BookImageUrl){
    this.preSelectedTitle = bookImageUrl.title;
  }

}
