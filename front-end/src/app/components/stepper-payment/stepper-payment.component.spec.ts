import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StepperPaymentComponent } from './stepper-payment.component';

describe('StepperPaymentComponent', () => {
  let component: StepperPaymentComponent;
  let fixture: ComponentFixture<StepperPaymentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StepperPaymentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StepperPaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
