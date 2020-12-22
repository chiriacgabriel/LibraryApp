import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Role} from '../model/Role';
import {UserService} from '../_services/user.service';
import {RoleService} from '../_services/role.service';
import {User} from '../model/User';
import {ModalDirective} from 'ng-uikit-pro-standard';
import {AlertsService} from '../_services/alerts.service';
import swal from 'sweetalert';


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
  errorMessage = '';
  isResetPasswordFailed = false;

  constructor(private userService: UserService,
              private roleService: RoleService,
              private alertsService: AlertsService) {
  }

  get password() {
    return this.userForm.get('password');
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
      password: new FormControl('', Validators.required),
      roleSet: new FormControl(this.listRoleSelected)
    });
  }

  resetPassword(modalDirective: ModalDirective): void {

    const index = this.users.findIndex(user => user.id == this.userForm.value.id);
    this.users[index] = this.userForm.value;
    const id = this.users[index].id;

    this.userService.editUserById(id, this.users[index]).subscribe(response => {

        this.ngOnInit();
        modalDirective.toggle();
        this.alertsService.success();

        this.isResetPasswordFailed = false;

      },
      err => {
        this.errorMessage = err.error.message;
        this.isResetPasswordFailed = true;
      });
  }

  saveUser(modalDirective: ModalDirective): void {
    modalDirective.toggle();

    const index = this.users.findIndex(user => user.id == this.userForm.value.id);
    this.users[index] = this.userForm.value;
    const id = this.users[index].id;

    this.userService.editUserById(id, this.users[index]).subscribe(response => {
        this.ngOnInit();
        this.alertsService.success();
      },
      err => {
        console.log(err);
      });

    this.listRoleSelected = undefined;
  }

  deleteUser(user: User, idTable: number) {

    swal({
      title: 'Are you sure?',
      text: 'Once deleted, you will not be able to recover this registration!',
      icon: 'warning',
      buttons: ['Cancel', 'Ok'],
      dangerMode: true,
    })
      .then((willDelete) => {
        if (willDelete) {

          const index = this.users.findIndex(obj => obj.id = user.id);
          const id = this.users[index].id;

          this.userService.deleteUser(id).subscribe(response => {
              this.ngOnInit();
            },
            error => {
              console.log(error);
            });

          this.users.splice(idTable, 1);

          swal('Your registration/file has been deleted!', {
            icon: 'success',
          });
        }
      });

  }


  selectedObject(role: Role) {
    this.listRoleSelected.push(role);
  }

}
