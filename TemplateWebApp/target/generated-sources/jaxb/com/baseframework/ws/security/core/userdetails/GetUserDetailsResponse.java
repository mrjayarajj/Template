//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.28 at 10:46:17 AM PST 
//


package com.baseframework.ws.security.core.userdetails;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="authorities">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="authority" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="account-locked" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="account-expired" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="credentials-expired" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "userId",
    "authorities",
    "username",
    "password",
    "enabled",
    "accountLocked",
    "accountExpired",
    "credentialsExpired"
})
@XmlRootElement(name = "GetUserDetailsResponse")
public class GetUserDetailsResponse {

    protected int userId;
    @XmlElement(required = true)
    protected GetUserDetailsResponse.Authorities authorities;
    @XmlElement(required = true)
    protected String username;
    @XmlElement(required = true)
    protected String password;
    protected boolean enabled;
    @XmlElement(name = "account-locked")
    protected boolean accountLocked;
    @XmlElement(name = "account-expired")
    protected boolean accountExpired;
    @XmlElement(name = "credentials-expired")
    protected boolean credentialsExpired;

    /**
     * Gets the value of the userId property.
     * 
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     */
    public void setUserId(int value) {
        this.userId = value;
    }

    /**
     * Gets the value of the authorities property.
     * 
     * @return
     *     possible object is
     *     {@link GetUserDetailsResponse.Authorities }
     *     
     */
    public GetUserDetailsResponse.Authorities getAuthorities() {
        return authorities;
    }

    /**
     * Sets the value of the authorities property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetUserDetailsResponse.Authorities }
     *     
     */
    public void setAuthorities(GetUserDetailsResponse.Authorities value) {
        this.authorities = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the enabled property.
     * 
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the value of the enabled property.
     * 
     */
    public void setEnabled(boolean value) {
        this.enabled = value;
    }

    /**
     * Gets the value of the accountLocked property.
     * 
     */
    public boolean isAccountLocked() {
        return accountLocked;
    }

    /**
     * Sets the value of the accountLocked property.
     * 
     */
    public void setAccountLocked(boolean value) {
        this.accountLocked = value;
    }

    /**
     * Gets the value of the accountExpired property.
     * 
     */
    public boolean isAccountExpired() {
        return accountExpired;
    }

    /**
     * Sets the value of the accountExpired property.
     * 
     */
    public void setAccountExpired(boolean value) {
        this.accountExpired = value;
    }

    /**
     * Gets the value of the credentialsExpired property.
     * 
     */
    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    /**
     * Sets the value of the credentialsExpired property.
     * 
     */
    public void setCredentialsExpired(boolean value) {
        this.credentialsExpired = value;
    }


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
     *         &lt;element name="authority" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
        "authority"
    })
    public static class Authorities {

        @XmlElement(required = true)
        protected List<String> authority;

        /**
         * Gets the value of the authority property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the authority property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAuthority().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getAuthority() {
            if (authority == null) {
                authority = new ArrayList<String>();
            }
            return this.authority;
        }

    }

}
