import { Component } from '@angular/core';
import { DashservicesstudService } from '../services/dashservicesstud.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  courses!:any[];

  constructor(private stdservice:DashservicesstudService) { }
  userId : any;
  ngOnInit(): void {
    this.userId = sessionStorage.getItem('userId')
    if(this.userId!=null){
    this.getCourses();
    }
  }

  getCourses(): void {
    // Call API to get courses
    // For demo purposes, I'll use a static array
    this.stdservice.getregisteredCourses(this.userId).then(
      response =>{
        console.log(response.data.courses)
        this.courses = response.data.courses;
        console.log(this.courses)
        // for(let course of this.courses){
        //    this.stdservice.getCourseImage(course.courseId).then(data=>{
        //     const blob = new Blob([data], { type: 'image/jpeg' });
        //     course.thumbnail = URL.createObjectURL(blob);
            
        //    });
        // }
        
      }
    ).catch(error=>{console.log(error.response.data)
      this.courses = error.response.data;
    })
    
  
   
  }
  isChatbotVisible = false;

  toggleChatbot() {
    this.isChatbotVisible = !this.isChatbotVisible;
  }
}


