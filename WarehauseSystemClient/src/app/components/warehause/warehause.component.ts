import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../services/article.service'
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-warehause',
  templateUrl: './warehause.component.html',
  styleUrls: ['./warehause.component.css']
})
export class WarehauseComponent implements OnInit {

  public warehause:any;
  public warehause2:any;
  public sectorWidth:number;
  public sectorHeight:number;
  public sectorName:String;
  Arr = Array;
  num:number = 10;
  input = 'ijkk';

  constructor(private route: ActivatedRoute, private warehauseService: ArticleService) {
}

  ngOnInit() {
    this.sectorWidth = +this.route.snapshot.paramMap.get('width');
    this.sectorHeight = +this.route.snapshot.paramMap.get('heigh');
    this.sectorName = this.route.snapshot.paramMap.get('sector');
    this.warehauseService.getAll('9').subscribe(data => {
      this.warehause = data;
    });
    debugger

  }


}
