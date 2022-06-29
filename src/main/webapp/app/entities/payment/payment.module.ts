import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { PaymentComponent } from './list/payment.component';
import { PaymentDetailComponent } from './detail/payment-detail.component';
import { PaymentUpdateComponent } from './update/payment-update.component';
import { PaymentRoutingModule } from './route/payment-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PaymentCreateDialogComponent } from './create/payment-create-dialog.component';

@NgModule({
  imports: [SharedModule, PaymentRoutingModule, FormsModule, ReactiveFormsModule],
  declarations: [PaymentComponent, PaymentDetailComponent, PaymentUpdateComponent, PaymentCreateDialogComponent],
  entryComponents: [PaymentCreateDialogComponent],
})
export class PaymentModule {}
