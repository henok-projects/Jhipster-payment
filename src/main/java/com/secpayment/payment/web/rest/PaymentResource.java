package com.secpayment.payment.web.rest;

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
import com.paypal.exception.*;
import com.paypal.sdk.exceptions.PayPalException;
import com.secpayment.payment.domain.Payment;
import com.secpayment.payment.repository.PaymentRepository;
import com.secpayment.payment.service.PaymentService;
import com.secpayment.payment.util.Configuration;
import com.secpayment.payment.web.rest.errors.BadRequestAlertException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;
import urn.ebay.api.PayPalAPI.*;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.*;

/**
 * REST controller for managing {@link com.secpayment.payment.domain.Payment}.
 */
@RestController
@RequestMapping("/api")
public class PaymentResource {

    URI propertiesUrl;
    private String dataValue = "https://mockbin.org/bin/165ce7bd-c7a6-4de8-bbf3-a5d04e2ba61c";
    private final Logger log = LoggerFactory.getLogger(PaymentResource.class);
    SetExpressCheckoutResponseType setExpressCheckoutResponse;
    //SetExpressCheckoutResponseType setExpressCheckoutResponse;

    private static final String ENTITY_NAME = "payment";
    Payment paymentAmout;

    //SetExpressCheckoutResponseType setExpressCheckoutResponseType;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentService paymentService;

    private final PaymentRepository paymentRepository;

    public PaymentResource(PaymentService paymentService, PaymentRepository paymentRepository) {
        this.paymentService = paymentService;
        this.paymentRepository = paymentRepository;
    }

    /**
     * {@code POST  /payments} : Create a new payment.
     *
     * @param payment the payment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new payment, or with status {@code 400 (Bad Request)} if the payment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */

