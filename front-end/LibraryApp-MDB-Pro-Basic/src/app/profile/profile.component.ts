import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  file: File;

  constructor() { }

  ngOnInit(): void {

  }

  onFileAdd(file: File) {
    this.file = file;
  }

  onFileRemove() {
    this.file = null;
  }
}
