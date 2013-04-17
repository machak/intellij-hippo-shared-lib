
/*
 * Copyright 2013 m.milicevic (http://www.machak.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.machak.idea.plugins.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         A fileSet allows the inclusion of groups of files into the assembly.
 *       
 * 
 * <p>Java class for FileSet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FileSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="useDefaultExcludes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="outputDirectory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includes" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="include" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
 *                   &lt;element name="exclude" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="fileMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="directoryMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="directory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lineEnding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filtered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileSet", namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", propOrder = {

})
public class FileSet {

    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", defaultValue = "true")
    protected Boolean useDefaultExcludes;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected String outputDirectory;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected FileSet.Includes includes;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected FileSet.Excludes excludes;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected String fileMode;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected String directoryMode;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected String directory;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected String lineEnding;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", defaultValue = "false")
    protected Boolean filtered;

    /**
     * Gets the value of the useDefaultExcludes property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseDefaultExcludes() {
        return useDefaultExcludes;
    }

    /**
     * Sets the value of the useDefaultExcludes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseDefaultExcludes(Boolean value) {
        this.useDefaultExcludes = value;
    }

    /**
     * Gets the value of the outputDirectory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputDirectory() {
        return outputDirectory;
    }

    /**
     * Sets the value of the outputDirectory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputDirectory(String value) {
        this.outputDirectory = value;
    }

    /**
     * Gets the value of the includes property.
     * 
     * @return
     *     possible object is
     *     {@link FileSet.Includes }
     *     
     */
    public FileSet.Includes getIncludes() {
        return includes;
    }

    /**
     * Sets the value of the includes property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileSet.Includes }
     *     
     */
    public void setIncludes(FileSet.Includes value) {
        this.includes = value;
    }

    /**
     * Gets the value of the excludes property.
     * 
     * @return
     *     possible object is
     *     {@link FileSet.Excludes }
     *     
     */
    public FileSet.Excludes getExcludes() {
        return excludes;
    }

    /**
     * Sets the value of the excludes property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileSet.Excludes }
     *     
     */
    public void setExcludes(FileSet.Excludes value) {
        this.excludes = value;
    }

    /**
     * Gets the value of the fileMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileMode() {
        return fileMode;
    }

    /**
     * Sets the value of the fileMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileMode(String value) {
        this.fileMode = value;
    }

    /**
     * Gets the value of the directoryMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectoryMode() {
        return directoryMode;
    }

    /**
     * Sets the value of the directoryMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectoryMode(String value) {
        this.directoryMode = value;
    }

    /**
     * Gets the value of the directory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * Sets the value of the directory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectory(String value) {
        this.directory = value;
    }

    /**
     * Gets the value of the lineEnding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineEnding() {
        return lineEnding;
    }

    /**
     * Sets the value of the lineEnding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineEnding(String value) {
        this.lineEnding = value;
    }

    /**
     * Gets the value of the filtered property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFiltered() {
        return filtered;
    }

    /**
     * Sets the value of the filtered property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFiltered(Boolean value) {
        this.filtered = value;
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
     *         &lt;element name="exclude" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "exclude"
    })
    public static class Excludes {

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
        protected List<String> exclude;

        /**
         * Gets the value of the exclude property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the exclude property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getExclude().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
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
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="include" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "include"
    })
    public static class Includes {

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
        protected List<String> include;

        /**
         * Gets the value of the include property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the include property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInclude().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getInclude() {
            if (include == null) {
                include = new ArrayList<String>();
            }
            return this.include;
        }

    }

}
