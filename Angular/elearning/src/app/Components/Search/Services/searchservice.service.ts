import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class SearchserviceService {
  private baseUrl = 'http://localhost:8080';
  constructor() { }

  getSearch(search:any):Promise<any>{
    console.log(search)
    return axios.get(`${this.baseUrl}/getallcourse/${search}`)
  }

  courseRegister(courseId:any,id:any):Promise<any>{
    console.log("fadfa"+id);
    console.log(courseId)
    return axios.post(`${this.baseUrl}/student/${id}/courseregister/${courseId}`)
  }
}
