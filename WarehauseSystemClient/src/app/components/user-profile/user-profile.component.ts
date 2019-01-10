import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  passwordChange :boolean = false;
  oldPassword : String;
  newPassword : String;
  constructor() { }

  ngOnInit() {
  }

  enablePasswordChangeForm(){
    this.passwordChange = this.passwordChange ? false : true;
  }
}
