import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {ProfileComponent} from './profile/profile.component';
import {BoardUserComponent} from './board-user/board-user.component';
import {BoardModeratorComponent} from './board-moderator/board-moderator.component';
import {BoardAdminComponent} from './board-admin/board-admin.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {NotFoundComponent} from './_errors/not-found/not-found.component';
import {UserComponent} from './user/user.component';
import {AuthorComponent} from './_core/author/author.component';
import {BookComponent} from './_core/book/book.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'user-board', component: BoardUserComponent},
  {path: 'mod-board', component: BoardModeratorComponent},
  {path: 'admin', component: BoardAdminComponent},
  {
    path: 'dashboard', component: DashboardComponent,
    children: [
      {
        path: 'user', component: UserComponent
      },
      {
        path: 'author', component: AuthorComponent
      },
      {
        path: 'book', component: BookComponent
      }
    ]
  },
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
