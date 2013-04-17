
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
 * 
 *         Defines a Maven repository to be included in the assembly. The artifacts
 *         available to be included in a repository are your project's dependency
 *         artifacts. The repository created contains the needed metadata entries
 *         and also contains both sha1 and md5 checksums. This is useful for creating
 *         archives which will be deployed to internal repositories.
 * 
 *         <br/><b>NOTE:</b> Currently, only artifacts from the central repository
 *         are allowed.
 * 
 *       
 * 
 * <p>Java class for Repository complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Repository">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
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
 *         &lt;element name="includeMetadata" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="groupVersionAlignments" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="groupVersionAlignment" type="{http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2}GroupVersionAlignment" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="scope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Repository", namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", propOrder = {

})
public class Repository {

    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected String outputDirectory;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected Repository.Includes includes;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected Repository.Excludes excludes;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected String fileMode;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected String directoryMode;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", defaultValue = "false")
    protected Boolean includeMetadata;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected Repository.GroupVersionAlignments groupVersionAlignments;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", defaultValue = "runtime")
    protected String scope;

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
     *     {@link Repository.Includes }
     *     
     */
    public Repository.Includes getIncludes() {
        return includes;
    }

    /**
     * Sets the value of the includes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Repository.Includes }
     *     
     */
    public void setIncludes(Repository.Includes value) {
        this.includes = value;
    }

    /**
     * Gets the value of the excludes property.
     * 
     * @return
     *     possible object is
     *     {@link Repository.Excludes }
     *     
     */
    public Repository.Excludes getExcludes() {
        return excludes;
    }

    /**
     * Sets the value of the excludes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Repository.Excludes }
     *     
     */
    public void setExcludes(Repository.Excludes value) {
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
     * Gets the value of the includeMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeMetadata() {
        return includeMetadata;
    }

    /**
     * Sets the value of the includeMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeMetadata(Boolean value) {
        this.includeMetadata = value;
    }

    /**
     * Gets the value of the groupVersionAlignments property.
     * 
     * @return
     *     possible object is
     *     {@link Repository.GroupVersionAlignments }
     *     
     */
    public Repository.GroupVersionAlignments getGroupVersionAlignments() {
        return groupVersionAlignments;
    }

    /**
     * Sets the value of the groupVersionAlignments property.
     * 
     * @param value
     *     allowed object is
     *     {@link Repository.GroupVersionAlignments }
     *     
     */
    public void setGroupVersionAlignments(Repository.GroupVersionAlignments value) {
        this.groupVersionAlignments = value;
    }

    /**
     * Gets the value of the scope property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScope() {
        return scope;
    }

    /**
     * Sets the value of the scope property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScope(String value) {
        this.scope = value;
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
     *         &lt;element name="groupVersionAlignment" type="{http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2}GroupVersionAlignment" maxOccurs="unbounded" minOccurs="0"/>
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
        "groupVersionAlignment"
    })
    public static class GroupVersionAlignments {

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
        protected List<GroupVersionAlignment> groupVersionAlignment;

        /**
         * Gets the value of the groupVersionAlignment property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the groupVersionAlignment property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getGroupVersionAlignment().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GroupVersionAlignment }
         * 
         * 
         */
        public List<GroupVersionAlignment> getGroupVersionAlignment() {
            if (groupVersionAlignment == null) {
                groupVersionAlignment = new ArrayList<GroupVersionAlignment>();
            }
            return this.groupVersionAlignment;
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
