import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserStorageService } from '../../../services/storage/user-storage.service';
import { Guitar } from '../../../models/guitar';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css',
})
export class HomepageComponent {
  products: Guitar[] = [];
  isUserLoggedIn: boolean = UserStorageService.isUserLoggedIn();

  constructor(
    private customerService: CustomerService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.getAllProducts();
  }

  getAllProducts() {
    this.products = [];
    this.customerService.getAllProduct().subscribe((res) => {
      res.forEach((element) => {
        element.image = 'data:image/png;base64,' + element.image;
        this.products.push(element);
      });
    });
  }

  addToCart(idGuitar: any) {
    this.customerService.addToCart(idGuitar).subscribe((res) => {
      //If the last product was added to cart
      if (res === 0) {
        this.products = this.products.filter(
          (product) => product.idGuitar !== idGuitar
        );

        this.snackBar.open(
          'The last product of this type was added to the cart!',
          'Close',
          {
            duration: 5000,
          }
        );
      } else {
        this.snackBar.open('Product added to the cart!', 'Close', {
          duration: 5000,
        });
      }
    });
  }
}
