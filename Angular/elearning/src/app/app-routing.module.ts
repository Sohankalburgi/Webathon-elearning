import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './Components/login/login.component';
import { StudentregisterComponent } from './Components/StudentRegister/studentregister/studentregister.component';
import { TeacherRegisterComponent } from './Components/TeacherRegister/teacher-register/teacher-register.component';
import { DashboardComponent } from './Components/dashboard/dashboard/dashboard.component';
import { TeacherdashboardComponent } from './Components/TeacherDashboard/teacherdashboard/teacherdashboard.component';
import { CourseregisterComponent } from './Components/CoursesRegister/courseregister/courseregister.component';
import { SearchComponent } from './Components/Search/search/search.component';
import { VideoplayComponent } from './Components/videoplay/videoplay/videoplay.component';

const routes: Routes = [
  {
    path:'',
    component:DashboardComponent
  }
  ,{
    path: 'login',
    component: LoginComponent
  },
  {
    path:'studentregister/:email',
    component:StudentregisterComponent
  },
  {
    path:'teacherregister/:email',
    component:TeacherRegisterComponent
  },
  {
    path:'dashboard',
    component:DashboardComponent
  },
  {
    path:'teacherdash/:id',
    component:TeacherdashboardComponent
  },
  {
    path:'teacherdash/:id/courseregister',
    component:CourseregisterComponent
  },
  {
    path:'dashboard/:id',
    component:DashboardComponent
  },
  {
    path:'search/:term',
    component:SearchComponent
  },
  {
    path:'video/:courseId',
    component:VideoplayComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

}
