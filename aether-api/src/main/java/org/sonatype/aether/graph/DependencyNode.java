package org.sonatype.aether.graph;

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

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.sonatype.aether.artifact.Artifact;
import org.sonatype.aether.repository.RemoteRepository;
import org.sonatype.aether.version.Version;
import org.sonatype.aether.version.VersionConstraint;

/**
 * A node within a dependency graph. <em>Note:</em> When traversing a dirty graph, i.e. a graph which hasn't undergone
 * conflict resolution, there can be multiple path leading to the same node instance.
 * 
 * @author Benjamin Bentmann
 */
public interface DependencyNode
{
    /**
     * Gets the parent node of this node.
     * 
     * @return The parent node of this node or {@code null} if root node.
     */
    DependencyNode getParent();

    /**
     * Gets the child nodes of this node.
     * 
     * @return The child nodes of this node, never {@code null}.
     */
    List<DependencyNode> getChildren();

    /**
     * Gets the dependency associated with this node. <em>Note:</em> For dependency graphs that have been constructed
     * without a root dependency, the root node will not have a dependency associated with it.
     * 
     * @return The dependency or {@code null} if none.
     */
    Dependency getDependency();

    /**
     * Sets the artifact of the dependency.
     * 
     * @param artifact The artifact satisfying the dependency, must not be {@code null}.
     */
    void setArtifact( Artifact artifact );

    /**
     * Gets the sequence of relocations that was followed to resolve the artifact referenced by the dependency.
     * 
     * @return The (read-only) sequence of relocations, never {@code null}.
     */
    List<Artifact> getRelocations();

    /**
     * Gets the known aliases for this dependency's artifact. An alias can be used to mark a patched rebuild of some
     * other artifact as such, thereby allowing conflict resolution to consider the patched and the original artifact as
     * a conflict.
     * 
     * @return The (read-only) set of known aliases, never {@code null}.
     */
    Collection<Artifact> getAliases();

    /**
     * Gets the version constraint that was parsed from the dependency's version declaration.
     * 
     * @return The version constraint for this node or {@code null}.
     */
    VersionConstraint getVersionConstraint();

    /**
     * Gets the version that was selected for the dependency's target artifact.
     * 
     * @return The parsed version or {@code null}.
     */
    Version getVersion();

    /**
     * Sets the scope of the dependency.
     * 
     * @param scope The scope, may be {@code null}.
     */
    void setScope( String scope );

    /**
     * Gets the version or version range for the dependency before dependency management was applied (if any).
     * 
     * @return The dependency version before dependency management or {@code null} if the version was not managed.
     */
    String getPremanagedVersion();

    /**
     * Gets the scope for the dependency before dependency management was applied (if any).
     * 
     * @return The dependency scope before dependency management or {@code null} if the scope was not managed.
     */
    String getPremanagedScope();

    /**
     * Gets the remote repositories from which this node's artifact shall be resolved.
     * 
     * @return The (read-only) list of remote repositories to use for artifact resolution, never {@code null}.
     */
    List<RemoteRepository> getRepositories();

    /**
     * Gets the request context in which this dependency node was created.
     * 
     * @return The request context, never {@code null}.
     */
    String getRequestContext();

    /**
     * Sets the request context in which this dependency node was created.
     * 
     * @param context The context, may be {@code null}.
     */
    void setRequestContext( String context );

    /**
     * Gets the custom data associated with this dependency node. Clients of the repository system can use this data to
     * annotate dependency nodes with domain-specific information.
     * 
     * @return The (read-only) key-value mappings, never {@code null}.
     */
    Map<Object, Object> getData();

    /**
     * Associates the specified dependency node data with the given key. <em>Note:</em> This method must not be called
     * while {@link #getData()} is being iterated.
     * 
     * @param key The key under which to store the data, must not be {@code null}.
     * @param value The data to associate with the key, may be {@code null} to remove the mapping.
     */
    void setData( Object key, Object value );

    /**
     * Traverses this node and potentially its children using the specified visitor.
     * 
     * @param visitor The visitor to call back, must not be {@code null}.
     * @return {@code true} to visit siblings nodes of this node as well, {@code false} to skip siblings.
     */
    boolean accept( DependencyVisitor visitor );

}
