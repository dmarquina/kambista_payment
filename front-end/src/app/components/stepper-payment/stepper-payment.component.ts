import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IUser } from 'src/app/interfaces/user';
import { IPaymentMethod } from 'src/app/interfaces/payment-method';
import { UserService } from 'src/app/services/user.service';
import { PaymentMethodService } from 'src/app/services/payment-method.service';
import { PaymentService } from 'src/app/services/payment.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-stepper-payment',
  templateUrl: './stepper-payment.component.html',
  styleUrls: ['./stepper-payment.component.scss'],
  providers: [LoginService, UserService, PaymentMethodService]
})
export class StepperPaymentComponent implements OnInit {
  isLinear = true;
  editingUser = true;
  editingPaymentMethod = true;
  initialFormGroup: FormGroup;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  users: IUser[] = [];
  userSelected: IUser;
  userId: number;
  paymentMethods: IPaymentMethod[] = [];
  paymentMethodSelected: IPaymentMethod;
  paymentMethodId: number;

  constructor(private _formBuilder: FormBuilder,
    private loginService: LoginService,
    private userService: UserService,
    private paymentMethodService: PaymentMethodService,
    private paymentService: PaymentService) { }

  ngOnInit() {
    this.initialFormGroup = this._formBuilder.group({
      usernameCtrl: ['', Validators.required],
      passwordCtrl: ['', Validators.required],
    });
    this.firstFormGroup = this._formBuilder.group({
      nameCtrl: ['', Validators.required],
      emailCtrl: ['', Validators.email],
      docTypeCtrl: ['', Validators.required],
      docNumberCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      countryCtrl: ['', Validators.required],
      currencyCtrl: ['', Validators.required]
    });
  }

  selectUser(id: number) {
    this.editingUser = false;
    this.firstFormGroup.disable();
    this.userSelected = this.users.find(u => u.id == id);
    this.firstFormGroup.get('nameCtrl').setValue(this.userSelected.name);
    this.firstFormGroup.get('emailCtrl').setValue(this.userSelected.email);
    this.firstFormGroup.get('docTypeCtrl').setValue(this.userSelected.docType);
    this.firstFormGroup.get('docNumberCtrl').setValue(this.userSelected.docNumber);
  }

  selectPaymentMethod(id: number) {
    this.editingPaymentMethod = false;
    this.secondFormGroup.disable();
    this.paymentMethodSelected = this.paymentMethods.find(p => p.id == id);
    this.secondFormGroup.get('countryCtrl').setValue(this.paymentMethodSelected.country);
    this.secondFormGroup.get('currencyCtrl').setValue(this.paymentMethodSelected.currency);
  }

  enableEditingUser() {
    this.editingUser = true;
    this.firstFormGroup.enable();
  }

  enableEditingPaymentMethod() {
    this.editingPaymentMethod = true;
    this.secondFormGroup.enable();
  }

  login() {
    this.loginService.login({
      "username": this.initialFormGroup.get('usernameCtrl').value,
      "password": this.initialFormGroup.get('passwordCtrl').value
    }).then(data => {
      localStorage.setItem("token", data.token);
      this.userService.getUsers().then(data => this.users = data);
      this.paymentMethodService.getPaymentMethods().then(data => this.paymentMethods = data);
    })
  }

  saveUser() {
    if (this.editingUser) {
      this.userService.createUser({
        "name": this.firstFormGroup.get('nameCtrl').value,
        "email": this.firstFormGroup.get('emailCtrl').value,
        "docType": this.firstFormGroup.get('docTypeCtrl').value,
        "docNumber": this.firstFormGroup.get('docNumberCtrl').value
      }).then(data => this.userId = data.id);
    } else {
      this.userId = this.userSelected.id;
    }
    this.enableEditingUser();
  }

  savePaymentMethod() {
    if (this.editingPaymentMethod) {
      this.paymentMethodService.createPaymentMethod({
        "country": this.secondFormGroup.get('countryCtrl').value,
        "currency": this.secondFormGroup.get('currencyCtrl').value,
      }).then(data => {
        this.paymentMethodId = data.id;
        this.makePayment();
      });
    } else {
      this.paymentMethodId = this.paymentMethodSelected.id;
      this.makePayment();
    }
    this.enableEditingPaymentMethod();
  }

  makePayment() {
    let paymentBody = {
      "idUser": this.userId,
      "idPaymentMethod": this.paymentMethodId,
      "totalAmount": 20
    };
    this.paymentService.createPayment(paymentBody);
  }
  refresh(): void {
    window.location.reload();
  }
}
