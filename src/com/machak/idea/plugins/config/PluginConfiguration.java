

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
                chooseFolder(tomcatDirectory, false);
            }
        });

        //############################################
        // DIST file
        //############################################

        final DocumentListener distListener = new DocumentAdapter() {
            protected void textChanged(DocumentEvent documentEvent) {
                distFile.getText();
            }
        };
        distFile.getChildComponent().getDocument().addDocumentListener(distListener);
        distFile.setTextFieldPreferredWidth(50);
        distFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseFolder(distFile, true);
            }
        });

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
                || copyOtherJars.isSelected() != component.isCopyOtherJars();
        if (changed) {
            return true;
        }
        final String tomcatText = tomcatDirectory.getText();
        final String tomcatDir = component.getTomcatDirectory();
        final boolean tomcatTextChanged = isTextChanged(tomcatText, tomcatDir);
        if (tomcatTextChanged) {
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
        component.setDeleteAllJars(deleteAllJars.isSelected());
        component.setCopyOtherJars(copyOtherJars.isSelected());
        component.setShowDialog(showConfirmationDialog.isSelected());
        component.setTomcatDirectory(tomcatDirectory.getText());
        component.setDistFile(distFile.getText());
    }

    public void readDataFrom(ApplicationComponent component) {
        deleteAllJars.setSelected(component.isDeleteAllJars());
        copyOtherJars.setSelected(component.isCopyOtherJars());
        showConfirmationDialog.setSelected(component.isShowDialog());
        tomcatDirectory.setText(component.getTomcatDirectory());
        distFile.setText(component.getDistFile());
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

    private void chooseFolder(final TextAccessor field, final boolean chooseFiles) {
        final FileChooserDescriptor descriptor = new FileChooserDescriptor(chooseFiles, !chooseFiles, false, false, false, false) {
            public String getName(VirtualFile virtualFile) {
                return virtualFile.getName();
            }

            @Nullable
            public String getComment(VirtualFile virtualFile) {
                return virtualFile.getPresentableUrl();
            }
        };
        descriptor.setTitle("Select Project Destination Folder");

        final String selectedPath = field.getText();
        final VirtualFile preselectedFolder = LocalFileSystem.getInstance().findFileByPath(selectedPath);

        final VirtualFile[] files = FileChooser.chooseFiles(descriptor, mainPanel, getProject(mainPanel), preselectedFolder);
        if (files.length > 0) {
            field.setText(files[0].getPath());
        }
    }
}