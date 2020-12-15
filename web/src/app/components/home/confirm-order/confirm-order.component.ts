import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators, FormControl, EmailValidator } from '@angular/forms';
import { HomeService } from '../home.service';

import { Order } from 'src/app/models/order';
import { User } from '../../../models/User';

@Component({
  selector: 'app-confirm-order',
  templateUrl: './confirm-order.component.html',
  styleUrls: ['./confirm-order.component.scss']
})
export class ConfirmOrderComponent implements OnInit {
  currentUser: User = new User();
  order: any;
  editedRow: Order;
  displayedColumns: string[] = ['isbn', 'title', 'sellersemail', 'quantity', 'price'];
  dataSource: MatTableDataSource<Order>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private homeService: HomeService, private router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.homeService.getConfirmOrderDetails()
    .subscribe((data)=>{
      this.order = data;
      orderItems: [];
      this.dataSource = new MatTableDataSource(this.order?.orderItems);
      // this.dataSource = new MatTableDataSource(this.order);
      this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    })
   }

   placeOrder() {
     this.homeService.placeOrder()
     .subscribe(data=>{
      alert("Order Placed Succesfully");
      this.router.navigate(['home/welcome']);
     }, error=>{
       alert("Unable to Place order! Please try again later");
     })
   }

   cancel() {
    this.router.navigate(['home/cart']);
   }

  ngOnInit(): void {
  }

}
