//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.06.27 at 12:09:28 pm EAT 
//


package com.secpayment.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:ebay:api:PayPalAPI}BillOutstandingAmountRequest"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "billOutstandingAmountRequest"
})
@XmlRootElement(name = "BillOutstandingAmountReq", namespace = "urn:ebay:api:PayPalAPI")
public class BillOutstandingAmountReq {

    @XmlElement(name = "BillOutstandingAmountRequest", namespace = "urn:ebay:api:PayPalAPI", required = true)
    protected BillOutstandingAmountRequestType billOutstandingAmountRequest;

    /**
     * Gets the value of the billOutstandingAmountRequest property.
     * 
     * @return
     *     possible object is
     *     {@link BillOutstandingAmountRequestType }
     *     
     */
    public BillOutstandingAmountRequestType getBillOutstandingAmountRequest() {
        return billOutstandingAmountRequest;
    }

    /**
     * Sets the value of the billOutstandingAmountRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillOutstandingAmountRequestType }
     *     
     */
    public void setBillOutstandingAmountRequest(BillOutstandingAmountRequestType value) {
        this.billOutstandingAmountRequest = value;
    }

}
