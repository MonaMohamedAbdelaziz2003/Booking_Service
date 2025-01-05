import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { UserStorageService } from '../storage/user-storage.service';
// api
const BASIC_URL='http://localhost:8080';
const AUTH_HEADER = "Authorization";

@Injectable({
  providedIn: 'root'
})

export class AuthService {

constructor(private http:HttpClient,private userStorgeServ:UserStorageService) { }

  signUpClient(dataOfClient:any): Observable<any> {
    return this.http.post(BASIC_URL+"/api/v1/auth/client/sign-up",dataOfClient);
  }
  signUpCompany(dataOfCompany:any): Observable<any> {
    console.log(dataOfCompany)
    return this.http.post(BASIC_URL+"/api/v1/auth/company/sign-up",dataOfCompany);
  }

  // add(dataOfCompany:any): Observable<any> {
  //   return this.http.post(BASIC_URL+"/api/v1/auth/add",dataOfCompany);
  // }
  // getdata(): Observable<any> {
  //    return this.http.get<any[]>(BASIC_URL + "/api/v1/auth/getpro");
  // }


  login(dataOfUser:any) {
    console.log('1',dataOfUser)
    return this.http.post(BASIC_URL+"/api/v1/auth/signin", dataOfUser,{observe:"response"})
    .pipe(
      map((res:HttpResponse<any>)=>{
        console.log(res.body);
        this.userStorgeServ.saveUser(res.body);
        const tokenLength=res.headers.get(AUTH_HEADER)?.length;
        const BearerToken=res.headers.get(AUTH_HEADER)?.substring(7,tokenLength);
        console.log(BearerToken);
        this.userStorgeServ.saveToken(BearerToken);

        return res;
      })
    );
  }


}
