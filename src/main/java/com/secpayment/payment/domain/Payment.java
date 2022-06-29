package com.secpayment.payment.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Payment.
 */
@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cik")
    private String cik;

    @Column(name = "ccc")
    private String ccc;

    @NotNull
    @Column(name = "payment_amout")
    private String paymentAmout;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Integer phone;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Payment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCik() {
        return this.cik;
    }

    public Payment cik(String cik) {
        this.setCik(cik);
        return this;
    }

    public void setCik(String cik) {
        this.cik = cik;
    }

    public String getCcc() {
        return this.ccc;
    }

    public Payment ccc(String ccc) {
        this.setCcc(ccc);
        return this;
    }

    public void setCcc(String ccc) {
        this.ccc = ccc;
    }

    public String getPaymentAmout() {
        return this.paymentAmout;
    }

    public Payment paymentAmout(String paymentAmout) {
        this.setPaymentAmout(paymentAmout);
        return this;
    }

    public void setPaymentAmout(String paymentAmout) {
        this.paymentAmout = paymentAmout;
    }

    public String getName() {
        return this.name;
    }

    public Payment name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public Payment email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return this.phone;
    }

    public Payment phone(Integer phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Payment)) {
            return false;
        }
        return id != null && id.equals(((Payment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Payment{" +
            "id=" + getId() +
            ", cik=" + getCik() +
            ", ccc='" + getCcc() + "'" +
            ", paymentAmout=" + getPaymentAmout() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone=" + getPhone() +
            "}";
    }
}
