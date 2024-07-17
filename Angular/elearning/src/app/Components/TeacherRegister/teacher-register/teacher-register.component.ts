import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TeacherserviceService } from '../TeacherService/teacherservice.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginServiceService } from '../../login/Services/login-service.service';

@Component({
  selector: 'app-teacher-register',
  templateUrl: './teacher-register.component.html',
  styleUrl: './teacher-register.component.css'
})
export class TeacherRegisterComponent implements OnInit {
  teacherForm: FormGroup;

  constructor(private fb: FormBuilder,private teacherservice:TeacherserviceService,private router:ActivatedRoute
    ,private route:Router,private loginservice:LoginServiceService
  ) {
    this.teacherForm = this.fb.group({
      name: ['', Validators.required],
      dateofbirth: ['', Validators.required],
      highestqualification: ['', Validators.required],
      location: ['', Validators.required]
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
    if (this.teacherForm.valid) {
      
      console.log('Registration Data:', this.teacherForm.value);
      // Add your registration logic here
      
      this.teacherservice.registerTeacher(this.teacherForm.value,this.email).subscribe(data=>{
        if(data){
          this.loginservice.getUserId(this.email).subscribe(data=>{
            sessionStorage.setItem('userId',data)
            this.route.navigate([`/teacherdash/${data}`])
          })
        }
      })
    }
  }
}
