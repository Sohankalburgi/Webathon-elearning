import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class DashservicesstudService {
  private baseUrl = 'http://localhost:8080';

  getregisteredCourses(id:any):Promise<any>{
    console.log(id)
    return axios.get(`${this.baseUrl}/student/${id}/getAllregisteredCourses`);
  }
  getCourseImage(id:any):Promise<any>{
    console.log(id+"thafjsdlk")
    return axios.get(`${this.baseUrl}/getImage/${id}`,{responseType:'blob'});
  }
}
