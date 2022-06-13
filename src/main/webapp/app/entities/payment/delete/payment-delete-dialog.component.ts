import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IPayment } from '../payment.model';
import { PaymentService } from '../service/payment.service';
import { Router, RouterEvent } from '@angular/router';
import { finalize, Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';

@Component({
  templateUrl: './payment-delete-dialog.component.html',
})
export class PaymentDeleteDialogComponent {
  // payments?: IPayment;
  isSaving = false;
  payment: any;
  pay: any;
  length: any;
  time: number;
  display: any;
  interval: any;

  constructor(protected paymentService: PaymentService, protected activeModal: NgbActiveModal, private router: Router) {
    this.time = 5;
  }

  ngOnInit(): void {
    this.startTimer();
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  previousState(): void {
    window.history.back();
  }
  startTimer(): void {
    console.log('=====>');
    this.interval = setInterval(() => {
      if (this.time !== 0) {
        this.time--;
      } else {
        this.confirmCreate();
        this.router.navigate(['/payment']);
        this.activeModal.dismiss();
        clearInterval(this.interval);
      }
      return this.time;
    }, 1000);
  }

  confirmCreate(): void {
    // clearInterval(this.interval);
    this.pay = sessionStorage.getItem('payment');
    this.payment = JSON.parse(this.pay);
    this.length = this.payment.cik.length;

    for (let index = 0; index < 10 - this.length; index++) {
      this.payment.cik = '0'.concat(this.payment.cik);
    }

    this.subscribeToSaveResponse(this.paymentService.create(this.payment));
    this.pay = sessionStorage.removeItem('payment');
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPayment>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.activeModal.close('save');
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }
}
