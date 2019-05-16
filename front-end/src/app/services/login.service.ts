import { Injectable } from '@angular/core';
import { Constants } from '../utils/constants';
import { Http, Response, Headers } from '@angular/http';
import { HttpHeaders } from '@angular/common/http';

const CONTEXT = '/login';
const BASE_URL = Constants.IP_API_CONNECTION + CONTEXT;
const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: Http) { }

  login(body: Object) {
    let getUsersURL = BASE_URL;
    return this.http.post(getUsersURL,body)
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
