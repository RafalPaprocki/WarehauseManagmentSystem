import {Component, OnInit, TemplateRef} from '@angular/core';
import {ArticleModel} from '../../model/article-model';
import {ArticleService} from "../../services/article.service";
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import { AccordionModule } from 'ngx-bootstrap/accordion'
@Component({
  selector: 'app-add-article',
  templateUrl: './add-article.component.html',
  styleUrls: ['./add-article.component.css']
})
export class AddArticleComponent implements OnInit {
  modalRef: BsModalRef;
  article: ArticleModel;
  added : boolean = false;
  public postData;

  constructor(public articleService: ArticleService, private modalService: BsModalService) {
  }
  ngOnInit() {
    this.article = new ArticleModel();
    setTimeout(function()
      {
        window.scrollTo(0,0);
      },
      100);
  }

  public addArticle(){
    console.log(this.article);
    this.articleService.sendPost(this.article).subscribe(data => {
      this.postData = data;
    });
    let a = this;
    a.added = true;

    setTimeout(function()
      {
        a.added = false;
      },
      1000);
    setTimeout(function()
      {
        location.reload();
      },
      1100);

  }



}
