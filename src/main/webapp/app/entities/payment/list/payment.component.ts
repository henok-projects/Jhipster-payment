import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPayment } from '../payment.model';
import { PaymentService } from '../service/payment.service';
import { PaymentDeleteDialogComponent } from '../delete/payment-delete-dialog.component';

@Component({
  selector: 'jhi-payment',
  templateUrl: './payment.component.html',
})
export class PaymentComponent implements OnInit {
  payments?: IPayment[];
  isLoading = false;
  pay: any;
  //payment: any;
  payment: any;
  paymentAmount: any;
  constructor(protected paymentService: PaymentService, protected modalService: NgbModal) {}

  // loadAll(): void {
  //   this.isLoading = true;

  //   this.payment = sessionStorage.getItem("payment");
  //   this.paymentAmount = JSON.parse(this.payment).paymentAmout;

  //   this.paymentService.getTransactionId().subscribe({
  //     next: (res: HttpResponse<string>) => {
  //       this.isLoading = false;
  //       this.payment = res.body ?? "";
  //       this.pay = JSON.parse(JSON.stringify(this.payment));
  //     },
  //     error: () => {
  //       this.isLoading = false;
  //     },
  //   });
  // }
  loadAll(): void {
    this.isLoading = true;

    this.paymentService.query().subscribe({
      next: (res: HttpResponse<string>) => {
        this.isLoading = false;
        this.pay = res.body ?? '';

        this.payment = JSON.parse(JSON.stringify(this.pay));
        // this.payments = res.body ?? "";
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IPayment): number {
    return item.id!;
  }

  delete(payment: IPayment): void {
    const modalRef = this.modalService.open(PaymentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.payment = payment;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
