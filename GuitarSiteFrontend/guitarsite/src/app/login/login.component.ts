import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../services/auth/auth.service';
import { Router } from '@angular/router';
import { UserStorageService } from '../services/storage/user-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  loginForm!: FormGroup;
  hidePassword: boolean = true;

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: [null, [Validators.required, Validators.required]],
      password: [null, [Validators.required]],
    });
  }
  togglePasswordVisibility(): void {
    this.hidePassword = !this.hidePassword;
  }

  onSubmit(): void {
    const password = this.loginForm.get('password')?.value;
    const email = this.loginForm.get('email')?.value;

    this.authService.login(email, password).subscribe(
      (res) => {
        this.snackBar.open('Login successful.', 'OK', {
          duration: 5000,
        });

        if (UserStorageService.isAdminLoggedIn()) {
          this.router.navigateByUrl('admin/homepage');
        } else if (UserStorageService.isUserLoggedIn()) {
          this.router.navigateByUrl('customer/homepage');
        }
      },
      (error) => {
        this.snackBar.open('Login failed. Please try again.', 'ERROR', {
          duration: 5000,
        });
      }
    );
  }
}
