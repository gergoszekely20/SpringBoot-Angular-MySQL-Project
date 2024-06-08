import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-productservice',
  templateUrl: './productservice.component.html',
  styleUrl: './productservice.component.css',
})
export class ProductserviceComponent {
  displayedColumns: string[] = [
    'idService',
    'contactInfo',
    'guitarName',
    'type',
    'guitarProblem',
    'date',
    'serviceType',
    'action',
  ];
  service: any;

  constructor(
    private adminService: AdminService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.getSentServices();
  }

  getSentServices() {
    this.adminService.getAllServices().subscribe((res) => {
      this.service = res;
    });
  }

  changeServiceStatus(idService: number, status: string) {
    this.adminService
      .changeServiceStatus(idService, status)
      .subscribe((res) => {
        if (res.idService != null) {
          this.snackBar.open('Service statuschanged', 'Close', {
            duration: 5000,
          });
          this.getSentServices();
        } else {
          this.snackBar.open(' Somthing went wrong', 'Close', {
            duration: 5000,
          });
        }
      });
  }
}
