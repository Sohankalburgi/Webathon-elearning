import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-teacher-register',
  templateUrl: './teacher-register.component.html',
  styleUrl: './teacher-register.component.css'
})
export class TeacherRegisterComponent implements OnInit {
  teacherForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.teacherForm = this.fb.group({
      name: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      highestQualification: ['', Validators.required],
      location: ['', Validators.required]
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    if (this.teacherForm.valid) {
      console.log('Registration Data:', this.teacherForm.value);
      // Add your registration logic here
    }
  }
}
