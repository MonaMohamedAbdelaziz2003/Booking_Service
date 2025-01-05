import { Component, ElementRef, ViewChild } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CompanyService } from '../company/services/company.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterModule,CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
 constructor(private companyServ:CompanyService,  private notification:NzNotificationService){}

  ngOnInit(){this.getAdpost();}

ads:any;
getAdpost(){
    this.companyServ.getAdpost().subscribe(res=>{
          this.ads=res;
      })
  }
  @ViewChild('serv') servElement: ElementRef;

  scrollToSection() {
    this.servElement.nativeElement.scrollIntoView({ behavior: 'smooth' });
  }
}
