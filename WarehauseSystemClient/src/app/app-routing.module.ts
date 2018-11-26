import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { DetailProductComponent } from './components/detail-product/detail-product.component';
import {WarehauseComponent} from './components/warehause/warehause.component';
import {AddArticleComponent} from './components/add-article/add-article.component';

const routes: Routes = [
  { path: '', redirectTo: 'warehauseComponent', pathMatch: 'full' },
  { path: 'warehauseComponent', component: WarehauseComponent },
  { path: 'detail/:id', component: DetailProductComponent },
  { path: 'article/add', component: AddArticleComponent}
];

@NgModule({
  declarations: [],
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {
}
