
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
 * Describes the component layout and packaging.
 * <p>
 * <p>Java class for Component complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="Component">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="moduleSets" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="moduleSet" type="{http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2}ModuleSet"
 * maxOccurs="unbounded" minOccurs="0"/>
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
 *                   &lt;element name="fileSet" type="{http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2}FileSet"
 * maxOccurs="unbounded" minOccurs="0"/>
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
 *                   &lt;element name="file" type="{http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2}FileItem"
 * maxOccurs="unbounded" minOccurs="0"/>
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
 *                   &lt;element name="dependencySet" type="{http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2}DependencySet"
 * maxOccurs="unbounded" minOccurs="0"/>
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
 *                   &lt;element name="repository" type="{http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2}Repository"
 * maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="containerDescriptorHandlers" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="containerDescriptorHandler" type="{http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2}ContainerDescriptorHandlerConfig"
 * maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "Component", namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2", propOrder = {

})
public class Component {

    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected Component.ModuleSets moduleSets;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected Component.FileSets fileSets;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected Component.Files files;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected Component.DependencySets dependencySets;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected Component.Repositories repositories;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected Component.ContainerDescriptorHandlers containerDescriptorHandlers;

    /**
     * Gets the value of the moduleSets property.
     *
     * @return possible object is {@link Component.ModuleSets }
     */
    public Component.ModuleSets getModuleSets() {
        return moduleSets;
    }

    /**
     * Sets the value of the moduleSets property.
     *
     * @param value allowed object is {@link Component.ModuleSets }
     */
    public void setModuleSets(Component.ModuleSets value) {
        this.moduleSets = value;
    }

    /**
     * Gets the value of the fileSets property.
     *
     * @return possible object is {@link Component.FileSets }
     */
    public Component.FileSets getFileSets() {
        return fileSets;
    }

    /**
     * Sets the value of the fileSets property.
     *
     * @param value allowed object is {@link Component.FileSets }
     */
    public void setFileSets(Component.FileSets value) {
        this.fileSets = value;
    }

    /**
     * Gets the value of the files property.
     *
     * @return possible object is {@link Component.Files }
     */
    public Component.Files getFiles() {
        return files;
    }

    /**
     * Sets the value of the files property.
     *
     * @param value allowed object is {@link Component.Files }
     */
    public void setFiles(Component.Files value) {
        this.files = value;
    }

    /**
     * Gets the value of the dependencySets property.
     *
     * @return possible object is {@link Component.DependencySets }
     */
    public Component.DependencySets getDependencySets() {
        return dependencySets;
    }

    /**
     * Sets the value of the dependencySets property.
     *
     * @param value allowed object is {@link Component.DependencySets }
     */
    public void setDependencySets(Component.DependencySets value) {
        this.dependencySets = value;
    }

    /**
     * Gets the value of the repositories property.
     *
     * @return possible object is {@link Component.Repositories }
     */
    public Component.Repositories getRepositories() {
        return repositories;
    }

    /**
     * Sets the value of the repositories property.
     *
     * @param value allowed object is {@link Component.Repositories }
     */
    public void setRepositories(Component.Repositories value) {
        this.repositories = value;
    }

    /**
     * Gets the value of the containerDescriptorHandlers property.
     *
     * @return possible object is {@link Component.ContainerDescriptorHandlers }
     */
    public Component.ContainerDescriptorHandlers getContainerDescriptorHandlers() {
        return containerDescriptorHandlers;
    }

    /**
     * Sets the value of the containerDescriptorHandlers property.
     *
     * @param value allowed object is {@link Component.ContainerDescriptorHandlers }
     */
    public void setContainerDescriptorHandlers(Component.ContainerDescriptorHandlers value) {
        this.containerDescriptorHandlers = value;
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
     *         &lt;element name="containerDescriptorHandler" type="{http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2}ContainerDescriptorHandlerConfig"
     * maxOccurs="unbounded" minOccurs="0"/>
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

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
        protected List<ContainerDescriptorHandlerConfig> containerDescriptorHandler;

        /**
         * Gets the value of the containerDescriptorHandler property.
         * <p>
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the containerDescriptorHandler property.
         * <p>
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContainerDescriptorHandler().add(newItem);
         * </pre>
         * <p>
         * <p>
         * <p>
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
     * <p>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="dependencySet" type="{http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2}DependencySet"
     * maxOccurs="unbounded" minOccurs="0"/>
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

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
        protected List<DependencySet> dependencySet;

        /**
         * Gets the value of the dependencySet property.
         * <p>
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dependencySet property.
         * <p>
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDependencySet().add(newItem);
         * </pre>
         * <p>
         * <p>
         * <p>
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
     * <p>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="file" type="{http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2}FileItem"
     * maxOccurs="unbounded" minOccurs="0"/>
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

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
        protected List<FileItem> file;

        /**
         * Gets the value of the file property.
         * <p>
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the file property.
         * <p>
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFile().add(newItem);
         * </pre>
         * <p>
         * <p>
         * <p>
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
     *         &lt;element name="moduleSet" type="{http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2}ModuleSet"
     * maxOccurs="unbounded" minOccurs="0"/>
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

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
        protected List<ModuleSet> moduleSet;

        /**
         * Gets the value of the moduleSet property.
         * <p>
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the moduleSet property.
         * <p>
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getModuleSet().add(newItem);
         * </pre>
         * <p>
         * <p>
         * <p>
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
     * <p>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="repository" type="{http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2}Repository"
     * maxOccurs="unbounded" minOccurs="0"/>
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

        @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
        protected List<Repository> repository;

        /**
         * Gets the value of the repository property.
         * <p>
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the repository property.
         * <p>
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRepository().add(newItem);
         * </pre>
         * <p>
         * <p>
         * <p>
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
