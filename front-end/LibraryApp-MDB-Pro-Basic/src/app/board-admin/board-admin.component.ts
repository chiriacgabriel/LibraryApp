import {Component, OnInit} from '@angular/core';
import {UserService} from '../_services/user.service';
import {ModalDirective} from 'ng-uikit-pro-standard';
import {FormControl, FormGroup} from '@angular/forms';
import {TokenStorageService} from '../_services/token-storage.service';
import {User} from '../Model/User';
import {RoleService} from '../_services/role.service';

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.scss']
})
export class BoardAdminComponent implements OnInit {

  users = [];
  userForm: FormGroup;
  listRoles = [];

  constructor(private userService: UserService,
              private roleService: RoleService,
              private tokenStorage: TokenStorageService) {
  }

  ngOnInit(): void {
    this.userService.getUsers().subscribe((data: any[]) => {
        console.log(data);
        this.users = data;

      },
      err => {
        this.users = JSON.parse(err.error).message;
      }
    );

    this.getAllRole();

  }

  editUser(user: User, modalDirective: ModalDirective) {
    modalDirective.show();

    this.userForm = new FormGroup({
      id: new FormControl(user.id),
      name: new FormControl(user.name),
      lastName: new FormControl(user.lastName),
      email: new FormControl(user.email),
      password: new FormControl(user.password),
      roleSet: new FormControl(this.listRoles)
    });

  }

  saveUser() {

    let index = this.users.findIndex(user => user.id == this.userForm.value.id);

    this.users[index] = this.userForm.value;

    let id = this.users[index].id;

    console.log(this.users[index]);
    this.userService.editUserById(id, this.users[index]).subscribe(response => {
        console.log(response);
      },
      error => {
        console.log(error);
      });

  }

  getAllRole() {
    this.roleService.getRole().subscribe((data: any) => {
        this.listRoles = data;
        console.log(data);
      },
      error => {
        console.log(error);
      });
  }

}
