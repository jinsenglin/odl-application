/*
 * Copyright (c) 2016 Ericsson India Global Services Pvt Ltd. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.sampleapp.impl;


import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.DataObjectModification;
import org.opendaylight.genius.mdsalutil.*;
import org.opendaylight.genius.mdsalutil.interfaces.IMdsalApiManager;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.sampleapp.rev150105.AccessList;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.sampleapp.rev150105.access.list.AccessListEntry;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.sampleapp.rev150105.access.list.access.list.entry.actions.packet.handling.Permit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SampleAppDataStoreHandler {

    private static final Logger LOG = LoggerFactory.getLogger(SampleAppDataStoreHandler.class);

    private DataBroker dataBroker ;
    private IMdsalApiManager mdsalApiManager;

    public SampleAppDataStoreHandler( DataBroker broker, IMdsalApiManager mdsalApiManager ) {
        this.dataBroker = broker ;
        this.mdsalApiManager = mdsalApiManager;
    }

    public void installFlow( DataObjectModification<AccessList> change) {
       LOG.info(" installflow with data {} ", change);

        AccessList accList = change.getDataAfter();

        List<AccessListEntry> entries = accList.getAccessListEntry() ;
        for (AccessListEntry entry : entries ) {
            FlowData flowData = new FlowData(entry);
            populateMDsalUtilWay(flowData);
        }
    }

    private void populateMDsalUtilWay (FlowData flowData){
            FlowEntity flowEntity = getFlowEntity(flowData, flowData.getNodeId());
            mdsalApiManager.installFlow(flowEntity);
            if (flowData.getPacketHandlingAction() instanceof Permit){
                mdsalApiManager.installFlow(getDownLinkFlowEntity(flowData, flowData.getNodeId()));
            }
            LOG.info("SampleApp default flow is installed successfully in node {}.", flowData.getNodeId());
    }

    private String getFlowRef(final String dstIp,final String ruleName) {
        return new StringBuilder(256).append(SampleAppConstants.FLOWID_PREFIX)
                .append(SampleAppConstants.RULENAME_SEPARATOR).append(ruleName).append(SampleAppConstants.RULENAME_SEPARATOR)
                .append(SampleAppConstants.FLOWID_SEPARATOR).append(dstIp).toString();
    }

    private String getNodeId(String nodeString){
        String [] parts = nodeString
                .split(SampleAppConstants.RULENAME_SEPARATOR ) ;
        return parts [1];
    }

    private FlowEntity getFlowEntity(FlowData flowBuilder, String nodeId){
        FlowEntity flowEntity = new FlowEntity(new BigInteger(getNodeId(nodeId)));
        long outPortNum = 2L;
        String flowId = getFlowRef(flowBuilder.getDstIpAddress(), flowBuilder.getRuleName()) ;
        BigInteger cookieValue = SampleAppConstants.COOKIE_SAMPLEAPP_BASE.add(new BigInteger("0120000", 16)).add(BigInteger.valueOf(10L));
        List<MatchInfo> mkMatches = new ArrayList<MatchInfo>();
        mkMatches.add(new MatchInfo(MatchFieldType.eth_type,
                new long[]{0x0800L}));
        if(flowBuilder.getDstPrefix() != 0) {
            mkMatches.add(new MatchInfo(MatchFieldType.ipv4_destination, new String[] {
                    flowBuilder.getDstIpAddress(), Integer.toString(flowBuilder.getDstPrefix()) }));
        }
        List<InstructionInfo> mkInstructions = new ArrayList<InstructionInfo>();
        List<ActionInfo> listActionInfo = new ArrayList<ActionInfo>();
        if (flowBuilder.getPacketHandlingAction() instanceof Permit){
            listActionInfo.add(new ActionInfo(ActionType.output, new String[] { Long.toString(outPortNum) }));
            flowEntity.setPriority(5);
        } else {
            flowEntity.setPriority(4);
        }
        mkInstructions.add(new InstructionInfo(InstructionType.apply_actions,
                listActionInfo));
        flowEntity.setCookie(cookieValue);
        flowEntity.setFlowId(flowId);
        flowEntity.setFlowName(SampleAppConstants.DEFAULT_RULE_NAME);
        flowEntity.setHardTimeOut(flowBuilder.getHardTimeout());
        flowEntity.setIdleTimeOut(flowBuilder.getIdleTimeout());
        flowEntity.setInstructionInfoList(mkInstructions);
        flowEntity.setMatchInfoList(mkMatches);
        flowEntity.setSendFlowRemFlag(false);
        flowEntity.setStrictFlag(false);
        flowEntity.setTableId(0);
        flowEntity.setDpnId(new BigInteger(getNodeId(nodeId)));
        return flowEntity;
    }

    private FlowEntity getDownLinkFlowEntity(FlowData flowBuilder, String nodeId){
        FlowEntity flowEntity = new FlowEntity(new BigInteger(getNodeId(nodeId)));
        long intPortNum = 1L;
        String flowId = getFlowRef(flowBuilder.getSrcIpAddress(), "DOWNLINKPERMIT") ;
        BigInteger cookieValue = SampleAppConstants.COOKIE_SAMPLEAPP_BASE.add(new BigInteger("0230000", 16)).add(BigInteger.valueOf(20L));
        List<MatchInfo> mkMatches = new ArrayList<MatchInfo>();
        mkMatches.add(new MatchInfo(MatchFieldType.eth_type,
                new long[]{0x0800L}));
        if(flowBuilder.getSrcPrefix() != 0) {
            mkMatches.add(new MatchInfo(MatchFieldType.ipv4_destination, new String[] {
                    flowBuilder.getSrcIpAddress(), Integer.toString(flowBuilder.getSrcPrefix()) }));
        }
        List<InstructionInfo> mkInstructions = new ArrayList<InstructionInfo>();
        List<ActionInfo> listActionInfo = new ArrayList<ActionInfo>();
        listActionInfo.add(new ActionInfo(ActionType.output, new String[] { Long.toString(intPortNum) }));
        flowEntity.setPriority(5);
        mkInstructions.add(new InstructionInfo(InstructionType.apply_actions,
                listActionInfo));
        flowEntity.setCookie(cookieValue);
        flowEntity.setFlowId(flowId);
        flowEntity.setFlowName("DOWNLINKPERMITFLOW");
        flowEntity.setHardTimeOut(flowBuilder.getHardTimeout());
        flowEntity.setIdleTimeOut(flowBuilder.getIdleTimeout());
        flowEntity.setInstructionInfoList(mkInstructions);
        flowEntity.setMatchInfoList(mkMatches);
        flowEntity.setSendFlowRemFlag(false);
        flowEntity.setStrictFlag(false);
        flowEntity.setTableId(0);
        flowEntity.setDpnId(new BigInteger(getNodeId(nodeId)));
        return flowEntity;
    }
}
