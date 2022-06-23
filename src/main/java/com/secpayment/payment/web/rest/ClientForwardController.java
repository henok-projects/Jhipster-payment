package com.secpayment.payment.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientForwardController {

    /**
     * Forwards any unmapped paths (except those containing a period) to the client {@code index.html}.
     * @return forward to client {@code index.html}.
     */
    @GetMapping(value = "/**/{path:[^\\.]*}")
    public String forward() {
        return "forward:/";
    }

    @GetMapping("/payment")
    public String getPayments() {
        //PaymentPageRequest request = new PaymentPageRequest();
        // request.setMid(1234567890L);
        // request.setTransactionChannel("Web Online");
        // request.setOrderId("orderid");
        // request.setAmount(123.0);
        // request.setCurrency("SEK");
        // request.setConsumerCountry("SE");
        // request.setConsumerLanguage("sv");
        // request.setReturnUrl("http://merchant.com?f=3&f=q");
        // request.setAuthorizationType(AuthorizationType.PRE_AUTHORIZATION);

        // String redirectUrl = paymentPageHandler.createRedirectUrl(request);
        return "no";
    }
}
