import { Injectable } from '@angular/core';
import { Constants } from '../utils/constants';
import { Http, Response, Headers } from '@angular/http';
import { HttpHeaders } from '@angular/common/http';

const CONTEXT = '/paymentmethods';
const BASE_URL = Constants.IP_API_CONNECTION + CONTEXT;

@Injectable({
  providedIn: 'root'
})
export class PaymentMethodService {

  constructor(private http: Http) { }

  getPaymentMethods<T>() {
    
    let token = localStorage.getItem("token");
    const paramsRequest: any = {
      headers: {"Authorization": token},
    };
    let getPaymentMethodsURL = BASE_URL + "/";
    return this.http.get(getPaymentMethodsURL,paramsRequest)
      .toPromise()
      .then(response => response.json())
      .catch(this.handleError);
  }

  createPaymentMethod(body: Object) {
    let token = localStorage.getItem("token");
    const paramsRequest: any = {
      headers: {"Authorization": token},
    };
    let createPaymentMethodURL = BASE_URL + "/";
    return this.http.post(createPaymentMethodURL, body,paramsRequest)
      .toPromise()
      .then(response => response.json())
      .catch(this.handleError);
  }

  private handleError(error: Response | any) {
    // In a real world app, we might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return errMsg;
  }
}

