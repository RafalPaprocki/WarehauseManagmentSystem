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
  oldPassword : String;
  newPassword : String;
  roles:string[];
  username: String;
  userInfo;
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
}
