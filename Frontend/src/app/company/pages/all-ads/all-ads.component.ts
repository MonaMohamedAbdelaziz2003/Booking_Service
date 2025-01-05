import { Component } from '@angular/core';
import { Router } from 'express';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { AuthService } from '../../../basic/services/auth/auth.service';
import { CompanyService } from '../../services/company.service';

@Component({
  selector: 'app-all-ads',
  // standalone: true,
  // imports: [],
  templateUrl: './all-ads.component.html',
  styleUrl: './all-ads.component.scss'
})
export class AllAdsComponent {
  constructor(private companyServ:CompanyService,  private notification:NzNotificationService
  ){}

  ngOnInit(){
      this.getAdpost();
  }

ads:any;
getAdpost(){
    this.companyServ.getAdpost().subscribe(res=>{
          this.ads=res;
      })
  }

  deleteAd(id:any){

    this.companyServ.deleteAd(id).subscribe(
      success=>{
        this.notification
        .success(
          'success',
          'deleted success',
          {nzDuration:5000}
      );
        this.getAdpost();
      },error=>{
        this.notification
        .error(
          'error',
          `${error.error}`,
          {nzDuration:5000}
      );}
    )

  }
  // updateimg(img){
  //   return 'data:image/jpeg;base64,'+img;
  // }
}
