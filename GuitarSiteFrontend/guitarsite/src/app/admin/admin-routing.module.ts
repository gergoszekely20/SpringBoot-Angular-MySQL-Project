import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { PostProductComponent } from './post-product/post-product.component';
import { OrdersComponent } from './components/orders/orders.component';
import { ProductserviceComponent } from './components/productservice/productservice.component';
import { UpdateProductComponent } from './components/update-product/update-product.component';

const routes: Routes = [
  { path: '', component: AdminComponent },
  { path: 'homepage', component: HomepageComponent },
  { path: 'product', component: PostProductComponent },
  { path: 'product/:idGuitar', component: UpdateProductComponent },
  { path: 'orders', component: OrdersComponent },
  { path: 'productservice', component: ProductserviceComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
