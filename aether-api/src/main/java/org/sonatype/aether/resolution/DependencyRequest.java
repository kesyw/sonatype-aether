package org.sonatype.aether.resolution;

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

import org.sonatype.aether.RepositorySystem;
import org.sonatype.aether.RepositorySystemSession;
import org.sonatype.aether.RequestTrace;
import org.sonatype.aether.artifact.Artifact;
import org.sonatype.aether.collection.CollectRequest;
import org.sonatype.aether.graph.DependencyFilter;
import org.sonatype.aether.graph.DependencyNode;

/**
 * A request to resolve transitive dependencies. This request can either be supplied with a {@link CollectRequest} to
 * calculate the transitive dependencies or with an already resolved dependency graph.
 * 
 * @author Benjamin Bentmann
 * @see RepositorySystem#resolveDependencies(RepositorySystemSession, DependencyRequest)
 * @see Artifact#getFile()
 */
public class DependencyRequest
{

    private DependencyNode root;

    private CollectRequest collectRequest;

    private DependencyFilter filter;

    private RequestTrace trace;

    /**
     * Creates an uninitialized request. Note that either {@link #setRoot(DependencyNode)} or
     * {@link #setCollectRequest(CollectRequest)} must eventually be called to create a valid request.
     */
    public DependencyRequest()
    {
        // enables default constructor
    }

    /**
     * Creates a request for the specified dependency graph and with the given resolution filter.
     * 
     * @param node The root node of the dependency graph whose artifacts should be resolved, may be {@code null}.
     * @param filter The resolution filter to use, may be {@code null}.
     */
    public DependencyRequest( DependencyNode node, DependencyFilter filter )
    {
        setRoot( node );
        setFilter( filter );
    }

    /**
     * Creates a request for the specified collect request and with the given resolution filter.
     * 
     * @param request The collect request used to calculate the dependency graph whose artifacts should be resolved, may
     *            be {@code null}.
     * @param filter The resolution filter to use, may be {@code null}.
     */
    public DependencyRequest( CollectRequest request, DependencyFilter filter )
    {
        setCollectRequest( request );
        setFilter( filter );
    }

    /**
     * Gets the root node of the dependency graph whose artifacts should be resolved.
     * 
     * @return The root node of the dependency graph or {@code null} if none.
     */
    public DependencyNode getRoot()
    {
        return root;
    }

    /**
     * Sets the root node of the dependency graph whose artifacts should be resolved. When this request is processed,
     * the nodes of the given dependency graph will be updated to refer to the resolved artifacts. Eventually, either
     * {@link #setRoot(DependencyNode)} or {@link #setCollectRequest(CollectRequest)} must be called to create a valid
     * request.
     * 
     * @param root The root node of the dependency graph, may be {@code null}.
     * @return This request for chaining, never {@code null}.
     */
    public DependencyRequest setRoot( DependencyNode root )
    {
        this.root = root;
        return this;
    }

    /**
     * Gets the collect request used to calculate the dependency graph whose artifacts should be resolved.
     * 
     * @return The collect request or {@code null} if none.
     */
    public CollectRequest getCollectRequest()
    {
        return collectRequest;
    }

    /**
     * Sets the collect request used to calculate the dependency graph whose artifacts should be resolved. Eventually,
     * either {@link #setRoot(DependencyNode)} or {@link #setCollectRequest(CollectRequest)} must be called to create a
     * valid request. If this request is supplied with a dependency node via {@link #setRoot(DependencyNode)}, the
     * collect request is ignored.
     * 
     * @param collectRequest The collect request, may be {@code null}.
     * @return This request for chaining, never {@code null}.
     */
    public DependencyRequest setCollectRequest( CollectRequest collectRequest )
    {
        this.collectRequest = collectRequest;
        return this;
    }

    /**
     * Gets the resolution filter used to select which artifacts of the dependency graph should be resolved.
     * 
     * @return The resolution filter or {@code null} to resolve all artifacts of the dependency graph.
     */
    public DependencyFilter getFilter()
    {
        return filter;
    }

    /**
     * Sets the resolution filter used to select which artifacts of the dependency graph should be resolved. For
     * example, use this filter to restrict resolution to dependencies of a certain scope.
     * 
     * @param filter The resolution filter, may be {@code null} to resolve all artifacts of the dependency graph.
     * @return This request for chaining, never {@code null}.
     */
    public DependencyRequest setFilter( DependencyFilter filter )
    {
        this.filter = filter;
        return this;
    }

    /**
     * Gets the trace information that describes the higher level request/operation in which this request is issued.
     * 
     * @return The trace information about the higher level operation or {@code null} if none.
     */
    public RequestTrace getTrace()
    {
        return trace;
    }

    /**
     * Sets the trace information that describes the higher level request/operation in which this request is issued.
     * 
     * @param trace The trace information about the higher level operation, may be {@code null}.
     * @return This request for chaining, never {@code null}.
     */
    public DependencyRequest setTrace( RequestTrace trace )
    {
        this.trace = trace;
        return this;
    }

    @Override
    public String toString()
    {
        if ( root != null )
        {
            return String.valueOf( root );
        }
        return String.valueOf( collectRequest );
    }

}
