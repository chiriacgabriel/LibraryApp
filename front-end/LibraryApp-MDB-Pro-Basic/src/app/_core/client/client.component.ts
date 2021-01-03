import {Component, OnInit} from '@angular/core';
import {ClientService} from '../../_services/client.service';
import {FormControl, FormGroup} from '@angular/forms';
import {AlertsService} from '../../_services/alerts.service';
import {ModalDirective} from 'ng-uikit-pro-standard';
import {Client} from '../../model/Client';
import swal from 'sweetalert';
import {ReloadPageService} from '../../_services/reload-page.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.scss']
})
export class ClientComponent implements OnInit {

  clients = [];
  addClientForm: FormGroup;
  editClientForm: FormGroup;
  errorMessage = '';
  isClientAlreadyExists = false;


  constructor(private clientService: ClientService,
              private alertsService: AlertsService,
              private reloadPageService: ReloadPageService) {
  }

  ngOnInit(): void {
    this.getClients();
    this.formClient();
  }

  formClient() {
    this.addClientForm = new FormGroup({
      firstName: new FormControl(''),
      lastName: new FormControl(''),
      email: new FormControl(''),
      phoneNumber: new FormControl('')
    });
  }

  editFormClient(modalDirective: ModalDirective, client: Client) {
    modalDirective.toggle();

    this.editClientForm = new FormGroup({
      id: new FormControl(client.id),
      firstName: new FormControl(client.firstName),
      lastName: new FormControl(client.lastName),
      email: new FormControl(client.email),
      phoneNumber: new FormControl(client.phoneNumber)
    });
  }

  getClients() {
    this.clientService.getAllClients().subscribe((data: any) => {
        this.clients = data;
      },
      err => {
        this.clients = JSON.parse(err.message).message;
      });
  }

  addClient(modalDirective: ModalDirective) {
    this.clientService.addClient(this.addClientForm.value).subscribe(response => {
        this.alertsService.success();
        this.isClientAlreadyExists = false;
        modalDirective.toggle();
        this.reloadPageService.reload();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isClientAlreadyExists = true;
      });
  }

  updateClient(modalDirective: ModalDirective) {
    const index = this.clients.find(client => client.id == this.editClientForm.value.id);
    this.clients[index] = this.editClientForm.value;
    const id = this.clients[index].id;

    this.clientService.editClientById(id, this.clients[index]).subscribe(response => {
        this.alertsService.success();
        modalDirective.toggle();
        this.reloadPageService.reload();
        this.isClientAlreadyExists = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isClientAlreadyExists = true;
      });
  }

  deleteClient(client: Client) {
    swal({
      title: 'Are you sure?',
      text: 'Once deleted, you will not be able to recover this registration!',
      icon: 'warning',
      buttons: ['Cancel', 'Ok'],
      dangerMode: true,
    })
      .then((willDelete) => {
        if (willDelete) {
          this.clientService.deleteClientById(client.id).subscribe(response => {
            this.reloadPageService.reload();
          }, error => {
            console.log(error);
          });
          swal('Your registration/file has been deleted!', {
            icon: 'success',
          });
        }
      });
  }
}
