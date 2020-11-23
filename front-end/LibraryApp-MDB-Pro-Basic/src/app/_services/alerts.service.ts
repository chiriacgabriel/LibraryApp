import {Injectable, OnInit} from '@angular/core';
import {ToastService} from 'ng-uikit-pro-standard';
import swal from 'sweetalert';
import {AuthorService} from "./author.service";
import {Author} from "../model/Author";

@Injectable({
  providedIn: 'root'
})
export class AlertsService {

  constructor(private toast: ToastService) { }

  alertShowSuccess(){
    const options = {extendedTimeOut: 4500};
    this.toast.success('Action performed successfully', '', options);
  }

  alertShowWarning(){
    const options = {extendedTimeOut: 4500};
    this.toast.warning('Delete was successful', '', options);
  }

}
