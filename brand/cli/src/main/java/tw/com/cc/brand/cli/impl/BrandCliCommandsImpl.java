/*
 * Copyright © 2016 CC, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package tw.com.cc.brand.cli.impl;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.com.cc.brand.cli.api.BrandCliCommands;

public class BrandCliCommandsImpl implements BrandCliCommands {

    private static final Logger LOG = LoggerFactory.getLogger(BrandCliCommandsImpl.class);
    private final DataBroker dataBroker;

    public BrandCliCommandsImpl(final DataBroker db) {
        this.dataBroker = db;
        LOG.info("BrandCliCommandImpl initialized");
    }

    @Override
    public Object testCommand(Object testArgument) {
        return "This is a test implementation of test-command";
    }
}