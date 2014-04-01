/*
 * Autopsy Forensic Browser
 *
 * Copyright 2011-2014 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.keywordsearch;

import org.openide.util.NbBundle;
import org.sleuthkit.autopsy.corecomponents.OptionsPanel;
import org.sleuthkit.autopsy.ingest.IngestModuleGlobalSetttingsPanel;

/**
 * Global options panel for keyword searching.
 */
final class KeywordSearchGlobalSettingsPanel extends IngestModuleGlobalSetttingsPanel implements OptionsPanel {

    private KeywordSearchGlobalListSettingsPanel listsPanel;
    private KeywordSearchGlobalLanguageSettingsPanel languagesPanel;
    private KeywordSearchGlobalSearchSettingsPanel generalPanel;

    public KeywordSearchGlobalSettingsPanel() {
        initComponents();
        customizeComponents();
    }

    private void customizeComponents() {
        setName(NbBundle.getMessage(this.getClass(), "KeywordSearchConfigurationPanel.customizeComponents.title"));
        listsPanel = new KeywordSearchGlobalListSettingsPanel();
        languagesPanel = new KeywordSearchGlobalLanguageSettingsPanel();
        generalPanel = new KeywordSearchGlobalSearchSettingsPanel();
        tabbedPane.insertTab(NbBundle.getMessage(this.getClass(), "KeywordSearchConfigurationPanel.customizeComponents.listTabTitle"), null,
                listsPanel, NbBundle.getMessage(this.getClass(), "KeywordSearchConfigurationPanel.customizeComponents.listLabToolTip"), 0);
        tabbedPane.insertTab(NbBundle.getMessage(this.getClass(), "KeywordSearchConfigurationPanel.customizeComponents.stringExtTitle"), null,
                languagesPanel, NbBundle.getMessage(this.getClass(), "KeywordSearchConfigurationPanel.customizeComponents.stringExtToolTip"), 1);
        tabbedPane.insertTab(NbBundle.getMessage(this.getClass(), "KeywordSearchConfigurationPanel.customizeComponents.genTabTitle"), null,
                generalPanel, NbBundle.getMessage(this.getClass(), "KeywordSearchConfigurationPanel.customizeComponents.genTabToolTip"), 2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void load() {
        // This calls actually clears the component.
        listsPanel.load();

        languagesPanel.load();
        generalPanel.load();

        // Reload the XML to avoid 'ghost' vars
        KeywordSearchListsXML.getCurrent().reload();
    }

    @Override
    public void saveSettings() {
        listsPanel.store();
        languagesPanel.store();
        generalPanel.store();        
    }

    @Override
    public void store() {
        saveSettings();
    }

    public void cancel() {
        KeywordSearchListsXML.getCurrent().reload();
    }

    boolean valid() {
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
}
