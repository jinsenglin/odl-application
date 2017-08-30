/*
 * Copyright (c) 2016 Ericsson India Global Services Pvt Ltd. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.sampleapp.impl;

import org.opendaylight.controller.md.sal.binding.api.*;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
//import org.opendaylight.genius.datastoreutils.AsyncDataTreeChangeListenerBase;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.sampleapp.rev150105.access.list.AccessListEntry;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.sampleapp.rev150105.AccessList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List ;

public class SampleAppListener implements ClusteredDataTreeChangeListener<AccessList>  {
    private static final Logger LOG = LoggerFactory.getLogger(SampleAppListener.class);

    private final DataBroker broker;
    private SampleAppDataStoreHandler dsHandler ;

    public SampleAppListener(final DataBroker db, SampleAppDataStoreHandler dsHandler) {
        broker = db;
        this.dsHandler = dsHandler ;
        registerListener();
    }

    /**
     * register listener to get AccessListEntry add/removed event.
     */
    public void registerListener() {
        final DataTreeIdentifier<AccessList> treeId = new DataTreeIdentifier<>(
                LogicalDatastoreType.CONFIGURATION, getWildCardPath());
         broker.registerDataTreeChangeListener(treeId, SampleAppListener.this);
         LOG.info("Sample App listener registration success");
    }

   
    protected InstanceIdentifier<AccessList> getWildCardPath() {
        return InstanceIdentifier.create(AccessList.class);
    }

    
    protected SampleAppListener getDataTreeChangeListener() {
        return this;
    }

    @Override
    public void onDataTreeChanged(@Nonnull Collection<DataTreeModification<AccessList>> changes) {
          LOG.info("Sample App listener onDataTreeChanged {} ", changes );
        for (DataTreeModification<AccessList> change : changes) {
            DataObjectModification<AccessList> mod = change.getRootNode();
            switch (mod.getModificationType()) {
                case DELETE:
                    LOG.info(" Delete after data {} ", mod.getDataAfter());
                    break;
                case SUBTREE_MODIFIED:
                    break;
                case WRITE:
                    if (mod.getDataBefore() == null) {
                        LOG.info(" Write after data {} ", mod.getDataAfter());
                        printData(mod) ;
                        dsHandler.installFlow( mod );
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unhandled node modification type " + mod.getModificationType());
            }
        }
    }

    private void printData( DataObjectModification<AccessList> mod) {
        // TODO -- PreCheck !NULL
        AccessList accList = mod.getDataAfter();

        List<AccessListEntry> entries = accList.getAccessListEntry() ;
        LOG.info( " Printing the AccessList Entries : ") ;

        for (AccessListEntry entry : entries) {
            // AccessListEntry entry = entries.getAccessListEntry() ;
            LOG.info(" Key : " + entry.getKey());
            LOG.info(" Rule Name : " + entry.getRuleName());
            LOG.info(" NodeList :  " + entry.getNodeId());
            LOG.info(" Actions : " + entry.getActions());
            LOG.info(" Matches " + entry.getMatches());
            LOG.info(" AccessListEntry : " + entry.toString());
        }

    }
}
