import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  title = 'elearning';
constructor(private router:Router){}
  onSubmit(arg0: string) {
    this.router.navigate([`/search/${arg0}`])
    }
    logout(){
      sessionStorage.removeItem('userItem')
      sessionStorage.clear()
      this.router.navigate(['/login'])
    }
}
