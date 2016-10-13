
/*
 * Copyright 2016 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.model.component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A file allows individual file inclusion with the option to change
 * the destination filename not supported by fileSets.
 * <p>
 * <p>
 * <p>Java class for FileItem complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="FileItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outputDirectory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lineEnding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filtered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileItem", namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2", propOrder = {

})
public class FileItem {

    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected String source;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected String outputDirectory;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected String destName;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected String fileMode;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected String lineEnding;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2", defaultValue = "false")
    protected Boolean filtered;

    /**
     * Gets the value of the source property.
     *
     * @return possible object is {@link String }
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     *
     * @param value allowed object is {@link String }
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the outputDirectory property.
     *
     * @return possible object is {@link String }
     */
    public String getOutputDirectory() {
        return outputDirectory;
    }

    /**
     * Sets the value of the outputDirectory property.
     *
     * @param value allowed object is {@link String }
     */
    public void setOutputDirectory(String value) {
        this.outputDirectory = value;
    }

    /**
     * Gets the value of the destName property.
     *
     * @return possible object is {@link String }
     */
    public String getDestName() {
        return destName;
    }

    /**
     * Sets the value of the destName property.
     *
     * @param value allowed object is {@link String }
     */
    public void setDestName(String value) {
        this.destName = value;
    }

    /**
     * Gets the value of the fileMode property.
     *
     * @return possible object is {@link String }
     */
    public String getFileMode() {
        return fileMode;
    }

    /**
     * Sets the value of the fileMode property.
     *
     * @param value allowed object is {@link String }
     */
    public void setFileMode(String value) {
        this.fileMode = value;
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

}
