import { Token } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { User } from '../../models/user';
import { UserType } from '../../models/user-type';

const TOKEN = 'econ-token';
const USER = 'econ-user';

@Injectable({
  providedIn: 'root',
})
export class UserStorageService {
  constructor() {}

  public saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }

  public saveUser(user): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  static getToken(): any {
    return localStorage.getItem(TOKEN);
  }

  static getUser(): User {
    return JSON.parse(localStorage.getItem(USER));
  }

  static getUserId(): number {
    const user = this.getUser();

    return user?.idUser;
  }

  static getUserType(): UserType {
    const user = this.getUser();
    // if (this.getToken() === null) {
    //   return '';
    // }
    return user?.userType;
  }

  static isAdminLoggedIn(): boolean {
    // if (this.getToken() == null) {
    //   return false;
    // }

    return this.getUserType() === UserType.ADMIN;
  }

  static isUserLoggedIn(): boolean {
    // if (this.getToken() == null) {
    //   return false;
    // }

    return this.getUserType() === UserType.USER;
  }

  static signOut(): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }
}
