package org.sonatype.aether.connector.async;

/*******************************************************************************
 * Copyright (c) 2010-2011 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * The Apache License v2.0 is available at
 *   http://www.apache.org/licenses/LICENSE-2.0.html
 * You may elect to redistribute this code under either of these licenses.
 *******************************************************************************/

import org.junit.Before;
import org.junit.runner.RunWith;
import org.sonatype.aether.repository.Authentication;
import org.sonatype.tests.http.runner.annotations.ConfiguratorList;
import org.sonatype.tests.http.runner.junit.ConfigurationRunner;

/**
 * @author Benjamin Hanzelmann
 */
@RunWith( ConfigurationRunner.class )
@ConfiguratorList( "AuthSuiteConfigurator.list" )
public class AuthPutTest
    extends PutTest
{

    @Before
    @Override
    public void before()
        throws Exception
    {
        super.before();

        repository().setAuthentication( new Authentication( "user", "password" ) );

    }

}
