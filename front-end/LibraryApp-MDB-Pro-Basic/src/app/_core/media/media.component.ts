import {Component, OnInit} from '@angular/core';
import {AuthorImageUrlService} from '../../_services/author-image-url.service';
import {ModalDirective, ToastService} from 'ng-uikit-pro-standard';
import {FormControl, FormGroup} from '@angular/forms';
import {AuthorImageUrl} from '../../model/AuthorImageUrl';
import {BookImageUrlService} from '../../_services/book-image-url.service';
import {BookImageUrl} from "../../model/BookImageUrl";
import {AlertsService} from "../../_services/alerts.service";

@Component({
  selector: 'app-media',
  templateUrl: './media.component.html',
  styleUrls: ['./media.component.scss']
})
export class MediaComponent implements OnInit {

  authorImages = [];
  authorImageForm: FormGroup;
  errorMessageAuthor = '';
  isAuthorTitlePresent = false;

  bookImages = [];
  bookImageForm: FormGroup;
  errorMessageBook = '';
  isBookTitlePresent = false;

  constructor(private authorImageUrlService: AuthorImageUrlService,
              private bookImageUrlService: BookImageUrlService,
              private alertsService: AlertsService) {
  }

  ngOnInit(): void {
    this.getAllAuthorImages();
    this.authorForm();

    this.bookForm();
    this.getAllBookImages();

  }

  authorForm() {
    this.authorImageForm = new FormGroup({
      title: new FormControl(''),
      imageUrl: new FormControl('')
    });
  }

  get authorTitle() {
    return this.authorImageForm.get('title');
  }

  getAllAuthorImages() {
    this.authorImageUrlService.getAllImages().subscribe((data: any) => {
        this.authorImages = data;
      },
      error => {
        this.authorImages = JSON.parse(error.message).message;
      }
    );
  }

  addAuthorImage(modalDirective: ModalDirective) {
    this.authorImageUrlService.createImageUrl(this.authorImageForm.value).subscribe(response => {
        this.ngOnInit();
        modalDirective.toggle();
        this.alertsService.alertShowSuccess();
        this.isAuthorTitlePresent = false;
      },
      err => {
        this.errorMessageAuthor = err.error.message;
        this.isAuthorTitlePresent = true;
      });
  }

  deleteAuthorImageById(authorImageUrl: AuthorImageUrl) {
    if (window.confirm('Are you sure you want to delete this image ?')) {
      this.authorImageUrlService.deleteImageById(authorImageUrl.id).subscribe(response => {
          this.ngOnInit();
          this.alertsService.alertShowWarning();
        },
        error => {
          console.log(error);
        }
      );
    }
  }

  bookForm() {
    this.bookImageForm = new FormGroup({
      title: new FormControl(''),
      imageUrl: new FormControl('')
    });
  }

  getAllBookImages(){
    this.bookImageUrlService.getAllImageBook().subscribe((data: any) => {
      this.bookImages = data;
    },
      err =>
    this.bookImages = JSON.parse(err.message).message
    );
  }

  addBookImage(modalDirective: ModalDirective){
    this.bookImageUrlService.addBookImageUrl(this.bookImageForm.value).subscribe(response => {
      this.ngOnInit();
      modalDirective.toggle();
      this.alertsService.alertShowSuccess();
      this.isBookTitlePresent = false;
    },
      err =>{
      this.errorMessageBook = err.error.message;
      this.isBookTitlePresent = true;
      });
  }

  deleteBookImageById(bookImageUrl: BookImageUrl){
    if (window.confirm('Are you sure you want to delete this image ?')){
      this.bookImageUrlService.deleteBookImageById(bookImageUrl.id).subscribe(response => {
        this.ngOnInit();
        this.alertsService.alertShowWarning()
      }, error => {
        console.log(error);
      });
    }
  }

}
