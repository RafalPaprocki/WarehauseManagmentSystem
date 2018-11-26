import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {CardServiceService} from '../../services/card-service.service';

@Component({
  selector: 'app-detail-product',
  templateUrl: './detail-product.component.html',
  styleUrls: ['./detail-product.component.css']
})
export class DetailProductComponent implements OnInit {
  public articles;
  constructor(private warehauseService: CardServiceService,
              private route: ActivatedRoute,
              private articleService: CardServiceService) { }

  ngOnInit() {

    const id = +this.route.snapshot.paramMap.get('id');
    this.articleService.id = id;

    this.warehauseService.getArticles(this.articleService.id.toString()).subscribe(data => {
      debugger
      this.articles = data;
    });
  }

}
