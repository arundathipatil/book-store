import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders} from '@angular/common/http';
import { User } from '../models/User';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  post(url: string, user: any) : Observable<any> {
    return this.http.post(url, user);
  }

  get(url: string, params?: any) {
    
    // var headers = new HttpHeaders({'Access-Control-Allow-Origin' : '*'});
    // var options = {      
    //   headers: headers,
    //   params: params
    // };
    return this.http.get(url, params);
  }

  delete(url, params?:any) {
    return this.http.delete(url, params);
  }

  put(url: string, data: any) {
    return this.http.put(url, data);
  }
}
