import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import{FetchingDataComponent} from './fetching-data/fetching-data.component'
import { AccountComponent } from './account/account.component';
import { CustomerComponent } from './customer/customer.component';
import { AddressComponent } from './address/address.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

const routes: Routes = [
  { path: '', redirectTo: '/addCustomerDetails', pathMatch: 'full' },
  { path: 'addCustomerDetails', component: CustomerComponent },
  { 
    path: 'addAddressDetails', 
    component: AddressComponent,
    /* children: [
    *    { path: 'addAccountDetails', component: AccountComponent},
    *   { path: 'getCreatedAccouuntDetails', component:FetchingDataComponent}
    * ]
    *in this way we can use child routes , for me not required so, not using these
    */

   },
   {path: 'getCreatedAccouuntDetails', component:FetchingDataComponent},
  { path: 'addAccountDetails', component: AccountComponent },
  { path: '**', component: PageNotFoundComponent }   

 /* these are the paths to navigate   '**' for default routes
  * (if any route notmatchs , then it is 
  *  going to access).
  * */
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routingComponents = [AddressComponent,
  CustomerComponent, 
  AccountComponent,
  PageNotFoundComponent,
  FetchingDataComponent
]
/*insted of importing or exporting these all routing components , we simply create
*on array of routers and then we can use ,
* i did that in above cod
 e*/
