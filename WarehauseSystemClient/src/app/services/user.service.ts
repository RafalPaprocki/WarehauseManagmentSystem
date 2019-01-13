import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = 'http://localhost:8888/api/test/user';
  private userInfo = 'http://localhost:8888/api/user/';
  private adminUrl = 'http://localhost:8888/api/test/admin';
  private userFiltrUrl = 'http://localhost:8888/api/user/filtr';

  constructor(private http: HttpClient) { }

  getUserBoard(): Observable<string> {
    return this.http.get(this.userUrl, { responseType: 'text' });
  }

  getUserInfo(username: string): Observable<any> {
    return this.http.get(this.userInfo + username);
  }

  getFilteredUsers(body: any): Observable<any> {
    return this.http.post("//localhost:8888/api/user/filtr", body);
  }

  getFilteredArticles(body:any): Observable<any>{
    return this.http.post("//localhost:8888/article/filtr", body);
  }
  getAdminBoard(): Observable<string> {
    return this.http.get(this.userFiltrUrl, { responseType: 'text' });
  }

  updateEmployee(body:any): Observable<any>{
    return this.http.put("//localhost:8888/api/user/update", body);
  }

  changePassword(body:any): Observable<any>{
    return this.http.post("//localhost:8888/api/auth/change/password", body)
  }
}
