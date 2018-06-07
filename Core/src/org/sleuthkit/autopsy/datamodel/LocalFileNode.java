/*
 * Autopsy Forensic Browser
 *
 * Copyright 2013-2018 Basis Technology Corp.
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
package org.sleuthkit.autopsy.datamodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.Action;
import org.openide.nodes.Sheet;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.sleuthkit.autopsy.actions.AddContentTagAction;
import org.sleuthkit.autopsy.actions.DeleteFileContentTagAction;
import org.sleuthkit.autopsy.centralrepository.AddEditCentralRepoCommentAction;
import org.sleuthkit.autopsy.centralrepository.datamodel.EamDbUtil;
import org.sleuthkit.autopsy.coreutils.ContextMenuExtensionPoint;
import org.sleuthkit.autopsy.coreutils.Logger;
import org.sleuthkit.autopsy.directorytree.ExternalViewerAction;
import org.sleuthkit.autopsy.directorytree.ExtractAction;
import org.sleuthkit.autopsy.directorytree.HashSearchAction;
import org.sleuthkit.autopsy.directorytree.NewWindowViewAction;
import org.sleuthkit.autopsy.directorytree.ViewContextAction;
import org.sleuthkit.autopsy.modules.embeddedfileextractor.ExtractArchiveWithPasswordAction;
import org.sleuthkit.datamodel.AbstractFile;
import org.sleuthkit.datamodel.BlackboardArtifact;
import org.sleuthkit.datamodel.TskCoreException;

/**
 * A Node for a LocalFile or DerivedFile content object.
 */
public class LocalFileNode extends AbstractAbstractFileNode<AbstractFile> {

    private static final Logger logger = Logger.getLogger(LocalFileNode.class.getName());

    public LocalFileNode(AbstractFile af) {
        super(af);

        this.setDisplayName(af.getName());

        // set name, display name, and icon
        if (af.isDir()) {
            this.setIconBaseWithExtension("org/sleuthkit/autopsy/images/Folder-icon.png"); //NON-NLS
        } else {
            this.setIconBaseWithExtension(FileNode.getIconForFileType(af));
        }

    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = super.createSheet();
        Sheet.Set sheetSet = sheet.get(Sheet.PROPERTIES);
        if (sheetSet == null) {
            sheetSet = Sheet.createPropertiesSet();
            sheet.put(sheetSet);
        }

        Map<String, Object> map = new LinkedHashMap<>();
        fillPropertyMap(map, getContent());

        sheetSet.put(new NodeProperty<>(NbBundle.getMessage(this.getClass(), "LocalFileNode.createSheet.name.name"),
                NbBundle.getMessage(this.getClass(), "LocalFileNode.createSheet.name.displayName"),
                NbBundle.getMessage(this.getClass(), "LocalFileNode.createSheet.name.desc"),
                getName()));

        final String NO_DESCR = NbBundle.getMessage(this.getClass(), "LocalFileNode.createSheet.noDescr.text");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sheetSet.put(new NodeProperty<>(entry.getKey(), entry.getKey(), NO_DESCR, entry.getValue()));
        }

        // add tags property to the sheet
        addTagProperty(sheetSet);

        return sheet;
    }

    @Override
    public Action[] getActions(boolean context) {
        List<Action> actionsList = new ArrayList<>();

        actionsList.addAll(Arrays.asList(super.getActions(true)));
        
        // Create the "Add/Edit Central Repository Comment" menu item if the enabled.
        AbstractFile file = content;
        if (file != null && file.isFile() && EamDbUtil.useCentralRepo()) {
            try {
                actionsList.add(AddEditCentralRepoCommentAction.createAddEditCentralRepoCommentAction(file));
            } catch (AddEditCentralRepoCommentAction.AddEditCentralRepoCommentException ex) {
                logger.log(Level.SEVERE, String.format(
                        "An error occurred while trying to create the 'Add/Edit Central Repository Comment' action for file \"%s\" (objId=%d).",
                        file.getName(), file.getId()), ex);
            }
        }
        
        actionsList.add(new ViewContextAction(NbBundle.getMessage(this.getClass(), "LocalFileNode.viewFileInDir.text"), this.content));
        actionsList.add(null); // creates a menu separator
        actionsList.add(new NewWindowViewAction(
                NbBundle.getMessage(this.getClass(), "LocalFileNode.getActions.viewInNewWin.text"), this));
        actionsList.add(new ExternalViewerAction(
                NbBundle.getMessage(this.getClass(), "LocalFileNode.getActions.openInExtViewer.text"), this));
        actionsList.add(null); // creates a menu separator

        actionsList.add(ExtractAction.getInstance());
        actionsList.add(new HashSearchAction(
                NbBundle.getMessage(this.getClass(), "LocalFileNode.getActions.searchFilesSameMd5.text"), this));
        actionsList.add(null); // creates a menu separator
        actionsList.add(AddContentTagAction.getInstance());

        final Collection<AbstractFile> selectedFilesList
                = new HashSet<>(Utilities.actionsGlobalContext().lookupAll(AbstractFile.class));
        if (selectedFilesList.size() == 1) {
            actionsList.add(DeleteFileContentTagAction.getInstance());
        }

        actionsList.addAll(ContextMenuExtensionPoint.getActions());
        if (FileTypeExtensions.getArchiveExtensions().contains("." + this.content.getNameExtension().toLowerCase())) {
            try {
                if (this.content.getArtifacts(BlackboardArtifact.ARTIFACT_TYPE.TSK_ENCRYPTION_DETECTED).size() > 0) {
                    actionsList.add(new ExtractArchiveWithPasswordAction(this.getContent()));
                }
            } catch (TskCoreException ex) {
                logger.log(Level.WARNING, "Unable to add unzip with password action to context menus", ex);
            }
        }
        return actionsList.toArray(new Action[0]);
    }

    @Override
    public <T> T accept(ContentNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public <T> T accept(DisplayableItemNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean isLeafTypeNode() {
        // This seems wrong, but it also seems that it is never called
        // because the visitor to figure out if there are children or 
        // not will check if it has children using the Content API
        return true; //!this.hasContentChildren();
    }

    @Override
    public String getItemType() {
        return getClass().getName();
    }
}
