

/*
 * Copyright 2013 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.config;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.options.BaseConfigurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.TextAccessor;

public class PluginConfiguration extends BaseConfigurable {
    private JLabel label;
    private JLabel labelDist;
    private JPanel mainPanel;
    private JCheckBox deleteAllJars;
    private JCheckBox showConfirmationDialog;
    private JCheckBox copyOtherJars;
    private TextFieldWithBrowseButton tomcatDirectory;
    private TextFieldWithBrowseButton distFile;
    private JLabel explanationLabel;
    private JCheckBox createProjectFile;
    private JLabel labelCreateProjectRoot;
    private JLabel tomcatRootLabel;
    private TextFieldWithBrowseButton tomcatRootDirectory;
    private TextFieldWithBrowseButton projectRootDirectory;
    private TextFieldWithBrowseButton log4jLocation;
    private JCheckBox copyLog4j;


    public PluginConfiguration() {

        createDirListener(tomcatDirectory, false, true);
        createDirListener(distFile, true, false);
        createDirListener(tomcatRootDirectory, false, true);
        createDirListener(log4jLocation, true, false);
        createDirListener(projectRootDirectory, false, true);

        explanationLabel.setText("<html>" +
                "<p>If selected, all *jar files* within above defined directory will be *deleted*,</p>" +
                "<p>otherwise only the *jar files* that are sharing same *prefix* as shared jar files.</p>" +
                "<p> </p>" +
                "<p>Use \"Delete all jars\" option *only* if you configured an extra shared directory within  </p>" +
                "<p>catalina.properties, e..g. : </p>" +
                "<pre>shared.loader=${catalina.home}/hippo,${catalina.home}/hippo/*.jar</pre>" +
                "<p> </p>" +
                "<p>If \"Delete all jars\" option is not selected,</p>" +
                "<p>only files with following prefixed files will be deleted:</p>" +
                "<p>jcr, jdo2-api, geronimo-jta_1.1_spec, mail, hippo-cms7-commons, </p>" +
                "<p>hippo-services, hippo-repository-api, hippo-repository-builtin,</p>" +
                "<p>slf4j-api, jcl-over-slf4j, slf4j-log4j12, log4j</p>" +
                "</html>");
    }

    private void createDirListener(final TextFieldWithBrowseButton button, final boolean chooseFiles, final boolean chooseFolders) {
        final DocumentListener listener = new DocumentAdapter() {
            @Override
            protected void textChanged(DocumentEvent documentEvent) {
                button.getText();
            }
        };
        button.getChildComponent().getDocument().addDocumentListener(listener);
        button.setTextFieldPreferredWidth(50);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFolder(button, chooseFiles, chooseFolders);
            }
        });
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Hippo Shared lib";
    }

    @Override
    public String getHelpTopic() {
        return null;
    }

    @Override
    public JComponent createComponent() {
        return mainPanel;
    }

    @Override
    public void apply() throws ConfigurationException {

    }

    @Override
    public void reset() {
    }

    @Override
    public void disposeUIResources() {
    }

    public boolean isModified(ApplicationComponent component) {
        final boolean changed = deleteAllJars.isSelected() != component.isDeleteAllJars()
                || showConfirmationDialog.isSelected() != component.isShowDialog()
                || createProjectFile.isSelected() != component.isCreateProjectFile()
                || copyLog4j.isSelected() != component.isCopyLog4J()
                || copyOtherJars.isSelected() != component.isCopyOtherJars();
        if (changed) {
            return true;
        }
        final String tomcatText = tomcatDirectory.getText();
        final String tomcatDir = component.getTomcatDirectory();
        if (isTextChanged(tomcatText, tomcatDir)) {
            return true;
        }
        final String tomcatRootText = tomcatRootDirectory.getText();
        final String tomcatRootDir = component.getTomcatDirectory();
        if (isTextChanged(tomcatRootText, tomcatRootDir)) {
            return true;
        }
        final String logText = log4jLocation.getText();
        final String logDir = component.getLog4JDirectory();
        if (isTextChanged(logText, logDir)) {
            return true;
        }

        final String projectRootText = projectRootDirectory.getText();
        final String projectRootDir = component.getProjectRootDirectory();
        if (isTextChanged(projectRootText, projectRootDir)) {
            return true;
        }

        final String distText = distFile.getText();
        final String distFileTxt = component.getDistFile();
        return isTextChanged(distText, distFileTxt);
    }


    private boolean isTextChanged(final String text, final String dir) {
        if (text == null) {
            if (dir == null) {
                return false;
            }
        } else if (text.equals(dir)) {
            return false;
        }
        return true;
    }

    public void storeDataTo(ApplicationComponent component) {
        component.setCopyLog4J(copyLog4j.isSelected());
        component.setLog4JDirectory(log4jLocation.getText());
        component.setDeleteAllJars(deleteAllJars.isSelected());
        component.setCopyOtherJars(copyOtherJars.isSelected());
        component.setShowDialog(showConfirmationDialog.isSelected());
        component.setCreateProjectFile(createProjectFile.isSelected());
        component.setTomcatDirectory(tomcatDirectory.getText());
        component.setTomcatRootDirectory(tomcatRootDirectory.getText());
        component.setProjectRootDirectory(projectRootDirectory.getText());
        component.setDistFile(distFile.getText());
    }

    public void readDataFrom(ApplicationComponent component) {
        deleteAllJars.setSelected(component.isDeleteAllJars());
        copyOtherJars.setSelected(component.isCopyOtherJars());
        createProjectFile.setSelected(component.isCreateProjectFile());
        showConfirmationDialog.setSelected(component.isShowDialog());
        tomcatDirectory.setText(component.getTomcatDirectory());
        tomcatRootDirectory.setText(component.getTomcatRootDirectory());
        log4jLocation.setText(component.getLog4JDirectory());
        copyLog4j.setSelected(component.isCopyLog4J());
        projectRootDirectory.setText(component.getProjectRootDirectory());
        distFile.setText(component.getDistFile());
    }

    public Project getProject(final Component component) {
        Project project = CommonDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext(component));
        if (project != null) {
            return project;
        }
        return ProjectManager.getInstance().getDefaultProject();
    }

    private void createUIComponents() {

    }

    //############################################
    //
    //############################################

    private void chooseFolder(final TextAccessor field, final boolean chooseFiles, final boolean chooseFolders) {
        final FileChooserDescriptor descriptor = new FileChooserDescriptor(chooseFiles, chooseFolders, false, false, false, false) {
            @Override
            public String getName(VirtualFile virtualFile) {
                return virtualFile.getName();
            }

            @Override
            @Nullable
            public String getComment(VirtualFile virtualFile) {
                return virtualFile.getPresentableUrl();
            }
        };
        descriptor.setTitle("Select  Folder");

        final String selectedPath = field.getText();
        final VirtualFile preselectedFolder = LocalFileSystem.getInstance().findFileByPath(selectedPath);

        final VirtualFile[] files = FileChooser.chooseFiles(descriptor, mainPanel, getProject(mainPanel), preselectedFolder);
        if (files.length > 0) {
            field.setText(files[0].getPath());
        }
    }
}