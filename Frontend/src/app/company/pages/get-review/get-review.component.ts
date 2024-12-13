import { Component } from '@angular/core';
import { CompanyService } from '../../services/company.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-get-review',
  // standalone: true,
  // imports: [],
  templateUrl: './get-review.component.html',
  styleUrl: './get-review.component.scss'
})
export class GetReviewComponent {
  constructor(private companyserv:CompanyService, private notification:NzNotificationService){}
  reviews:any;
  ngOnInit(){
    this.getAllReviews();
  }

  getAllReviews(){
    this.companyserv.getReview().subscribe(res=>{
      this.reviews=res;
      console.log(res);
    })
  }

  deleteReview(id:any){
    this.companyserv.deletereview(id).subscribe(
      success=>{
        console.log(success);
        this.notification
        .success(
          'success',
          'deleted success',
          {nzDuration:5000}
      );
        this.getAllReviews();
      }
      ,error=>{
        this.notification
        .error(
          'error',
          `${error.error}`,
          {nzDuration:5000}
      );}
    )}

}
