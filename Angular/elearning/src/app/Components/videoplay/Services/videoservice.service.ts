import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import axios from 'axios';
import { Observable, ObservableLike } from 'rxjs';
import { Course, Response } from '../course.model';

@Injectable({
  providedIn: 'root'
})
export class VideoserviceService {
  
  private baseUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  // Get course details
  getCoursedetails(courseId: any): Observable<any> {
    return this.http.get<Course>(`${this.baseUrl}/course/${courseId}`);
  }

  addvideo(formData:FormData,courseId: any):Promise<any> {
    console.log(courseId)
    console.log(formData)
    const headers = {
      'Content-Type':'multipart/form-data',
    };
    return axios.post(`${this.baseUrl}/course/${courseId}/uploadvideofile`,formData);
  }

  getRole(userId:any):Observable<any>{
    return this.http.get<Response>(`${this.baseUrl}/user/getRole?userId=${userId}`)
  }

  markvideo(userId:any,courseId:any,videoId:any):Observable<any>{
    return this.http.post(`${this.baseUrl}/markVideo?studentId=${userId}&courseId=${courseId}&videoId=${videoId}`,{})
  }

  getmarkingvideo(userId:any,courseId:any):Observable<any>{
    return this.http.get(`${this.baseUrl}/getmarkedstatus?studentId=${userId}&courseId=${courseId}`);
  }
}
