import {Component, OnInit, TemplateRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ArticleService} from '../../services/article.service';
import {BsModalService} from "ngx-bootstrap/modal";
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import {CompartmentService} from "../../services/compartment.service";

@Component({
  selector: 'app-detail-product',
  templateUrl: './detail-product.component.html',
  styleUrls: ['./detail-product.component.css']
})
export class DetailProductComponent implements OnInit {
  public articles;
  public modalRef: BsModalRef;
  public compartment;
  public sector;
  public displayContent = true;
  constructor(private route: ActivatedRoute,
              private articleService: ArticleService,
              private modalService: BsModalService,
              private compartmentService: CompartmentService,
              private router: Router) { }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.sector = this.route.snapshot.paramMap.get('sector');
    this.articleService.id = id;
    debugger
    this.articleService.getArticleInCompartment(this.sector, this.articleService.id.toString()).subscribe(data => {
      this.articles = data;
    });

    this.compartmentService.getCompartmentBySectorAndNumber(this.articleService.id.toString(), this.sector).subscribe(data => {
      this.compartment = data;
      console.log(this.compartment)
    })
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(
      template,
      Object.assign({}, { class: 'modal-sm' })
    );
  }

  updateArticleQuantity(quantity){
    this.compartmentService.updateDefinedArticleQuantity(
      quantity, this.articleService.id.toString(), this.sector).subscribe( data =>{
        this.compartment.articleQuantity = data.articleQuantity;
        this.compartment = data;
        this.articles = this.compartment.article;
      }
    );
  }

  addToEmptyCompartment(){
    this.displayContent = false;
  }

  addArticleToThisCompartment(data){
    this.displayContent = true;
    console.log(this.compartment.number)
    console.log(data.id)
    this.compartmentService.updateCompartmentWithArticle(data.quantity,
     data.id,this.compartment.id).subscribe(data => {
        this.compartment = data;
        this.articles = data.article;
    });

  }
}
