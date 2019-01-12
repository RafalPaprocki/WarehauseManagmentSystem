import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable({
  providedIn: 'root'
})
export class CompartmentService {

  constructor(private http: HttpClient) {
  }

  getCompartmentBySectorAndNumber(id:string, sector:string): Observable<any> {
    return this.http.get('//localhost:8888/compartment/' + id + "/" + sector );
  }

  updateDefinedArticleQuantity( quantity:string, id:string, sector:string): Observable<any>{
    return this.http.get('//localhost:8888/compartment/add/article/' + quantity + '/' + id + '/' + sector);
  }

  addArticleToCompartment(articleId:string, quantity:string): Observable<any>{
    return this.http.get('//localhost:8888/add/article/' + articleId + '/' + quantity);
  }
}
