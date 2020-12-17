import { Injectable } from '@angular/core';
import { ApiService } from '../../core/api.service';
import { constant } from '../../constant/const';
import { User } from '../../models/User';
import { Book } from '../../models/book';
import { Cart } from '../../models/cart';
import { CartItem } from '../../models/cartItem';


@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private apiService: ApiService) { }

  getUserDetails(email: any) {
    return this.apiService.get(constant.baseUrl+constant.urls.getUserDeatils);
  }

  updateUserDetails(user: User) {
     return this.apiService.put(constant.baseUrl+constant.urls.updateUserDetails, user);
  }

  addBook(book: Book) {
    return this.apiService.post(constant.baseUrl+constant.urls.addBook, book);
  }

  getAllBooksByemail(email: string) {
    return this.apiService.get(constant.baseUrl+constant.urls.getAllBooksByemail+ "?email="+email)
  }

  updateBookDetails(book: Book) {
    return this.apiService.put(constant.baseUrl+constant.urls.updateBookDetails, book);
  }

  deleteBook(id: number) {
    return this.apiService.delete(constant.baseUrl+constant.urls.deleteBook + "?id="+id);
    // return this.apiService.delete(constant.urls.deleteBook + );
  }

  getAllBooksToBuy(email: string) {
    return this.apiService.get(constant.baseUrl+constant.urls.getAllBooksToBuy + "?email=" +email);
  }

  addItemToCart(cart: CartItem) {
    return this.apiService.post(constant.baseUrl+constant.urls.addItemToCart, cart);
  }

  deleteItemFromCart(id: number) {
    return this.apiService.delete(constant.baseUrl+constant.urls.deleteItemFromCart +"?id="+id);
  }

  getAllCartItems(email: string) {
    return this.apiService.get(constant.baseUrl+constant.urls.getCartItems +"?email="+email);
  }

  changePassword(data: any) {
    return this.apiService.post(constant.baseUrl+constant.urls.changepwd, data);
  }

  uploadPhoto(data: any) {
    return this.apiService.post(constant.urls.uploadPhoto, data);
  }

  getImages(userEmail: string, isbn: string) {
    return this.apiService.get(constant.urls.getImages+"?userEmail="+userEmail+"&isbn="+isbn);
  }

  deleteImage(id: number) {
    return this.apiService.delete(constant.urls.deleteImage+"?id="+id);
  }

  getConfirmOrderDetails() {
      return this.apiService.get(constant.baseUrl+constant.urls.getConfirmOrderDetails);
  }

  placeOrder() {
    return this.apiService.post(constant.baseUrl+constant.urls.placeOrder, null);
  }

  getMyOrders() {
    return this.apiService.get(constant.baseUrl+constant.urls.getmyOrders);
  }

  getOrderDetails(orderId: number) {
    return this.apiService.get(constant.baseUrl+constant.urls.getOrderDetails+"?id="+orderId);
  }

  getAllUsers() {
    return this.apiService.get(constant.baseUrl+constant.urls.getAllUsers);
  }

  getAllProducts() {
    return this.apiService.get(constant.baseUrl+constant.urls.getAllProducts);
  }

  getAllOrders() {
    return this.apiService.get(constant.baseUrl+constant.urls.getAllOrders);
  }

  updateUserByAdmin(user: User) {
    return this.apiService.post(constant.baseUrl+constant.urls.updateUserByAdmin, user);
  }

  updatBookByAdmin(book: Book) {
    return this.apiService.put(constant.baseUrl+constant.urls.updateBookByAdmin, book);
  }
}
