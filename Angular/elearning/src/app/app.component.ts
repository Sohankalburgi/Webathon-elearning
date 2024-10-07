import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthserviceService } from './services/authservice.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  isLoggedIn : boolean = false;
  title = 'elearning';

  ngOnInit(): void {
    // Subscribe to the isLoggedIn$ observable to update the UI reactively
    this.authService.isLoggedIn$.subscribe(status => {
      this.isLoggedIn = status;
    });
  }
  constructor(private router:Router, private authService:AuthserviceService){
   
  }

  onSubmit(arg0: string) {
    this.router.navigate([`/search/${arg0}`])
    }
    logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
