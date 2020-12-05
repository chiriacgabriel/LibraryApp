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
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {ProfileComponent} from './profile/profile.component';
import {RegisterComponent} from './register/register.component';
import {authInterceptorProviders} from './_helpers/auth.interceptor';
import {AppRoutingModule} from './app-routing.module';
import {DashboardComponent} from './dashboard/dashboard.component';
import {NotFoundComponent} from './_errors/not-found/not-found.component';
import {UserComponent} from './user/user.component';
import {AuthorComponent} from './_core/author/author.component';
import {BookComponent} from './_core/book/book.component';
import {MediaComponent} from './_core/media/media.component';
import {AuthorDetailsComponent} from './_core/author/author-details/author-details.component';
import {StatsCardComponent} from './dashboard/stats-card/stats-card.component';
import {ClientComponent} from './_core/client/client.component';
import {ReservationComponent} from './_core/reservation/reservation.component';
import {CalendarModule, DateAdapter} from 'angular-calendar';
import {adapterFactory} from 'angular-calendar/date-adapters/date-fns';
import {CommonModule} from '@angular/common';
import {FlatpickrModule} from 'angularx-flatpickr';
import {ToastrModule} from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    ProfileComponent,
    RegisterComponent,
    DashboardComponent,
    NotFoundComponent,
    UserComponent,
    AuthorComponent,
    BookComponent,
    MediaComponent,
    AuthorDetailsComponent,
    StatsCardComponent,
    ClientComponent,
    ReservationComponent,

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
    ToastrModule.forRoot(),
    MDBBootstrapModulesPro.forRoot(),
    AgmCoreModule.forRoot({
      // https://developers.google.com/maps/documentation/javascript/get-api-key?hl=en#key
      apiKey: 'Your_api_key'
    }),
    AppRoutingModule,
    CommonModule,
    FlatpickrModule.forRoot(),
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
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
