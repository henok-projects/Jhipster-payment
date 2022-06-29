package com.secpayment.payment.util;

import java.util.HashMap;
import java.util.Map;

/**
 *  For a full list of configuration parameters refer in wiki page.(https://github.com/paypal/sdk-core-java/blob/master/README.md)
 */
public class Configuration {

    private Configuration() {}

    public static Map<String, String> getAcctAndConfig() {
        Map<String, String> configMap = new HashMap<>(getConfig());

        configMap.put("acct1.UserName", "henokaddis72_api1.gmail.com");
        configMap.put("acct1.Password", "4GAJZFPCSQJ3XM9P");
        configMap.put("acct1.Signature", "A8Vpc0Ws5acpR3s0gcM6u4Qtk1utAGDumGMSC8rerk0dKa3jM5m1Yuug");

        //        configMap.put("acct1.UserName", "henokaddispop_api1.gmail.com");
        //        configMap.put("acct1.Password", "CH6LR98HGV884K7E");
        //        configMap.put("acct1.Signature", "AqXu0ybKuB8HE7UckDfvfyfLMXUfA7wmBcsGhNFbfSmPfxeX9kEXcA.6");
        //

        /** Subject is optional, only required in case of third party permission
         *   configMap.put("acct1.Subject", "");

         * Sample Certificate credential
         * configMap.put("acct2.UserName", "certuser_biz_api1.paypal.com");
         * configMap.put("acct2.Password", "D6JNKKULHN3G5B8A");
         * configMap.put("acct2.CertKey", "password");
         * configMap.put("acct2.CertPath", "resource/sdk-cert.p12");
         * configMap.put("acct2.AppId", "APP-80W284485P519543T");
         */

        return configMap;
    }

    public static Map<String, String> getConfig() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode", "sandbox");

        /** These values are defaulted in SDK. If you want to override default values, uncomment it and add your value.
         * configMap.put("http.ConnectionTimeOut", "5000");
         * configMap.put("http.Retry", "2");
         * configMap.put("http.ReadTimeOut", "30000");
         * configMap.put("http.MaxConnection", "100");
         */
        return configMap;
    }
}
