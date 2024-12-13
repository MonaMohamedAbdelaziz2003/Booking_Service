import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { ClientService } from '../../services/client.service';
import { UserStorageService } from '../../../basic/services/storage/user-storage.service';

@Component({
  selector: 'app-review',
  // standalone: true,
  // imports: [],
  templateUrl: './review.component.html',
  styleUrl: './review.component.scss'
})
export class ReviewComponent {
  ad:any;
  validateForm:FormGroup;
  bookId=this.activatedrout.snapshot.params['id'];

  constructor(private clientServ:ClientService
    ,private activatedrout:ActivatedRoute
    ,private notification:NzNotificationService,
    private router:Router,
    private fb:FormBuilder){}

  ngOnInit(){
    this.validateForm=this.fb.group({
      rating:['',[Validators.required]],
      review:['',[Validators.required]]
    })
  }

  getReview(){
    const reviewDto={
      rating:this.validateForm.get("rating").value,
      review:this.validateForm.get("review").value,
      userId:UserStorageService.getUserId(),
      bookId:this.bookId
    }
console.log(reviewDto);
    this.clientServ.review(reviewDto)
      .subscribe(
        (res)=>{
          console.log(res);
        this.notification
        .success(
          'success',
          'Review success',
          {nzDuration:5000}
      );
    this.router.navigateByUrl('client/mybooking');
    }
    , error=>{
      this.notification
      .error(
        'error',
        `${error.error}`,
        {nzDuration:5000},
      )
      }
    )}


}
