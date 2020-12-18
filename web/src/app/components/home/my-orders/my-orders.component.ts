import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, NavigationExtras } from '@angular/router';
import { Order } from 'src/app/models/order';
import { HomeService } from '../home.service';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.scss']
})
export class MyOrdersComponent implements OnInit {
  orderList: any;

  constructor(private router: Router, private homervice: HomeService) { 

    this.homervice.getMyOrders()
    .subscribe(data=>{
        this.orderList = data;
    }, error=>{
      alert(error?.error + " :Unable to fetch order list! Please try again later!");
    })
    
  }

  ngOnInit(): void {
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

  }
}
