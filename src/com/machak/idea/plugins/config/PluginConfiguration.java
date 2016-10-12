

/*
 * Copyright 2013 m.milicevic (http://www.machak.com)
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.machak.idea.plugins.config;

import java.awt.Component;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

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
        System.out.println("label = " + label);
    }

    @Override
    public void reset() {
    }

    @Override
    public void disposeUIResources() {
    }

    public boolean isModified(final StorageState state) {
        final boolean changed = deleteAllJars.isSelected() != state.isDeleteAllJars()
                || showConfirmationDialog.isSelected() != state.isShowDialog()
                || createProjectFile.isSelected() != state.isCreateProjectFile()
                || copyLog4j.isSelected() != state.isCopyLog4J()
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

    public void storeDataTo(final StorageState state) {
        state.setCopyLog4J(copyLog4j.isSelected());
        state.setLog4JDirectory(log4jLocation.getText());
        state.setDeleteAllJars(deleteAllJars.isSelected());
        state.setCopyOtherJars(copyOtherJars.isSelected());
        state.setShowDialog(showConfirmationDialog.isSelected());
        state.setCreateProjectFile(createProjectFile.isSelected());
        state.setTomcatDirectory(tomcatDirectory.getText());
        state.setTomcatRootDirectory(tomcatRootDirectory.getText());
        state.setProjectRootDirectory(projectRootDirectory.getText());
        state.setDistFile(distFile.getText());

    }

    public void readDataFrom(final StorageState state) {
        deleteAllJars.setSelected(state.isDeleteAllJars());
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(13, 3, new Insets(0, 0, 0, 0), -1, -1));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(11, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tomcatDirectory = new TextFieldWithBrowseButton();
        mainPanel.add(tomcatDirectory, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label = new JLabel();
        label.setHorizontalAlignment(10);
        label.setHorizontalTextPosition(10);
        label.setText("Tomcat shared directory:");
        mainPanel.add(label, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        distFile = new TextFieldWithBrowseButton();
        mainPanel.add(distFile, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelDist = new JLabel();
        labelDist.setHorizontalAlignment(10);
        labelDist.setHorizontalTextPosition(10);
        labelDist.setText("distribution.xml  (optional):");
        mainPanel.add(labelDist, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        copyOtherJars = new JCheckBox();
        copyOtherJars.setText("Copy also /common/lib jars (mail etc)");
        copyOtherJars.setMnemonic('C');
        copyOtherJars.setDisplayedMnemonicIndex(0);
        mainPanel.add(copyOtherJars, new GridConstraints(6, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        showConfirmationDialog = new JCheckBox();
        showConfirmationDialog.setText("Show confirmation dialog");
        showConfirmationDialog.setMnemonic('S');
        showConfirmationDialog.setDisplayedMnemonicIndex(0);
        mainPanel.add(showConfirmationDialog, new GridConstraints(9, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteAllJars = new JCheckBox();
        deleteAllJars.setText("Delete all jars in directory");
        deleteAllJars.setMnemonic('D');
        deleteAllJars.setDisplayedMnemonicIndex(0);
        mainPanel.add(deleteAllJars, new GridConstraints(10, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        explanationLabel = new JLabel();
        explanationLabel.setText("explain");
        mainPanel.add(explanationLabel, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelCreateProjectRoot = new JLabel();
        labelCreateProjectRoot.setText("Project root:");
        mainPanel.add(labelCreateProjectRoot, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createProjectFile = new JCheckBox();
        createProjectFile.setText("Create a text file containing project root path");
        createProjectFile.setMnemonic('R');
        createProjectFile.setDisplayedMnemonicIndex(38);
        mainPanel.add(createProjectFile, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tomcatRootDirectory = new TextFieldWithBrowseButton();
        mainPanel.add(tomcatRootDirectory, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tomcatRootLabel = new JLabel();
        tomcatRootLabel.setHorizontalAlignment(10);
        tomcatRootLabel.setHorizontalTextPosition(10);
        tomcatRootLabel.setText("Tomcat root directory:");
        mainPanel.add(tomcatRootLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        projectRootDirectory = new TextFieldWithBrowseButton();
        mainPanel.add(projectRootDirectory, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Project directory(optional):");
        mainPanel.add(label1, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        copyLog4j = new JCheckBox();
        copyLog4j.setText("Copy conf/log4j-dev.xml to ${CATALINA_BASE}/conf/log4j.xml (or define one below:)");
        copyLog4j.setMnemonic('O');
        copyLog4j.setDisplayedMnemonicIndex(1);
        mainPanel.add(copyLog4j, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        log4jLocation = new TextFieldWithBrowseButton();
        mainPanel.add(log4jLocation, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Log4j file location(optional):");
        mainPanel.add(label2, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }


}