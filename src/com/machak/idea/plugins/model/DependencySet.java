
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
 *         A dependencySet allows inclusion and exclusion of project dependencies
 *         in the assembly.
 *       
 * 
 * <p>Java class for DependencySet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DependencySet">
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
 *         &lt;element name="useStrictFiltering" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="outputFileNameMapping" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unpack" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="unpackOptions" type="{http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2}UnpackOptions" minOccurs="0"/>
 *         &lt;element name="scope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useProjectArtifact" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="useProjectAttachments" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="useTransitiveDependencies" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="useTransitiveFiltering" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DependencySet", namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", propOrder = {

})
public class DependencySet {

    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected String outputDirectory;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected DependencySet.Includes includes;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected DependencySet.Excludes excludes;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected String fileMode;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected String directoryMode;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", defaultValue = "false")
    protected Boolean useStrictFiltering;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", defaultValue = "${artifact.artifactId}-${artifact.version}${dashClassifier?}.${artifact.extension}")
    protected String outputFileNameMapping;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", defaultValue = "false")
    protected Boolean unpack;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected UnpackOptions unpackOptions;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", defaultValue = "runtime")
    protected String scope;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", defaultValue = "true")
    protected Boolean useProjectArtifact;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", defaultValue = "false")
    protected Boolean useProjectAttachments;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", defaultValue = "true")
    protected Boolean useTransitiveDependencies;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", defaultValue = "false")
    protected Boolean useTransitiveFiltering;

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
     *     {@link DependencySet.Includes }
     *     
     */
    public DependencySet.Includes getIncludes() {
        return includes;
    }

    /**
     * Sets the value of the includes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DependencySet.Includes }
     *     
     */
    public void setIncludes(DependencySet.Includes value) {
        this.includes = value;
    }

    /**
     * Gets the value of the excludes property.
     * 
     * @return
     *     possible object is
     *     {@link DependencySet.Excludes }
     *     
     */
    public DependencySet.Excludes getExcludes() {
        return excludes;
    }

    /**
     * Sets the value of the excludes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DependencySet.Excludes }
     *     
     */
    public void setExcludes(DependencySet.Excludes value) {
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
     * Gets the value of the useStrictFiltering property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseStrictFiltering() {
        return useStrictFiltering;
    }

    /**
     * Sets the value of the useStrictFiltering property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseStrictFiltering(Boolean value) {
        this.useStrictFiltering = value;
    }

    /**
     * Gets the value of the outputFileNameMapping property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputFileNameMapping() {
        return outputFileNameMapping;
    }

    /**
     * Sets the value of the outputFileNameMapping property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputFileNameMapping(String value) {
        this.outputFileNameMapping = value;
    }

    /**
     * Gets the value of the unpack property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUnpack() {
        return unpack;
    }

    /**
     * Sets the value of the unpack property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUnpack(Boolean value) {
        this.unpack = value;
    }

    /**
     * Gets the value of the unpackOptions property.
     * 
     * @return
     *     possible object is
     *     {@link UnpackOptions }
     *     
     */
    public UnpackOptions getUnpackOptions() {
        return unpackOptions;
    }

    /**
     * Sets the value of the unpackOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnpackOptions }
     *     
     */
    public void setUnpackOptions(UnpackOptions value) {
        this.unpackOptions = value;
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
     * Gets the value of the useProjectArtifact property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseProjectArtifact() {
        return useProjectArtifact;
    }

    /**
     * Sets the value of the useProjectArtifact property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseProjectArtifact(Boolean value) {
        this.useProjectArtifact = value;
    }

    /**
     * Gets the value of the useProjectAttachments property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseProjectAttachments() {
        return useProjectAttachments;
    }

    /**
     * Sets the value of the useProjectAttachments property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseProjectAttachments(Boolean value) {
        this.useProjectAttachments = value;
    }

    /**
     * Gets the value of the useTransitiveDependencies property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseTransitiveDependencies() {
        return useTransitiveDependencies;
    }

    /**
     * Sets the value of the useTransitiveDependencies property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseTransitiveDependencies(Boolean value) {
        this.useTransitiveDependencies = value;
    }

    /**
     * Gets the value of the useTransitiveFiltering property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseTransitiveFiltering() {
        return useTransitiveFiltering;
    }

    /**
     * Sets the value of the useTransitiveFiltering property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseTransitiveFiltering(Boolean value) {
        this.useTransitiveFiltering = value;
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
