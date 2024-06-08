import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { PlaceOrderComponent } from '../place-order/place-order.component';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css',
})
export class CartComponent {
  cartItems: any[] = [];
  order: any;

  constructor(
    private customerService: CustomerService,
    private snackBar: MatSnackBar,
    private fb: FormBuilder,
    public dialog: MatDialog
  ) {}

  ngOnInit() {
    this.getCart();
  }

  getCart() {
    this.cartItems = [];
    this.customerService.getCartByUserId().subscribe((res) => {
      this.order = res;

      res.cartItems.forEach((element) => {
        element.cartImage = 'data:image/png;base64,' + element.image;
        this.cartItems.push(element);
      });
    });
  }

  increaseQuantity(idGuitar: any) {
    this.customerService.increaseQuantity(idGuitar).subscribe((res) => {
      this.snackBar.open('Product quantity increased.', 'Close', {
        duration: 5000,
      });

      this.getCart();
    });
  }

  decreaseQuantity(idGuitar: any) {
    this.customerService.decreaseQuantity(idGuitar).subscribe((res) => {
      this.snackBar.open('Product quantity decreased.', 'Close', {
        duration: 5000,
      });
      this.getCart();
    });
  }

  placeOrder() {
    this.dialog.open(PlaceOrderComponent);
  }
}
