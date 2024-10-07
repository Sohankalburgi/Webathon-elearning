import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthserviceService {

  private loggedIn = new BehaviorSubject<boolean>(this.hasUserId());

  public isLoggedIn$ = this.loggedIn.asObservable();

  constructor() { }

  private hasUserId(): boolean {
    return !!sessionStorage.getItem('userId');
  }

  // Log the user in by setting the userId in session storage and updating the BehaviorSubject
  login(userId: string) {
    sessionStorage.setItem('userId', userId);
    this.loggedIn.next(true);  // Notify subscribers that the user is logged in
  }
  logout() {
    sessionStorage.removeItem('userId');
    this.loggedIn.next(false); // Notify subscribers that the user is logged out
  }


}
