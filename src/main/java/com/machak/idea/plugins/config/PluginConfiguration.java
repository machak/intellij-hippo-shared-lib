

/*
 * Copyright 2013 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.config;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import com.google.common.base.Strings;
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
    private JCheckBox copyContentJar;
    private JCheckBox autoModifyProjectOutput;
    private JCheckBox silentMode;


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
        button.addActionListener(e -> chooseFolder(button, chooseFiles, chooseFolders));
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
        //System.out.println("label = " + label);
    }

    @Override
    public void reset() {
    }

    @Override
    public void disposeUIResources() {
    }


    public void setData(final StorageState state) {
        state.setAutoModifyProjectOutput(autoModifyProjectOutput.isSelected());
        state.setCopyContentJar(copyContentJar.isSelected());
        state.setCopyLog4J(copyLog4j.isSelected());
        state.setLog4JDirectory(log4jLocation.getText());
        state.setDeleteAllJars(deleteAllJars.isSelected());
        state.setCopyOtherJars(copyOtherJars.isSelected());
        state.setSilentMode(silentMode.isSelected());
        state.setShowDialog(showConfirmationDialog.isSelected());
        state.setCreateProjectFile(createProjectFile.isSelected());
        state.setTomcatDirectory(tomcatDirectory.getText());
        state.setTomcatRootDirectory(tomcatRootDirectory.getText());
        state.setProjectRootDirectory(projectRootDirectory.getText());
        state.setDistFile(distFile.getText());

    }

    public void getData(final StorageState state) {
        copyContentJar.setSelected(state.isCopyContentJar());
        autoModifyProjectOutput.setSelected(state.isAutoModifyProjectOutput());
        deleteAllJars.setSelected(state.isDeleteAllJars());
        silentMode.setSelected(state.isSilentMode());
        copyOtherJars.setSelected(state.isCopyOtherJars());
        createProjectFile.setSelected(state.isCreateProjectFile());
        showConfirmationDialog.setSelected(state.isShowDialog());
        tomcatDirectory.setText(state.getTomcatDirectory());
        tomcatRootDirectory.setText(state.getTomcatRootDirectory());
        log4jLocation.setText(state.getLog4JDirectory());
        copyLog4j.setSelected(state.isCopyLog4J());
        projectRootDirectory.setText(state.getProjectRootDirectory());
        distFile.setText(state.getDistFile());
    }

    public boolean isModified(final StorageState state) {
        if (state == null) {
            return false;
        }
        final boolean changed = deleteAllJars.isSelected() != state.isDeleteAllJars()
                || showConfirmationDialog.isSelected() != state.isShowDialog()
                || createProjectFile.isSelected() != state.isCreateProjectFile()
                || copyLog4j.isSelected() != state.isCopyLog4J()
                || copyContentJar.isSelected() != state.isCopyContentJar()
                || silentMode.isSelected() != state.isSilentMode()
                || autoModifyProjectOutput.isSelected() != state.isAutoModifyProjectOutput()
                || copyOtherJars.isSelected() != state.isCopyOtherJars();
        if (changed) {
            return true;
        }
        final String tomcatText = tomcatDirectory.getText();
        final String tomcatDir = state.getTomcatDirectory();
        if (isTextChanged(tomcatText, tomcatDir)) {
            return true;
        }
        final String tomcatRootText = tomcatRootDirectory.getText();
        final String tomcatRootDir = state.getTomcatDirectory();
        if (isTextChanged(tomcatRootText, tomcatRootDir)) {
            return true;
        }
        final String logText = log4jLocation.getText();
        final String logDir = state.getLog4JDirectory();
        if (isTextChanged(logText, logDir)) {
            return true;
        }

        final String projectRootText = projectRootDirectory.getText();
        final String projectRootDir = state.getProjectRootDirectory();
        if (isTextChanged(projectRootText, projectRootDir)) {
            return true;
        }

        final String distText = distFile.getText();
        final String distFileTxt = state.getDistFile();
        return isTextChanged(distText, distFileTxt);
    }

    private boolean isTextChanged(final String text, final String dir) {
        if (Strings.isNullOrEmpty(text) && Strings.isNullOrEmpty(dir)) {
            return false;
        } else if (text.equals(dir)) {
            return false;
        }
        return true;
    }


    public Project getProject(final Component component) {
        Project project = CommonDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext(component));
        if (project != null) {
            return project;
        }
        return ProjectManager.getInstance().getDefaultProject();
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