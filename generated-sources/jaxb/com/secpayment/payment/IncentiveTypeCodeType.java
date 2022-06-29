//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.06.27 at 12:09:28 pm EAT 
//


package com.secpayment.payment;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IncentiveTypeCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IncentiveTypeCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Coupon"/>
 *     &lt;enumeration value="eBayGiftCertificate"/>
 *     &lt;enumeration value="eBayGiftCard"/>
 *     &lt;enumeration value="PayPalRewardVoucher"/>
 *     &lt;enumeration value="MerchantGiftCertificate"/>
 *     &lt;enumeration value="eBayRewardVoucher"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IncentiveTypeCodeType")
@XmlEnum
public enum IncentiveTypeCodeType {

    @XmlEnumValue("Coupon")
    COUPON("Coupon"),
    @XmlEnumValue("eBayGiftCertificate")
    E_BAY_GIFT_CERTIFICATE("eBayGiftCertificate"),
    @XmlEnumValue("eBayGiftCard")
    E_BAY_GIFT_CARD("eBayGiftCard"),
    @XmlEnumValue("PayPalRewardVoucher")
    PAY_PAL_REWARD_VOUCHER("PayPalRewardVoucher"),
    @XmlEnumValue("MerchantGiftCertificate")
    MERCHANT_GIFT_CERTIFICATE("MerchantGiftCertificate"),
    @XmlEnumValue("eBayRewardVoucher")
    E_BAY_REWARD_VOUCHER("eBayRewardVoucher");
    private final String value;

    IncentiveTypeCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IncentiveTypeCodeType fromValue(String v) {
        for (IncentiveTypeCodeType c: IncentiveTypeCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
