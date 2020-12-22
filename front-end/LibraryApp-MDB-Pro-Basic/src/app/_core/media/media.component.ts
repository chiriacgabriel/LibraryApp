import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthorImageUrlService} from '../../_services/author-image-url.service';
import {ModalDirective} from 'ng-uikit-pro-standard';
import {FormControl, FormGroup} from '@angular/forms';
import {AuthorImageUrl} from '../../model/AuthorImageUrl';
import {BookImageUrlService} from '../../_services/book-image-url.service';
import {BookImageUrl} from '../../model/BookImageUrl';
import {AlertsService} from '../../_services/alerts.service';
import {ReloadPageService} from '../../_services/reload-page.service';
import swal from 'sweetalert';
import {DomSanitizer} from '@angular/platform-browser';
import {NavigationEnd, Router} from '@angular/router';

@Component({
  selector: 'app-media',
  templateUrl: './media.component.html',
  styleUrls: ['./media.component.scss']
})
export class MediaComponent implements OnInit, OnDestroy {

  authorImages = [];
  authorImageForm: FormGroup;
  errorMessageAuthor = '';
  isAuthorTitlePresent = false;

  bookImages = [];
  bookImageForm: FormGroup;
  errorMessageBook = '';
  isBookTitlePresent = false;

  selectedFiles: FileList;
  currentFile: File;

  bookImageSubscription: any;
  authorImageSubscription: any;
  navigationSubscription: any;

  constructor(private authorImageUrlService: AuthorImageUrlService,
              private bookImageUrlService: BookImageUrlService,
              private alertsService: AlertsService,
              private reloadPageService: ReloadPageService,
              private sanitizer: DomSanitizer,
              private router: Router) {
    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      if (e instanceof NavigationEnd) {
        this.getAllBookImages();
        this.getAllAuthorImages();
      }
    });
  }

  get authorTitle() {
    return this.authorImageForm.get('title');
  }

  ngOnInit(): void {
    this.authorForm();
    this.bookForm();
  }

  ngOnDestroy() {
    if (this.bookImageSubscription) {
      this.bookImageSubscription.unsubscribe();
    }
    if (this.authorImageSubscription) {
      this.authorImageSubscription.unsubscribe();
    }
  }

  authorForm() {
    this.authorImageForm = new FormGroup({
      title: new FormControl(''),
      // imageUrl: new FormControl('')
    });
  }

  getAllAuthorImages() {
    this.authorImageUrlService.getAllImages().subscribe((data: any) => {
        this.authorImages = data;

        this.authorImages.forEach((imageAuthor: any) => {
          imageAuthor.imageUrl = this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/png;base64, ${imageAuthor.imageUrl}`);
        });

      },
      error => {
        this.authorImages = JSON.parse(error.message).message;
      }
    );
  }

  addAuthorImage(modalDirective: ModalDirective) {
    this.currentFile = this.selectedFiles.item(0);

    this.authorImageSubscription = this.authorImageUrlService.createImageUrl(this.authorImageForm.value.title, this.currentFile).subscribe(response => {
        modalDirective.toggle();
        this.reloadPageService.reload();
        this.alertsService.success();
        this.isAuthorTitlePresent = false;
      },
      err => {
        this.errorMessageAuthor = err.error.message;
        this.isAuthorTitlePresent = true;
      });
  }

  deleteAuthorImageById(authorImageUrl: AuthorImageUrl) {

    swal({
      title: 'Are you sure?',
      text: 'Once deleted, you will not be able to recover this registration!',
      icon: 'warning',
      buttons: ['Cancel', 'Ok'],
      dangerMode: true,
    })
      .then((willDelete) => {
        if (willDelete) {
          this.authorImageUrlService.deleteImageById(authorImageUrl.id).subscribe(response => {
              this.alertsService.warning();
              this.reloadPageService.reload();
            },
            error => {
              console.log(error);
            }
          );
          swal('Your registration/file has been deleted!', {
            icon: 'success',
          });
        }
      });

  }

  bookForm() {
    this.bookImageForm = new FormGroup({
      title: new FormControl(''),
      // imageUrl: new FormControl('')
    });
  }

  getAllBookImages() {

    this.bookImageUrlService.getAllImageBook().subscribe((data: any) => {
        this.bookImages = data;

        for (let i = 0; i < data.length; i++) {
          this.bookImages[i].imageUrl = this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/png;base64, ${data[i].imageUrl}`);
        }
      },
      err =>
        this.bookImages = JSON.parse(err.message).message
    );
  }

  uploadImage(event) {
    this.selectedFiles = event.target.files;
  }


  addBookImage(modalDirective: ModalDirective) {
    this.currentFile = this.selectedFiles.item(0);

    this.bookImageSubscription = this.bookImageUrlService.addBookImageUrl(this.bookImageForm.value.title, this.currentFile).subscribe(response => {
        modalDirective.toggle();
        this.reloadPageService.reload();
        this.alertsService.success();
        this.isBookTitlePresent = false;
      },
      err => {
        this.errorMessageBook = err.error.message;
        this.isBookTitlePresent = true;
      });
  }

  deleteBookImageById(bookImageUrl: BookImageUrl) {

    swal({
      title: 'Are you sure?',
      text: 'Once deleted, you will not be able to recover this registration!',
      icon: 'warning',
      buttons: ['Cancel', 'Ok'],
      dangerMode: true,
    })
      .then((willDelete) => {
        if (willDelete) {
          this.bookImageUrlService.deleteBookImageById(bookImageUrl.id).subscribe(response => {
            this.alertsService.warning();
            this.reloadPageService.reload();
          }, error => {
            console.log(error);
          });
          swal('Your registration/file has been deleted!', {
            icon: 'success',
          });
        }
      });

  }


}
