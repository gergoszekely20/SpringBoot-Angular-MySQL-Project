import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { GuitarTypeOptions, GuitarType } from '../../../models/guitar-types';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css'],
})
export class UpdateProductComponent implements OnInit {
  productForm: FormGroup;
  selectedFile: File | null = null;
  imagePreview: string | ArrayBuffer | null = null;
  guitarTypeOptions = GuitarTypeOptions;

  idGuitar = this.activatedroute.snapshot.params['idGuitar'];

  existingImage: string | null = null;

  imageChanged: boolean = false;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService,
    private activatedroute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.productForm = this.fb.group({
      name: [null, [Validators.required]],
      type: [null, [Validators.required]],
      price: [null, [Validators.required]],
      guitarInfo: [null, [Validators.required]],
      quantity: [null, [Validators.required]],
    });

    this.getProductById();
  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
      this.previewImage();
      this.imageChanged = true;
      this.existingImage = null;
    }
  }

  previewImage() {
    if (!this.selectedFile) {
      return;
    }
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(this.selectedFile);
  }

  getProductById() {
    this.adminService.getProductById(this.idGuitar).subscribe((res) => {
      this.productForm.patchValue(res);
      this.existingImage = 'data:image/png;base64,' + res.image;
    });
  }

  updateProduct(): void {
    if (this.productForm.valid) {
      const formData = new FormData();

      if (this.imageChanged && this.selectedFile) {
        formData.append('image', this.selectedFile);
      }

      formData.append('name', this.productForm.get('name')?.value);
      formData.append('type', this.productForm.get('type')?.value);
      formData.append('price', this.productForm.get('price')?.value);
      formData.append('guitarInfo', this.productForm.get('guitarInfo')?.value);
      formData.append('quantity', this.productForm.get('quantity')?.value);

      this.adminService.updateProduct(this.idGuitar, formData).subscribe(
        (res) => {
          if (res.idGuitar != null) {
            this.snackBar.open('Product updated successfully', 'Close', {
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
      Object.keys(this.productForm.controls).forEach((field) => {
        const control = this.productForm.get(field);
        control?.markAsDirty();
        control?.updateValueAndValidity();
      });
    }
  }
}
