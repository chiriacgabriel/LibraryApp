import {Injectable} from '@angular/core';
import {ToastService} from 'ng-uikit-pro-standard';

@Injectable({
  providedIn: 'root'
})
export class AlertsService {

  constructor(private toast: ToastService) {
  }

  success() {
    const options = {timeOut: 1500};
    this.toast.success('Action performed successfully', '', options);
  }

  warning() {
    const options = {timeOut: 1500};
    this.toast.warning('Delete was successful', '', options);
  }

}
