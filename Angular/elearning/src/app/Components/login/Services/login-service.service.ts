import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {
  private baseUrl = 'http://localhost:8080';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  constructor(private http:HttpClient) {}


  registerUser(user:any):Observable<any>{ 
    
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post(`${this.baseUrl}/user/registerUser`,user,this.httpOptions);
  }

  checkuser(email:any):Observable<any>{ 
    
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.get(`${this.baseUrl}/user/checkUserExists/${email}`,this.httpOptions);
  }

  loginUser(user:any):Observable<any>{
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post(`${this.baseUrl}/user/login`,user,this.httpOptions);
  }

}
