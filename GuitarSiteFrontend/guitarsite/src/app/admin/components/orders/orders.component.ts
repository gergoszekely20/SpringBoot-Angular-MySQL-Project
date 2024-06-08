import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css',
})
export class OrdersComponent {
  displayedColumns: string[] = [
    'idOrder',
    'orderDescription',
    'date',
    'amount',
    'address',
    'totalAmount',
    'orderStatus',
    'action',
  ];
  orders: any;

  constructor(
    private adminService: AdminService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.getPlacedOrders();
  }

  getPlacedOrders() {
    this.adminService.getAllOrders().subscribe((res) => {
      this.orders = res;
    });
  }

  changeOrderStatus(idOrder: number, status: string) {
    this.adminService.changeOrderStatus(idOrder, status).subscribe((res) => {
      if (res.idOrder != null) {
        this.snackBar.open('Order statuschanged', 'Close', {
          duration: 5000,
        });
        this.getPlacedOrders();
      } else {
        this.snackBar.open(' Somthing went wrong', 'Close', {
          duration: 5000,
        });
      }
    });
  }
}
