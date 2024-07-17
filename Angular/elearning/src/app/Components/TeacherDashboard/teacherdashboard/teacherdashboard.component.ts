import { Component, OnInit } from '@angular/core';
import { TeacherdashserviceService } from '../teacherdashboardservices/teacherdashservice.service';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-teacherdashboard',
  templateUrl: './teacherdashboard.component.html',
  styleUrl: './teacherdashboard.component.css'
})
export class TeacherdashboardComponent implements OnInit {
  courses: any[] = [];
  

  constructor(private teacherservice:TeacherdashserviceService,private sanitizer: DomSanitizer) { }
  userId : any;
  ngOnInit(): void {
    this.userId = sessionStorage.getItem('userId')
    this.getCourses();
    
  }

  getCourses(): void {
    // Call API to get courses
    // For demo purposes, I'll use a static array
    console.log(this.userId)
    this.teacherservice.getUploadedCourses(this.userId).then(
      res =>{
        console.log(res)
        this.courses = res.data.courses;
        console.log(this.courses)
        for(let course of this.courses){
           this.teacherservice.getCourseImage(course.courseId).then(data=>{
            const blob = new Blob([data], { type: 'image/jpeg' });
            course.thumbnail = URL.createObjectURL(blob);
            
           });
        }
        
      }
    ).catch(error=>console.log(error))
   
  }



 

  
}
