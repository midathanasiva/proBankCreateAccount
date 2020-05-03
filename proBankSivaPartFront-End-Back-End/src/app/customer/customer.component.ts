import { Component, OnInit } from '@angular/core';
import { Customercls } from '../customercls';
import { ServiceService } from '../service.service';
import { Router, ActivatedRoute, ParamMap} from '@angular/router';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  constructor(private _enrollmentService: ServiceService) { }
  title = 'app-customer';
  topics = ['Male', 'Female', 'TransGender'];
  userModel = new Customercls('Name',9556665566, 'default', '123456789012','ASDF1234A','22/12/2020');
 /* here userModel is reference variable to customer cls as it acts as object
  * and it is parametrized constructor and values act as default in html page 
  *Topics array is used to display as dropdown menu and user need to select his gender 
  *from that
  */
  topicHasError = true;
  submitted = false;
  errorMsg = '';
  message:any;

  validateTopic(value) {
    if (value === 'default') {
      this.topicHasError = true;
    } else {
      this.topicHasError = false;
    }
  }
  /*this method is being called from html templete 
    *to check for calidations ahd error
    */
  

  onSubmit() {
    this.submitted = true;
   let repo= this._enrollmentService.enroll(this.userModel);
     repo.subscribe(
       ( (data)=>this.message=data),
        error => this.errorMsg = error.statusText
      )

  }
  /* this method is being called from html template when user clicks on submit button 
   *and the service class is going to call this
   *method to post data to the server
   */

  ngOnInit(): void {
  }

}
