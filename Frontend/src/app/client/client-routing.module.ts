import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientComponent } from './client.component';
import { ClientDashboardComponent } from './pages/client-dashboard/client-dashboard.component';

const routes: Routes = [
  {path:'',component:ClientComponent},
  {path:'client/dashboard',component:ClientDashboardComponent},
  // {path:'client/bookung',component:ClientDashboardComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
