import { GuitarType } from './guitar-types';

export interface Guitar {
  idGuitar: number;
  name: string;
  type: GuitarType;
  price: number;
  guitarInfo: string;
  quantity: number;
  image: string;
}
