export class SignUpInfo {
  name: string;
  username: string;
  email: string;
  role: string[];
  password: string;
  surname: string;
  agreement: string;
  position: string;
  constructor(name: string, surname:string, username: string, email: string, password: string,
              agreement: string, position: string) {
    this.name = name;
    this.surname = surname;
    this.username = username;
    this.email = email;
    this.password = password;
    if(position == 'administrator')
      this.role = ['admin'];
    else this.role = ['user'];
    this.agreement = agreement;
    this.position = position;
  }
}
