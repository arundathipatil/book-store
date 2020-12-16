import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
// import { HomeComponent } from './components/home/home.component';
import { AuthenticationService } from './services/authentication.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularMatModule } from './modules/angular-mat/angular-mat.module';
import { RegistrationComponent } from './components/registration/registration.component';
import { HomeModule } from './components/home/home.module';

import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatPaginatorModule } from '@angular/material/paginator';
import {MatDialogModule} from '@angular/material/dialog';
import {MatCardModule} from '@angular/material/card';

import { UpdateProfileDialogComponent } from './components/common/update-profile-dialog/update-profile-dialog.component';
import { ChangePasswordDialogComponent } from './components/common/change-password-dialog/change-password-dialog.component';
// import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpConfigInterceptor } from './interceptor/http-config-interceptor';
import { AdminComponent } from './components/admin/admin.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    // HomeComponent,
    RegistrationComponent,
    UpdateProfileDialogComponent,
    ChangePasswordDialogComponent,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AngularMatModule,
    FormsModule,
    HomeModule,
    MatTableModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatDialogModule,
    MatCardModule
  ],
  providers: [
    AuthenticationService,
    { provide: HTTP_INTERCEPTORS, useClass: HttpConfigInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
