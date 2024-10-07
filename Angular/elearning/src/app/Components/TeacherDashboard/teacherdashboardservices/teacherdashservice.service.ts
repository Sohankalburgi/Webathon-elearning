import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import axios from 'axios';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TeacherdashserviceService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }
  
  getUploadedCourses(id:any):Promise<any>{
    console.log(id+"inside")
    return axios.get(`${this.baseUrl}/mentor/`+id);
  }

  getCourseImage(id:any):Observable<any>{
    return this.http.get(`${this.baseUrl}/getImage/${id}`);
  }
}
