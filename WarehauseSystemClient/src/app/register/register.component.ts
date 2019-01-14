import { Component, OnInit } from '@angular/core';

import { AuthService } from '../auth/auth.service';
import { SignUpInfo } from '../auth/signup-info';
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  signupInfo: SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
  added = false;

  constructor(private authService: AuthService, private router : Router) { }

  ngOnInit() { }

  onSubmit() {
    console.log(this.form);
    this.signupInfo = new SignUpInfo(
      this.form.name,
      this.form.surname,
      this.form.username,
      this.form.email,
      this.form.password,
      this.form.kind,
      this.form.position);

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        let a = this;
        a.added = true;

        setTimeout(function () {
            a.added = false;
            a.router.navigateByUrl('/home', {skipLocationChange: true}).then(()=>
              a.router.navigate(['/', 'signup']));
              window.scrollTo(0,0);
          },
          1000);
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );
  }
}

