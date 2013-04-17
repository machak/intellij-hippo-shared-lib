
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * An assembly defines a collection of files usually distributed in an
 * archive format such as zip, tar, or tar.gz that is generated from a
 * project. For example, a project could produce a ZIP assembly which
 * contains a project's JAR artifact in the root directory, the
 * runtime dependencies in a lib/ directory, and a shell script to launch
 * a stand-alone application.
 * <p/>
 * <p/>
 * <p/>
 * <p>Java class for Assembly complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="Assembly">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formats" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="includeBaseDirectory" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="baseDirectory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeSiteDirectory" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="containerDescriptorHandlers" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="containerDescriptorHandler" type="{http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2}ContainerDescriptorHandlerConfig" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="moduleSets" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="moduleSet" type="{http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2}ModuleSet" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="fileSets" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="fileSet" type="{http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2}FileSet" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="files" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="file" type="{http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2}FileItem" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
 *         &lt;element name="repositories" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="repository" type="{http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2}Repository" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="componentDescriptors" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="componentDescriptor" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Assembly", namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
public class Assembly {

    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected String id;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected Assembly.Formats formats;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", defaultValue = "true")
    protected Boolean includeBaseDirectory;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected String baseDirectory;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2", defaultValue = "false")
    protected Boolean includeSiteDirectory;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected Assembly.ContainerDescriptorHandlers containerDescriptorHandlers;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected Assembly.ModuleSets moduleSets;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected Assembly.FileSets fileSets;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected Assembly.Files files;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected Assembly.DependencySets dependencySets;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected Assembly.Repositories repositories;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
    protected Assembly.ComponentDescriptors componentDescriptors;

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the formats property.
     *
     * @return possible object is
     *         {@link Assembly.Formats }
     */
    public Assembly.Formats getFormats() {
        return formats;
    }

    /**
     * Sets the value of the formats property.
     *
     * @param value allowed object is
     *              {@link Assembly.Formats }
     */
    public void setFormats(Assembly.Formats value) {
        this.formats = value;
    }

    /**
     * Gets the value of the includeBaseDirectory property.
     *
     * @return possible object is
     *         {@link Boolean }
     */
    public Boolean isIncludeBaseDirectory() {
        return includeBaseDirectory;
    }

    /**
     * Sets the value of the includeBaseDirectory property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setIncludeBaseDirectory(Boolean value) {
        this.includeBaseDirectory = value;
    }

    /**
     * Gets the value of the baseDirectory property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getBaseDirectory() {
        return baseDirectory;
    }

    /**
     * Sets the value of the baseDirectory property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setBaseDirectory(String value) {
        this.baseDirectory = value;
    }

    /**
     * Gets the value of the includeSiteDirectory property.
     *
     * @return possible object is
     *         {@link Boolean }
     */
    public Boolean isIncludeSiteDirectory() {
        return includeSiteDirectory;
    }

    /**
     * Sets the value of the includeSiteDirectory property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setIncludeSiteDirectory(Boolean value) {
        this.includeSiteDirectory = value;
    }

    /**
     * Gets the value of the containerDescriptorHandlers property.
     *
     * @return possible object is
     *         {@link Assembly.ContainerDescriptorHandlers }
     */
    public Assembly.ContainerDescriptorHandlers getContainerDescriptorHandlers() {
        return containerDescriptorHandlers;
    }

    /**
     * Sets the value of the containerDescriptorHandlers property.
     *
     * @param value allowed object is
     *              {@link Assembly.ContainerDescriptorHandlers }
     */
    public void setContainerDescriptorHandlers(Assembly.ContainerDescriptorHandlers value) {
        this.containerDescriptorHandlers = value;
    }

    /**
     * Gets the value of the moduleSets property.
     *
     * @return possible object is
     *         {@link Assembly.ModuleSets }
     */
    public Assembly.ModuleSets getModuleSets() {
        return moduleSets;
    }

    /**
     * Sets the value of the moduleSets property.
     *
     * @param value allowed object is
     *              {@link Assembly.ModuleSets }
     */
    public void setModuleSets(Assembly.ModuleSets value) {
        this.moduleSets = value;
    }

    /**
     * Gets the value of the fileSets property.
     *
     * @return possible object is
     *         {@link Assembly.FileSets }
     */
    public Assembly.FileSets getFileSets() {
        return fileSets;
    }

    /**
     * Sets the value of the fileSets property.
     *
     * @param value allowed object is
     *              {@link Assembly.FileSets }
     */
    public void setFileSets(Assembly.FileSets value) {
        this.fileSets = value;
    }

    /**
     * Gets the value of the files property.
     *
     * @return possible object is
     *         {@link Assembly.Files }
     */
    public Assembly.Files getFiles() {
        return files;
    }

    /**
     * Sets the value of the files property.
     *
     * @param value allowed object is
     *              {@link Assembly.Files }
     */
    public void setFiles(Assembly.Files value) {
        this.files = value;
    }

    /**
     * Gets the value of the dependencySets property.
     *
     * @return possible object is
     *         {@link Assembly.DependencySets }
     */
    public Assembly.DependencySets getDependencySets() {
        return dependencySets;
    }

    /**
     * Sets the value of the dependencySets property.
     *
     * @param value allowed object is
     *              {@link Assembly.DependencySets }
     */
    public void setDependencySets(Assembly.DependencySets value) {
        this.dependencySets = value;
    }