    @PostMapping("/payment")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) throws URISyntaxException {
        log.debug("REST request to save Pay : {}", payment);
        if (payment.getId() != null) {
            throw new BadRequestAlertException("A new pay cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Payment result = paymentRepository.save(payment);
        SendMail mail = new SendMail();
        mail.sendMail(payment.getEmail(), payment.getName());

        return ResponseEntity
            .created(new URI("/api/payment/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    //    @PostMapping("/payment")
    //    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) throws URISyntaxException {
    //        System.out.println("--------------------------------------------");
    //        log.debug("Maybe here");
    //        log.debug("REST request to save Pay : {}", payment);
    //        if (payment.getId() != null) {
    //            throw new BadRequestAlertException("A new pay cannot already have an ID", ENTITY_NAME, "idexists");
    //        }
    //        Payment result = paymentService.save(payment);
    //        //Payment result = paymentRepository.save(payment);
    ////        SendMail mail = new SendMail();
    ////        mail.sendMail(payment.getEmail(), payment.getName());
    //
    //        return ResponseEntity
    //            .created(new URI("/api/payment/" + result.getId()))
    //            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
    //            .body(result);
    //    }

    //    @GetMapping("")
    //    public String getPayment(@Valid @RequestBody Payment payment) {
    //        this.paymentAmout = payment;
    //        return "paymentAmout";
    //    }

    @GetMapping("/paymentSB")
    public CreateHostedCheckoutResponse getRedirectUrl() throws URISyntaxException, IOException {
        Payment payment = new Payment();
        this.paymentAmout = payment;
        try (Client client = getClient()) {
            HostedCheckoutSpecificInput hostedCheckoutSpecificInput = new HostedCheckoutSpecificInput();
            hostedCheckoutSpecificInput.setLocale("en_GB");
            hostedCheckoutSpecificInput.setVariant("100");
            //hostedCheckoutSpecificInput.setReturnUrl(this.returnUrl);
            hostedCheckoutSpecificInput.setReturnUrl("http://localhost:8080/payment/list");
            hostedCheckoutSpecificInput.setShowResultPage(false);

            AmountOfMoney amountOfMoney = new AmountOfMoney();
            //amountOfMoney.setAmount(payment.getPaymentAmout());
            amountOfMoney.setAmount(20000l);
            //amountOfMoney.setAmount((long) paymentAmout.getPaymentAmout().intValue());
            amountOfMoney.setCurrencyCode("USD");

            Address billingAddress = new Address();
            billingAddress.setCountryCode("US");

            Customer customer = new Customer();
            customer.setBillingAddress(billingAddress);
            customer.setMerchantCustomerId("1234");

            Order order = new Order();
            order.setAmountOfMoney(amountOfMoney);
            order.setCustomer(customer);

            CreateHostedCheckoutRequest body = new CreateHostedCheckoutRequest();
            body.setHostedCheckoutSpecificInput(hostedCheckoutSpecificInput);
            body.setOrder(order);

            CreateHostedCheckoutResponse response = client.merchant("1204").hostedcheckouts().create(body);
            log.info("Worldline partial redirect url : {}", response.getPartialRedirectUrl());

            //Console.log(response.getPartialRedirectUrl());

            return response;
        }
    }

    @Value("${spring.application.apiKeyId}")
    String apiKeyId;

    @Value("${spring.application.secretApiKey}")
    String secretApiKey;

    private com.ingenico.connect.gateway.sdk.java.Client getClient() throws URISyntaxException {
        String apiKey = System.getProperty("apiKeyId", this.apiKeyId);
        String secretApi = System.getProperty("secretApiKey", this.secretApiKey);

        URL propertiesUrl = getClass().getResource("/hostedpaymentpage.properties");
        assert propertiesUrl != null;
        CommunicatorConfiguration configuration = Factory.createConfiguration(propertiesUrl.toURI(), apiKey, secretApi);
        return Factory.createClient(configuration);
    }

    @Value("${spring.application.paypalUrl}")
    String paypalUrl;

    @Value("${spring.application.returnUrl}")
    String returnUrl;

    @Value("${spring.application.cancelUrl}")
    String cancelUrl;

    @GetMapping("/paypal")
    public String setExpressCheckout()
        throws PayPalException, ClientActionRequiredException, SSLConfigurationException, MissingCredentialException, InvalidResponseDataException, InvalidCredentialException, IOException, HttpErrorException, InterruptedException, SAXException, ParserConfigurationException {
        //String paymentAmount = paymentAmout.getPaymentAmout();
        Long payerId = 5L;
        //String paymentAmount = paymentAmout.getPaymentAmout();

        String returnURL = this.returnUrl;
        String cancelURL = this.cancelUrl;

        PaymentActionCodeType paymentAction = PaymentActionCodeType.SALE;
        CurrencyCodeType currencyCode = CurrencyCodeType.EUR;

        Map<String, String> configurationMap = Configuration.getAcctAndConfig();
        PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);

        SetExpressCheckoutRequestType setExpressCheckoutReq = new SetExpressCheckoutRequestType();
        setExpressCheckoutReq.setVersion("63.0");

        SetExpressCheckoutRequestDetailsType details = new SetExpressCheckoutRequestDetailsType();

        PaymentDetailsType paymentDetails = new PaymentDetailsType();
        paymentDetails.setOrderDescription("PayGov integration with paypal");
        paymentDetails.setInvoiceID("INVOICE-" + Math.random());
        BasicAmountType orderTotal = new BasicAmountType();
        orderTotal.setValue("2000");
        orderTotal.setCurrencyID(currencyCode);
        paymentDetails.setOrderTotal(orderTotal);
        paymentDetails.setPaymentAction(paymentAction);
        details.setPaymentDetails(Arrays.asList(new PaymentDetailsType[] { paymentDetails }));
        details.setReturnURL(returnURL);
        details.setCancelURL(cancelURL);
        details.setCustom(payerId.toString());

        setExpressCheckoutReq.setSetExpressCheckoutRequestDetails(details);

        SetExpressCheckoutReq expressCheckoutReq = new SetExpressCheckoutReq();

        expressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutReq);

        setExpressCheckoutResponse = service.setExpressCheckout(expressCheckoutReq);

        getExpressCheckoutDetails(setExpressCheckoutResponse.getToken());

        String redirectURL = (this.paypalUrl + setExpressCheckoutResponse.getToken());

        log.info("Paypal returned url : {}", redirectURL);

        return JSONObject.quote(redirectURL);
    }

    AckCodeType doExpressResponse;

    @GetMapping("/paypalDoEC")
    public String startDoExpressResponse()
        throws ClientActionRequiredException, SSLConfigurationException, MissingCredentialException, InvalidResponseDataException, InvalidCredentialException, IOException, ParserConfigurationException, HttpErrorException, InterruptedException, SAXException {
        try {
            doExpressResponse(getExpressCheckoutDetails(setExpressCheckoutResponse.getToken()));
        } catch (PayPalException e) {
            e.printStackTrace();
        }
        return "DoExpressCheckoutPaymentResponseType : " + doExpressResponse;
    }

    public GetExpressCheckoutDetailsResponseDetailsType getExpressCheckoutDetails(String token)
        throws PayPalException, SAXException, ParserConfigurationException, SSLConfigurationException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, IOException, InterruptedException, InvalidCredentialException {
        Map<String, String> configurationMap = Configuration.getAcctAndConfig();
        PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);

        GetExpressCheckoutDetailsReq getExpressCheckoutDetailsReq = new GetExpressCheckoutDetailsReq();
        GetExpressCheckoutDetailsRequestType getExpressCheckoutDetailsRequestType = new GetExpressCheckoutDetailsRequestType();
        getExpressCheckoutDetailsRequestType.setVersion("63.0");

        getExpressCheckoutDetailsReq.setGetExpressCheckoutDetailsRequest(new GetExpressCheckoutDetailsRequestType(token));
        GetExpressCheckoutDetailsResponseType getExpressCheckoutDetailsResponseType = service.getExpressCheckoutDetails(
            getExpressCheckoutDetailsReq
        );

        log.info("GetExpressCheckoutDetailsResponseDetailsType");
        log.info(
            "PayerID         : {}",
            getExpressCheckoutDetailsResponseType.getGetExpressCheckoutDetailsResponseDetails().getPayerInfo().getPayerID()
        );
        log.info("Ack             : {}", getExpressCheckoutDetailsResponseType.getAck());
        log.info("Token           : {}", getExpressCheckoutDetailsResponseType.getGetExpressCheckoutDetailsResponseDetails().getToken());
        log.info(
            "Payment Details : {}",
            getExpressCheckoutDetailsResponseType.getGetExpressCheckoutDetailsResponseDetails().getPaymentDetails()
        );
        log.info(
            "Payment Info    : {}",
            getExpressCheckoutDetailsResponseType.getGetExpressCheckoutDetailsResponseDetails().getPaymentInfo()
        );
        log.info(
            "Billing Address : {}",
            getExpressCheckoutDetailsResponseType.getGetExpressCheckoutDetailsResponseDetails().getBillingAddress()
        );
        log.info(
            "Checkout Status : {}",
            getExpressCheckoutDetailsResponseType.getGetExpressCheckoutDetailsResponseDetails().getCheckoutStatus()
        );

        return getExpressCheckoutDetailsResponseType.getGetExpressCheckoutDetailsResponseDetails();
    }

    public void doExpressResponse(GetExpressCheckoutDetailsResponseDetailsType response)
        throws PayPalException, SAXException, ParserConfigurationException, SSLConfigurationException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, IOException, InterruptedException, InvalidCredentialException {
        Map<String, String> configurationMap = Configuration.getAcctAndConfig();

        PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);

        DoExpressCheckoutPaymentRequestType doExpressCheckoutPaymentRequestType = new DoExpressCheckoutPaymentRequestType();

        doExpressCheckoutPaymentRequestType.setVersion("63.0");

        DoExpressCheckoutPaymentRequestDetailsType paymentDetailsRequestType = new DoExpressCheckoutPaymentRequestDetailsType();

        paymentDetailsRequestType.setPaymentDetails(response.getPaymentDetails());

        paymentDetailsRequestType.setToken(response.getToken());

        paymentDetailsRequestType.setPayerID(response.getPayerInfo().getPayerID());

        paymentDetailsRequestType.setPaymentAction(PaymentActionCodeType.SALE);

        doExpressCheckoutPaymentRequestType.setDoExpressCheckoutPaymentRequestDetails(paymentDetailsRequestType);

        DoExpressCheckoutPaymentReq doExpressCheckoutPaymentReq = new DoExpressCheckoutPaymentReq();

        doExpressCheckoutPaymentReq.setDoExpressCheckoutPaymentRequest(doExpressCheckoutPaymentRequestType);

        DoExpressCheckoutPaymentResponseType doExpressCheckoutPaymentResponseType = service.doExpressCheckoutPayment(
            doExpressCheckoutPaymentReq
        );

        this.doExpressResponse = doExpressCheckoutPaymentResponseType.getAck();
        log.info("DoExpressCheckoutPayment : {}", doExpressCheckoutPaymentResponseType.getAck());
    }

    /**
     * {@code PUT  /payments/:id} : Updates an existing payment.
     *
     * @param id the id of the payment to save.
     * @param payment the payment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payment,
     * or with status {@code 400 (Bad Request)} if the payment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the payment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/payments/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable(value = "id", required = false) final Long id, @RequestBody Payment payment)
        throws URISyntaxException {
        log.debug("REST request to update Payment : {}, {}", id, payment);
        if (payment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, payment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Payment result = paymentService.update(payment);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, payment.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /payments/:id} : Partial updates given fields of an existing payment, field will ignore if it is null
     *
     * @param id the id of the payment to save.
     * @param payment the payment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payment,
     * or with status {@code 400 (Bad Request)} if the payment is not valid,
     * or with status {@code 404 (Not Found)} if the payment is not found,
     * or with status {@code 500 (Internal Server Error)} if the payment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/payments/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Payment> partialUpdatePayment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Payment payment
    ) throws URISyntaxException {
        log.debug("REST request to partial update Payment partially : {}, {}", id, payment);
        if (payment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, payment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Payment> result = paymentService.partialUpdate(payment);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, payment.getId().toString())
        );
    }

    /**
     * {@code GET  /payments} : get all the payments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of payments in body.
     */
    @GetMapping("/payments")
    public String getAllPayments() {
        final String uri = "https://mockbin.org/bin/165ce7bd-c7a6-4de8-bbf3-a5d04e2ba61c";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

    /**
     * {@code GET  /payments/:id} : get the "id" payment.
     *
     * @param id the id of the payment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the payment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payments/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long id) {
        log.debug("REST request to get Payment : {}", id);
        Optional<Payment> payment = paymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(payment);
    }

    /**
     * {@code DELETE  /payments/:id} : delete the "id" payment.
     *
     * @param id the id of the payment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/payments/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        log.debug("REST request to delete Payment : {}", id);
        paymentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
