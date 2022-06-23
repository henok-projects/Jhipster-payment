import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IPayment } from '../payment.model';
import { PaymentService } from '../service/payment.service';
import { Router, RouterEvent } from '@angular/router';
import { finalize, Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { SharedService } from 'app/shared/shared.service';
import { IHostedPayment } from '../hostedpayment.model';

@Component({
  templateUrl: './payment-delete-dialog.component.html',
})
export class PaymentDeleteDialogComponent {
  // payments?: IPayment;
  isSaving = false;
  payment: any;
  formValues: any;
  pay: any;
  length: any;
  time: number;
  data: any;
  display: any = 'https://payment/.';
  interval: any;
  form: any;
  ihostedPayment: any;
  constructor(
    protected paymentService: PaymentService,
    protected activeModal: NgbActiveModal,
    private router: Router,
    private sharedService: SharedService
  ) {
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
        this.paymentService.getHostedPayment().subscribe({
          next: (res: HttpResponse<IHostedPayment>) => {
            this.ihostedPayment = res.body;
            window.location.href = 'https://payment.'.concat(this.ihostedPayment.partialRedirectUrl);
            // window.location.href = 'https://payment.pay1.sandbox.secured-by-ingenico.com/checkout/1204-aa68e219e76147e1bbae536e2d6af61c:062b443e-9510-71ff-b4ac-125f5de6f287:8c98fbba94964347a538ad569fa00124';
            //window.location.href = this.ihostedPayment;
            // this.payments = res.body ?? "";
          },
        });

        // this.router.navigate(['/payment']);
        this.activeModal.dismiss();
        clearInterval(this.interval);
      }
      return this.time;
    }, 1000);
  }

  save(): void {
    //this.subscribeToSaveResponse(this.paymentService.getHostedPayment());
    this.paymentService.getHostedPayment().subscribe({
      next: (res: HttpResponse<IHostedPayment>) => {
        this.ihostedPayment = res.body;
        window.location.href = 'https://payment.'.concat(this.ihostedPayment.partialRedirectUrl);
        //window.location.href = 'https://payment.pay1.sandbox.secured-by-ingenico.com/checkout/1204-aa68e219e76147e1bbae536e2d6af61c:062b443e-9510-71ff-b4ac-125f5de6f287:8c98fbba94964347a538ad569fa00124';
        // this.payments = res.body ?? "";
      },
    });
    //this.subscribeToSaveResponse(this.paymentService.getHostedPayment());
  }

  getFormData(): void {
    this.data = this.sharedService.getData();
  }
  storedData(data: any): void {
    const jsonData = JSON.stringify(data);
    sessionStorage.setItem('formData', jsonData);
  }
  // confirmCreate(): void {
  //   // clearInterval(this.interval);
  //   this.pay = sessionStorage.getItem('payment');
  //   this.payment = JSON.parse(this.pay);
  //   this.length = this.payment.cik.length;

  //   for (let index = 0; index < 10 - this.length; index++) {
  //     this.payment.cik = '0'.concat(this.payment.cik);
  //   }

  //   this.subscribeToSaveResponse(this.paymentService.create(this.payment));
  //   this.pay = sessionStorage.removeItem('payment');
  // }

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
