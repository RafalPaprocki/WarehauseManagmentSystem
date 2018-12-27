import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable({
  providedIn: 'root'
})
export class CompartmentService {

  constructor(private http: HttpClient) {
  }

  getCompartmentById(id:string): Observable<any> {
    return this.http.get('//localhost:8888/compartment/' + id );
  }

  addDefinedArticleQuantity( quantity:string, id:string): Observable<any>{
    return this.http.put('//localhost:8888/compartment/add/article/' + quantity + '/' + id, {});
  }
}
