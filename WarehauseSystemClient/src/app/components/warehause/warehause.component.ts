import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../services/article.service'

@Component({
  selector: 'app-warehause',
  templateUrl: './warehause.component.html',
  styleUrls: ['./warehause.component.css']
})
export class WarehauseComponent implements OnInit {

  public warehause:any;
  public warehause2:any;
  public sectorWidth:number = 10;
  public sectorHeight:number = 15;
  Arr = Array;
  num:number = 10;
  input = 'ijkk';

  constructor(private warehauseService: ArticleService) { }

  ngOnInit() {
    this.warehauseService.getAll('9').subscribe(data => {
      this.warehause = data;
    });
    document.getElementById('#divek');
    debugger
  }

}
