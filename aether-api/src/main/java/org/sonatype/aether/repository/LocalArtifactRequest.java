package org.sonatype.aether.repository;

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

import java.util.Collections;
import java.util.List;

import org.sonatype.aether.RepositorySystemSession;
import org.sonatype.aether.artifact.Artifact;

/**
 * A query to the local repository for the existence of an artifact.
 * 
 * @author Benjamin Bentmann
 * @see LocalRepositoryManager#find(RepositorySystemSession, LocalArtifactRequest)
 */
public class LocalArtifactRequest
{

    private Artifact artifact;

    private String context = "";

    private List<RemoteRepository> repositories = Collections.emptyList();

    /**
     * Creates an uninitialized query.
     */
    public LocalArtifactRequest()
    {
        // enables default constructor
    }

    /**
     * Creates a query with the specified properties.
     * 
     * @param artifact The artifact to query for, may be {@code null}.
     * @param repositories The remote repositories that should be considered as potential sources for the artifact, may
     *            be {@code null}.
     * @param context The resolution context for the artifact, may be {@code null}.
     */
    public LocalArtifactRequest( Artifact artifact, List<RemoteRepository> repositories, String context )
    {
        setArtifact( artifact );
        setRepositories( repositories );
        setContext( context );
    }

    /**
     * Gets the artifact to query for.
     * 
     * @return The artifact or {@code null} if not set.
     */
    public Artifact getArtifact()
    {
        return artifact;
    }

    /**
     * Sets the artifact to query for.
     * 
     * @param artifact The artifact, may be {@code null}.
     * @return This query for chaining, never {@code null}.
     */
    public LocalArtifactRequest setArtifact( Artifact artifact )
    {
        this.artifact = artifact;
        return this;
    }

    /**
     * Gets the resolution context.
     * 
     * @return The resolution context, never {@code null}.
     */
    public String getContext()
    {
        return context;
    }

    /**
     * Sets the resolution context.
     * 
     * @param context The resolution context, may be {@code null}.
     * @return This query for chaining, never {@code null}.
     */
    public LocalArtifactRequest setContext( String context )
    {
        this.context = ( context != null ) ? context : "";
        return this;
    }

    /**
     * Gets the remote repositories to consider as sources of the artifact.
     * 
     * @return The remote repositories, never {@code null}.
     */
    public List<RemoteRepository> getRepositories()
    {
        return repositories;
    }

    /**
     * Sets the remote repositories to consider as sources of the artifact.
     * 
     * @param repositories The remote repositories, may be {@code null}.
     * @return This query for chaining, never {@code null}.
     */
    public LocalArtifactRequest setRepositories( List<RemoteRepository> repositories )
    {
        if ( repositories != null )
        {
            this.repositories = repositories;
        }
        else
        {
            this.repositories = Collections.emptyList();
        }
        return this;
    }

    @Override
    public String toString()
    {
        return getArtifact() + " @ " + getRepositories();
    }

}
