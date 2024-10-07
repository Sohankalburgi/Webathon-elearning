import { Component, OnInit } from '@angular/core';
import { VideoserviceService } from '../Services/videoservice.service';
import { ActivatedRoute } from '@angular/router';
import { Course, videoStatus } from '../course.model';

@Component({
  selector: 'app-videoplay',
  templateUrl: './videoplay.component.html',
  styleUrl: './videoplay.component.css',
})
export class VideoplayComponent implements OnInit {
  videoPlayer!: String;
  course!: any;
  courseId: any;
  role!: String;
  videostatus!: videoStatus[];

  constructor(
    private videoservice: VideoserviceService,
    private router: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.router.paramMap.subscribe((params) => {
      this.courseId = params.get('courseId');
      this.fetchCourse(this.courseId);
      this.fetchStatus();
      const userId = sessionStorage.getItem('userId');
      if (userId) {
        this.fetchRole(userId);
      } else {
        console.error('User ID is missing');
      }
   
    });
  }

  fetchCourse(courseId: any) {
    this.videoservice.getCoursedetails(this.courseId).subscribe(
      (res) => {
        this.course = res;
        console.log(this.course)
        this.videoPlayer = res.videos[0].fileURL;
      },
      (error) => console.error(error)
    );
  }

  onFileSelected(e: Event) {
    const input = e.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      const formData = new FormData();
      formData.append('file', file);

      this.videoservice
        .addvideo(formData, this.courseId)
        .then((res) => {
          console.log(res.data);
        })
        .catch((error) => console.log(error));
    }
  }

  changeVideo(url: any) {
    this.videoPlayer = url;
  }

  fetchRole(userId: any) {
    this.videoservice.getRole(userId).subscribe(
      (res) => {
        this.role = res.message; // If the role is within the response object
        console.log(this.role);
      },
      (error) => {
        this.role = error.error.message
        console.log(this.role)
      }
    );
  }

  markvideo(videoid:any){
    const userId = sessionStorage.getItem('userId');
    console.log("this is under the mark video")
    this.videoservice.markvideo(userId,this.courseId,videoid).subscribe(
      res=>{
        console.log(res)
        alert(res.message);
      }
      ,error => console.error(error)
    )
  }

  fetchStatus(){
    const userId = sessionStorage.getItem('userId');
    if(userId){
    this.videoservice.getmarkingvideo(userId,this.courseId).subscribe(
      res=>{
        console.log("this is fetch Status",res);
        this.videostatus = res
        for(let i of this.videostatus){
          for(let j of this.course.videos){
            if(i.videoId === j.id){
              j.completed = i.completed;
            }
          }
        }
        console.log(this.course)
      },error=>{console.log(error.error)
        this.videostatus = error.error
        for(let i of this.videostatus){
          for(let j of this.course.videos){
            if(i.videoId === j.id){
              j.completed = i.completed;
            }
          }
        }
        console.log(this.course)
      }
    )
  }
  }
  
}


