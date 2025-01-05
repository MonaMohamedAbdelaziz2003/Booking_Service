import { Component } from '@angular/core';
import { AuthService } from '../../../basic/services/auth/auth.service';
import { ClientService } from '../../services/client.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-client-dashboard',
  templateUrl: './client-dashboard.component.html',
  styleUrl: './client-dashboard.component.scss'
})
export class ClientDashboardComponent {
data:any=[];
validateForm!:FormGroup;

constructor(private clientser:ClientService,private fb:FormBuilder){}

ngOnInit(){
  this.getAd();
  this.validateForm=this.fb.group({
    service:['',[Validators.required]]
  })
  if(this.validateForm.invalid){
    this.getAd();
  }
}
  getAd(){
    this.clientser.getAdpost().subscribe(res=>{
          this.data=res;
      })
    }
  
    searchByName(){
    console.log("1",this.validateForm.get(['service']).value)
    this.clientser.searchByName(this.validateForm.get(['service']).value).subscribe(res=>{
      this.data=res;
    })
  }

}

