import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import axios from 'axios';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CourseServiceService {
  private baseUrl = 'http://localhost:8080';

 
  constructor(private http:HttpClient) {}

  registerCourse(course:FormData,id:any):Promise<any>{ 
    console.log("from service"+id)
    console.log(course)
    course.forEach(course=>{
      console.log(course)
    })
    const headers = {
      'Content-Type': 'ultipart/form-data',
    };
    return axios.post(`${this.baseUrl}/mentor/${id}/addcourse`, course, { headers: headers });
  }
}
