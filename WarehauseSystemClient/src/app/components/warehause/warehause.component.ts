import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../services/article.service'
import {ActivatedRoute} from "@angular/router";
import {CompartmentService} from "../../services/compartment.service";

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
  timer;
  hoveredElemSector;
  hoveredElemNumber;
  article;
  compartment;

  constructor(private route: ActivatedRoute, private articleService: ArticleService,
              private compartmentService: CompartmentService) {
  }

  ngOnInit() {
    this.sectorWidth = +this.route.snapshot.paramMap.get('width');
    this.sectorHeight = +this.route.snapshot.paramMap.get('heigh');
    this.sectorName = this.route.snapshot.paramMap.get('sector');
    this.articleService.getAll('9').subscribe(data => {
      this.warehause = data;
    });
    debugger

  }

  displayContentInCart(compartment, num, sec){
    this.articleService.getArticleInCompartment(sec, num).subscribe(data => {
       this.article = data;
    });
    this.compartmentService.getCompartmentBySectorAndNumber(num, sec).subscribe(data => {
      this.compartment = data;
      console.log(this.compartment)
    })

    this.timer = setTimeout(() => {
        let elm = document.getElementById("cart");
        elm.style.top =  String(compartment.pageY - 250) + "px";
        elm.style.left = String(compartment.pageX - 100) + "px";
        elm.style.visibility = "visible";
        this.hoveredElemSector = sec;
        this.hoveredElemNumber = num;
        },
      700);
  }

  unvisibleCart(){
    clearTimeout(this.timer)
    let elm = document.getElementById("cart");
    elm.style.visibility = "hidden";
    this.hoveredElemSector = undefined;
    this.hoveredElemNumber = undefined;
  }

}
