import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { UserStorageService } from '../storage/user-storage.service';

const BASIC_URL = 'http://localhost:9191';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(
    private http: HttpClient,
    private userStorageService: UserStorageService
  ) {}

  register(signupRequest: any): Observable<any> {
    return this.http.post(BASIC_URL + '/auth/register', signupRequest);
  }

  login(email: String, password: String) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const body = { email, password };

    return this.http
      .post(BASIC_URL + '/auth/login', body, { headers, observe: 'response' })
      .pipe(
        map((res) => {
          console.log(res);
          // const token = res.headers.get('authorization')?.substring(7);
          const user = res.body;

          if (/*token && */ user) {
            //this.userStorageService.saveToken(token);
            this.userStorageService.saveUser(user);
            return true;
          }
          return false;
        })
      );
  }
}
