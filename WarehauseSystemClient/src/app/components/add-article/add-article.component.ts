import { Component, OnInit } from '@angular/core';
import {ArticleModel} from '../../model/article-model';

@Component({
  selector: 'app-add-article',
  templateUrl: './add-article.component.html',
  styleUrls: ['./add-article.component.css']
})
export class AddArticleComponent implements OnInit {

  constructor() { }
  article: ArticleModel;
  ngOnInit() {
  }
  public addArticle(){
    console.log(this.article);
  }
}
