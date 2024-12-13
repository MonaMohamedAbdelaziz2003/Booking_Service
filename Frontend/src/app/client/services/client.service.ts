import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserStorageService } from '../../basic/services/storage/user-storage.service';
import { Observable } from 'rxjs';
const BASIC_URL='http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  constructor(private http:HttpClient) {}

  getAdpost():Observable<any>{
    return this.http.get(BASIC_URL+`api/v1/auth/ads`);
  }

  searchByName(name:string):Observable<any>{
    return this.http.get(BASIC_URL+`api/v1/auth/search/${name}`);
  }

  getAdByid(id:any):Observable<any>{
    return this.http.get(BASIC_URL+`api/v1/auth/adById/${id}`);
  }


  bookservice(bookDto:any):Observable<any>{
    return this.http.post(BASIC_URL+"api/v1/auth/book-service",bookDto);
  }

  review(review:any):Observable<any>{
    return this.http.post(BASIC_URL+"api/v1/auth/review",review);
  }

  Mybookservice():Observable<any>{
    const id=UserStorageService.getUserId();
    return this.http.get(BASIC_URL+`api/v1/auth/myBooking/${id}`);
  }

  createAuthorizationHeader():HttpHeaders{
    let authHeader:HttpHeaders=new HttpHeaders();
    return authHeader.set(
      'Authorization',
      'Bearer' +UserStorageService.getToken()
    )
   }
}
