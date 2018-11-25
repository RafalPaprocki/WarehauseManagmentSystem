import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';


@Injectable({
  providedIn: 'root'
})
export class CardServiceService {
  public id:number;
  constructor(private http: HttpClient) {
  }

  getAll(id:string): Observable<any> {
    return this.http.get('//localhost:8888/lista/' + id );
  }

  sendPost(body:any): Observable<any>{
    return this.http.post('//localhost:8888/listaa/', body);
  }

}
