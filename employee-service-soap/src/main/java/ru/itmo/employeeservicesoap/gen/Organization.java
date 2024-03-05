//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.03.05 at 01:59:18 AM MSK 
//


package ru.itmo.employeeservicesoap.gen;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for organization complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="organization"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="coordinates" type="{http://www.itmo.ru/employeeservicesoap/gen}coordinates"/&gt;
 *         &lt;element name="creationDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="annualTurnover" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="fullName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="employeesCount" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="type" type="{http://www.itmo.ru/employeeservicesoap/gen}organizationType"/&gt;
 *         &lt;element name="postalAddress" type="{http://www.itmo.ru/employeeservicesoap/gen}address"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "organization", propOrder = {
    "id",
    "name",
    "coordinates",
    "creationDate",
    "annualTurnover",
    "fullName",
    "employeesCount",
    "type",
    "postalAddress"
})
public class Organization {

    protected long id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected Coordinates coordinates;
    @XmlElement(required = true)
    protected String creationDate;
    protected float annualTurnover;
    @XmlElement(required = true)
    protected String fullName;
    protected int employeesCount;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected OrganizationType type;
    @XmlElement(required = true)
    protected Address postalAddress;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the coordinates property.
     * 
     * @return
     *     possible object is
     *     {@link Coordinates }
     *     
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the value of the coordinates property.
     * 
     * @param value
     *     allowed object is
     *     {@link Coordinates }
     *     
     */
    public void setCoordinates(Coordinates value) {
        this.coordinates = value;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreationDate(String value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the annualTurnover property.
     * 
     */
    public float getAnnualTurnover() {
        return annualTurnover;
    }

    /**
     * Sets the value of the annualTurnover property.
     * 
     */
    public void setAnnualTurnover(float value) {
        this.annualTurnover = value;
    }

    /**
     * Gets the value of the fullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullName(String value) {
        this.fullName = value;
    }

    /**
     * Gets the value of the employeesCount property.
     * 
     */
    public int getEmployeesCount() {
        return employeesCount;
    }

    /**
     * Sets the value of the employeesCount property.
     * 
     */
    public void setEmployeesCount(int value) {
        this.employeesCount = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationType }
     *     
     */
    public OrganizationType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationType }
     *     
     */
    public void setType(OrganizationType value) {
        this.type = value;
    }

    /**
     * Gets the value of the postalAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getPostalAddress() {
        return postalAddress;
    }

    /**
     * Sets the value of the postalAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setPostalAddress(Address value) {
        this.postalAddress = value;
    }

}
