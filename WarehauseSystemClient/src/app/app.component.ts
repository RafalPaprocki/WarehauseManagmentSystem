import {Component, TemplateRef} from '@angular/core';
import {ToastrService} from "./services/toastr.service";
import {ArticlesListComponent} from "./components/articles-list/articles-list.component";
import {ArticleService} from "./services/article.service";
import {BsModalRef} from "ngx-bootstrap/modal/bs-modal-ref.service";
import {BsModalService} from "ngx-bootstrap";
import {Router} from '@angular/router';
import {TokenStorageService} from "./auth/token-storage.service";
import {UserService} from "./services/user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'WarehauseSystemClient';
  filteredArticles: any[];
  articleToSearch;
  compartments;
  findArticleName;
  private roles: string[];
  private authority: string;
  private isLoggedIn = false;
  private username;
  public modalRef: BsModalRef;
  userInfo;
  constructor(private articleService : ArticleService, private modalService:BsModalService,
              private router: Router, private token: TokenStorageService,
              private tokenStorage: TokenStorageService, private userService: UserService) {
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.username = this.token.getUsername();
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        } else if (role === 'ROLE_PM') {
          this.authority = 'pm';
          return false;
        }
        this.authority = 'user';
        return true;
      });
      this.userService.getUserInfo(this.username).subscribe(data =>{
        this.userInfo = data;
      });
    }
    if(this.isLoggedIn == false){
      this.router.navigate(["/auth/login"]);
    }
    if(this.isLoggedIn == true){
      this.router.navigate(["/"]);
    }
  }

  filterCountrySingle(event) {
    this.articleService.findArticlesByIdLike(event.query).subscribe(data => {
      this.filteredArticles = data;
      console.log(this.filteredArticles)
    });
  }

  selected(event){
    this.articleToSearch = event;
    console.log(this.articleToSearch)
  }

  findArticleLocalization(article){
    this.articleService.findCompartmentsWithArticle(article.id.toString()).subscribe(data => {
      this.compartments = data;
      console.log( this.compartments)
    })
    this.findArticleName = "";

  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(
      template,
      Object.assign({}, { class: 'modal-sm' })
    );
  }

  refresh(number, sector){
    let sec = sector.toString().split(' ')[1];
    this.router.navigateByUrl('/home', {skipLocationChange: true}).then(()=>
      this.router.navigate(['/', 'detail' , number.toString(), sec]));
  }

  logout() {
    this.token.signOut();
    window.location.reload();
  }

}

