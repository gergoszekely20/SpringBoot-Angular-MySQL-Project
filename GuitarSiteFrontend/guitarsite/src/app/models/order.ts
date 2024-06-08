export interface Order {
  idOrder: number;
  orderDescription: string;
  date: Date;
  amount: number;
  address: string;
  payment: string;
  totalAmount: number;
  discount: number;
  orderStatus: OrderStatus;
  userName: string;
}

export enum OrderStatus {
  PENDING = 'PENDING',
  PLACED = 'PLACED',
  SHIPPED = 'SHIPPED',
  DELEVERD = 'DELEVERD',
}
