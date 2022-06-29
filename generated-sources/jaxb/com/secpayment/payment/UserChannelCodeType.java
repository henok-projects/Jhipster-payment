//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.06.27 at 12:09:28 pm EAT 
//


package com.secpayment.payment;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserChannelCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UserChannelCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="WEB"/>
 *     &lt;enumeration value="MOBILE"/>
 *     &lt;enumeration value="POS"/>
 *     &lt;enumeration value="KIOSK"/>
 *     &lt;enumeration value="IHSTB"/>
 *     &lt;enumeration value="IVR"/>
 *     &lt;enumeration value="ADMIN"/>
 *     &lt;enumeration value="CSOPS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UserChannelCodeType")
@XmlEnum
public enum UserChannelCodeType {

    WEB,
    MOBILE,
    POS,
    KIOSK,
    IHSTB,
    IVR,
    ADMIN,
    CSOPS;

    public String value() {
        return name();
    }

    public static UserChannelCodeType fromValue(String v) {
        return valueOf(v);
    }

}
