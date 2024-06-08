import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomerService } from '../../services/customer.service';
import { Route, Router } from '@angular/router';
import { Dialog } from '@angular/cdk/dialog';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-place-order',
  templateUrl: './place-order.component.html',
  styleUrl: './place-order.component.css',
})
export class PlaceOrderComponent {
  orderForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private customerService: CustomerService,
    private router: Router,
    public dialog: MatDialog
  ) {}

  ngOnInit() {
    this.orderForm = this.fb.group({
      orderDescription: [null],
      address: [null, [Validators.required]],
    });
  }

  placeOrder() {
    this.customerService.placeOrder(this.orderForm.value).subscribe((res) => {
      if (res.idUser != null) {
        this.snackBar.open('Order have been placed!', 'Close', {
          duration: 5000,
        });
        this.router.navigateByUrl('/customer/my__orders');
        this.closeForm();
      } else {
        this.snackBar.open('Something went wrong', 'Close', { duration: 5000 });
      }
    });
  }

  closeForm() {
    this.dialog.closeAll();
  }
}
