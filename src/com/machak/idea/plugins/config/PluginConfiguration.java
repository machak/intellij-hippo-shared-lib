

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
import com.intellij.openapi.actionSystem.PlatformDataKeys;
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

public class PluginConfiguration extends BaseConfigurable {
    private JLabel label;
    private JPanel mainPanel;
    private JCheckBox deleteAllJars;
    private JCheckBox copyOtherJars;
    private TextFieldWithBrowseButton tomcatDirectory;
    private JLabel explanationLabel;


    public PluginConfiguration() {

        final DocumentListener listener = new DocumentAdapter() {
            protected void textChanged(DocumentEvent documentEvent) {
                tomcatDirectory.getText();
            }
        };
        tomcatDirectory.getChildComponent().getDocument().addDocumentListener(listener);
        tomcatDirectory.setTextFieldPreferredWidth(50);

        tomcatDirectory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseFolder();
            }
        });

        explanationLabel.setText("<html><p>If selected, all *jar files* within above defined directory will be *deleted*,</p>" +
                "<p>otherwise only the *jar files* that are sharing same *prefix* as shared jar files.</p>" +
                "<p> </p>" +
                "<p>e.g. these are currently  \"prefixes\" of the shared jar files:</p>" +
                "<p> </p>" +
                "<p>jcr, jdo2-api, geronimo-jta_1.1_spec, mail, hippo-cms7-commons, </p>" +
                "<p>hippo-services, hippo-repository-api, hippo-repository-builtin,</p>" +
                "<p>slf4j-api, jcl-over-slf4j, slf4j-log4j12, log4j</p>" +
                "</html>");
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
        /*projectComponent.getState().directory = directoryField.getText();
        projectComponent.getState().configured = true;*/
    }

    @Override
    public void reset() {
    }

    @Override
    public void disposeUIResources() {
    }

    public boolean isModified(ApplicationComponent component) {
        final boolean changed = deleteAllJars.isSelected() != component.isDeleteAllJars() || copyOtherJars.isSelected() != component.isCopyOtherJars();
        if (changed) {
            return true;
        }
        final String text = tomcatDirectory.getText();
        final String dir = component.getTomcatDirectory();
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
        component.setDeleteAllJars(deleteAllJars.isSelected());
        component.setCopyOtherJars(copyOtherJars.isSelected());
        component.setTomcatDirectory(tomcatDirectory.getText());
    }

    public void readDataFrom(ApplicationComponent component) {
        deleteAllJars.setSelected(component.isDeleteAllJars());
        copyOtherJars.setSelected(component.isCopyOtherJars());
        tomcatDirectory.setText(component.getTomcatDirectory());
    }

    public Project getProject(final Component component) {
        Project project = PlatformDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext(component));
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

    private void chooseFolder() {
        FileChooserDescriptor descriptor = new FileChooserDescriptor(false, true, false, false, false, false) {
            public String getName(VirtualFile virtualFile) {
                return virtualFile.getName();
            }

            @Nullable
            public String getComment(VirtualFile virtualFile) {
                return virtualFile.getPresentableUrl();
            }
        };
        descriptor.setTitle("Select Project Destination Folder");

        String preselectedFolderPath = tomcatDirectory.getText();
        VirtualFile preselectedFolder = LocalFileSystem.getInstance().findFileByPath(preselectedFolderPath);

        VirtualFile[] files = FileChooser.chooseFiles(descriptor, mainPanel, getProject(mainPanel), preselectedFolder);
        if (files.length > 0) {
            tomcatDirectory.setText(files[0].getPath());
        }
    }
}