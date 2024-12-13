import { Component } from '@angular/core';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-my-booking',
  // standalone: true,
  // imports: [],
  templateUrl: './my-booking.component.html',
  styleUrl: './my-booking.component.scss'
})
export class MyBookingComponent {
constructor(private clientSer:ClientService){}

ngOnInit(){
this.getMyBooking();
}

mybooking:any;
getMyBooking(){
  this.clientSer.Mybookservice().subscribe(res=>{
    console.log(res)
    this.mybooking=res;
  })
}
}
