import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { UserStorageService } from '../../services/storage/user-storage.service';
import { CartItem } from '../../models/cart-item';

const BASIC_URL = 'http://localhost:9191';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  constructor(private http: HttpClient) {}

  getAllProduct(): Observable<any> {
    return this.http.get(BASIC_URL + '/showGuitars');
  }

  addToCart(idGuitar: any): Observable<any> {
    const cartItem: CartItem = {
      idProduct: idGuitar,
      idUser: UserStorageService.getUserId(),
    };
    return this.http.post(BASIC_URL + '/addToCart', cartItem);
  }

  increaseQuantity(idGuitar: any): Observable<any> {
    const cartItem: CartItem = {
      idProduct: idGuitar,
      idUser: UserStorageService.getUserId(),
    };
    return this.http.post(BASIC_URL + '/increaseQuantity', cartItem);
  }

  decreaseQuantity(idGuitar: any): Observable<any> {
    const cartItem: CartItem = {
      idProduct: idGuitar,
      idUser: UserStorageService.getUserId(),
    };
    return this.http.post(BASIC_URL + '/decreaseQuantity', cartItem);
  }

  placeOrder(orderDTO: any): Observable<any> {
    orderDTO.idUser = UserStorageService.getUserId();
    return this.http.post(BASIC_URL + '/saveOrder', orderDTO);
  }

  getMyOrders(): Observable<any> {
    const idUser = UserStorageService.getUserId();
    return this.http.get(BASIC_URL + `/myorders/${idUser}`);
  }

  getCartByUserId(): Observable<any> {
    const idUser = UserStorageService.getUserId();
    return this.http.get(BASIC_URL + `/getCartDetails/${idUser}`);
  }

  addProductService(guitarService: any): Observable<any> {
    return this.http.post(BASIC_URL + '/addGuitarService', guitarService);
  }
}
