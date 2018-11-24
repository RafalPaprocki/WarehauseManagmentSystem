import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { CardServiceService } from './services/card-service.service'
import { HttpClientModule } from '@angular/common/http';
import { WarehauseComponent } from './components/warehause/warehause.component';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    WarehauseComponent
  ],
  imports: [
    BrowserModule,
     HttpClientModule,
     AppRoutingModule
  ],
  providers: [CardServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
