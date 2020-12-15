import { Book } from "./book";

export class CartItem {
    id?: number;
    book: Book;
    quantity: number;
    shoppingCart?: any;
  }