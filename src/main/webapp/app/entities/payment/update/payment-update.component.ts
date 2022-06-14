// eslint-disable-next-line @typescript-eslint/typedef
import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IPayment, Payment } from '../payment.model';
import { PaymentService } from '../service/payment.service';

@Component({
  selector: 'jhi-payment-update',
  templateUrl: './payment-update.component.html',
})
export class PaymentUpdateComponent implements OnInit {
  isSaving = false;
  // eslint-disable-next-line @typescript-eslint/typedef
  s!: string;
  // eslint-disable-next-line @typescript-eslint/typedef
  x!: string;
  maxlength: any = 11;
  length!: number;
  value!: any;
  payment: any;
  pay: any;

  editForm = this.fb.group({
    id: [],
    cik: [null, [Validators.required]],
    ccc: [null, [Validators.required, Validators.pattern('((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$@#]).{8,30})')]],
    paymentAmout: [null, [Validators.required]],
    name: [null, [Validators.required]],
    email: [null, [Validators.required, Validators.email]],
    phone: [null, [Validators.required]],
  });

  constructor(protected paymentService: PaymentService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {
    // this.maxlength =11;
  }
  ngOnInit(): void {
    this.pay = sessionStorage.getItem('payment');
    this.payment = JSON.parse(this.pay);
    this.editForm.patchValue({
      cik: this.payment.cik,
      ccc: this.payment.ccc,
      paymentAmout: this.payment.paymentAmout,
      name: this.payment.name,
      email: this.payment.email,
      phone: this.payment.phone,
    });
  }

  // ngOnInit(): void {
  //   this.activatedRoute.data.subscribe(({ payment }) => {
  //     this.updateForm(payment);
  //   });
  // sessionStorage.setItem('key', 'value');
  // const data = sessionStorage.getItem('key');
  // }

  onKey(event: any): void {
    // without type info
    this.value = event.target.value;
    console.log(this.value);
    console.log(Number(this.value));
    console.log(String(Number(this.value)).length);

    this.value = String(Number(this.value));
    this.length = String(Number(this.value)).length;
    if (this.length === 10) {
      this.maxlength = 10;
    } else {
      this.maxlength = 11;
    }
    if (this.length <= 10) {
      for (let index = 0; index < 10 - this.length; index++) {
        this.value = '0'.concat(this.value);
      }

      this.editForm.patchValue({
        cik: this.value,
      });
    }
  }

  clear(): void {
    //this.editForm.resetForm();
    sessionStorage.removeItem('payment');
    this.editForm.patchValue({
      id: null,
      cik: null,
      ccc: null,
      paymentAmout: null,
      name: null,
      email: null,
      phone: null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const payment = this.createFromForm();
    sessionStorage.setItem('payment', JSON.stringify(payment));
    // if (payment.id !== undefined) {
    //   this.subscribeToSaveResponse(this.paymentService.update(payment));
    // } else {
    //   this.subscribeToSaveResponse(this.paymentService.create(payment));
    // }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPayment>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(payment: IPayment): void {
    this.editForm.patchValue({
      id: payment.id,
      cik: payment.cik,
      ccc: payment.ccc,
      paymentAmout: payment.paymentAmout,
      name: payment.name,
      email: payment.email,
      phone: payment.phone,
    });
  }

  protected createFromForm(): IPayment {
    return {
      ...new Payment(),
      id: this.editForm.get(['id'])!.value,
      cik: this.editForm.get(['cik'])!.value,
      ccc: this.editForm.get(['ccc'])!.value,
      paymentAmout: this.editForm.get(['paymentAmout'])!.value,
      name: this.editForm.get(['name'])!.value,
      email: this.editForm.get(['email'])!.value,
      phone: this.editForm.get(['phone'])!.value,
    };
  }
}
