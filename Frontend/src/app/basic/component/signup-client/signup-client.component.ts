import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup-client',
  // standalone: true,
  // imports: [RouterModule,RouterOutlet],
  templateUrl: './signup-client.component.html',
  styleUrl: './signup-client.component.scss'
})

export class SignupClientComponent {
validateForm!:FormGroup;
constructor(private fb:FormBuilder,
  private authService:AuthService,
  private notification:NzNotificationService,
  private router:Router){
}

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
signupClient(){
  if (this.validateForm.valid) {
    this.field='';
    if( this.validateForm.get('password')?.value == this.validateForm.get('chickpassword')?.value){
      this.field='';
        this.authService.signUpClient(this.validateForm.value)
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
