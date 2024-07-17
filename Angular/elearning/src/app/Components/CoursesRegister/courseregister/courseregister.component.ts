import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CourseServiceService } from './Services/course-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-courseregister',
  templateUrl: './courseregister.component.html',
  styleUrl: './courseregister.component.css'
})
export class CourseregisterComponent {
  courseForm: FormGroup;

  userId:any;
  thumbnail!: File;
  video!: File;

  handleThumbnailChange($event: Event) {
    const target = $event.target as HTMLInputElement;
    if (target && target.files) {
      this.thumbnail = target.files[0];
    }
  }

  handleVideoChange($event: Event) {
    const target = $event.target as HTMLInputElement;
    if (target && target.files) {
      this.video = target.files[0];
    }
  }

  constructor(private fb: FormBuilder, private courseservice : CourseServiceService,private route:Router) {
    // Initialize the form group with form controls and validation rules
    this.courseForm = this.fb.group({
      coursename: ['', Validators.required],
      coursedescription: ['', Validators.required],
      courseduration: ['', Validators.required],
      thumbnail:['',Validators.required],
      video:['',Validators.required]
    });
    this.userId = sessionStorage.getItem("userId"); 
    console.log(this.userId)
  }

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.courseForm.valid) {
      const formData = new FormData();
  
      formData.append('coursename', this.courseForm.get('coursename')?.value);
    formData.append('coursedescription', this.courseForm.get('coursedescription')?.value);
    formData.append('courseduration', this.courseForm.get('courseduration')?.value);
    formData.append('thumbnail', this.thumbnail);
    formData.append('video', this.video);
      
      this.courseservice.registerCourse(formData,this.userId).then(data=>{
        console.log(data);
        this.route.navigate([`/teacherdash/${this.userId}`])
      }).catch(error=>console.log(error))
        
      
      
    }
  }
}
