import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './basic/component/signup/signup.component';
import { SignupClientComponent } from './basic/component/signup-client/signup-client.component';
import { BrowserModule } from '@angular/platform-browser';
import { SignupCompanyComponent } from './basic/component/signup-company/signup-company.component';
import { LoginComponent } from './basic/component/login/login.component';
import { AppComponent } from './app.component';
import { ClientDashboardComponent } from './client/pages/client-dashboard/client-dashboard.component';
import { CompanyDashboardComponent } from './company/pages/company-dashboard/company-dashboard.component';
import { CreateAdComponent } from './company/pages/create-ad/create-ad.component';
import { AllAdsComponent } from './company/pages/all-ads/all-ads.component';
import { UpdateAdComponent } from './company/pages/update-ad/update-ad.component';
import { AdDetailsComponent } from './client/pages/ad-details/ad-details.component';
import { MyBookingComponent } from './client/pages/my-booking/my-booking.component';
import { ReviewComponent } from './client/pages/review/review.component';
import { GetReviewComponent } from './company/pages/get-review/get-review.component';
import { HomeComponent } from './home/home.component';

export const routes: Routes = [
  {path:'client',loadChildren:()=>import('./client/client.module').then(m=>m.ClientModule)},
  {path:'company',loadChildren:()=>import('./company/company.module').then(m=>m.CompanyModule)},
  {path:'signup-client',component:SignupClientComponent},
  {path:'home',component:HomeComponent},
  // {path:'signup/signup-client',component:SignupClientComponent},
  {path:'signup/signup-company',component:SignupCompanyComponent},
  {path:'signin',component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path:'client/dashboard',component:ClientDashboardComponent},
  {path:'company/dashboard',component:CompanyDashboardComponent},
  {path:'company/reviews',component:GetReviewComponent},
  {path:'company/ad',component:CreateAdComponent},
  {path:'ads',component:AllAdsComponent},
  {path:'ads/update/:id',component:UpdateAdComponent},
  {path:'client/dashboard/ad/:id',component:AdDetailsComponent},
  {path:'client/mybooking',component:MyBookingComponent},
  {path:'client/mybooking/review/:id',component:ReviewComponent},

];

@NgModule({
  imports: [
    BrowserModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutes { }
