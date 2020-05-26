/*
 * Autopsy Forensic Browser
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
package org.sleuthkit.autopsy.contentviewers;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import javax.swing.JScrollPane;
import org.sleuthkit.autopsy.coreutils.Logger;
import org.sleuthkit.autopsy.datamodel.ContentUtils;
import org.sleuthkit.datamodel.BlackboardArtifact;
import org.sleuthkit.datamodel.BlackboardAttribute;
import org.sleuthkit.datamodel.Content;
import org.sleuthkit.datamodel.DataSource;
import org.sleuthkit.datamodel.TskCoreException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.openide.util.NbBundle;

/**
 * This is a viewer for TSK_CALLLOG artifacts.
 *
 *
 */
@SuppressWarnings("PMD.SingularField") // UI widgets cause lots of false positives
public class CallLogArtifactViewer extends javax.swing.JPanel implements ArtifactContentViewer {

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    private final static Logger logger = Logger.getLogger(CallLogArtifactViewer.class.getName());
    private static final long serialVersionUID = 1L;

    private static final Set<Integer> HANDLED_ATTRIBUTE_TYPES = new HashSet<Integer>(Arrays.asList(
            BlackboardAttribute.ATTRIBUTE_TYPE.TSK_PHONE_NUMBER.getTypeID(),
            BlackboardAttribute.ATTRIBUTE_TYPE.TSK_PHONE_NUMBER_TO.getTypeID(),
            BlackboardAttribute.ATTRIBUTE_TYPE.TSK_PHONE_NUMBER_FROM.getTypeID(),
            BlackboardAttribute.ATTRIBUTE_TYPE.TSK_DIRECTION.getTypeID(),
            BlackboardAttribute.ATTRIBUTE_TYPE.TSK_DATETIME.getTypeID(),
            BlackboardAttribute.ATTRIBUTE_TYPE.TSK_DATETIME_START.getTypeID(),
            BlackboardAttribute.ATTRIBUTE_TYPE.TSK_DATETIME_END.getTypeID()
    ));

    /**
     * Creates new form CalllogArtifactViewer
     */
    public CallLogArtifactViewer() {
        initComponents();
        customizeComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        callDetailsPanel = new javax.swing.JPanel();
        directionLabel = new javax.swing.JLabel();
        dateTimeLabel = new javax.swing.JLabel();
        durationLabel = new javax.swing.JLabel();
        callDetailsLabel = new javax.swing.JLabel();
        onLabel = new javax.swing.JLabel();
        callLabel = new javax.swing.JLabel();
        participantsListPanel = new javax.swing.JPanel();
        otherAttributesPanel = new javax.swing.JPanel();
        otherInfoLabel = new javax.swing.JLabel();
        otherAttributesListPanel = new javax.swing.JPanel();
        bottomPanel = new javax.swing.JPanel();
        localAccountInfoPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        dataSourceNameLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        deviceIdLabel = new javax.swing.JLabel();
        localAccountLabel = new javax.swing.JLabel();
        localAccountIdLabel = new javax.swing.JLabel();
        sourceSectionLabel = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new java.awt.BorderLayout());

        callDetailsPanel.setPreferredSize(new java.awt.Dimension(400, 150));

