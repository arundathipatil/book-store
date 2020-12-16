import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, NavigationStart } from '@angular/router';
import { HomeService } from '../home.service';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.scss']
})
export class OrderDetailComponent implements OnInit {

  orderID: number;
  orderItems: any;
  constructor(private route: ActivatedRoute, private homeService:HomeService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.orderID = params["orderId"]; 

      this.homeService.getOrderDetails(this.orderID)
      .subscribe(data=>{
          this.orderItems = data;
      }, error=>{
        alert("Unable to fetch Order Details");
      })
  });
  }

}
