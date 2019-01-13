import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ArticleService } from './services/article.service'
import { HttpClientModule } from '@angular/common/http';
import { WarehauseComponent } from './components/warehause/warehause.component';
import { AppRoutingModule } from './app-routing.module';
import { DetailProductComponent } from './components/detail-product/detail-product.component';
import { AddArticleComponent } from './components/add-article/add-article.component';
import { FormsModule } from '@angular/forms';
import {BsModalService, ModalModule} from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import { AccordionModule } from 'ngx-bootstrap/accordion'
import {ComponentLoaderFactory, PositioningService} from "ngx-bootstrap";
import { ArticlesListComponent } from './components/articles-list/articles-list.component';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {ConfirmationService, MessageService} from 'primeng/api';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AutoCompleteModule} from 'primeng/autocomplete';
import {ToastModule} from 'primeng/toast';
import {ToastrService} from "./services/toastr.service";
import { HomeComponent } from './components/home/home.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { EmployeesListComponent } from './components/employees-list/employees-list.component';

@NgModule({
  declarations: [
    AppComponent,
    WarehauseComponent,
    DetailProductComponent,
    AddArticleComponent,
    ArticlesListComponent,
    HomeComponent,
    UserProfileComponent,
    LoginComponent,
    RegisterComponent,
    EmployeesListComponent
  ],
  imports: [
    BrowserModule,
     HttpClientModule,
     AppRoutingModule,
     FormsModule,
    BrowserAnimationsModule,
    ConfirmDialogModule,
    AutoCompleteModule,
    ToastModule,
    AccordionModule.forRoot(),
    ModalModule.forRoot(),

  ],
  providers: [
    BsModalService,
    ArticleService,
    ConfirmationService,
    MessageService,
    ToastrService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
