package com.secpayment.payment.service;

import com.ingenico.connect.gateway.sdk.java.Client;
import com.ingenico.connect.gateway.sdk.java.CommunicatorConfiguration;
import com.ingenico.connect.gateway.sdk.java.Factory;
import com.ingenico.connect.gateway.sdk.java.domain.definitions.Address;
import com.ingenico.connect.gateway.sdk.java.domain.definitions.AmountOfMoney;
import com.ingenico.connect.gateway.sdk.java.domain.hostedcheckout.CreateHostedCheckoutRequest;
import com.ingenico.connect.gateway.sdk.java.domain.hostedcheckout.CreateHostedCheckoutResponse;
import com.ingenico.connect.gateway.sdk.java.domain.hostedcheckout.definitions.HostedCheckoutSpecificInput;
import com.ingenico.connect.gateway.sdk.java.domain.payment.definitions.Customer;
import com.ingenico.connect.gateway.sdk.java.domain.payment.definitions.Order;
import com.secpayment.payment.domain.Payment;
import com.secpayment.payment.repository.PaymentRepository;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Payment}.
 */
@Service
@Transactional
public class PaymentService {

    private final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * Save a payment.
     *
     * @param payment the entity to save.
     * @return the persisted entity.
     */
    public Payment save(Payment payment) {
        log.debug("Request to save Payment : {}", payment);
        return paymentRepository.save(payment);
    }

    /**
     * Update a payment.
     *
     * @param payment the entity to save.
     * @return the persisted entity.
     */
    public Payment update(Payment payment) {
        log.debug("Request to save Payment : {}", payment);
        return paymentRepository.save(payment);
    }

    // public CreateHostedCheckoutResponse pay(Payment payment) throws URISyntaxException, IOException {
    //     try (Client client = getClient()) {
    //         HostedCheckoutSpecificInput hostedCheckoutSpecificInput = new HostedCheckoutSpecificInput();

    //         hostedCheckoutSpecificInput.setLocale("en_GB");
    //         hostedCheckoutSpecificInput.setVariant("100");
    //         //hostedCheckoutSpecificInput.setReturnUrl(this.returnUrl);
    //         hostedCheckoutSpecificInput.setReturnUrl("http://localhost:8080/payment/list");
    //         hostedCheckoutSpecificInput.setShowResultPage(false);

    //         AmountOfMoney amountOfMoney = new AmountOfMoney();
    //         amountOfMoney.setAmount(payment.getPaymentAmout());
    //         //amountOfMoney.setAmount(100l);
    //         //amountOfMoney.setAmount((long) paymentAmout.getPaymentAmout().intValue());
    //         amountOfMoney.setCurrencyCode("USD");

    //         Address billingAddress = new Address();
    //         billingAddress.setCountryCode("US");

    //         Customer customer = new Customer();
    //         customer.setBillingAddress(billingAddress);
    //         customer.setMerchantCustomerId("1234");

    //         Order order = new Order();
    //         order.setAmountOfMoney(amountOfMoney);
    //         order.setCustomer(customer);

    //         CreateHostedCheckoutRequest body = new CreateHostedCheckoutRequest();
    //         body.setHostedCheckoutSpecificInput(hostedCheckoutSpecificInput);
    //         body.setOrder(order);

    //         CreateHostedCheckoutResponse response = client.merchant("1204").hostedcheckouts().create(body);
    //         log.info("Worldline partial redirect url : {}", response.getPartialRedirectUrl());

    //         //Console.log(response.getPartialRedirectUrl());

    //         return response;
    //     }

    // }

    // @Value("${spring.application.apiKeyId}")
    //  String apiKeyId;

    //  @Value("${spring.application.secretApiKey}")
    //  String secretApiKey;
    // private com.ingenico.connect.gateway.sdk.java.Client getClient() throws URISyntaxException {
    //     String apiKey = System.getProperty("apiKeyId", this.apiKeyId);
    //     String secretApi = System.getProperty("secretApiKey", this.secretApiKey);

    //     URL propertiesUrl = getClass().getResource("/hostedpaymentpage.properties");
    //     assert propertiesUrl != null;
    //     CommunicatorConfiguration configuration = Factory.createConfiguration(propertiesUrl.toURI(), apiKey, secretApi);
    //     return Factory.createClient(configuration);
    // }
    /**
     * Partially update a payment.
     *
     * @param payment the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Payment> partialUpdate(Payment payment) {
        log.debug("Request to partially update Payment : {}", payment);

        return paymentRepository
            .findById(payment.getId())
            .map(existingPayment -> {
                if (payment.getCik() != null) {
                    existingPayment.setCik(payment.getCik());
                }
                if (payment.getCcc() != null) {
                    existingPayment.setCcc(payment.getCcc());
                }
                if (payment.getPaymentAmout() != null) {
                    existingPayment.setPaymentAmout(payment.getPaymentAmout());
                }
                if (payment.getName() != null) {
                    existingPayment.setName(payment.getName());
                }
                if (payment.getEmail() != null) {
                    existingPayment.setEmail(payment.getEmail());
                }
                if (payment.getPhone() != null) {
                    existingPayment.setPhone(payment.getPhone());
                }

                return existingPayment;
            })
            .map(paymentRepository::save);
    }

    /**
     * Get all the payments.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Payment> findAll() {
        log.debug("Request to get all Payments");
        return paymentRepository.findAll();
    }

    /**
     * Get one payment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Payment> findOne(Long id) {
        log.debug("Request to get Payment : {}", id);
        return paymentRepository.findById(id);
    }

    /**
     * Delete the payment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Payment : {}", id);
        paymentRepository.deleteById(id);
    }
}
