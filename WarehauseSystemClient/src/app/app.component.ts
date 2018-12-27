import {Component, TemplateRef} from '@angular/core';
import {ToastrService} from "./services/toastr.service";
import {ArticlesListComponent} from "./components/articles-list/articles-list.component";
import {ArticleService} from "./services/article.service";
import {BsModalRef} from "ngx-bootstrap/modal/bs-modal-ref.service";
import {BsModalService} from "ngx-bootstrap";
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'WarehauseSystemClient';
  filteredArticles: any[];
  articleToSearch;
  compartments;
  findArticleName;
  public modalRef: BsModalRef;
  constructor(private articleService : ArticleService, private modalService:BsModalService,
              private router: Router) {
  }

  filterCountrySingle(event) {
    this.articleService.findArticlesByIdLike(event.query).subscribe(data => {
      this.filteredArticles = data;
      console.log(this.filteredArticles)
    });

  }

  selected(event){
    this.articleToSearch = event;
    console.log(this.articleToSearch)
  }

  findArticleLocalization(article){
    this.articleService.findCompartmentsWithArticle(article.id.toString()).subscribe(data => {
      this.compartments = data;
      console.log(this.compartments)
    })
    this.findArticleName = "";

  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(
      template,
      Object.assign({}, { class: 'modal-sm' })
    );
  }

  refresh(id){
    this.router.navigateByUrl('/warehauseComponent', {skipLocationChange: true}).then(()=>
      this.router.navigate(['/', 'detail' , id.toString()]));
  }


}
