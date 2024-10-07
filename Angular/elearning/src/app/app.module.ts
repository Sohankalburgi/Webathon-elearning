import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ImageCropperComponent } from 'ngx-image-cropper';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './Components/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { StudentregisterComponent } from './Components/StudentRegister/studentregister/studentregister.component';
import { TeacherRegisterComponent } from './Components/TeacherRegister/teacher-register/teacher-register.component';
import { DashboardComponent } from './Components/dashboard/dashboard/dashboard.component';
import { CourselComponent } from './Components/Coursel/coursel/coursel.component';
import { TeacherdashboardComponent } from './Components/TeacherDashboard/teacherdashboard/teacherdashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { CourseregisterComponent } from './Components/CoursesRegister/courseregister/courseregister.component';
import { SearchComponent } from './Components/Search/search/search.component';
import { VideoplayComponent } from './Components/videoplay/videoplay/videoplay.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    StudentregisterComponent,
    TeacherRegisterComponent,
    DashboardComponent,
    CourselComponent,
    TeacherdashboardComponent,
    CourseregisterComponent,
    SearchComponent,
    VideoplayComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
    HttpClientModule,
    ImageCropperComponent, 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
