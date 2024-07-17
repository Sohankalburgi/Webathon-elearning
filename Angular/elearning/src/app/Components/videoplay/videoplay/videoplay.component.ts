import { Component, OnInit } from '@angular/core';
import { VideoserviceService } from '../Services/videoservice.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-videoplay',
  templateUrl: './videoplay.component.html',
  styleUrl: './videoplay.component.css'
})
export class VideoplayComponent implements OnInit{
 videoPlayer: any;
  course:any;

 constructor(private videoservice:VideoserviceService,private router:ActivatedRoute){}
  courseId:any;
  ngOnInit(): void {
    this.router.paramMap.subscribe(params=>{
      this.courseId = params.get('courseId');
      
    });
    this.videoservice.getCoursedetails(this.courseId).then(data=>{
      console.log(data);
      this.course=data;
    }).catch(error=>{
      console.log(error.response.data);
      this.course = error.response.data;
      console.log(this.course)
      const file = error.response.data.videos[0].fileData;
      
      const blob = new Blob([file],{type : 'video/mp4'})
      
      this.videoPlayer = URL.createObjectURL(file);
    });
   
   

  }
 
  getVideoBlob(){
    this.videoservice.getVideoDetails(this.courseId).then(data=>{
      const blob = new Blob(data,{ type: 'video/mp4' });
      this.videoPlayer = URL.createObjectURL(blob);
    })
  }
  

 
}

