
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
 * Contains configuration options for including the source files of a project module in an assembly.
 * <p>
 * <p>
 * <p>Java class for ModuleSources complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="ModuleSources">
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
 *         &lt;element name="fileMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="directoryMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileSets" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="fileSet" type="{http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2}FileSet"
 * maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="includeModuleDirectory" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="excludeSubModuleDirectories" type="{http://www.w3.org/2001/XMLSchema}boolean"
 * minOccurs="0"/>
 *         &lt;element name="outputDirectoryMapping" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModuleSources", namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2", propOrder = {

})
public class ModuleSources {

    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2", defaultValue = "true")
    protected Boolean useDefaultExcludes;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected String outputDirectory;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected ModuleSources.Includes includes;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected ModuleSources.Excludes excludes;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected String fileMode;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected String directoryMode;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected ModuleSources.FileSets fileSets;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2", defaultValue = "true")
    protected Boolean includeModuleDirectory;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2", defaultValue = "true")
    protected Boolean excludeSubModuleDirectories;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2", defaultValue = "${module.artifactId}")
    protected String outputDirectoryMapping;

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
     * Gets the value of the includes property.
     *
     * @return possible object is {@link ModuleSources.Includes }
     */
    public ModuleSources.Includes getIncludes() {
        return includes;
    }

    /**
     * Sets the value of the includes property.
     *
     * @param value allowed object is {@link ModuleSources.Includes }
     */
    public void setIncludes(ModuleSources.Includes value) {
        this.includes = value;
    }

    /**
     * Gets the value of the excludes property.
     *
     * @return possible object is {@link ModuleSources.Excludes }
     */
    public ModuleSources.Excludes getExcludes() {
        return excludes;
    }

    /**
     * Sets the value of the excludes property.
     *
     * @param value allowed object is {@link ModuleSources.Excludes }
     */
    public void setExcludes(ModuleSources.Excludes value) {
        this.excludes = value;
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
     * Gets the value of the directoryMode property.
     *
     * @return possible object is {@link String }
     */
    public String getDirectoryMode() {
        return directoryMode;
    }

    /**
     * Sets the value of the directoryMode property.
     *
     * @param value allowed object is {@link String }
     */
    public void setDirectoryMode(String value) {
        this.directoryMode = value;
    }

    /**
     * Gets the value of the fileSets property.
     *
     * @return possible object is {@link ModuleSources.FileSets }
     */
    public ModuleSources.FileSets getFileSets() {
        return fileSets;
    }

    /**
     * Sets the value of the fileSets property.
     *
     * @param value allowed object is {@link ModuleSources.FileSets }
     */
    public void setFileSets(ModuleSources.FileSets value) {
        this.fileSets = value;
    }

    /**
     * Gets the value of the includeModuleDirectory property.
     *
     * @return possible object is {@link Boolean }
     */
    public Boolean isIncludeModuleDirectory() {
        return includeModuleDirectory;
    }

    /**
     * Sets the value of the includeModuleDirectory property.
     *
     * @param value allowed object is {@link Boolean }
     */
    public void setIncludeModuleDirectory(Boolean value) {
        this.includeModuleDirectory = value;
    }

    /**
     * Gets the value of the excludeSubModuleDirectories property.
     *
     * @return possible object is {@link Boolean }
     */
    public Boolean isExcludeSubModuleDirectories() {
        return excludeSubModuleDirectories;
    }

    /**
     * Sets the value of the excludeSubModuleDirectories property.
     *
     * @param value allowed object is {@link Boolean }
     */
    public void setExcludeSubModuleDirectories(Boolean value) {
        this.excludeSubModuleDirectories = value;
    }

    /**
     * Gets the value of the outputDirectoryMapping property.
     *
     * @return possible object is {@link String }
     */
    public String getOutputDirectoryMapping() {
        return outputDirectoryMapping;
    }

    /**
     * Sets the value of the outputDirectoryMapping property.
     *
     * @param value allowed object is {@link String }
     */
    public void setOutputDirectoryMapping(String value) {
        this.outputDirectoryMapping = value;
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
     *         &lt;element name="fileSet" type="{http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2}FileSet"
     * maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "fileSet"
    })
    public static class FileSets {

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
        protected List<FileSet> fileSet;

        /**
         * Gets the value of the fileSet property.
         * <p>
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the fileSet property.
         * <p>
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFileSet().add(newItem);
         * </pre>
         * <p>
         * <p>
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FileSet }
         */
        public List<FileSet> getFileSet() {
            if (fileSet == null) {
                fileSet = new ArrayList<FileSet>();
            }
            return this.fileSet;
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
