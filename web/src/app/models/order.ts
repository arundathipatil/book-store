export class Order {
    id?: number;
    buyersemail: string;
    orderItems: any;
    totalPrice: number;
    status?: string;
    orderDate?: Date;
}