        directionLabel.setFont(directionLabel.getFont());
        org.openide.awt.Mnemonics.setLocalizedText(directionLabel, org.openide.util.NbBundle.getMessage(CallLogArtifactViewer.class, "CallLogArtifactViewer.directionLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(dateTimeLabel, org.openide.util.NbBundle.getMessage(CallLogArtifactViewer.class, "CallLogArtifactViewer.dateTimeLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(durationLabel, org.openide.util.NbBundle.getMessage(CallLogArtifactViewer.class, "CallLogArtifactViewer.durationLabel.text")); // NOI18N

        callDetailsLabel.setFont(callDetailsLabel.getFont().deriveFont(callDetailsLabel.getFont().getStyle() | java.awt.Font.BOLD));
        org.openide.awt.Mnemonics.setLocalizedText(callDetailsLabel, org.openide.util.NbBundle.getMessage(CallLogArtifactViewer.class, "CallLogArtifactViewer.callDetailsLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(onLabel, org.openide.util.NbBundle.getMessage(CallLogArtifactViewer.class, "CallLogArtifactViewer.onLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(callLabel, org.openide.util.NbBundle.getMessage(CallLogArtifactViewer.class, "CallLogArtifactViewer.callLabel.text")); // NOI18N

        javax.swing.GroupLayout participantsListPanelLayout = new javax.swing.GroupLayout(participantsListPanel);
        participantsListPanel.setLayout(participantsListPanelLayout);
        participantsListPanelLayout.setHorizontalGroup(
            participantsListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        participantsListPanelLayout.setVerticalGroup(
            participantsListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout callDetailsPanelLayout = new javax.swing.GroupLayout(callDetailsPanel);
        callDetailsPanel.setLayout(callDetailsPanelLayout);
        callDetailsPanelLayout.setHorizontalGroup(
            callDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(callDetailsPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(callDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(callDetailsLabel)
                    .addGroup(callDetailsPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(callDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(callDetailsPanelLayout.createSequentialGroup()
                                .addComponent(directionLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(callLabel))
                            .addGroup(callDetailsPanelLayout.createSequentialGroup()
                                .addComponent(onLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateTimeLabel)
                                .addGap(18, 18, 18)
                                .addComponent(durationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(participantsListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(485, Short.MAX_VALUE))
        );
        callDetailsPanelLayout.setVerticalGroup(
            callDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(callDetailsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(callDetailsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(participantsListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(callDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(directionLabel)
                    .addComponent(callLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(callDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(onLabel)
                    .addComponent(dateTimeLabel)
                    .addComponent(durationLabel))
                .addGap(20, 20, 20))
        );

        topPanel.add(callDetailsPanel, java.awt.BorderLayout.PAGE_START);

        otherInfoLabel.setFont(otherInfoLabel.getFont().deriveFont(otherInfoLabel.getFont().getStyle() | java.awt.Font.BOLD));
        org.openide.awt.Mnemonics.setLocalizedText(otherInfoLabel, org.openide.util.NbBundle.getMessage(CallLogArtifactViewer.class, "CallLogArtifactViewer.otherInfoLabel.text")); // NOI18N

        javax.swing.GroupLayout otherAttributesListPanelLayout = new javax.swing.GroupLayout(otherAttributesListPanel);
        otherAttributesListPanel.setLayout(otherAttributesListPanelLayout);
        otherAttributesListPanelLayout.setHorizontalGroup(
            otherAttributesListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 413, Short.MAX_VALUE)
        );
        otherAttributesListPanelLayout.setVerticalGroup(
            otherAttributesListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 88, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout otherAttributesPanelLayout = new javax.swing.GroupLayout(otherAttributesPanel);
        otherAttributesPanel.setLayout(otherAttributesPanelLayout);
        otherAttributesPanelLayout.setHorizontalGroup(
            otherAttributesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherAttributesPanelLayout.createSequentialGroup()
                .addGroup(otherAttributesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(otherAttributesListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(otherAttributesPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(otherInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        otherAttributesPanelLayout.setVerticalGroup(
            otherAttributesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherAttributesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(otherInfoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(otherAttributesListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        topPanel.add(otherAttributesPanel, java.awt.BorderLayout.PAGE_END);

        add(topPanel, java.awt.BorderLayout.PAGE_START);

        bottomPanel.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(CallLogArtifactViewer.class, "CallLogArtifactViewer.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(dataSourceNameLabel, org.openide.util.NbBundle.getMessage(CallLogArtifactViewer.class, "CallLogArtifactViewer.dataSourceNameLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(CallLogArtifactViewer.class, "CallLogArtifactViewer.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(deviceIdLabel, org.openide.util.NbBundle.getMessage(CallLogArtifactViewer.class, "CallLogArtifactViewer.deviceIdLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(localAccountLabel, org.openide.util.NbBundle.getMessage(CallLogArtifactViewer.class, "CallLogArtifactViewer.localAccountLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(localAccountIdLabel, org.openide.util.NbBundle.getMessage(CallLogArtifactViewer.class, "CallLogArtifactViewer.localAccountIdLabel.text")); // NOI18N

        sourceSectionLabel.setFont(sourceSectionLabel.getFont().deriveFont(sourceSectionLabel.getFont().getStyle() | java.awt.Font.BOLD));
        org.openide.awt.Mnemonics.setLocalizedText(sourceSectionLabel, org.openide.util.NbBundle.getMessage(CallLogArtifactViewer.class, "CallLogArtifactViewer.sourceSectionLabel.text")); // NOI18N

        javax.swing.GroupLayout localAccountInfoPanelLayout = new javax.swing.GroupLayout(localAccountInfoPanel);
        localAccountInfoPanel.setLayout(localAccountInfoPanelLayout);
        localAccountInfoPanelLayout.setHorizontalGroup(
            localAccountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(localAccountInfoPanelLayout.createSequentialGroup()
                .addGroup(localAccountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(localAccountInfoPanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(localAccountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(localAccountLabel)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(24, 24, 24)
                        .addGroup(localAccountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(localAccountIdLabel)
                            .addComponent(dataSourceNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deviceIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)))
                    .addGroup(localAccountInfoPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(sourceSectionLabel)))
                .addContainerGap(243, Short.MAX_VALUE))
        );
        localAccountInfoPanelLayout.setVerticalGroup(
            localAccountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(localAccountInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sourceSectionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(localAccountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(localAccountLabel)
                    .addComponent(localAccountIdLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(localAccountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataSourceNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(localAccountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(deviceIdLabel))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        bottomPanel.add(localAccountInfoPanel, java.awt.BorderLayout.PAGE_END);

        add(bottomPanel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void customizeComponents() {
        // disable the name label for now.
        // this.toOrFromNameLabel.setVisible(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JLabel callDetailsLabel;
    private javax.swing.JPanel callDetailsPanel;
    private javax.swing.JLabel callLabel;
    private javax.swing.JLabel dataSourceNameLabel;
    private javax.swing.JLabel dateTimeLabel;
    private javax.swing.JLabel deviceIdLabel;
    private javax.swing.JLabel directionLabel;
    private javax.swing.JLabel durationLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel localAccountIdLabel;
    private javax.swing.JPanel localAccountInfoPanel;
    private javax.swing.JLabel localAccountLabel;
    private javax.swing.JLabel onLabel;
    private javax.swing.JPanel otherAttributesListPanel;
    private javax.swing.JPanel otherAttributesPanel;
    private javax.swing.JLabel otherInfoLabel;
    private javax.swing.JPanel participantsListPanel;
    private javax.swing.JLabel sourceSectionLabel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setArtifact(BlackboardArtifact artifact) {

        this.removeAll();
        this.initComponents();
        this.customizeComponents();

        CallLogViewData callLogViewData = null;

        try {
            callLogViewData = getCallLogViewData(artifact);
        } catch (TskCoreException ex) {
            logger.log(Level.SEVERE, String.format("Error getting attributes for Calllog artifact (artifact_id=%d, obj_id=%d)", artifact.getArtifactID(), artifact.getObjectID()), ex);
        }

        // update the view with the call log data
        if (callLogViewData != null) {
            updateView(callLogViewData);
        }
        // repaint
        this.revalidate();
    }

    /**
     * Updates the call log viewer UI components from the given data.
     * 
     * @param callLogViewData Call log data to display.
     */
    private void updateView(CallLogViewData callLogViewData) {
             
        // populate call partcipants list.
            updateParticipantsPanel(callLogViewData);
           
            if (callLogViewData.getDirection() != null) {
                this.directionLabel.setText(callLogViewData.getDirection());
            } else {
                this.directionLabel.setVisible(false);
                this.callLabel.setVisible(false);
            }

            if (callLogViewData.getDateTimeStr() != null) {
                this.dateTimeLabel.setText(callLogViewData.getDateTimeStr());
                if (callLogViewData.getDuration() != null) {
                    this.durationLabel.setText(callLogViewData.getDuration());
                } else {
                    this.durationLabel.setVisible(false);
                }
            } else {
                this.dateTimeLabel.setVisible(false);
                this.durationLabel.setVisible(false);
            }

            // Populate other attributs panel
            updateOtherAttributesPanel(callLogViewData.getOtherAttributes());

            // populate local account and data source
            if (callLogViewData.getLocalAccountId() != null) {
                // Vik-6383 find and display the persona for this account, and a button
                this.localAccountIdLabel.setText(callLogViewData.getLocalAccountId());
            } else {
                this.localAccountLabel.setVisible(false);
                this.localAccountIdLabel.setVisible(false);
            }
            if (callLogViewData.getDataSourceName() != null) {
                this.dataSourceNameLabel.setText(callLogViewData.getDataSourceName());
            }
            if (callLogViewData.getDataSourceDeviceId() != null) {
                this.deviceIdLabel.setText(callLogViewData.getDataSourceDeviceId());
            }
    }
   

    /**
     * Updates the Call participants panel.
     * 
     * @param callLogViewData  Call log data to display.
     */
    private void updateParticipantsPanel(CallLogViewData callLogViewData) {

        // create a gridbag layout to show each participant on one line
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.gridy = 0;
        constraints.insets = new java.awt.Insets(4, 0, 0, 0);
        
        // show primary sender/receiver
        showParticipant(callLogViewData.getNumberDesignator(), callLogViewData.getNumber(), gridBagLayout, constraints );
        constraints.gridy++;
        
        for (String recipient : callLogViewData.getOtherRecipients()) {
            showParticipant(Bundle.CallLogArtifactViewer_number_to(), recipient, gridBagLayout, constraints );
            constraints.gridy++;
        }
        participantsListPanel.setLayout(gridBagLayout);
        participantsListPanel.revalidate();
    }
    
    /** 
     * Display a call participant in the view.
     * 
     * @param participantDesignator Label to show - To/From.
     * @param accountIdentifier account identifier for the participant.
     * @param gridBagLayout
     * @param constraints 
     */
    private void showParticipant(String participantDesignator, String accountIdentifier, GridBagLayout gridBagLayout, GridBagConstraints constraints) {

        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0;
        constraints.gridx = 0;

        javax.swing.Box.Filler filler1 = new javax.swing.Box.Filler(new Dimension(25, 0), new Dimension(25, 0), new Dimension(25, 0));
        participantsListPanel.add(filler1, constraints);

        constraints.gridx++;
        javax.swing.JLabel toLabel = new javax.swing.JLabel();
        toLabel.setText(participantDesignator);

        gridBagLayout.setConstraints(toLabel, constraints);
        participantsListPanel.add(toLabel);

        constraints.gridx++;

        javax.swing.Box.Filler filler2 = new javax.swing.Box.Filler(new Dimension(5, 0), new Dimension(5, 0), new Dimension(5, 0));
        participantsListPanel.add(filler2, constraints);

        // Add attribute name label
        constraints.gridx++;

        // Add recipients number/Id
        javax.swing.JLabel participantAccountLabel = new javax.swing.JLabel();
        participantAccountLabel.setText(accountIdentifier);

        gridBagLayout.setConstraints(participantAccountLabel, constraints);
        participantsListPanel.add(participantAccountLabel);

        // TBD Vik-6383 find and display the persona for this account, and a button
//            constraints.gridx += 2;
//            javax.swing.JButton personaButton = new javax.swing.JButton();
//            personaButton.setText("Persona");
//            gridBagLayout.setConstraints(personaButton, constraints);
//            otherParticipantsListPanel.add(personaButton);


        // add a filler to take up rest of the space
        constraints.gridx++;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        participantsListPanel.add(new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0)));

    }
    /**
     * Updates the Other Attributes panel with the given list of attributes.
     * This panel displays any uncommon attributes that might not be shown already.
     * 
     * @param otherAttributes List of attributes to display.
     */
    private void updateOtherAttributesPanel(Map<String, String> otherAttributes) {
        if (otherAttributes == null || otherAttributes.isEmpty()) {
            this.otherAttributesPanel.setVisible(false);
            return;
        }

        // create a gridbag layout to show attribute on one line
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.gridy = 0;
        constraints.insets = new java.awt.Insets(4, 12, 0, 0);
        for (Map.Entry<String, String> attribute : otherAttributes.entrySet()) {
            constraints.fill = GridBagConstraints.NONE;
            constraints.weightx = 0;
            constraints.gridx = 0;

            // Add a small horizontal filler at the beginning
            javax.swing.Box.Filler filler1 = new javax.swing.Box.Filler(new Dimension(25, 0), new Dimension(25, 0), new Dimension(25, 0));
            otherAttributesListPanel.add(filler1, constraints);

            // Add attribute name label
            constraints.gridx++;
            javax.swing.JLabel attrNameLabel = new javax.swing.JLabel();
            attrNameLabel.setText(attribute.getKey());

            gridBagLayout.setConstraints(attrNameLabel, constraints);
            this.otherAttributesListPanel.add(attrNameLabel);

            // Add value
            constraints.gridx += 2;
            javax.swing.JLabel attrValueLabel = new javax.swing.JLabel();
            attrValueLabel.setText(attribute.getValue());

            gridBagLayout.setConstraints(attrValueLabel, constraints);
            this.otherAttributesListPanel.add(attrValueLabel);

            // add a filler to take up rest of the horizontal space
            constraints.gridx++;
            constraints.weightx = 1.0;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            otherAttributesListPanel.add(new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0)));

            constraints.gridy++;
        }
        otherAttributesListPanel.setLayout(gridBagLayout);
        otherAttributesListPanel.revalidate();
    }

    @Override
    public Component getComponent() {
        return new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    @Override
    public boolean isSupported(BlackboardArtifact artifact) {
        return artifact.getArtifactTypeID() == BlackboardArtifact.ARTIFACT_TYPE.TSK_CALLLOG.getTypeID();
    }

    /**
     * Extracts data from the call log artifact for display in the view.
     *
     * @param artifact Artifact to extract data from.
     * @return CallLogViewData Extracted data to be displayed.
     * @throws TskCoreException
     */
     @NbBundle.Messages({
        "CallLogArtifactViewer_number_from=From",
        "CallLogArtifactViewer_number_to=To"})
    private CallLogViewData getCallLogViewData(BlackboardArtifact artifact) throws TskCoreException {

        BlackboardAttribute directionAttr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_DIRECTION));
        BlackboardAttribute recipientsAttr  = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_PHONE_NUMBER_TO));
        BlackboardAttribute numberAttr = null;
        BlackboardAttribute localAccountAttr = null;
        

        CallLogViewData callLogViewData = null;
        String direction = null;
        String numberDesignator = null;

        if (directionAttr != null) {
            // if direction is known,  depending on the direction,
            // the TO or the FROM attribute is the primary number of interest.
            // and the other, if available, is possibly the local account of device owner.
            direction = directionAttr.getValueString();

            if (direction.equalsIgnoreCase("Incoming")) {
                numberAttr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_PHONE_NUMBER_FROM));
                localAccountAttr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_PHONE_NUMBER_TO));
                numberDesignator = Bundle.CallLogArtifactViewer_number_from();
            } else if (direction.equalsIgnoreCase("Outgoing")) {
                numberAttr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_PHONE_NUMBER_TO));
                localAccountAttr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_PHONE_NUMBER_FROM));
                numberDesignator = Bundle.CallLogArtifactViewer_number_to();
            } 

        }
        // if direction isn't known, check all the usual attributes that may have the number/address
        if (numberAttr == null) {
            numberAttr = ObjectUtils.firstNonNull(
                    artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_PHONE_NUMBER_FROM)),
                    artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_PHONE_NUMBER_TO)),
                    artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_PHONE_NUMBER)),
                    artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_ID))
            );
        }
               
        if (numberAttr != null) {

            // check if it's a list of numbers and if so,
            // split it, take the first one.
            String[] numbers = numberAttr.getValueString().split(",");
        
            // create a CallLogViewData with the primary number/id.
            callLogViewData = new CallLogViewData(StringUtils.trim(numbers[0]));
      
            // set direction
            callLogViewData.setDirection(direction);
            callLogViewData.setNumberDesignator(numberDesignator);
            
            // get other recipients
            extractOtherRecipeints(recipientsAttr, directionAttr, callLogViewData);
            
            // get date, duration,
            extractTimeAndDuration(artifact, callLogViewData);

            // get the data source name and device id
            Content dataSource = artifact.getDataSource();

            callLogViewData.setDataSourceName(dataSource.getName());
            String deviceId = ((DataSource) dataSource).getDeviceId();
            callLogViewData.setDataSourceDeviceId(deviceId);

            // set the local account, if it can be deduced
            if (localAccountAttr != null) {
                String attrValue = localAccountAttr.getValueString();
                if (attrValue.equalsIgnoreCase(deviceId) == false && attrValue.contains(",") == false) {
                    callLogViewData.setLocalAccountId(attrValue);
                }
            }

            // set any other additional attriubutes.
            callLogViewData.setOtherAttributes(extractOtherAttributes(artifact));
        }

        return callLogViewData;
    }
 
    /**
     * Extracts the other recipients numbers from the given attribute.
     * Updates the given CallLogViewData with the other recipients list.
     * 
     * @param recipientsAttr TO attribute, must not be null. 
     * @param directionAttr  Direction attribute.
     * @param callLogViewData CallLogViewData object to update.
     * 
     */
    private void extractOtherRecipeints(BlackboardAttribute recipientsAttr, BlackboardAttribute directionAttr, CallLogViewData callLogViewData) {
        if (recipientsAttr == null) {
            return;
        }
        // check if it's a list of numbers and and if so,
        // split it,
        String[] numbers = recipientsAttr.getValueString().split(",");
        List<String> otherNumbers = null;
        int startIdx = 0;
        // for an outgoing call, the first number has already been plucked as the primary recipient.
        // for an incoming call with multiple recipeints, we can't distinguish the local account number, so everyone is "other"
        if (directionAttr != null && directionAttr.getValueString().equalsIgnoreCase("Outgoing")) {
            startIdx = 1;
        }
        if (numbers.length > 1) {
            otherNumbers = new ArrayList<>();
            for (int i = startIdx; i < numbers.length; i++) {
                otherNumbers.add(StringUtils.trim(numbers[i]));
            }
        }
        
        callLogViewData.setOtherRecipients(otherNumbers);
    }
       
    
    /**
     * Returns  the attributes from the given artifact that are not already 
     * displayed by the artifact viewer.
     *
     * @param artifact Call log artifact.
     * @return Attribute names/values.
     * @throws TskCoreException
     */
    private Map<String, String> extractOtherAttributes(BlackboardArtifact artifact) throws TskCoreException {
        List<BlackboardAttribute> attributes = artifact.getAttributes();
        Map<String, String> otherAttributes = new HashMap<>();

        for (BlackboardAttribute attr : attributes) {
            if (HANDLED_ATTRIBUTE_TYPES.contains(attr.getAttributeType().getTypeID()) == false) {
                otherAttributes.put(attr.getAttributeType().getDisplayName(), attr.getDisplayString());
            }
        }

        return otherAttributes;
    }
    
    /**
     * Extract the call time and duration from the artifact and saves in the
     * CallLogViewData.
     *
     * @param artifact Call log artifact.
     * @param callLogViewData CallLogViewData object to save the time & duration
     * in.
     *
     * @throws TskCoreException
     */
    private void extractTimeAndDuration(BlackboardArtifact artifact, CallLogViewData callLogViewData ) throws TskCoreException {
        
        BlackboardAttribute startTimeAttr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_DATETIME_START));
        if (startTimeAttr == null) {
            startTimeAttr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_DATETIME));
        }
        if (startTimeAttr != null) {
            long startTime = startTimeAttr.getValueLong();
            Content content = artifact.getDataSource();
            if (null != content && 0 != startTime) {
                dateFormatter.setTimeZone(ContentUtils.getTimeZone(content));
                callLogViewData.setDateTimeStr(dateFormatter.format(new java.util.Date(startTime * 1000)));
            }

            BlackboardAttribute endTimeAttr = artifact.getAttribute(new BlackboardAttribute.Type(BlackboardAttribute.ATTRIBUTE_TYPE.TSK_DATETIME_END));
            if (endTimeAttr != null) {
                long endTime = endTimeAttr.getValueLong();
                if (endTime > 0 && (endTime - startTime) > 0) {
                    callLogViewData.setDuration(String.format("%d seconds", (endTime - startTime)));
                }
            }
        }
    }
}
