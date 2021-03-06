import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { DetailProductComponent } from './components/detail-product/detail-product.component';
import {WarehauseComponent} from './components/warehause/warehause.component';
import {AddArticleComponent} from './components/add-article/add-article.component';
import {ArticlesListComponent} from "./components/articles-list/articles-list.component";
import {HomeComponent} from "./components/home/home.component";
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import {RegisterComponent} from "./register/register.component";
import {LoginComponent} from "./login/login.component";
import {EmployeesListComponent} from "./components/employees-list/employees-list.component";

const routes: Routes = [
  { path: '', redirectTo: 'homeComponent', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'warehauseComponent/:width/:heigh/:sector', component: WarehauseComponent },
  { path: 'homeComponent', component: HomeComponent },
  { path: 'detail/:id/:sector', component: DetailProductComponent },
  { path: 'article/add', component: AddArticleComponent},
  { path: 'article/list', component: ArticlesListComponent},
  { path: 'user/profile', component: UserProfileComponent },
  { path: 'signup', component: RegisterComponent },
  { path: 'auth/login', component: LoginComponent },
  { path: 'employees/edit', component: EmployeesListComponent}
];

@NgModule({
  declarations: [],
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {
}
