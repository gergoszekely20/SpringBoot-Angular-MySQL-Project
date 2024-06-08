import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css',
})
export class HomepageComponent {
  products: any[] = [];
  constructor(
    private adminService: AdminService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.getAllProducts();
  }

  getAllProducts() {
    this.products = [];
    this.adminService.getAllProduct().subscribe((res) => {
      res.forEach((element) => {
        element.resource = 'data:image/png;base64,' + element.image;
        this.products.push(element);
      });
    });
  }

  deleteProduct(idGuitar: any) {
    this.adminService.deleteProduct(idGuitar).subscribe(
      (res) => {
        this.snackBar.open('Product deleted successfully!', 'OK', {
          duration: 5000,
        });
        this.getAllProducts();
      },
      (err) =>
        this.snackBar.open('Product could not be deleted', 'close', {
          duration: 5000,
        })
    );
  }
}
