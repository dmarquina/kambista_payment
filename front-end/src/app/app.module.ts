import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { NgModule } from '@angular/core';
import { MatStepperModule } from '@angular/material/stepper';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PaymentComponent } from './pages/payment/payment.component';
import { StepperPaymentComponent } from './components/stepper-payment/stepper-payment.component';

@NgModule({
  declarations: [
    PaymentComponent,
    StepperPaymentComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    MatStepperModule,
    MatInputModule,
    BrowserAnimationsModule,
    FormsModule,
    MatCardModule,
    MatListModule,
    MatIconModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatButtonModule,
    MatFormFieldModule
  ],
  providers: [],
  bootstrap: [PaymentComponent]
})
export class AppModule { }
