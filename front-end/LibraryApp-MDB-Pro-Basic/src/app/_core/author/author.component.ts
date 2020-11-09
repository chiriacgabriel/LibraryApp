import {Component, OnInit} from '@angular/core';
import {AuthorService} from '../../_services/author.service';
import {FormControl, FormGroup} from '@angular/forms';
import {AuthorImageUrlService} from '../../_services/author-image-url.service';
import {ModalDirective} from 'ng-uikit-pro-standard';
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
  dateForm: FormGroup;

  constructor(private authorService: AuthorService,
              private authorImageUrl: AuthorImageUrlService,
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

  addAuthor(modalDirective: ModalDirective) {
    this.addAuthorForm.controls.dateOfBirth.setValue(this.dateForm.controls.dateBirth.value + ' - ' + this.dateForm.controls.deathDate.value);

    this.authorService.addAuthor(this.addAuthorForm.value).subscribe(response => {
        this.ngOnInit();
      },
      error => {
        console.log(error);
      });
    modalDirective.toggle();
  }

  sendToAuthorInfo(author: Author) {
    this.router.navigateByUrl('dashboard/author/' + author.id);
  }

}
