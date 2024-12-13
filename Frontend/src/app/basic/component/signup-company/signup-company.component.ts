import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { AuthService } from '../../services/auth/auth.service';
@Component({
  selector: 'app-signup-company',
  // standalone: true,
  // imports: [ NzFormModule,NzButtonModule,],
  templateUrl: './signup-company.component.html',
  styleUrl: './signup-company.component.scss'
})
export class SignupCompanyComponent {
  validateForm!:FormGroup;
  constructor(private fb:FormBuilder,
    private authService:AuthService,
    private notification:NzNotificationService,
    private router:Router){}

ngOnInit(){
    this.validateForm=this.fb.group({
      email:['',[Validators.email,Validators.required]],
      firstname:['',[Validators.required]],
      lastname:['',[Validators.required]],
      phone:['',[Validators.required]],
      password:['',[Validators.required,Validators.minLength(6)]],
      chickpassword:['',[Validators.required,Validators.minLength(6)]],
    })
}


field:string='';
signUpCompany(){
  if (this.validateForm.valid) {
    this.field='';
    if( this.validateForm.get('password')?.value == this.validateForm.get('chickpassword')?.value){
      this.field='';
        this.authService.signUpCompany(this.validateForm.value)
        .subscribe(res=>{
    this.notification
    .success(
      'success',
      'signup success',
      {nzDuration:5000}
  );
  this.router.navigateByUrl('signin');
}, error=>{
  this.notification
  .error(
    'error',
    `${error.error}`,
    {nzDuration:5000},
  )
  });
}else{
  this.field="check password";
}
}else{
    this.field="check if fields is full";
  }
}
}
