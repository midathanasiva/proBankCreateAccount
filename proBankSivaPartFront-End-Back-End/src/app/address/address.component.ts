import { Component, OnInit } from '@angular/core';
import { Addresscls } from '../addresscls';
import { ServiceService } from '../service.service';
import { Router, ActivatedRoute, ParamMap} from '@angular/router';
@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {

  constructor(private _enrollmentService: ServiceService) { }

  title = 'app-customer';
  topics = ['Male', 'Female', 'TransGender'];
  // THis is for Gender ,need to select by user
  userModel1 = new Addresscls('city','PresentAddress', 'PermamentAddress', 'country','531115');
/*  ths is parameterized constructor
  for Address class to store data for address entered by user 
  and these values are going to display over there*/

  topicHasError = true;
  submitted = false;
  IdUrl:number=0;
  errorMsg = '';
  respo1:any;
  message:any;


  validateTopic(value) {
    if (value === 'default') {
      this.topicHasError = true;
    } else {
      this.topicHasError = false;
    }
    /*this method is being called from html templete 
    *to check for calidations ahd error
    */
  }

  onSubmit() {
    this.submitted = true;
    let repo=this._enrollmentService.enroll1(this.userModel1,this.IdUrl);
     repo.subscribe(
      ( (data)=>this.message=data),
      error => this.errorMsg = error.statusText
    )
  }
 /* this method is being called from html template when user clicks on submit button 
  and the service class is going to call this
   method to post data to the server*/

  ngOnInit(): void {
  }

}
