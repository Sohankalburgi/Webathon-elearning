import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CourseServiceService } from './Services/course-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-courseregister',
  templateUrl: './courseregister.component.html',
  styleUrls: ['./courseregister.component.css']
})
export class CourseregisterComponent implements OnInit {
  courseForm: FormGroup;
  imageError: string = "";
  userId: string | null;
  thumbnail!: File;
  video!: File;
  submissionError: string = "";
  submissionSuccess: boolean = false;

  constructor(private fb: FormBuilder, private courseservice: CourseServiceService, private route: Router) {
    this.courseForm = this.fb.group({
      coursename: ['', Validators.required],
      coursedescription: ['', Validators.required],
      courseduration: ['', Validators.required],
      thumbnail: ['', Validators.required],
      video: ['', Validators.required]
    });
    this.userId = sessionStorage.getItem("userId");
  }

  ngOnInit(): void {}

  validateImage(event: any): void {
    const file = event.target.files[0];
    this.imageError = ''; // Reset previous error

    if (file) {
      const img = new Image();
      const reader = new FileReader();

      if (file.type.startsWith('image/')) {
        reader.onload = (e: any) => {
          img.src = e.target.result;

          img.onload = () => {
            const width = img.width;
            const height = img.height;

            if (width > height) {
              this.thumbnail = file; // Store the valid thumbnail
              this.courseForm.patchValue({ thumbnail: file }); // Update form control value
            } else {
              this.imageError = 'Image must be in landscape mode.';
            }
          };
        };
        reader.readAsDataURL(file);
      } else {
        this.imageError = 'Please upload a valid image file.';
      }
    }
  }

  handleVideoChange(event: Event): void {
    const target = event.target as HTMLInputElement;
    if (target && target.files) {
      this.video = target.files[0];
      this.courseForm.patchValue({ video: this.video }); // Update form control value
    }
  }

  onSubmit(): void {
    if (this.courseForm.valid) {
      const formData = new FormData();
      formData.append('coursename', this.courseForm.get('coursename')?.value);
      formData.append('coursedescription', this.courseForm.get('coursedescription')?.value);
      formData.append('courseduration', this.courseForm.get('courseduration')?.value);
      formData.append('thumbnail', this.thumbnail);
      formData.append('video', this.video);

      this.courseservice.registerCourse(formData, this.userId).then(data => {
        this.submissionSuccess = true; // Indicate success
        this.submissionError = ''; // Reset error message
        this.route.navigate([`/teacherdash/${this.userId}`]);
      }).catch(error => {
        console.error(error);
        this.submissionError = 'There was an error registering the course. Please try again.'; // Handle error
      });
    } else {
      this.submissionError = 'Please fill out all fields correctly.'; // Handle form invalid case
    }
  }
}
