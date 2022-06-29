//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.06.27 at 12:09:28 pm EAT 
//


package com.secpayment.payment;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DoExpressCheckoutPaymentResponseDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DoExpressCheckoutPaymentResponseDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Token" type="{urn:ebay:apis:eBLBaseComponents}ExpressCheckoutTokenType"/>
 *         &lt;element name="PaymentInfo" type="{urn:ebay:apis:eBLBaseComponents}PaymentInfoType" maxOccurs="10" minOccurs="0"/>
 *         &lt;element name="BillingAgreementID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RedirectRequired" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MsgSubID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SuccessPageRedirectRequested" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserSelectedOptions" type="{urn:ebay:apis:eBLBaseComponents}UserSelectedOptionType" minOccurs="0"/>
 *         &lt;element name="CoupledPaymentInfo" type="{urn:ebay:apis:eBLBaseComponents}CoupledPaymentInfoType" maxOccurs="5" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DoExpressCheckoutPaymentResponseDetailsType", propOrder = {
    "token",
    "paymentInfo",
    "billingAgreementID",
    "redirectRequired",
    "note",
    "msgSubID",
    "successPageRedirectRequested",
    "userSelectedOptions",
    "coupledPaymentInfo"
})
public class DoExpressCheckoutPaymentResponseDetailsType {

    @XmlElement(name = "Token", required = true)
    protected String token;
    @XmlElement(name = "PaymentInfo")
    protected List<PaymentInfoType> paymentInfo;
    @XmlElement(name = "BillingAgreementID")
    protected String billingAgreementID;
    @XmlElement(name = "RedirectRequired")
    protected String redirectRequired;
    @XmlElement(name = "Note")
    protected String note;
    @XmlElement(name = "MsgSubID")
    protected String msgSubID;
    @XmlElement(name = "SuccessPageRedirectRequested")
    protected String successPageRedirectRequested;
    @XmlElement(name = "UserSelectedOptions")
    protected UserSelectedOptionType userSelectedOptions;
    @XmlElement(name = "CoupledPaymentInfo")
    protected List<CoupledPaymentInfoType> coupledPaymentInfo;

    /**
     * Gets the value of the token property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

    /**
     * Gets the value of the paymentInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentInfoType }
     * 
     * 
     */
    public List<PaymentInfoType> getPaymentInfo() {
        if (paymentInfo == null) {
            paymentInfo = new ArrayList<PaymentInfoType>();
        }
        return this.paymentInfo;
    }

    /**
     * Gets the value of the billingAgreementID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingAgreementID() {
        return billingAgreementID;
    }

    /**
     * Sets the value of the billingAgreementID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingAgreementID(String value) {
        this.billingAgreementID = value;
    }

    /**
     * Gets the value of the redirectRequired property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRedirectRequired() {
        return redirectRequired;
    }

    /**
     * Sets the value of the redirectRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRedirectRequired(String value) {
        this.redirectRequired = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Gets the value of the msgSubID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgSubID() {
        return msgSubID;
    }

    /**
     * Sets the value of the msgSubID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgSubID(String value) {
        this.msgSubID = value;
    }

    /**
     * Gets the value of the successPageRedirectRequested property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuccessPageRedirectRequested() {
        return successPageRedirectRequested;
    }

    /**
     * Sets the value of the successPageRedirectRequested property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuccessPageRedirectRequested(String value) {
        this.successPageRedirectRequested = value;
    }

    /**
     * Gets the value of the userSelectedOptions property.
     * 
     * @return
     *     possible object is
     *     {@link UserSelectedOptionType }
     *     
     */
    public UserSelectedOptionType getUserSelectedOptions() {
        return userSelectedOptions;
    }

    /**
     * Sets the value of the userSelectedOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserSelectedOptionType }
     *     
     */
    public void setUserSelectedOptions(UserSelectedOptionType value) {
        this.userSelectedOptions = value;
    }

    /**
     * Gets the value of the coupledPaymentInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coupledPaymentInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCoupledPaymentInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CoupledPaymentInfoType }
     * 
     * 
     */
    public List<CoupledPaymentInfoType> getCoupledPaymentInfo() {
        if (coupledPaymentInfo == null) {
            coupledPaymentInfo = new ArrayList<CoupledPaymentInfoType>();
        }
        return this.coupledPaymentInfo;
    }

}
