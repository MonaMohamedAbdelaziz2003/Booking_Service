import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, RouterOutlet } from '@angular/router';
import { UserStorageService } from './basic/services/storage/user-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet,RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})

export class AppComponent {
  title = 'BookingServiceWeb';
  isClient:boolean;
  isCompny:boolean;
  constructor(private router:Router){
    if (typeof localStorage !== 'undefined') {
      this.isClient=UserStorageService.isClientLogIn();
      this.isCompny=UserStorageService.isCompnyLogIn();
      console.log("client",UserStorageService.isClientLogIn())
      console.log("company",UserStorageService.isCompnyLogIn())
    }

  }

ngOnInit(){
this.router.events.subscribe( event =>{
  this.isClient=UserStorageService.isClientLogIn();
  this.isCompny=UserStorageService.isCompnyLogIn();
  })
}

signOut(){
  UserStorageService.signOut();
  this.router.navigateByUrl('signin');
}
}
