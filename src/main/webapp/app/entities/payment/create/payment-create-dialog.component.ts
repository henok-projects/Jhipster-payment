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
  templateUrl: './payment-create-dialog.component.html',
})
export class PaymentCreateDialogComponent {
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
  token: any;
  ppUrl: any;
  ppToken: any;
  fullPaypalUrl: any;

  doc: any;
  parser: any;

  constructor(
    protected paymentService: PaymentService,
    protected activeModal: NgbActiveModal,
    private router: Router,
    private sharedService: SharedService
  ) {
    this.time = 5;
  }

  ngOnInit(): void {
    this.formValues = sessionStorage.getItem('form');

    this.data = JSON.parse(this.formValues);

    this.paymentService.getPayment(this.data).subscribe((msg: any) => {
      const dt = msg;
      console.log('Sending payment amount to paypal : ', dt);
    });

    this.startTimer();

    this.storedData(this.data);
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
        // this.paymentService.getHostedPayment().subscribe({
        //   next: (res: HttpResponse<IHostedPayment>) => {
        //     this.ihostedPayment = res.body;
        //     window.location.href = 'https://payment.'.concat(this.ihostedPayment.partialRedirectUrl);
        //
        //   },
        // });

        this.save();

        // this.router.navigate(['/payment']);
      }
      return this.time;
    }, 1000);
  }

  save(): void {
    this.pay = sessionStorage.getItem('payment');
    this.payment = JSON.parse(this.pay);
    this.length = this.payment.cik.length;

    for (let index = 0; index < 10 - this.length; index++) {
      this.payment.cik = '0'.concat(this.payment.cik);
    }

    // this.pay = sessionStorage.removeItem('payment');
    this.subscribeToSaveResponse(this.paymentService.getPpToken(this.payment));
    this.activeModal.dismiss();
    clearInterval(this.interval);

    // this.paymentService.getHostedPayment().subscribe({
    //   next: (res: HttpResponse<IHostedPayment>) => {
    //     this.ihostedPayment = res.body;
    //     window.location.href = 'https://payment.'.concat(this.ihostedPayment.partialRedirectUrl);
    //
    //
    //   },
    // });
  }

  getFormData(): void {
    this.data = this.sharedService.getData();
  }
  storedData(data: any): void {
    const jsonData = JSON.stringify(data);
    sessionStorage.setItem('formData', jsonData);
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<any>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: (res: any) => {
        this.onSaveSuccess();

        this.token = JSON.stringify(res);
        this.ppUrl = JSON.parse(this.token);
        window.location.href = this.ppUrl.body;
      },
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
