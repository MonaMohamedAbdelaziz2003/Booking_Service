import { ApplicationRef, NgModule } from '@angular/core';
import { CompanyModule } from './company/company.module';
import { ClientModule } from './client/client.module';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClient, HttpClientModule, provideHttpClient, withFetch } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterModule, RouterOutlet } from '@angular/router';
import { SignupClientComponent } from './basic/component/signup-client/signup-client.component';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { SignupCompanyComponent } from './basic/component/signup-company/signup-company.component';
import { LoginComponent } from './basic/component/login/login.component';
import { AppComponent } from './app.component';
import { CompanyRoutingModule } from './company/company-routing.module';
import { SignupComponent } from './basic/component/signup/signup.component';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';

@NgModule({
declarations:[
      SignupClientComponent,
      SignupCompanyComponent,
      SignupComponent,
      LoginComponent,
],
imports: [
    CommonModule,
    CompanyModule,
    ClientModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RouterModule,
    RouterLink,
    RouterOutlet,
    NzFormModule,
    NzButtonModule,
    CompanyRoutingModule,
    NzDatePickerModule,
    // NzTableModule
    // NgZorroAntdModule ,
    // DemoNgZorroAntdModuleModule,

  ],
  exports:[RouterModule],
  // bootstrap:[AppComponent]
})
export class AppModule {
  // constructor(private appRef: ApplicationRef) {}
  // ngDoBootstrap() {
  //   this.appRef.bootstrap(AppComponent); // Bootstrap AppComponent programmatically
  // }
}
