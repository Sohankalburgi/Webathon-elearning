import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';
import { Form, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginServiceService } from './Services/login-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  loginForm : FormGroup;

  registerForm : FormGroup;

  passwordPattern:string ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
  emailPattern: string = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";

  constructor(private renderer: Renderer2, private el: ElementRef,private fb : FormBuilder,
    private loginService:LoginServiceService, private router:Router) {
    this.loginForm = this.fb.group({
      'email': [null, [Validators.required, Validators.email,Validators.pattern(this.emailPattern)]],
      'password': [null, [Validators.required, Validators.minLength(8),Validators.pattern(this.passwordPattern)]],
    });

    this.registerForm = this.fb.group({
      'email': [null, [Validators.required, Validators.email,Validators.pattern(this.emailPattern)]],
      'password': [null, [Validators.required, Validators.minLength(8),Validators.pattern(this.passwordPattern)]],
      'role' : [null,[Validators.required]]
    });
  }

  ngOnInit(): void {
    

  }

  onSignUp() {
    const container = this.el.nativeElement.querySelector('#container');
    this.renderer.addClass(container, 'right-panel-active');
  }

  onSignIn() {
    const container = this.el.nativeElement.querySelector('#container');
    this.renderer.removeClass(container, 'right-panel-active');
  }

  onSubmitRegister() {
    console.log(this.registerForm.value);
    const email = this.registerForm.value.email;
    this.loginService.checkuser(this.registerForm.value.email).subscribe(data=>{
      if(data==false){
        this.loginService.registerUser(this.registerForm.value).subscribe(res=>{
          console.log(res)
          if(res.message=="Created"){
            
            if(this.registerForm.value.role=="student"){
            
            this.router.navigate([`/studentregister/${email}`])}
            else{
              this.router.navigate([`/teacherregister/${email}`])
            }
          }
        },(error)=>{
          alert("Internal Server Error")
        })
      }
      else{
        alert("User already Exists Please login")
      }
    })
    

  }
    
  onSubmitlogin() {
   this.loginService.loginUser(this.loginForm.value).subscribe(data=>{
    console.log(data)
    if(data){
      alert("Login Succesful")
      if(data.role=='student'){
      this.router.navigate([`/dashboard/${data.id}`])
      }
      else{
        this.router.navigate([`/teacherdash/${data.id}`])
      }
    }
    else{
      alert("Please Sign in")
    }
   })
    }
}
