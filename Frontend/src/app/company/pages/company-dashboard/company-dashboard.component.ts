import { Component } from '@angular/core';
import { CompanyService } from '../../services/company.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-company-dashboard',
  templateUrl: './company-dashboard.component.html',
  styleUrl: './company-dashboard.component.scss'
})
export class CompanyDashboardComponent {
constructor(private companyserv:CompanyService, private notification:NzNotificationService){}
booking:any;
ngOnInit(){
  this.getAllBooking();
}

getAllBooking(){
  this.companyserv.getBooking().subscribe(res=>{
    this.booking=res;
    console.log(res);
  })
}
changebookingstatus(bookingId:Number,status:String){
  this.companyserv.changebookingstatus(bookingId,status).subscribe(
    success=>{
      console.log(success);
      this.notification
      .success(
        'success',
        'Booking Change Successfully',
        {nzDuration:5000}
    );
    this.getAllBooking();
    }
    ,error=>{
      this.notification
      .error(
        'error',
        `${error.error}`,
        {nzDuration:5000}
    );}
  )
}

getStatusClass(status: string): string {
  switch (status) {
      case 'APPROVED':
          return 'status-approved';
      case 'REJECTED':
          return 'status-rejected';
      case 'PENDING':
          return 'status-pending';
      default:
          return '';
  }
}


}
