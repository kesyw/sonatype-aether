package org.sonatype.aether.util;

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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.sonatype.aether.RepositoryCache;
import org.sonatype.aether.RepositorySystemSession;

/**
 * A simplistic repository cache backed by a {@link ConcurrentHashMap}. The simplistic nature of this cache makes it
 * only suitable for use with short-lived repository system sessions.
 * 
 * @author Benjamin Bentmann
 */
public class DefaultRepositoryCache
    implements RepositoryCache
{

    private Map<Object, Object> cache = new ConcurrentHashMap<Object, Object>( 256 );

    public Object get( RepositorySystemSession session, Object key )
    {
        return cache.get( key );
    }

    public void put( RepositorySystemSession session, Object key, Object data )
    {
        if ( data != null )
        {
            cache.put( key, data );
        }
    }

}
