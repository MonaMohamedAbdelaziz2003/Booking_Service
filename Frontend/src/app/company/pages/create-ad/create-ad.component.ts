import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { CompanyService } from '../../services/company.service';
import { error } from 'console';
import { AuthService } from '../../../basic/services/auth/auth.service';

@Component({
  selector: 'app-create-ad',
  // standalone: true,
  // imports: [],
  templateUrl: './create-ad.component.html',
  styleUrl: './create-ad.component.scss'
})
export class CreateAdComponent {
selectFile:File|null;
imgPreview:String | ArrayBuffer|null;
validateForm:FormGroup;
constructor(private fb:FormBuilder,
  private notification:NzNotificationService,
  private companyServ:CompanyService,
private router:Router){}

ngOnInit(){
  this.validateForm=this.fb.group({
    serviceName:[null,[Validators.required]],
    description:[null,[Validators.required]],
    price:[null,[Validators.required]],
  })}

  onFileSelected(event:any){
    this.selectFile=event.target.files[0];
    this.previewImg();
  }

  previewImg(){
    const reader =new FileReader();
    reader.onload=()=>{
      this.imgPreview=reader.result;
    }
    reader.readAsDataURL(this.selectFile);
  }

  postAs(){
    const formData:FormData=new FormData();
    // formData.append('img',this.selectFile);
    formData.append('serviceName',this.validateForm.get('serviceName').value);
    formData.append('description',this.validateForm.get('description').value);
    formData.append('price',this.validateForm.get('price').value);

    this.companyServ.postAd(formData).subscribe(
      success=>{
        this.notification
        .success(
          'success',
          'posted success',
          {nzDuration:5000}
      );
        this.router.navigateByUrl("company/dashboard")
      },error=>{
        this.notification
        .error(
          'error',
          `${error.error}`,
          {nzDuration:5000}
      );
      }
    )
  }


}


