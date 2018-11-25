import { Component, OnInit } from '@angular/core';
import { CardServiceService } from '../../services/card-service.service'

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
  num:number = 20;

  constructor(private warehauseService: CardServiceService) { }

  ngOnInit() {
    this.warehauseService.getAll('87').subscribe(data => {
      this.warehause = data;
    });

    this.warehauseService.sendPost("sad").subscribe(data => {
      this.warehause2 = data;
    })
  }

}
