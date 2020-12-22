import {Component, OnInit} from '@angular/core';
import {AuthorService} from '../../../_services/author.service';
import {ActivatedRoute} from '@angular/router';
import {Author} from '../../../model/Author';
import {AuthorImageUrl} from '../../../model/AuthorImageUrl';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-author-details',
  templateUrl: './author-details.component.html',
  styleUrls: ['./author-details.component.scss']
})
export class AuthorDetailsComponent implements OnInit {

  author: Author;

  constructor(private authorService: AuthorService,
              private activatedRoute: ActivatedRoute,
              private sanitizer: DomSanitizer) {
    this.author = new Author();
    this.author.authorImageUrl = new AuthorImageUrl();
  }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    this.authorService.getAuthorById(Number(id)).subscribe(author => {
      this.author = author;
      this.author.authorImageUrl.imageUrl = this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/png;base64, ${this.author.authorImageUrl.imageUrl}`);
      this.author.bookSet.forEach((image: any) => {
        image.bookImageUrl.imageUrl = this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/png;base64, ${image.bookImageUrl.imageUrl}`);
      });
    });
  }


}
