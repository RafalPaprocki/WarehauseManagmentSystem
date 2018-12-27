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

  constructor(private route: ActivatedRoute,
              private articleService: ArticleService,
              private modalService: BsModalService,
              private compartmentService: CompartmentService,
              private router: Router) { }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.articleService.id = id;
    this.articleService.getArticleInCompartment(this.articleService.id.toString()).subscribe(data => {
      this.articles = data;
    });

    this.compartmentService.getCompartmentById(this.articleService.id.toString()).subscribe(data => {
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
    this.compartmentService.addDefinedArticleQuantity(
      quantity, this.compartment.id.toString()).subscribe( data =>{
      this.router.navigateByUrl('/warehauseComponent', {skipLocationChange: true}).then(()=>
        this.router.navigate(['/', 'detail' , this.articleService.id]));
      }
    );
  }
}
