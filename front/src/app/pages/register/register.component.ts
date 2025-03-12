import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AplazoButtonComponent } from '@apz/shared-ui/button';
import { AplazoLogoComponent } from '@apz/shared-ui/logo';
import {NgForOf, NgIf} from "@angular/common";
import {CustomerRequest} from "../../model/CustomerRequest";
import {CustomerService} from "../../services/customer.service";

@Component({
  standalone: true,
  selector: 'app-register',
  templateUrl: './register.component.html',
  imports: [ReactiveFormsModule, AplazoButtonComponent, AplazoLogoComponent, NgIf, NgForOf],
})
export class RegisterComponent {
  errorMessage: string;
  places = ['New York', 'Los Angeles', 'Chicago', 'Houston', 'Philadelphia'];

  constructor(
    private customersService: CustomerService,
  ) {}

  readonly firstName = new FormControl<string>('', {
    nonNullable: true,
    validators: [Validators.required],
  });

  readonly lastName = new FormControl<string>('', {
    nonNullable: true,
    validators: [Validators.required],
  });

  readonly secondLastName = new FormControl<string>('', {
    nonNullable: true,
    validators: [Validators.required],
  });

  readonly dateOfBirth = new FormControl<Date>(new Date(), {
    nonNullable: true,
    validators: [Validators.required],
  });

  readonly form = new FormGroup({
    firstName: this.firstName,
    lastName: this.lastName,
    secondLastName: this.secondLastName,
    dateOfBirth: this.dateOfBirth,
  });

  register(): void {
    console.log(this.form.value);
    if (this.form.invalid) {
      console.log('Invalid form');
      this.errorMessage = 'Please fill out all fields';
      return;
    }
    this.errorMessage = "";

    const createCustomerRequest: CustomerRequest = {
      firstName: this.form.value.firstName || '',
      lastName: this.form.value.lastName || '',
      secondLastName: this.form.value.secondLastName || '',
      dateOfBirth: this.form.value.dateOfBirth || new Date(),
    };

    this.customersService.createCustomer(createCustomerRequest)
  }
}
