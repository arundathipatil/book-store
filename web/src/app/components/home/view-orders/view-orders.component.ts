import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, NavigationExtras } from '@angular/router';
import { Order } from 'src/app/models/order';
import { HomeService } from '../home.service';

@Component({
  selector: 'app-view-orders',
  templateUrl: './view-orders.component.html',
  styleUrls: ['./view-orders.component.scss']
})
export class ViewOrdersComponent implements OnInit {
  orderList: any;
  constructor(private router: Router, private homervice: HomeService) { }

  ngOnInit(): void {
    this.homervice.getAllOrders()
    .subscribe(data=>{
        this.orderList = data;
    }, error=>{
      alert(error?.error + " :Unable to fetch order list! Please try again later!");
    })
  }
  showOrderDetail(order:Order) {
    let navigationExtras: NavigationExtras = {
      queryParams: {
          "orderId": order["id"]
      }
    };
  this.router.navigate(['home/orderDetail'], navigationExtras);
  }

  cancelOrder(order: Order) {
    this.homervice.cancelOrder(order)
    .subscribe(data=>{
      alert("Order Cancelled Succesfully");
    }, error=>{
        alert(error?.error + ": Unable to cancel order! Please try again later!");
    });
  }

}
