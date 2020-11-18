import {Component, OnInit} from '@angular/core';
import {AuthorService} from '../../_services/author.service';
import {FormControl, FormGroup} from '@angular/forms';
import {AuthorImageUrlService} from '../../_services/author-image-url.service';
import {ModalDirective, ToastService} from 'ng-uikit-pro-standard';
import {Author} from '../../model/Author';
import {Router} from '@angular/router';

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

  constructor(private authorService: AuthorService,
              private authorImageUrl: AuthorImageUrlService,
              private toast: ToastService,
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
      name: new FormControl(''),
      lastName: new FormControl(''),
      dateOfBirth: new FormControl(''),
      nationality: new FormControl(''),
      description: new FormControl(''),
      type: new FormControl(''),
      authorImageUrl: new FormControl('')
    });
  }

  editFormAuthor(author: Author, modalDirective: ModalDirective) {
    modalDirective.toggle();

    this.editAuthorForm = new FormGroup({
      id: new FormControl(author.id),
      name: new FormControl(author.name),
      lastName: new FormControl(author.lastName),
      dateOfBirth: new FormControl(author.dateOfBirth),
      nationality: new FormControl(author.nationality),
      description: new FormControl(author.description),
      type: new FormControl(author.type),
      bookList: new FormControl(author.bookSet),
      authorImageUrl: new FormControl(author.authorImageUrl)
    });
  }

  addAuthor(modalDirective: ModalDirective) {
    // tslint:disable-next-line:max-line-length
    this.addAuthorForm.controls.dateOfBirth.setValue(this.dateForm.controls.dateBirth.value + ' - ' + this.dateForm.controls.deathDate.value);

    this.authorService.addAuthor(this.addAuthorForm.value).subscribe(response => {
        this.ngOnInit();
        this.alertShowSuccess();
        modalDirective.toggle();
      },
      error => {
        console.log(error);
      });
  }

  updateAuthor(modalDirective: ModalDirective): void {
    modalDirective.toggle();
    const index = this.authors.findIndex(author => author.id == this.editAuthorForm.value.id);
    this.authors[index] = this.editAuthorForm.value;
    const id = this.authors[index].id;

    this.authorService.editAuthorById(id, this.authors[index]).subscribe(response => {
      this.ngOnInit();
    }, error => {
      console.log(error);
    });
  }

  deleteAuthor(author: Author) {
    if (window.confirm('Are you sure you want to delete this Author ?')) {
      const index = this.authors.findIndex(obj => obj.id = author.id);
      const id = this.authors[index].id;

      this.authorService.deleteAuthorById(id).subscribe(response => {
          this.ngOnInit();
        },
        error => {
          console.log(error);
        });
      this.alertShowWarning();
    }
  }

  sendToAuthorInfo(author: Author) {
    this.router.navigateByUrl('dashboard/author/' + author.id);
  }

  alertShowSuccess(){
    const options = {extendedTimeOut: 4500};
    this.toast.success('Action performed successfully', '', options);
  }

  alertShowWarning(){
    const options = {extendedTimeOut: 4500};
    this.toast.warning('Delete was successful', '', options);
  }
}
