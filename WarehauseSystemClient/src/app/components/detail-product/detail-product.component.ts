import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {CardServiceService} from '../../services/card-service.service';

@Component({
  selector: 'app-detail-product',
  templateUrl: './detail-product.component.html',
  styleUrls: ['./detail-product.component.css']
})
export class DetailProductComponent implements OnInit {

  constructor(private route: ActivatedRoute, private articleService: CardServiceService) { }

  ngOnInit() {
    debugger
    const id = +this.route.snapshot.paramMap.get('id');
    this.articleService.id = id;
  }

}
