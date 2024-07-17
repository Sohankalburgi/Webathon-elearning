import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class VideoserviceService {
  private baseUrl = 'http://localhost:8080';
  constructor() { }

  getCoursedetails(courseId:any):Promise<any>{
    return axios.get(`${this.baseUrl}/course/${courseId}`);
  }

  getVideoDetails(courseId:any):Promise<any>{
    return axios.get(`${this.baseUrl}/course/${courseId}/downloadvideo`);
  }
}
