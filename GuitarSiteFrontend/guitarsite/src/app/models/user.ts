import { UserType } from './user-type';

export interface User {
  idUser: number;
  email: string;
  name: string;
  address: string;
  telefon: string;
  userType: UserType;
}
