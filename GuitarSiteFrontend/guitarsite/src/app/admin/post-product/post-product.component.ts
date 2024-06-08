import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Route, Router } from '@angular/router';
import { AdminService } from '../service/admin.service';
import { GuitarType, GuitarTypeOptions } from '../../models/guitar-types';

@Component({
  selector: 'app-post-product',
  templateUrl: './post-product.component.html',
  styleUrl: './post-product.component.css',
})
export class PostProductComponent {
  productForm: FormGroup;
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;
  guitarTypeOptions = GuitarTypeOptions;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService
  ) {}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(this.selectedFile);
  }

  ngOnInit(): void {
    this.productForm = this.fb.group({
      name: [null, [Validators.required]],
      type: [null, [Validators.required]],
      price: [null, [Validators.required]],
      guitarInfo: [null, [Validators.required]],
      quantity: [null, [Validators.required]],
    });
  }

  addProduct(): void {
    if (this.productForm.valid) {
      const formData = new FormData();
      formData.append('name', this.productForm.get('name').value);
      formData.append('type', GuitarType[this.productForm.get('type').value]);
      formData.append('price', this.productForm.get('price').value);
      formData.append('guitarInfo', this.productForm.get('guitarInfo').value);
      formData.append('quantity', this.productForm.get('quantity').value);
      formData.append('image', this.selectedFile);

      this.adminService.addProduct(formData).subscribe(
        (res) => {
          if (res.idGuitar != null) {
            this.snackBar.open('Product added successfully', 'Close', {
              duration: 5000,
            });
            this.router.navigateByUrl('/admin/homepage');
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
        this.productForm.contains[i].markAsDirty();
        this.productForm.contains[i].updateValueAndValidity();
      }
    }
  }
}
