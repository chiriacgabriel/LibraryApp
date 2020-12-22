import {Component, OnInit} from '@angular/core';
import {AuthorService} from '../../_services/author.service';
import {FormControl, FormGroup} from '@angular/forms';
import {AuthorImageUrlService} from '../../_services/author-image-url.service';
import {ModalDirective} from 'ng-uikit-pro-standard';
import {Author} from '../../model/Author';
import {Router} from '@angular/router';
import {AlertsService} from '../../_services/alerts.service';
import swal from 'sweetalert';
import {ReloadPageService} from '../../_services/reload-page.service';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.scss']
})
export class AuthorComponent implements OnInit {

  authors = [];
  imageUrlList = [];
  addAuthorForm: FormGroup;
  editAuthorForm: FormGroup;
  dateForm: FormGroup;
  errorMessageAddAuthor = '';
  isAuthorAlreadyExist = false;

  constructor(private authorService: AuthorService,
              private authorImageUrl: AuthorImageUrlService,
              private alertsService: AlertsService,
              private reloadPageService: ReloadPageService,
              private sanitizer: DomSanitizer,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getAuthors();
    this.getImageUrlAuthors();
    this.formAuthor();

    this.dateForm = new FormGroup({
      dateBirth: new FormControl(''),
      deathDate: new FormControl('')
    });

  }

  getAuthors() {
    this.authorService.getAllAuthors().subscribe((data: any) => {
        this.authors = data;

        this.authors.forEach((image: any) => {
          image.authorImageUrl.imageUrl = this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/png;base64, ${image.authorImageUrl.imageUrl}`);
        });

      },
      error => {
        this.authors = JSON.parse(error.message).message;
      });
  }

  getImageUrlAuthors() {
    this.authorImageUrl.getAllImages().subscribe((data: any) => {
        this.imageUrlList = data;
      },
      error => {
        this.imageUrlList = JSON.parse(error.message).message;
      });
  }

  formAuthor() {
    this.addAuthorForm = new FormGroup({
      firstName: new FormControl(''),
      lastName: new FormControl(''),
      dateOfBirth: new FormControl(''),
      nationality: new FormControl(''),
      description: new FormControl(''),
      type: new FormControl(''),
      authorImageUrl: new FormControl('')
    });
  }

  editFormAuthor(author: Author, modalDirective: ModalDirective) {
    const authorImageSelected = this.imageUrlList.find(i => i.id == author.authorImageUrl.id);

    modalDirective.toggle();

    this.editAuthorForm = new FormGroup({
      id: new FormControl(author.id),
      firstName: new FormControl(author.firstName),
      lastName: new FormControl(author.lastName),
      dateOfBirth: new FormControl(author.dateOfBirth),
      nationality: new FormControl(author.nationality),
      description: new FormControl(author.description),
      type: new FormControl(author.type),
      bookList: new FormControl(author.bookSet),
      authorImageUrl: new FormControl(author.authorImageUrl)
    });

    this.editAuthorForm.get('authorImageUrl').setValue(authorImageSelected);

  }

  addAuthor(modalDirective: ModalDirective) {
    // tslint:disable-next-line:max-line-length
    this.addAuthorForm.controls.dateOfBirth.setValue(this.dateForm.controls.dateBirth.value + ' - ' + this.dateForm.controls.deathDate.value);

    if (this.addAuthorForm.value.authorImageUrl == '') {
      this.addAuthorForm.get('authorImageUrl').setValue(null);
    }

    this.authorService.addAuthor(this.addAuthorForm.value).subscribe(response => {
        this.isAuthorAlreadyExist = false;
        this.alertsService.success();
        modalDirective.toggle();
        this.reloadPageService.reload();
      },
      err => {
        this.errorMessageAddAuthor = err.error.message;
        this.isAuthorAlreadyExist = true;
      });
  }

  updateAuthor(modalDirective: ModalDirective): void {
    const index = this.authors.findIndex(author => author.id == this.editAuthorForm.value.id);
    this.authors[index] = this.editAuthorForm.value;
    const id = this.authors[index].id;

    this.authorService.editAuthorById(id, this.authors[index]).subscribe(response => {
      this.alertsService.success();
      this.isAuthorAlreadyExist = false;
      modalDirective.toggle();
      this.reloadPageService.reload();
    }, err => {
      this.errorMessageAddAuthor = err.error.message;
      this.isAuthorAlreadyExist = true;
    });
  }

  deleteAuthor(author: Author) {

    swal({
      title: 'Are you sure?',
      text: 'Once deleted, you will not be able to recover this registration!',
      icon: 'warning',
      buttons: ['Cancel', 'Ok'],
      dangerMode: true,
    })
      .then((willDelete) => {
        if (willDelete) {
          this.authorService.deleteAuthorById(Number(author.id)).subscribe(response => {
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

  sendToAuthorInfo(author: Author) {
    this.router.navigateByUrl('dashboard/author/' + author.id);
  }

}
