package org.sonatype.aether.spi.locator;

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

/**
 * A stateless component of the repository system. The primary purpose of this interface is to provide a convenient
 * means to programmatically wire the several components of the repository system together when it is used outside of an
 * IoC container.
 * 
 * @author Benjamin Bentmann
 */
public interface Service
{

    /**
     * Provides the opportunity to initialize this service and to acquire other services for its operation from the
     * locator. A service must not save the reference to the provided service locator.
     * 
     * @param locator The service locator, must not be {@code null}.
     */
    void initService( ServiceLocator locator );

}
