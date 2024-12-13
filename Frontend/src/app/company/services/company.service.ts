import { HttpClient, HttpHandler, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../../basic/services/storage/user-storage.service';

const BASIC_URL='http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http:HttpClient) {}
   postAd(adDTO:any):Observable<any>{
    const userId=UserStorageService.getUserId();
    return this.http.post(BASIC_URL+`api/v1/auth/ad/${userId}`,adDTO);//,{headers:this.createAuthorizationHeader()}
   }

   updateAd(adId:any,adDTO:any):Observable<any>{
    return this.http.put(BASIC_URL+`api/v1/auth/ad/${adId}`,adDTO);//,{headers:this.createAuthorizationHeader()}
   }


   deleteAd(adId:any):Observable<any>{
    return this.http.delete(BASIC_URL+`api/v1/auth/ad/${adId}`);//,{headers:this.createAuthorizationHeader()}
   }
   deletereview(reviewId:any):Observable<any>{
    return this.http.delete(BASIC_URL+`api/v1/auth/review/${reviewId}`);//,{headers:this.createAuthorizationHeader()}
   }

   getAdpost():Observable<any>{
    return this.http.get(BASIC_URL+`api/v1/auth/ads`);
   }

   getAd(adId:any):Observable<any>{
    return this.http.get(BASIC_URL+`api/v1/auth/ad/${adId}`);
   }

   getBooking():Observable<any>{
    const companyId=UserStorageService.getUserId();
    return this.http.get(BASIC_URL+`api/v1/auth/Booking/${companyId}`);
   }
   getReview():Observable<any>{
    return this.http.get(BASIC_URL+`api/v1/auth/reviews`);
   }

   changebookingstatus(bookingId:Number,status:String):Observable<any>{
    return this.http.get(BASIC_URL+`api/v1/auth/booking/${bookingId}/${status}`);
   }


   createAuthorizationHeader():HttpHeaders{
    let authHeader:HttpHeaders=new HttpHeaders();
    return authHeader.set(
      'Authorization',
      'Bearer' +UserStorageService.getToken()
    )

   }
}
