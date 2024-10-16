import { Component, OnInit } from '@angular/core';
import { SearchserviceService } from '../Services/searchservice.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit {

  courses!: any[];
  constructor(private searchservice : SearchserviceService,private route:ActivatedRoute,private router:Router){}
  search : any;
  userId : any;
  ngOnInit(): void {

    this.userId = sessionStorage.getItem('userId')

    this.route.paramMap.subscribe(params=>{
      this.search = params.get('term');
    });
    this.searchservice.getSearch(this.search).then(data=>{
      this.courses = data.data;
      console.log(this.courses)
    })
  }
  registercoursebystudent(courseId:any){
    console.log(courseId)
    if(this.userId===null){
      alert('Please Login/Register from your account')
      this.router.navigate(['/login'])
    }
    
    this.searchservice.courseRegister(courseId,this.userId).then(data=>{
      console.log(data);
      this.router.navigate([`/dashboard/${this.userId}`])
    }).catch(error=>alert(error.response.data.message));
  }

    

}
