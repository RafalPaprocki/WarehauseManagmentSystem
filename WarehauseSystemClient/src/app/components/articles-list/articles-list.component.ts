import {Component, OnInit, TemplateRef} from '@angular/core';
import {ArticleService} from "../../services/article.service";
import {ArticleModel} from "../../model/article-model";
import {ConfirmationService, Message} from "primeng/api";
import {AutoCompleteModule} from "primeng/primeng";
import {BsModalRef} from "ngx-bootstrap/modal/bs-modal-ref.service";
import {BsModalService} from "ngx-bootstrap/modal";
import {CompartmentService} from "../../services/compartment.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-articles-list',
  templateUrl: './articles-list.component.html',
  styleUrls: ['./articles-list.component.css']
})
export class ArticlesListComponent implements OnInit {

  allArticles: Array<ArticleModel>;
  articleModel:ArticleModel;
  articleEdit:ArticleModel;
  msgs: Message[] = [];
  retStr;
  articleId;
  public modalRef: BsModalRef;
  public modal2Ref: BsModalRef;
  constructor(public articleService : ArticleService, private compartmentService: CompartmentService,
              private modalService: BsModalService, private router: Router) { }

  ngOnInit() {
    this.articleModel = new ArticleModel();
    this.articleModel.name = "";
    this.articleEdit = new ArticleModel();
    this.articleService.getAllArticles().subscribe(data => {
      this.allArticles = data.content;
      console.log(this.allArticles);
    });
  }

  filtrArticlesList(){
    console.log(this.articleModel.name);
    this.articleService.getFilteredArticles(this.articleModel).subscribe(data =>{
      this.allArticles = data;
      console.log(data);
    });
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(
      template,
      Object.assign({}, { class: 'modal-sm' })
    );
  }

  openModal2(template: TemplateRef<any>) {
    this.modal2Ref = this.modalService.show(
      template,
      Object.assign({}, { class: 'modal-sm' })
    );
  }

  aa(article){
    this.articleEdit.id = article.id;
    this.articleEdit.name = article.name;
    this.articleEdit.descryption = article.descryption;
    this.articleEdit.heigh = article.heigh;
    this.articleEdit.kind = article.kind;
    this.articleEdit.width = article.width;
    this.articleEdit.color = article.color;
    console.log(this.articleEdit)
  }

  updateArticle(){
    this.articleService.updateArticle(this.articleEdit.id.toString(), this.articleEdit).subscribe(data => {
        this.articleEdit = data;
        location.reload();
      }
    );
  }

  deleteArticle(id){
    this.articleService.deleteArticle(id.toString()).subscribe(data => {
      this.articleEdit = data;
      window.scrollTo(0,0);
      location.reload();
    })
  }

  addArticleToCompartment(id, quantity){
    this.compartmentService.addArticleToCompartment(id.toString(), quantity).subscribe(data => {
      this.retStr = data;
    })
  }

  saveCurrentArticleId(articleId){
    this.articleId = articleId;
  }

  toCompartment(number, sector){
    let sec = sector.toString().split(' ')[1];
    this.router.navigate(['/', 'detail' , number.toString(), sec]);
  }
}
