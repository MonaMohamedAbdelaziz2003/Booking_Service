import { Component } from '@angular/core';
import { CompanyService } from '../../services/company.service';
import { ActivatedRoute, Router } from '@angular/router';
import { log } from 'console';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { AuthService } from '../../../basic/services/auth/auth.service';

@Component({
  selector: 'app-update-ad',
  // standalone: true,
  // imports: [],
  templateUrl: './update-ad.component.html',
  styleUrl: './update-ad.component.scss'
})
export class UpdateAdComponent {

adId:any=this.activate.snapshot.params['id'];

selectFile:File|null;
imgPreview:String | ArrayBuffer|null;
validateForm:FormGroup;
existImg:String|null=null;


constructor(private companyServ:CompanyService,private activate:ActivatedRoute,private fb:FormBuilder,
  private notification:NzNotificationService,
private router:Router){}

ngOnInit(){
  this.getAd();
  this.validateForm=this.fb.group({
    serviceName:[null,[Validators.required]],
    description:[null,[Validators.required]],
    price:[null,[Validators.required]],
  })
}

getAd(){
  this.companyServ.getAd(this.adId).subscribe(res=>{
    console.log(res);
    this.validateForm.patchValue(res);
    // this.existImg="data:image/jpeg;base64,"+res.imgreturned;
  })
}


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

  updateAds(){
    const formData:FormData=new FormData();
    // formData.append('img',this.selectFile);
    formData.append('serviceName',this.validateForm.get('serviceName').value);
    formData.append('description',this.validateForm.get('description').value);
    formData.append('price',this.validateForm.get('price').value);
console.log(this.companyServ.updateAd(this.adId,formData));
    this.companyServ.updateAd(this.adId,formData).subscribe(
      success=>{
        this.notification
        .success(
          'success',
          'updated success',
          {nzDuration:5000}
      );
        this.router.navigateByUrl("company/ad")
      },error=>{
        this.notification
        .error(
          'error',
          `${error.error}`,
          {nzDuration:5000}
      );
    })
  }
}
