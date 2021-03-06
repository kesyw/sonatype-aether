package org.sonatype.aether.impl.internal;

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.sonatype.aether.artifact.Artifact;
import org.sonatype.aether.graph.DependencyNode;
import org.sonatype.aether.repository.RemoteRepository;

/**
 * @author Benjamin Bentmann
 */
class GraphNode
{

    private DependencyNode ingoingEdge;

    private List<DependencyNode> outgoingEdges = new ArrayList<DependencyNode>( 0 );

    private Collection<Artifact> aliases = Collections.emptyList();

    private List<RemoteRepository> repositories = Collections.emptyList();

    public DependencyNode getIngoingEdge()
    {
        return ingoingEdge;
    }

    public void setIngoingEdge(DependencyNode ingoingEdge)
    {
        this.ingoingEdge = ingoingEdge;
    }
    
    public List<DependencyNode> getOutgoingEdges()
    {
        return outgoingEdges;
    }

    public List<RemoteRepository> getRepositories()
    {
        return repositories;
    }

    public void setRepositories( List<RemoteRepository> repositories )
    {
        if ( repositories == null || repositories.isEmpty() )
        {
            this.repositories = Collections.emptyList();
        }
        else
        {
            this.repositories = repositories;
        }
    }

    public Collection<Artifact> getAliases()
    {
        return aliases;
    }

    public void setAliases( Collection<Artifact> aliases )
    {
        if ( aliases == null || aliases.isEmpty() )
        {
            this.aliases = Collections.emptyList();
        }
        else
        {
            this.aliases = aliases;
        }
    }

    @Override
    public String toString()
    {
        return String.valueOf( getOutgoingEdges() );
    }

}
