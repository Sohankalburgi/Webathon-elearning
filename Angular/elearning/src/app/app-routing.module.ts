import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './Components/login/login.component';
import { StudentregisterComponent } from './Components/StudentRegister/studentregister/studentregister.component';
import { TeacherRegisterComponent } from './Components/TeacherRegister/teacher-register/teacher-register.component';
import { DashboardComponent } from './Components/dashboard/dashboard/dashboard.component';
import { TeacherdashboardComponent } from './Components/TeacherDashboard/teacherdashboard/teacherdashboard.component';

const routes: Routes = [
  {
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
    path:'dashboard/:id',
    component:DashboardComponent
  },
  {
    path:'teacherdash/:id',
    component:TeacherdashboardComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

}
