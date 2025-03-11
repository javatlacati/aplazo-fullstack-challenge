import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CustomerRequest} from "../model/CustomerRequest";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private apiUrl = `http://localhost:8080/v1/customers`;

  constructor(private client: HttpClient) {}

  createCustomer(createCustomerRequest: CustomerRequest) {
    return this.client.post<CustomerRequest>(this.apiUrl, createCustomerRequest).subscribe()
  }
}
