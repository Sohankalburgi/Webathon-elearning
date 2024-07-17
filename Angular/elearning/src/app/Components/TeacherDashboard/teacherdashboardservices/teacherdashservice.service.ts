import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class TeacherdashserviceService {
  private baseUrl = 'http://localhost:8080';

  getUploadedCourses(id:any):Promise<any>{
    console.log(id+"inside")
    return axios.get(`${this.baseUrl}/mentor/`+id);
  }
  getCourseImage(id:any):Promise<any>{
    console.log(id+"thafjsdlk")
    return axios.get(`${this.baseUrl}/getImage/${id}`,{responseType:'blob'});
  }
}
