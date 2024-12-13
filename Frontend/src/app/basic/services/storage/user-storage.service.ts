import { Injectable } from '@angular/core';
import { json } from 'stream/consumers';

const TOKEN='s_token';
const USER='s_user';

@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() { }

  public saveToken(token:string):void{
    if (typeof localStorage !== 'undefined') {
       window.localStorage.removeItem(TOKEN);
       window.localStorage.setItem(TOKEN,token);
    } else {
      console.log('localStorage is not available.');
    }
  }
  static getToken():any{
    if (typeof localStorage !== 'undefined') {

      return localStorage.getItem(TOKEN);
    }
  }
  public saveUser(user:string):void{
    if (typeof localStorage !== 'undefined') {
      console.log(user);
      window.localStorage.removeItem(USER);
      window.localStorage.setItem(USER,JSON.stringify(user));
    }
  }

  static getUser():any{
    if (typeof localStorage !== 'undefined') {
      return JSON.parse(localStorage.getItem(USER));
    }
  }

  static getUserId():string{
    const user =this.getUser();
    if(user===null){return '';}
    return user.userId;
  }

  static getUserRole():string{
    const user =this.getUser();
    if(user==null){return '';}
    console.log(user.role)
    return user.role;
  }

  static isClientLogIn(){
    if(this.getToken()==null){
      return false;
    }
    const role:string=this.getUserRole();
    return role=="CLIENT";
  }

  static isCompnyLogIn(){
    if(this.getToken()==null){
      return false;
    }
    const role:string=this.getUserRole();
    console.log("company"+role === "COMPANY");
    return role === "COMPANY";
  }

  static signOut(){
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }
}
