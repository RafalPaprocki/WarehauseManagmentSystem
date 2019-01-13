import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../auth/token-storage.service";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  passwordChange :boolean = false;
  oldPassword : string;
  newPassword : string;
  roles:string[];
  username: string;
  userInfo;
  passwordForm = {
    username: "",
    oldPassword: "",
    newPassword: ""
  };
  errorMessage;
  isLoginFailed = false;
  added : boolean = false;
  constructor(private tokenStorage:TokenStorageService, private userService:UserService) { }

  ngOnInit() {
    this.roles = this.tokenStorage.getAuthorities();
    console.log(this.roles);
    this.username = this.tokenStorage.getUsername();
    this.userService.getUserInfo(this.username).subscribe(data =>{
      this.userInfo = data;
      console.log(this.userInfo)
    });
  }

  enablePasswordChangeForm(){
    this.passwordChange = this.passwordChange ? false : true;
  }

  onSubmit(){
    this.passwordForm.username = this.username;

    this.userService.changePassword(this.passwordForm).subscribe(data => {
        this.isLoginFailed = false;
      let a = this;
      a.added = true;

      setTimeout(function () {
          a.added = false;
        },
        1000);
      setTimeout(function () {
          location.reload();
        },
        1100);
    },
      error => {
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      })

  }
}
