import { Component } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { UserStorageService } from '../../../basic/services/storage/user-storage.service';
import { json } from 'stream/consumers';

@Component({
  selector: 'app-ad-details',
  // standalone: true,
  // imports: [],
  templateUrl: './ad-details.component.html',
  styleUrl: './ad-details.component.scss'
})

export class AdDetailsComponent {

  ad:any;
  review:any;
  validateForm:FormGroup;
  adId=this.activatedrout.snapshot.params['id'];


  constructor(private clientServ:ClientService
  ,private activatedrout:ActivatedRoute
  ,private notification:NzNotificationService,
  private router:Router,
  private fb:FormBuilder){}

ngOnInit(){
  this.getdetails();

  this.validateForm=this.fb.group({
    bookDto:['',[Validators.required]]
  })

}

    getdetails(){
      this.clientServ.getAdByid(this.adId).subscribe(
        res=>{
        this.ad=res.adDTO;
        this.review=res.reviewDate;
        console.log(res);
        console.log(res.adDTO.id);
        // const bookId = res.reviewDate[0].serviceName;

        // console.log( res.reviewDate[0].serviceName);
      })
      // console.log( this.review[0].serviceName);

    }

    bookService(){

      const book={
        bookData:this.validateForm.get(['bookDto']).value,
        adid:this.adId,
        userid:UserStorageService.getUserId()
      }

      this.clientServ.bookservice(book)
      .subscribe(
        (res)=>{
        this.notification
        .success(
          'success',
          'Booking success',
          {nzDuration:5000}
      );
    this.router.navigateByUrl('client/booking');
    }
    , error=>{
      this.notification
      .error(
        'error',
        `${error.error}`,
        {nzDuration:5000},
      )
      }
    )
    }



}
