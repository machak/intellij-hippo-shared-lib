
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
 * A moduleSet represent one or more project &lt;module&gt; present inside a project's pom.xml. This allows you to
 * include sources or binaries belonging to a project's &lt;modules&gt;.
 * <p>
 * <br/><b>NOTE:</b> When using &lt;moduleSets&gt; from the command-line, it is required to pass first the package phase
 * by doing: "mvn package assembly:assembly". This bug/issue is scheduled to be addressed by Maven 2.1.
 * <p>
 * <p>
 * <p>
 * <p>Java class for ModuleSet complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="ModuleSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="useAllReactorProjects" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="includeSubModules" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
 *         &lt;element name="sources" type="{http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2}ModuleSources"
 * minOccurs="0"/>
 *         &lt;element name="binaries" type="{http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2}ModuleBinaries"
 * minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModuleSet", namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2", propOrder = {

})
public class ModuleSet {

    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2", defaultValue = "false")
    protected Boolean useAllReactorProjects;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2", defaultValue = "true")
    protected Boolean includeSubModules;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected ModuleSet.Includes includes;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected ModuleSet.Excludes excludes;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected ModuleSources sources;
    @XmlElement(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2")
    protected ModuleBinaries binaries;

    /**
     * Gets the value of the useAllReactorProjects property.
     *
     * @return possible object is {@link Boolean }
     */
    public Boolean isUseAllReactorProjects() {
        return useAllReactorProjects;
    }

    /**
     * Sets the value of the useAllReactorProjects property.
     *
     * @param value allowed object is {@link Boolean }
     */
    public void setUseAllReactorProjects(Boolean value) {
        this.useAllReactorProjects = value;
    }

    /**
     * Gets the value of the includeSubModules property.
     *
     * @return possible object is {@link Boolean }
     */
    public Boolean isIncludeSubModules() {
        return includeSubModules;
    }

    /**
     * Sets the value of the includeSubModules property.
     *
     * @param value allowed object is {@link Boolean }
     */
    public void setIncludeSubModules(Boolean value) {
        this.includeSubModules = value;
    }

    /**
     * Gets the value of the includes property.
     *
     * @return possible object is {@link ModuleSet.Includes }
     */
    public ModuleSet.Includes getIncludes() {
        return includes;
    }

    /**
     * Sets the value of the includes property.
     *
     * @param value allowed object is {@link ModuleSet.Includes }
     */
    public void setIncludes(ModuleSet.Includes value) {
        this.includes = value;
    }

    /**
     * Gets the value of the excludes property.
     *
     * @return possible object is {@link ModuleSet.Excludes }
     */
    public ModuleSet.Excludes getExcludes() {
        return excludes;
    }

    /**
     * Sets the value of the excludes property.
     *
     * @param value allowed object is {@link ModuleSet.Excludes }
     */
    public void setExcludes(ModuleSet.Excludes value) {
        this.excludes = value;
    }

    /**
     * Gets the value of the sources property.
     *
     * @return possible object is {@link ModuleSources }
     */
    public ModuleSources getSources() {
        return sources;
    }

    /**
     * Sets the value of the sources property.
     *
     * @param value allowed object is {@link ModuleSources }
     */
    public void setSources(ModuleSources value) {
        this.sources = value;
    }

    /**
     * Gets the value of the binaries property.
     *
     * @return possible object is {@link ModuleBinaries }
     */
    public ModuleBinaries getBinaries() {
        return binaries;
    }

    /**
     * Sets the value of the binaries property.
     *
     * @param value allowed object is {@link ModuleBinaries }
     */
    public void setBinaries(ModuleBinaries value) {
        this.binaries = value;
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
