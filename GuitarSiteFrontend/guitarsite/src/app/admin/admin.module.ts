import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DemoMaterialModule } from '../DemoMaterialModule';
import { PostProductComponent } from './post-product/post-product.component';
import { OrdersComponent } from './components/orders/orders.component';
import { ProductserviceComponent } from './components/productservice/productservice.component';
import { UpdateProductComponent } from './components/update-product/update-product.component';

@NgModule({
  declarations: [AdminComponent, HomepageComponent, PostProductComponent, OrdersComponent, ProductserviceComponent, UpdateProductComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    DemoMaterialModule,
  ],
})
export class AdminModule {}
