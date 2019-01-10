import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';


@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  public id:number;
  constructor(private http: HttpClient) {
  }

  getAll(id:string): Observable<any> {
    return this.http.get('//localhost:8888/lista/' + id );
  }

  sendPost(body:any): Observable<any>{
    return this.http.post('//localhost:8888/article/add', body);
  }

  getArticles(id:string): Observable<any>{
    return this.http.get('//localhost:8888/article/' + id);
  }

  getArticleInCompartment(sector:string, id:string): Observable<any>{
    return this.http.get('//localhost:8888/compartments/get/article/' + sector + '/' + id);
  }

  getAllArticles(): Observable<any>{
    return this.http.get('//localhost:8888/articles/all');
  }

  getFilteredArticles(body:any): Observable<any>{
    return this.http.post("//localhost:8888/article/filtr", body);
  }

  updateArticle(id:string, body:any): Observable<any>{
    return this.http.put("//localhost:8888/article/" + id, body);
  }

  deleteArticle(id:string): Observable<any>{
    return this.http.delete("//localhost:8888/article/" + id);
  }

  findArticlesByIdLike(id:string): Observable<any>{
    return this.http.get("//localhost:8888/article/search/" + id);
  }

  findCompartmentsWithArticle(id:string): Observable<any>{
    return this.http.get("//localhost:8888/compartments/" + id);
  }
}
