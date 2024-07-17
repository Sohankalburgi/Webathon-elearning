import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentserviceService } from '../Services/studentservice.service';
import { LoginServiceService } from '../../login/Services/login-service.service';

@Component({
  selector: 'app-studentregister',
  templateUrl: './studentregister.component.html',
  styleUrl: './studentregister.component.css'
})
export class StudentregisterComponent implements OnInit{
  studentForm: FormGroup;

  constructor(private fb: FormBuilder,private router:ActivatedRoute,private studentservice:StudentserviceService,
    private route:Router,private loginservice:LoginServiceService
  ) {
    this.studentForm = this.fb.group({
      name: ['', Validators.required],
      highestqualification: ['', Validators.required],
      institutename: ['', Validators.required],
      dateofbirth: ['', Validators.required],
      location:['', Validators.required],
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
      this.studentservice.registerStudent(this.studentForm.value,this.email).subscribe(data=>{
        if(data){
          alert("Registration succesful")
          
          this.loginservice.getUserId(this.email).subscribe(res=>{
            sessionStorage.setItem('userId',res)
            this.route.navigate([`/dashboard/${res}`])
          })
          
        }
      });
      
    }
  }
}
