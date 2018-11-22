


/*
 * Copyright 2013 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Contains configuration options for including the binary files of a
 * project module in an assembly.
 * <p/>
 * <p/>
 * <p>Java class for ModuleBinaries complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="ModuleBinaries">
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
 *         &lt;element name="attachmentClassifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeDependencies" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="dependencySets" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="dependencySet" type="{http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2}DependencySet" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="unpack" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="unpackOptions" type="{http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2}UnpackOptions" minOccurs="0"/>
 *         &lt;element name="outputFileNameMapping" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModuleBinaries", namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", propOrder = {

})
public class ModuleBinaries {

    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected String outputDirectory;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected ModuleBinaries.Includes includes;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected ModuleBinaries.Excludes excludes;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected String fileMode;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected String directoryMode;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected String attachmentClassifier;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", defaultValue = "true")
    protected Boolean includeDependencies;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected ModuleBinaries.DependencySets dependencySets;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", defaultValue = "true")
    protected Boolean unpack;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected UnpackOptions unpackOptions;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", defaultValue = "${module.artifactId}-${module.version}${dashClassifier?}.${module.extension}")
    protected String outputFileNameMapping;

    /**
     * Gets the value of the outputDirectory property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getOutputDirectory() {
        return outputDirectory;
    }

    /**
     * Sets the value of the outputDirectory property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOutputDirectory(String value) {
        this.outputDirectory = value;
    }

    /**
     * Gets the value of the includes property.
     *
     * @return possible object is
     *         {@link ModuleBinaries.Includes }
     */
    public ModuleBinaries.Includes getIncludes() {
        return includes;
    }

    /**
     * Sets the value of the includes property.
     *
     * @param value allowed object is
     *              {@link ModuleBinaries.Includes }
     */
    public void setIncludes(ModuleBinaries.Includes value) {
        this.includes = value;
    }

    /**
     * Gets the value of the excludes property.
     *
     * @return possible object is
     *         {@link ModuleBinaries.Excludes }
     */
    public ModuleBinaries.Excludes getExcludes() {
        return excludes;
    }

    /**
     * Sets the value of the excludes property.
     *
     * @param value allowed object is
     *              {@link ModuleBinaries.Excludes }
     */
    public void setExcludes(ModuleBinaries.Excludes value) {
        this.excludes = value;
    }

    /**
     * Gets the value of the fileMode property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getFileMode() {
        return fileMode;
    }

    /**
     * Sets the value of the fileMode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFileMode(String value) {
        this.fileMode = value;
    }

    /**
     * Gets the value of the directoryMode property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDirectoryMode() {
        return directoryMode;
    }

    /**
     * Sets the value of the directoryMode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDirectoryMode(String value) {
        this.directoryMode = value;
    }

    /**
     * Gets the value of the attachmentClassifier property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getAttachmentClassifier() {
        return attachmentClassifier;
    }

    /**
     * Sets the value of the attachmentClassifier property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAttachmentClassifier(String value) {
        this.attachmentClassifier = value;
    }

    /**
     * Gets the value of the includeDependencies property.
     *
     * @return possible object is
     *         {@link Boolean }
     */
    public Boolean isIncludeDependencies() {
        return includeDependencies;
    }

    /**
     * Sets the value of the includeDependencies property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setIncludeDependencies(Boolean value) {
        this.includeDependencies = value;
    }

    /**
     * Gets the value of the dependencySets property.
     *
     * @return possible object is
     *         {@link ModuleBinaries.DependencySets }
     */
    public ModuleBinaries.DependencySets getDependencySets() {
        return dependencySets;
    }

    /**
     * Sets the value of the dependencySets property.
     *
     * @param value allowed object is
     *              {@link ModuleBinaries.DependencySets }
     */
    public void setDependencySets(ModuleBinaries.DependencySets value) {
        this.dependencySets = value;
    }

    /**
     * Gets the value of the unpack property.
     *
     * @return possible object is
     *         {@link Boolean }
     */
    public Boolean isUnpack() {
        return unpack;
    }

    /**
     * Sets the value of the unpack property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setUnpack(Boolean value) {
        this.unpack = value;
    }

    /**
     * Gets the value of the unpackOptions property.
     *
     * @return possible object is
     *         {@link UnpackOptions }
     */
    public UnpackOptions getUnpackOptions() {
        return unpackOptions;
    }

    /**
     * Sets the value of the unpackOptions property.
     *
     * @param value allowed object is
     *              {@link UnpackOptions }
     */
    public void setUnpackOptions(UnpackOptions value) {
        this.unpackOptions = value;
    }

    /**
     * Gets the value of the outputFileNameMapping property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getOutputFileNameMapping() {
        return outputFileNameMapping;
    }

    /**
     * Sets the value of the outputFileNameMapping property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOutputFileNameMapping(String value) {
        this.outputFileNameMapping = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * <p/>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p/>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="dependencySet" type="{http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2}DependencySet" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "dependencySet"
    })
    public static class DependencySets {

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
        protected List<DependencySet> dependencySet;

        /**
         * Gets the value of the dependencySet property.
         * <p/>
         * <p/>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dependencySet property.
         * <p/>
         * <p/>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDependencySet().add(newItem);
         * </pre>
         * <p/>
         * <p/>
         * <p/>
         * Objects of the following type(s) are allowed in the list
         * {@link DependencySet }
         */
        public List<DependencySet> getDependencySet() {
            if (dependencySet == null) {
                dependencySet = new ArrayList<DependencySet>();
            }
            return this.dependencySet;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * <p/>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p/>
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
         * <p/>
         * <p/>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the exclude property.
         * <p/>
         * <p/>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getExclude().add(newItem);
         * </pre>
         * <p/>
         * <p/>
         * <p/>
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
     * <p/>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p/>
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
         * <p/>
         * <p/>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the include property.
         * <p/>
         * <p/>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInclude().add(newItem);
         * </pre>
         * <p/>
         * <p/>
         * <p/>
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
