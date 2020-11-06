import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Role} from '../model/Role';
import {UserService} from '../_services/user.service';
import {RoleService} from '../_services/role.service';
import {User} from '../model/User';
import {ModalDirective} from 'ng-uikit-pro-standard';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  users = [];
  userForm: FormGroup;
  listRoles: any;
  listRoleSelected: Role[] = [];

  constructor(private userService: UserService,
              private roleService: RoleService) {
  }

  ngOnInit(): void {
    this.getAllUsers();
    this.getAllRole();

  }

  getAllUsers() {
    this.userService.getUsers().subscribe((data: any[]) => {
        this.users = data;

      },
      err => {
        this.users = JSON.parse(err.error).message;
      }
    );

  }

  getAllRole() {
    this.roleService.getRole().subscribe(data => {
        this.listRoles = data;
      },
      error => {
        console.log(error);
      });
  }

  editUser(user: User, modalDirective: ModalDirective) {
    modalDirective.toggle();

    this.userForm = new FormGroup({
      id: new FormControl(user.id),
      name: new FormControl(user.name),
      lastName: new FormControl(user.lastName),
      email: new FormControl(user.email),
      password: new FormControl(''),
      roleSet: new FormControl(this.listRoleSelected)
    });
  }

  saveUser(modalDirective: ModalDirective): void {
    modalDirective.toggle();
    const index = this.users.findIndex(user => user.id == this.userForm.value.id);
    this.users[index] = this.userForm.value;
    const id = this.users[index].id;

    this.userService.editUserById(id, this.users[index]).subscribe(response => {
        this.ngOnInit();
      },
      error => {
        console.log(error);
      });

    this.listRoleSelected = [];
  }


  deleteUser(user: User, idTable: number) {
    const index = this.users.findIndex(obj => obj.id = user.id);
    const id = this.users[index].id;

    this.userService.deleteUser(id).subscribe(response => {
        this.ngOnInit();
      },
      error => {
        console.log(error);
      });

    this.users.splice(idTable, 1);
  }

  selectedObject(role: Role) {
    this.listRoleSelected.push(role);
  }

}
