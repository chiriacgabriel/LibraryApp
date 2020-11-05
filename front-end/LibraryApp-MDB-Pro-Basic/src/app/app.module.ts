import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule, NO_ERRORS_SCHEMA} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AgmCoreModule} from '@agm/core';
import {AppComponent} from './app.component';

import {
  MDBBootstrapModulesPro,
  MDBSpinningPreloader,
  NavbarModule,
  StickyHeaderModule,
  ToastModule
} from 'ng-uikit-pro-standard';
import {BoardAdminComponent} from './board-admin/board-admin.component';
import {BoardModeratorComponent} from './board-moderator/board-moderator.component';
import {BoardUserComponent} from './board-user/board-user.component';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {ProfileComponent} from './profile/profile.component';
import {RegisterComponent} from './register/register.component';
import {authInterceptorProviders} from './_helpers/auth.interceptor';
import {AppRoutingModule} from './app-routing.module';
import {DashboardComponent} from './dashboard/dashboard.component';
import {NotFoundComponent} from './_errors/not-found/not-found.component';
import {UserComponent} from './user/user.component';
import { AuthorComponent } from './_core/author/author.component';
import { BookComponent } from './_core/book/book.component';

@NgModule({
  declarations: [
    AppComponent,
    BoardAdminComponent,
    BoardModeratorComponent,
    BoardUserComponent,
    HomeComponent,
    LoginComponent,
    ProfileComponent,
    RegisterComponent,
    DashboardComponent,
    NotFoundComponent,
    UserComponent,
    AuthorComponent,
    BookComponent,

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    StickyHeaderModule,
    NavbarModule,
    ToastModule.forRoot(),
    MDBBootstrapModulesPro.forRoot(),
    AgmCoreModule.forRoot({
      // https://developers.google.com/maps/documentation/javascript/get-api-key?hl=en#key
      apiKey: 'Your_api_key'
    }),
    AppRoutingModule
  ],
  entryComponents: [LoginComponent, RegisterComponent],
  providers: [
    MDBSpinningPreloader,
    authInterceptorProviders,

  ],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AppModule {
}
