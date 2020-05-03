import { Component, OnInit } from '@angular/core';
import { Accountcls } from '../accountcls';
import { ServiceService } from '../service.service';
import { Router, ActivatedRoute, ParamMap} from '@angular/router';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  constructor(private _enrollmentService: ServiceService) { }
  /*here with  this _enrollmentService variable we are going to 
   *use service component methods while posting or fetching data.
   * here "types" variable array helps to display dropdown menu for the user to select type of account 
   * he wants
   */
  title = 'app-account';
  types = ['SavingAccount', 'StudentAccount', 'ZeroBalance'];
  
  types1 = ['SIV000001'];
  userModel2 = new Accountcls('121212121212','default', 'default',0);
 /* here creating object to Accountcls to store the account details , and as it is 
  * parametrized constructor , in html the default values is going to display
  */
  topicHasError = true;
  submitted = false;
  errorMsg = '';
  respo1:any;
  IdUrl:number=0;
  message:any;

  validateTopic(value) {
    if (value === 'default') {
      this.topicHasError = true;
    } else {
      this.topicHasError = false;
    }
   /* this method is being called from html templete 
     *to check for calidations ahd error
    */
   


  }
  

  onSubmit() {
    this.submitted = true;
    let repo=this._enrollmentService.enroll2(this.userModel2,this.IdUrl);
     repo.subscribe(
      ( (data)=>this.message=data),
      error => this.errorMsg = error.statusText)
  }
  /* this method is being called from html template when user clicks on submit button 
   * and the service class is going to call this
   * method to post data to the server
   * 
   * */

  ngOnInit(): void {
  }

}
