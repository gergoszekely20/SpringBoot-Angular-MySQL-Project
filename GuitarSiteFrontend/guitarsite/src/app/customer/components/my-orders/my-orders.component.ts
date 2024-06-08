import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrl: './my-orders.component.css',
})
export class MyOrdersComponent {
  displayedColumns: string[] = [
    'idOrder',
    'orderDescription',
    'date',
    'amount',
    'address',
    'orderStatus',
  ];

  myOrders: any;

  constructor(private customerService: CustomerService) {}

  ngOnInit() {
    this.getMyOrders();
  }
  getMyOrders() {
    this.customerService.getMyOrders().subscribe((res) => {
      this.myOrders = res;
    });
  }
}
