/*
 * Copyright © 2017 CC, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.sampleapp.cli.impl;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.opendaylight.sampleapp.cli.api.SampleappCliCommands;

public class SampleappCliCommandsImpl implements SampleappCliCommands {

    private static final Logger LOG = LoggerFactory.getLogger(SampleappCliCommandsImpl.class);
    private final DataBroker dataBroker;

    public SampleappCliCommandsImpl(final DataBroker db) {
        this.dataBroker = db;
        LOG.info("SampleappCliCommandImpl initialized");
    }

    @Override
    public Object testCommand(Object testArgument) {
        return "This is a test implementation of test-command";
    }
}