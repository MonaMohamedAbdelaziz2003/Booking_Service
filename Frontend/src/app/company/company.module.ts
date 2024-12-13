// import { NgModule } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { CompanyRoutingModule } from './company-routing.module';
// import { CompanyDashboardComponent } from './pages/company-dashboard/company-dashboard.component';
// import { CreateAdComponent } from './pages/create-ad/create-ad.component';
// import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// import { RouterLink, RouterModule, RouterOutlet } from '@angular/router';
// import { NzButtonModule } from 'ng-zorro-antd/button';
// import { NzFormModule } from 'ng-zorro-antd/form';
// import { CompanyComponent } from './company.component';
// import { AllAdsComponent } from './pages/all-ads/all-ads.component';
// import { UpdateAdComponent } from './pages/update-ad/update-ad.component';
// import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';


// @NgModule({
//   declarations: [
//     CompanyDashboardComponent,
//     CreateAdComponent,
//     CompanyComponent,
//     AllAdsComponent,
//     UpdateAdComponent,
//   ],
//   imports: [
//     CommonModule,
//     CompanyRoutingModule,
//     FormsModule,
//     ReactiveFormsModule,
//     RouterModule,
//     RouterLink,
//     RouterOutlet,
//     NzDatePickerModule,
//     NzFormModule,
//     NzButtonModule,
//     // NzTableModule,


//   ],
//   // exports:[CompanyModule]
// })
// export class CompanyModule { }
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompanyRoutingModule } from './company-routing.module';
import { CompanyDashboardComponent } from './pages/company-dashboard/company-dashboard.component';
import { CreateAdComponent } from './pages/create-ad/create-ad.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzFormModule } from 'ng-zorro-antd/form';
import { CompanyComponent } from './company.component';
import { AllAdsComponent } from './pages/all-ads/all-ads.component';
import { UpdateAdComponent } from './pages/update-ad/update-ad.component';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { GetReviewComponent } from './pages/get-review/get-review.component';

@NgModule({
  declarations: [
    CompanyDashboardComponent,
    CreateAdComponent,
    CompanyComponent,
    AllAdsComponent,
    UpdateAdComponent,
    GetReviewComponent,
  ],
  imports: [
    CommonModule,
    CompanyRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    NzDatePickerModule,
    NzFormModule,
    NzButtonModule,
  ],
})
export class CompanyModule { }
