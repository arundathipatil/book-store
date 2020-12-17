import { Component, OnInit, ViewChild } from '@angular/core';
import { User } from 'src/app/models/User';
import { HomeService } from '../home.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { UpdateProfileDialogComponent } from "../../common/update-profile-dialog/update-profile-dialog.component";

@Component({
  selector: 'app-view-users',
  templateUrl: './view-users.component.html',
  styleUrls: ['./view-users.component.scss']
})
export class ViewUsersComponent implements OnInit {

  userList: any;
  dataSource: MatTableDataSource<User>;
  displayedColumns: string[] = ['First Name', 'Last Name', 'action'];
  
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(private homeService: HomeService, public dialog: MatDialog) { }

  ngOnInit(): void {
        this.homeService.getAllUsers()
        .subscribe(data=>{
            this.userList = data;
            this.dataSource = new MatTableDataSource(this.userList);
            this.dataSource.paginator = this.paginator;
            this.dataSource.sort = this.sort;
        }, error=>{
            alert(error?.error + " :Unable to fetch User list. Please try again later!");
        });
  }


  manageUser(user: User): void {
    // this.user = new User();
    // this.user = JSON.parse(sessionStorage.getItem('currentUser'));
    const dialogRef = this.dialog.open(UpdateProfileDialogComponent, {
      width: '250px',
      data: user
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if(result!=null) {
        this.homeService.updateUserByAdmin(result)
        .subscribe(data=>{
          alert("User details updated successfully");
        }, error=>{
          alert(error?.error + " : User details could not be saved Successfully");
        });
      }      
    });
  }
}
