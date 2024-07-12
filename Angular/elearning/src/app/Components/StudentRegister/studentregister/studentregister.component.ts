import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-studentregister',
  templateUrl: './studentregister.component.html',
  styleUrl: './studentregister.component.css'
})
export class StudentregisterComponent implements OnInit{
  studentForm: FormGroup;

  constructor(private fb: FormBuilder,private router:ActivatedRoute) {
    this.studentForm = this.fb.group({
      studentName: ['', Validators.required],
      collegeName: ['', Validators.required],
      qualification: ['', Validators.required],
      location:['', Validators.required],
      dateOfBirth: ['', Validators.required],
    });
  }
  email : any|null;
  ngOnInit(): void {
    this.router.paramMap.subscribe(params=>{
      this.email = String(params.get('email'));
      console.log(this.email)
    });
  }

  onSubmit() {
    if (this.studentForm.valid) {
      console.log('Registration Data:', this.studentForm.value);
      // Add your registration logic here
    }
  }
}
