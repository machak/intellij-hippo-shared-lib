
/*
 * Copyright 2016 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.model.component;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Specifies options for including/excluding/filtering items extracted from an archive. (Since 2.2)
 * <p>
 * <p>
 * <p>Java class for UnpackOptions complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="UnpackOptions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="includes" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="include" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"
 * minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="excludes" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="exclude" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"
 * minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="filtered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="lineEnding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useDefaultExcludes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnpackOptions", namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2", propOrder = {

})
public class UnpackOptions {

    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected UnpackOptions.Includes includes;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected UnpackOptions.Excludes excludes;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2", defaultValue = "false")
    protected Boolean filtered;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected String lineEnding;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2", defaultValue = "true")
    protected Boolean useDefaultExcludes;

    /**
     * Gets the value of the includes property.
     *
     * @return possible object is {@link UnpackOptions.Includes }
     */
    public UnpackOptions.Includes getIncludes() {
        return includes;
    }

    /**
     * Sets the value of the includes property.
     *
     * @param value allowed object is {@link UnpackOptions.Includes }
     */
    public void setIncludes(UnpackOptions.Includes value) {
        this.includes = value;
    }

    /**
     * Gets the value of the excludes property.
     *
     * @return possible object is {@link UnpackOptions.Excludes }
     */
    public UnpackOptions.Excludes getExcludes() {
        return excludes;
    }

    /**
     * Sets the value of the excludes property.
     *
     * @param value allowed object is {@link UnpackOptions.Excludes }
     */
    public void setExcludes(UnpackOptions.Excludes value) {
        this.excludes = value;
    }

    /**
     * Gets the value of the filtered property.
     *
     * @return possible object is {@link Boolean }
     */
    public Boolean isFiltered() {
        return filtered;
    }

    /**
     * Sets the value of the filtered property.
     *
     * @param value allowed object is {@link Boolean }
     */
    public void setFiltered(Boolean value) {
        this.filtered = value;
    }

    /**
     * Gets the value of the lineEnding property.
     *
     * @return possible object is {@link String }
     */
    public String getLineEnding() {
        return lineEnding;
    }

    /**
     * Sets the value of the lineEnding property.
     *
     * @param value allowed object is {@link String }
     */
    public void setLineEnding(String value) {
        this.lineEnding = value;
    }

    /**
     * Gets the value of the useDefaultExcludes property.
     *
     * @return possible object is {@link Boolean }
     */
    public Boolean isUseDefaultExcludes() {
        return useDefaultExcludes;
    }

    /**
     * Sets the value of the useDefaultExcludes property.
     *
     * @param value allowed object is {@link Boolean }
     */
    public void setUseDefaultExcludes(Boolean value) {
        this.useDefaultExcludes = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * <p>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="exclude" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"
     * minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "exclude"
    })
    public static class Excludes {

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
        protected List<String> exclude;

        /**
         * Gets the value of the exclude property.
         * <p>
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the exclude property.
         * <p>
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getExclude().add(newItem);
         * </pre>
         * <p>
         * <p>
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         */
        public List<String> getExclude() {
            if (exclude == null) {
                exclude = new ArrayList<String>();
            }
            return this.exclude;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * <p>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="include" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"
     * minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "include"
    })
    public static class Includes {

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
        protected List<String> include;

        /**
         * Gets the value of the include property.
         * <p>
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the include property.
         * <p>
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInclude().add(newItem);
         * </pre>
         * <p>
         * <p>
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         */
        public List<String> getInclude() {
            if (include == null) {
                include = new ArrayList<String>();
            }
            return this.include;
        }

    }

}
