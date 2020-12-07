import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject , of} from 'rxjs';
import { ApiService } from '../../core/api.service';
import { constant } from '../../constant/const';
import { User } from '../../models/User';
import { UserDTO } from '../../models/UserDTO'

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private userDetails: BehaviorSubject<User>;

  constructor(private apiService: ApiService) {
    let user: User = null;
    this.userDetails = new BehaviorSubject<User>(user);
   }

  login(email: string, pwd: string) : Observable<any> {
    let user: UserDTO = {
      email: email,
      password: pwd
    }
    return this.apiService.post(constant.baseUrl+constant.urls.login , user);
    // return of([{name:"sucess"}]);
  }

  // not needed
  setUser(u : User) : void {
    this.userDetails.next(u);
  }
  
  // not needed
  getUser(): Observable<User> {
    return this.userDetails.asObservable();
  }

  // not needed
  getUserDeatislFromApi(email: string) {
     return this.apiService.get(constant.urls.getUserDeatils + "?email="+email);
    // return of({firstName:"Prati", lastName: "Patil", email:"prati.patil@gmail.com"});
  }

  sendResetPwdEmail(email: string) {
    return this.apiService.get(constant.urls.resetPassword+ "?email="+email)
  }

  testLogin() {
    return this.apiService.get("http://localhost:8080/Springmvcangular/getemployees");
  }
}
