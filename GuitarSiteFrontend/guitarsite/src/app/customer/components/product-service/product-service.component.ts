import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserStorageService } from '../../../services/storage/user-storage.service';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-product-service',
  templateUrl: './product-service.component.html',
  styleUrls: ['./product-service.component.css'],
})
export class ProductServiceComponent implements OnInit {
  productForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private customerService: CustomerService
  ) {}

  ngOnInit(): void {
    this.productForm = this.fb.group({
      idUser: UserStorageService.getUserId(),
      contactInfo: [null, [Validators.required]],
      guitarName: [null, [Validators.required]],
      type: [null, [Validators.required]],
      guitarProblem: [null, [Validators.required]],
    });
  }

  addProductService(): void {
    if (this.productForm.valid) {
      this.customerService.addProductService(this.productForm.value).subscribe(
        (res) => {
          if (res.idService != null) {
            this.snackBar.open('Product Service added successfully', 'Close', {
              duration: 5000,
            });
            this.router.navigateByUrl('/customer/homepage');
          } else {
            this.snackBar.open('Something went wrong', 'Close', {
              duration: 5000,
            });
          }
        },
        (err) => console.log(err)
      );
    } else {
      for (const i in this.productForm.controls) {
        if (this.productForm.controls.hasOwnProperty(i)) {
          this.productForm.controls[i].markAsDirty();
          this.productForm.controls[i].updateValueAndValidity();
        }
      }
    }
  }
}
