import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule,routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomerComponent } from './customer/customer.component';
import { AddressComponent } from './address/address.component';
import { AccountComponent } from './account/account.component';
import { FormsModule }   from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { FetchingDataComponent } from './fetching-data/fetching-data.component';





@NgModule({
  declarations: [
    AppComponent,
    CustomerComponent,
    AddressComponent,
    AccountComponent,
    routingComponents,
    PageNotFoundComponent,
    FetchingDataComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
