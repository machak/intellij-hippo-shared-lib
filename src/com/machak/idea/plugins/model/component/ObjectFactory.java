
/*
 * Copyright 2016 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.model.component;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.onehippo.gpoc.scorm.model package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Component_QNAME = new QName("http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2", "component");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package:
     * org.onehippo.gpoc.scorm.model
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ModuleSources }
     */
    public ModuleSources createModuleSources() {
        return new ModuleSources();
    }

    /**
     * Create an instance of {@link ModuleSet }
     */
    public ModuleSet createModuleSet() {
        return new ModuleSet();
    }

    /**
     * Create an instance of {@link GroupVersionAlignment }
     */
    public GroupVersionAlignment createGroupVersionAlignment() {
        return new GroupVersionAlignment();
    }

    /**
     * Create an instance of {@link DependencySet }
     */
    public DependencySet createDependencySet() {
        return new DependencySet();
    }

    /**
     * Create an instance of {@link ContainerDescriptorHandlerConfig }
     */
    public ContainerDescriptorHandlerConfig createContainerDescriptorHandlerConfig() {
        return new ContainerDescriptorHandlerConfig();
    }

    /**
     * Create an instance of {@link FileSet }
     */
    public FileSet createFileSet() {
        return new FileSet();
    }

    /**
     * Create an instance of {@link ModuleBinaries }
     */
    public ModuleBinaries createModuleBinaries() {
        return new ModuleBinaries();
    }

    /**
     * Create an instance of {@link Repository }
     */
    public Repository createRepository() {
        return new Repository();
    }

    /**
     * Create an instance of {@link UnpackOptions }
     */
    public UnpackOptions createUnpackOptions() {
        return new UnpackOptions();
    }

    /**
     * Create an instance of {@link Component }
     */
    public Component createComponent() {
        return new Component();
    }

    /**
     * Create an instance of {@link FileItem }
     */
    public FileItem createFileItem() {
        return new FileItem();
    }

    /**
     * Create an instance of {@link ModuleSources.Includes }
     */
    public ModuleSources.Includes createModuleSourcesIncludes() {
        return new ModuleSources.Includes();
    }

    /**
     * Create an instance of {@link ModuleSources.Excludes }
     */
    public ModuleSources.Excludes createModuleSourcesExcludes() {
        return new ModuleSources.Excludes();
    }

    /**
     * Create an instance of {@link ModuleSources.FileSets }
     */
    public ModuleSources.FileSets createModuleSourcesFileSets() {
        return new ModuleSources.FileSets();
    }

    /**
     * Create an instance of {@link ModuleSet.Includes }
     */
    public ModuleSet.Includes createModuleSetIncludes() {
        return new ModuleSet.Includes();
    }

    /**
     * Create an instance of {@link ModuleSet.Excludes }
     */
    public ModuleSet.Excludes createModuleSetExcludes() {
        return new ModuleSet.Excludes();
    }

    /**
     * Create an instance of {@link GroupVersionAlignment.Excludes }
     */
    public GroupVersionAlignment.Excludes createGroupVersionAlignmentExcludes() {
        return new GroupVersionAlignment.Excludes();
    }

    /**
     * Create an instance of {@link DependencySet.Includes }
     */
    public DependencySet.Includes createDependencySetIncludes() {
        return new DependencySet.Includes();
    }

    /**
     * Create an instance of {@link DependencySet.Excludes }
     */
    public DependencySet.Excludes createDependencySetExcludes() {
        return new DependencySet.Excludes();
    }

    /**
     * Create an instance of {@link ContainerDescriptorHandlerConfig.Configuration }
     */
    public ContainerDescriptorHandlerConfig.Configuration createContainerDescriptorHandlerConfigConfiguration() {
        return new ContainerDescriptorHandlerConfig.Configuration();
    }

    /**
     * Create an instance of {@link FileSet.Includes }
     */
    public FileSet.Includes createFileSetIncludes() {
        return new FileSet.Includes();
    }

    /**
     * Create an instance of {@link FileSet.Excludes }
     */
    public FileSet.Excludes createFileSetExcludes() {
        return new FileSet.Excludes();
    }

    /**
     * Create an instance of {@link ModuleBinaries.Includes }
     */
    public ModuleBinaries.Includes createModuleBinariesIncludes() {
        return new ModuleBinaries.Includes();
    }

    /**
     * Create an instance of {@link ModuleBinaries.Excludes }
     */
    public ModuleBinaries.Excludes createModuleBinariesExcludes() {
        return new ModuleBinaries.Excludes();
    }

    /**
     * Create an instance of {@link ModuleBinaries.DependencySets }
     */
    public ModuleBinaries.DependencySets createModuleBinariesDependencySets() {
        return new ModuleBinaries.DependencySets();
    }

    /**
     * Create an instance of {@link Repository.Includes }
     */
    public Repository.Includes createRepositoryIncludes() {
        return new Repository.Includes();
    }

    /**
     * Create an instance of {@link Repository.Excludes }
     */
    public Repository.Excludes createRepositoryExcludes() {
        return new Repository.Excludes();
    }

    /**
     * Create an instance of {@link Repository.GroupVersionAlignments }
     */
    public Repository.GroupVersionAlignments createRepositoryGroupVersionAlignments() {
        return new Repository.GroupVersionAlignments();
    }

    /**
     * Create an instance of {@link UnpackOptions.Includes }
     */
    public UnpackOptions.Includes createUnpackOptionsIncludes() {
        return new UnpackOptions.Includes();
    }

    /**
     * Create an instance of {@link UnpackOptions.Excludes }
     */
    public UnpackOptions.Excludes createUnpackOptionsExcludes() {
        return new UnpackOptions.Excludes();
    }

    /**
     * Create an instance of {@link Component.ModuleSets }
     */
    public Component.ModuleSets createComponentModuleSets() {
        return new Component.ModuleSets();
    }

    /**
     * Create an instance of {@link Component.FileSets }
     */
    public Component.FileSets createComponentFileSets() {
        return new Component.FileSets();
    }

    /**
     * Create an instance of {@link Component.Files }
     */
    public Component.Files createComponentFiles() {
        return new Component.Files();
    }

    /**
     * Create an instance of {@link Component.DependencySets }
     */
    public Component.DependencySets createComponentDependencySets() {
        return new Component.DependencySets();
    }

    /**
     * Create an instance of {@link Component.Repositories }
     */
    public Component.Repositories createComponentRepositories() {
        return new Component.Repositories();
    }

    /**
     * Create an instance of {@link Component.ContainerDescriptorHandlers }
     */
    public Component.ContainerDescriptorHandlers createComponentContainerDescriptorHandlers() {
        return new Component.ContainerDescriptorHandlers();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Component }{@code >}}
     */
    @XmlElementDecl(namespace = "http://maven.apache.org/plugins/maven-assembly-plugin/component/1.1.2", name = "component")
    public JAXBElement<Component> createComponent(Component value) {
        return new JAXBElement<Component>(_Component_QNAME, Component.class, null, value);
    }

}
