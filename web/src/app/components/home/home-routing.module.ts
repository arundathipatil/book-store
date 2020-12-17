import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { HomeComponent} from './home.component';
import { MyBookStoreComponent } from './my-book-store/my-book-store.component';
import { AddBookComponent } from './add-book/add-book.component';
import { BuyBooksComponent } from './buy-books/buy-books.component';
import { CartComponent } from './cart/cart.component';
import { BookDetailsComponent } from './book-details/book-details.component';
import { ConfirmOrderComponent } from './confirm-order/confirm-order.component';
import { MyOrdersComponent } from './my-orders/my-orders.component';
import { OrderDetailComponent } from './order-detail/order-detail.component';
import { ViewUsersComponent } from './view-users/view-users.component';
import { ViewProductsComponent } from './view-products/view-products.component';
import { ViewOrdersComponent } from './view-orders/view-orders.component';

const routes: Routes = [
    // {path: '' , component : HomeComponent},
    // {path: 'welcome', component: WelcomeComponent}
    {path: '',  component: HomeComponent,
     children: [{path: 'welcome', component: WelcomeComponent},
                {path: 'myBookStore', component: MyBookStoreComponent},
                {path: 'addBook', component: AddBookComponent},
                {path: 'buyBook', component: BuyBooksComponent},
                {path: 'cart', component: CartComponent},
                {path: 'bookDetails', component: BookDetailsComponent},
                {path: 'confirmOrder', component: ConfirmOrderComponent},
                {path: 'myOrders', component: MyOrdersComponent},
                {path: 'orderDetail', component: OrderDetailComponent},
                {path: 'viewUsers', component: ViewUsersComponent},
                {path: 'viewProducts', component: ViewProductsComponent},
                {path: 'viewOrders', component: ViewOrdersComponent}]
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