    /**
     * Gets the value of the repositories property.
     *
     * @return possible object is
     *         {@link Assembly.Repositories }
     */
    public Assembly.Repositories getRepositories() {
        return repositories;
    }

    /**
     * Sets the value of the repositories property.
     *
     * @param value allowed object is
     *              {@link Assembly.Repositories }
     */
    public void setRepositories(Assembly.Repositories value) {
        this.repositories = value;
    }

    /**
     * Gets the value of the componentDescriptors property.
     *
     * @return possible object is
     *         {@link Assembly.ComponentDescriptors }
     */
    public Assembly.ComponentDescriptors getComponentDescriptors() {
        return componentDescriptors;
    }

    /**
     * Sets the value of the componentDescriptors property.
     *
     * @param value allowed object is
     *              {@link Assembly.ComponentDescriptors }
     */
    public void setComponentDescriptors(Assembly.ComponentDescriptors value) {
        this.componentDescriptors = value;
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
     *         &lt;element name="componentDescriptor" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "componentDescriptor"
    })
    public static class ComponentDescriptors {

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
        protected List<String> componentDescriptor;

        /**
         * Gets the value of the componentDescriptor property.
         * <p/>
         * <p/>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the componentDescriptor property.
         * <p/>
         * <p/>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getComponentDescriptor().add(newItem);
         * </pre>
         * <p/>
         * <p/>
         * <p/>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         */
        public List<String> getComponentDescriptor() {
            if (componentDescriptor == null) {
                componentDescriptor = new ArrayList<String>();
            }
            return this.componentDescriptor;
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
     *         &lt;element name="containerDescriptorHandler" type="{http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2}ContainerDescriptorHandlerConfig" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "containerDescriptorHandler"
    })
    public static class ContainerDescriptorHandlers {

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
        protected List<ContainerDescriptorHandlerConfig> containerDescriptorHandler;

        /**
         * Gets the value of the containerDescriptorHandler property.
         * <p/>
         * <p/>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the containerDescriptorHandler property.
         * <p/>
         * <p/>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContainerDescriptorHandler().add(newItem);
         * </pre>
         * <p/>
         * <p/>
         * <p/>
         * Objects of the following type(s) are allowed in the list
         * {@link ContainerDescriptorHandlerConfig }
         */
        public List<ContainerDescriptorHandlerConfig> getContainerDescriptorHandler() {
            if (containerDescriptorHandler == null) {
                containerDescriptorHandler = new ArrayList<ContainerDescriptorHandlerConfig>();
            }
            return this.containerDescriptorHandler;
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
     *         &lt;element name="file" type="{http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2}FileItem" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "file"
    })
    public static class Files {

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
        protected List<FileItem> file;

        /**
         * Gets the value of the file property.
         * <p/>
         * <p/>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the file property.
         * <p/>
         * <p/>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFile().add(newItem);
         * </pre>
         * <p/>
         * <p/>
         * <p/>
         * Objects of the following type(s) are allowed in the list
         * {@link FileItem }
         */
        public List<FileItem> getFile() {
            if (file == null) {
                file = new ArrayList<FileItem>();
            }
            return this.file;
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
     *         &lt;element name="fileSet" type="{http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2}FileSet" maxOccurs="unbounded" minOccurs="0"/>
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

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
        protected List<FileSet> fileSet;

        /**
         * Gets the value of the fileSet property.
         * <p/>
         * <p/>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the fileSet property.
         * <p/>
         * <p/>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFileSet().add(newItem);
         * </pre>
         * <p/>
         * <p/>
         * <p/>
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
     * <p/>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p/>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "format"
    })
    public static class Formats {

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
        protected List<String> format;

        /**
         * Gets the value of the format property.
         * <p/>
         * <p/>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the format property.
         * <p/>
         * <p/>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFormat().add(newItem);
         * </pre>
         * <p/>
         * <p/>
         * <p/>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         */
        public List<String> getFormat() {
            if (format == null) {
                format = new ArrayList<String>();
            }
            return this.format;
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
     *         &lt;element name="moduleSet" type="{http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2}ModuleSet" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "moduleSet"
    })
    public static class ModuleSets {

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
        protected List<ModuleSet> moduleSet;

        /**
         * Gets the value of the moduleSet property.
         * <p/>
         * <p/>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the moduleSet property.
         * <p/>
         * <p/>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getModuleSet().add(newItem);
         * </pre>
         * <p/>
         * <p/>
         * <p/>
         * Objects of the following type(s) are allowed in the list
         * {@link ModuleSet }
         */
        public List<ModuleSet> getModuleSet() {
            if (moduleSet == null) {
                moduleSet = new ArrayList<ModuleSet>();
            }
            return this.moduleSet;
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
     *         &lt;element name="repository" type="{http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2}Repository" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "repository"
    })
    public static class Repositories {

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2")
        protected List<Repository> repository;

        /**
         * Gets the value of the repository property.
         * <p/>
         * <p/>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the repository property.
         * <p/>
         * <p/>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRepository().add(newItem);
         * </pre>
         * <p/>
         * <p/>
         * <p/>
         * Objects of the following type(s) are allowed in the list
         * {@link Repository }
         */
        public List<Repository> getRepository() {
            if (repository == null) {
                repository = new ArrayList<Repository>();
            }
            return this.repository;
        }

    }

}
