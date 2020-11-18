import {Component, OnInit} from '@angular/core';
import {AuthorService} from '../../../_services/author.service';
import {ActivatedRoute} from '@angular/router';
import {Author} from '../../../model/Author';
import {AuthorImageUrl} from '../../../model/AuthorImageUrl';
import {BookService} from '../../../_services/book.service';

@Component({
  selector: 'app-author-details',
  templateUrl: './author-details.component.html',
  styleUrls: ['./author-details.component.scss']
})
export class AuthorDetailsComponent implements OnInit {

  author: Author;

  constructor(private authorService: AuthorService,
              private activatedRoute: ActivatedRoute) {
    this.author = new Author();
    this.author.authorImageUrl = new AuthorImageUrl();
  }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    this.authorService.getAuthorById(Number(id)).subscribe(author => this.author = author);
  }



}
