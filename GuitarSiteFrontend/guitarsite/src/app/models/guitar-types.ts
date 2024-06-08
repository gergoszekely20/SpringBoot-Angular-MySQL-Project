import { style } from '@angular/animations';

export enum GuitarType {
  CLASSIC,
  ELECTRICAL,
  BASS,
  ACOUSTIC,
}

export const GuitarTypeOptions = [
  {
    name: 'Classical',
    value: GuitarType.CLASSIC,
  },
  {
    name: 'Electrical',
    value: GuitarType.ELECTRICAL,
  },
  {
    name: 'Bass',
    value: GuitarType.BASS,
  },
  {
    name: 'Acoustic',
    value: GuitarType.ACOUSTIC,
  },
];
