import {User} from "./User";

export class Book {
    id?: number;
    isbn: string;
    title: string;
    authors: string;
    publicationDate: Date;
    quantity: number;
    price: number;
    createdDate: Date;
    updatedDate: Date;
    userEmail: string;
}