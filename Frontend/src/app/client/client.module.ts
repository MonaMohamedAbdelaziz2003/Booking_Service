import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientDashboardComponent } from './pages/client-dashboard/client-dashboard.component';
import { ClientComponent } from './client.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterLink, RouterModule, RouterOutlet } from '@angular/router';
import { AdDetailsComponent } from './pages/ad-details/ad-details.component';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { MyBookingComponent } from './pages/my-booking/my-booking.component';
import { ReviewComponent } from './pages/review/review.component';
import { NzRateModule } from 'ng-zorro-antd/rate';
import { NzCardModule } from 'ng-zorro-antd/card'; // Import NzCardModule

@NgModule({
  declarations: [
    ClientDashboardComponent,
    ClientComponent,
    AdDetailsComponent,
    MyBookingComponent,
    ReviewComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    RouterLink,
    RouterOutlet,
    NzDatePickerModule,
    NzFormModule,
    NzButtonModule,
    NzRateModule,
    NzCardModule
  ]
})
export class ClientModule { }
