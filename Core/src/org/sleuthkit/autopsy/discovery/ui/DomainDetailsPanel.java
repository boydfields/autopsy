/*
 * Autopsy
 *
 * Copyright 2020 Basis Technology Corp.
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
package org.sleuthkit.autopsy.discovery.ui;

import com.google.common.eventbus.Subscribe;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.apache.commons.lang.StringUtils;
import org.sleuthkit.autopsy.discovery.search.DiscoveryEventUtils;
import org.sleuthkit.datamodel.BlackboardArtifact;
import org.sleuthkit.autopsy.discovery.search.SearchData;

final class DomainDetailsPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static ArtifactsWorker detailsWorker;
    private static String domain;
    private static String selectedTabName;

    /**
     * Creates new form ArtifactDetailsPanel
     */
    DomainDetailsPanel() {
        initComponents();
        addArtifactTabs();
    }

    /**
     * Add the tabs for each of the artifact types which we will be displaying.
     */
    private void addArtifactTabs() {
        for (BlackboardArtifact.ARTIFACT_TYPE type : SearchData.Type.DOMAIN.getArtifactTypes()) {
            DomainArtifactsTabPanel newTab = new DomainArtifactsTabPanel(type);
            jTabbedPane1.add(type.getDisplayName(), newTab);
            if (!StringUtils.isBlank(selectedTabName)) {
                jTabbedPane1.setSelectedComponent(newTab);
            }
        }
        jTabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (jTabbedPane1.getSelectedIndex() >= 0) {
                    String newTabTitle = jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex());
                    if (selectedTabName == null || !selectedTabName.equals(newTabTitle)) {
                        selectedTabName = newTabTitle;
                        runDomainWorker();
                    }
                }
            }
        });
    }

    /**
     * Run the worker which retrieves the list of artifacts for the domain to
     * populate the details area.
     */
    private void runDomainWorker() {
        SwingUtilities.invokeLater(() -> {
            Component selectedComponent = jTabbedPane1.getSelectedComponent();
            if (selectedComponent != null && selectedComponent instanceof DomainArtifactsTabPanel) {
                if (detailsWorker != null && !detailsWorker.isDone()) {
                    detailsWorker.cancel(true);
                }
                DomainArtifactsTabPanel selectedTab = (DomainArtifactsTabPanel) selectedComponent;
                if (selectedTab.getStatus() == DomainArtifactsTabPanel.ARTIFACT_RETRIEVAL_STATUS.UNPOPULATED) {
                    selectedTab.setStatus(DomainArtifactsTabPanel.ARTIFACT_RETRIEVAL_STATUS.POPULATING);
                    DiscoveryEventUtils.getDiscoveryEventBus().register(selectedTab);
                    detailsWorker = new ArtifactsWorker(selectedTab.getArtifactType(), domain);
                    detailsWorker.execute();
                }
            }
        });
    }

    /**
     * Clear the tabs of any previous content.
     */
    private void clearTabs() {
        int tabCount = jTabbedPane1.getTabCount();
        // We invoke removeTabAt for each tab, otherwise we may end up
        // removing Components added by the UI.
        while (tabCount > 0) {
            tabCount--;
            jTabbedPane1.removeTabAt(tabCount);
        }
        addArtifactTabs();

    }

    /**
     * Populate the the details tabs.
     *
     * @param populateEvent The PopulateDomainTabsEvent which indicates which
     *                      domain the details tabs should be populated for.
     */
    @Subscribe
    synchronized void handlePopulateDomainTabsEvent(DiscoveryEventUtils.PopulateDomainTabsEvent populateEvent) {
        SwingUtilities.invokeLater(() -> {
            domain = populateEvent.getDomain();
            runDomainWorker();
            clearTabs();
            if (StringUtils.isBlank(domain)) {
                //send fade out event
                DiscoveryEventUtils.getDiscoveryEventBus().post(new DiscoveryEventUtils.DetailsVisibleEvent(false));
            } else {
                //send fade in event
                DiscoveryEventUtils.getDiscoveryEventBus().post(new DiscoveryEventUtils.DetailsVisibleEvent(true));
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();

        setEnabled(false);
        setLayout(new java.awt.BorderLayout());
        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
