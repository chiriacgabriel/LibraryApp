import {Component, OnInit} from '@angular/core';
import {AuthorImageUrlService} from '../../_services/author-image-url.service';
import {ModalDirective} from 'ng-uikit-pro-standard';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-media',
  templateUrl: './media.component.html',
  styleUrls: ['./media.component.scss']
})
export class MediaComponent implements OnInit {

  authorImages = [];
  authorImageForm: FormGroup;

  constructor(private authorImageUrlService: AuthorImageUrlService) {
  }

  ngOnInit(): void {
    this.getAllAuthorImages();
    this.authorForm();

  }

  authorForm() {
    this.authorImageForm = new FormGroup({
      title: new FormControl(''),
      imageUrl: new FormControl('')
    });
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
      },
      error => {
        console.log(error);
      });


    modalDirective.toggle();
  }
}
