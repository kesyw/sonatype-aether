package org.sonatype.aether.spi.connector;

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

import org.sonatype.aether.RepositorySystemSession;
import org.sonatype.aether.repository.RemoteRepository;
import org.sonatype.aether.transfer.NoRepositoryConnectorException;

/**
 * A factory to create repository connectors. A repository connector is responsible for uploads/downloads to/from a
 * certain kind of remote repository. When the repository system needs a repository connector for a given remote
 * repository, it iterates the registered factories in descending order of their priority and calls
 * {@link #newInstance(RepositorySystemSession, RemoteRepository)} on them. The first connector returned by a factory
 * will then be used for the transfer.
 * 
 * @author Benjamin Bentmann
 */
public interface RepositoryConnectorFactory
{

    /**
     * Tries to create a repository connector for the specified remote repository. Typically, a factory will inspect
     * {@link RemoteRepository#getProtocol()} and {@link RemoteRepository#getContentType()} to determine whether it can
     * handle a repository.
     * 
     * @param session The repository system session from which to configure the connector, must not be {@code null}. In
     *            particular, a connector must notify any {@link RepositorySystemSession#getTransferListener()} set for
     *            the session and should obey the timeouts configured for the session.
     * @param repository The remote repository to create a connector for, must not be {@code null}.
     * @return The connector for the given repository, never {@code null}.
     * @throws NoRepositoryConnectorException If the factory cannot create a connector for the specified remote
     *             repository.
     */
    RepositoryConnector newInstance( RepositorySystemSession session, RemoteRepository repository )
        throws NoRepositoryConnectorException;

    /**
     * The priority of this factory. Factories with higher priority are preferred over those with lower priority.
     * 
     * @return The priority of this factory.
     */
    int getPriority();

}
