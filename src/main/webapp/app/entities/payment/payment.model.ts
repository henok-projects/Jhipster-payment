export interface IPayment {
  id?: number;
  cik?: number | null;
  ccc?: string | null;
  paymentAmout?: number | null;
  name?: string | null;
  email?: string | null;
  phone?: number | null;
}

export class Payment implements IPayment {
  constructor(
    public id?: number,
    public cik?: number | null,
    public ccc?: string | null,
    public paymentAmout?: number | null,
    public name?: string | null,
    public email?: string | null,
    public phone?: number | null
  ) {}
}

export function getPaymentIdentifier(payment: IPayment): number | undefined {
  return payment.id;
}
