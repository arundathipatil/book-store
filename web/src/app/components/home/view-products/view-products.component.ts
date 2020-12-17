import { Component, OnInit, ViewChild } from '@angular/core';
import { Book } from 'src/app/models/book';
import { HomeService } from '../home.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { UpdateProfileDialogComponent } from "../../common/update-profile-dialog/update-profile-dialog.component";
import { UpdateBookDialogComponent } from "../my-book-store/my-book-store.component";

@Component({
  selector: 'app-view-products',
  templateUrl: './view-products.component.html',
  styleUrls: ['./view-products.component.scss']
})
export class ViewProductsComponent implements OnInit {

  bookList: any;
  dataSource: MatTableDataSource<Book>;
  editedRow: Book;
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
        alert(error?.error + " :Unable to fetch Book list. Please try again later!");
    });
  }

  manageProduct(row: Book): void {
    const dialogRef = this.dialog.open(UpdateBookDialogComponent, {
      width: '250px',
      data: row
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.editedRow = result;
      this.editedRow.updatedDate = new Date();
      if(result!=null){
        this.homeService.updatBookByAdmin(this.editedRow)
        .subscribe(data => {
          alert("Book details updated successfully");
        }, error => {
          alert(error?.error + " : Book details could not be saved Successfully");
        })
      }
    });
  }
}
