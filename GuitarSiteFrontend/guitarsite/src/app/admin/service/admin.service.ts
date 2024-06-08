import { HttpClient, HttpHandler, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../../services/storage/user-storage.service';

const BASIC_URL = 'http://localhost:9191';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  constructor(private http: HttpClient) {}

  addProduct(product: any): Observable<any> {
    return this.http.post(BASIC_URL + '/addGuitar', product);
  }

  getAllProduct(): Observable<any> {
    return this.http.get(BASIC_URL + '/showGuitars');
  }

  getAllOrders(): Observable<any> {
    return this.http.get(BASIC_URL + '/showOrders');
  }

  getProductById(idGuitar): Observable<any> {
    return this.http.get(BASIC_URL + `/guitarById/${idGuitar}`);
  }

  changeOrderStatus(idOrder: number, status: string): Observable<any> {
    return this.http.get(BASIC_URL + `/changeStatus/${idOrder}/${status}`);
  }

  changeServiceStatus(idService: number, status: string): Observable<any> {
    return this.http.get(
      BASIC_URL + `/changeServiceStatus/${idService}/${status}`
    );
  }

  getAllServices(): Observable<any> {
    return this.http.get(BASIC_URL + '/showGuitarServices');
  }

  deleteProduct(idGuitar: any): Observable<any> {
    return this.http.delete(BASIC_URL + `/guitarDelete/${idGuitar}`);
  }

  updateProduct(idGuitar: any, guitarDto: any): Observable<any> {
    return this.http.put(BASIC_URL + `/guitarUpdate/${idGuitar}`, guitarDto);
  }
}
