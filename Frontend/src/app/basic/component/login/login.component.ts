import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { AuthService } from '../../services/auth/auth.service';
import { UserStorageService } from '../../services/storage/user-storage.service';

@Component({
  selector: 'app-login',
  // standalone: true,
  // imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  validateForm!:FormGroup;
  constructor(private fb:FormBuilder,
    private authService:AuthService,
    private notification:NzNotificationService,
    private router:Router){}

  ngOnInit(){
    this.validateForm=this.fb.group({
      email:['',[Validators.email,Validators.required]],
      password:['',[Validators.required,Validators.minLength(6)]]
    })
  }

field:string='';
login(){
  if (this.validateForm.valid) {
    this.authService.login(this.validateForm.value)
    .subscribe(
      success =>{
        if(UserStorageService.isClientLogIn()){
          this.router.navigateByUrl('client/dashboard');
        }else {//if(UserStorageService.isCompnyLogIn())
          this.router.navigateByUrl('company/dashboard');
        }
        this.notification.success(
          'success',
          'login success',
          {nzDuration:5000})
    },
    error=>{
         console.log("error");
         this.notification
         .error(
              'error',
              'wrong',
              {nzDuration:5000},
            )
       }
    )
  }
else{
    this.field="check if fields is full";
  }
}
}

