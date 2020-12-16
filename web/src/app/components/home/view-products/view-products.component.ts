import { Component, OnInit, ViewChild } from '@angular/core';
import { Book } from 'src/app/models/book';
import { HomeService } from '../home.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { UpdateProfileDialogComponent } from "../../common/update-profile-dialog/update-profile-dialog.component";

@Component({
  selector: 'app-view-products',
  templateUrl: './view-products.component.html',
  styleUrls: ['./view-products.component.scss']
})
export class ViewProductsComponent implements OnInit {

  bookList: any;
  dataSource: MatTableDataSource<Book>;
  displayedColumns: string[] = ['ISBN', 'Title', 'Sold By', 'Action'];
  
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  constructor(private homeService: HomeService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.homeService.getAllProducts()
    .subscribe(data=>{
        this.bookList = data;
        this.dataSource = new MatTableDataSource(this.bookList);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    }, error=>{
        alert("Unable to fetch Book list. Please try again later!");
    });
  }

  manageProduct(row: Book) {

  }
}
