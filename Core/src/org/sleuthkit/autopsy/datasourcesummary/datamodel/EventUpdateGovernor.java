/*
 * Autopsy Forensic Browser
 *
 * Copyright 2019 Basis Technology Corp.
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
package org.sleuthkit.autopsy.datasourcesummary.datamodel;

import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.Set;
import org.sleuthkit.autopsy.casemodule.Case;
import org.sleuthkit.autopsy.ingest.ModuleContentEvent;
import org.sleuthkit.autopsy.ingest.ModuleDataEvent;

/**
 * Interface for determiining when data should update based on autopsy (i.e.
 * case/ingest) events.
 */
public interface EventUpdateGovernor {

    /**
     * @return The set of Case Events for which data should be updated.
     */
    default Set<Case.Events> getCaseEventUpdates() {
        return Collections.emptySet();
    }

    boolean isRefreshRequired(ModuleDataEvent evt);

    boolean isRefreshRequired(ModuleContentEvent evt);

    boolean isRefreshRequiredForCaseEvent(PropertyChangeEvent evt);
}
