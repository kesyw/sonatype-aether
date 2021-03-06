package org.sonatype.aether.util.version;

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

import java.util.Locale;

import org.sonatype.aether.util.version.GenericVersion;
import org.sonatype.aether.version.Version;

import junit.framework.TestCase;

/**
 * Test ComparableVersion.
 * 
 * @author <a href="mailto:hboutemy@apache.org">Hervé Boutemy</a>
 */
public class GenericVersionTest
    extends TestCase
{

    private Version newComparable( String version )
    {
        return new GenericVersion( version );
    }

    private static final String[] VERSIONS_QUALIFIER =
        { "1-alpha2snapshot", "1-alpha2", "1-alpha-123", "1-beta-2", "1-beta123", "1-m2", "1-m11", "1-rc", "1-cr2",
            "1-rc123", "1-SNAPSHOT", "1", "1-sp", "1-sp2", "1-sp123", "1-abc", "1-def", "1-pom-1", "1-1-snapshot",
            "1-1", "1-2", "1-123" };

    private static final String[] VERSIONS_NUMBER =
        { "2.0", "2-1", "2.0.a", "2.0.0.a", "2.0.2", "2.0.123", "2.1.0", "2.1-a", "2.1b", "2.1-c", "2.1-1", "2.1.0.1",
            "2.2", "2.123", "11.a2", "11.a11", "11.b2", "11.b11", "11.m2", "11.m11", "11", "11.a", "11b", "11c", "11m" };

    private void checkVersionsOrder( String[] versions )
    {
        Version[] c = new Version[versions.length];
        for ( int i = 0; i < versions.length; i++ )
        {
            c[i] = newComparable( versions[i] );
        }

        for ( int i = 1; i < versions.length; i++ )
        {
            Version low = c[i - 1];
            for ( int j = i; j < versions.length; j++ )
            {
                Version high = c[j];
                assertTrue( "expected " + low + " < " + high, low.compareTo( high ) < 0 );
                assertTrue( "expected " + high + " > " + low, high.compareTo( low ) > 0 );
            }
        }
    }

    private void checkVersionsEqual( String v1, String v2 )
    {
        Version c1 = newComparable( v1 );
        Version c2 = newComparable( v2 );
        assertTrue( "expected " + v1 + " == " + v2, c1.compareTo( c2 ) == 0 );
        assertTrue( "expected " + v2 + " == " + v1, c2.compareTo( c1 ) == 0 );
        assertTrue( "expected same hashcode for " + v1 + " and " + v2, c1.hashCode() == c2.hashCode() );
        assertTrue( "expected " + v1 + ".equals( " + v2 + " )", c1.equals( c2 ) );
        assertTrue( "expected " + v2 + ".equals( " + v1 + " )", c2.equals( c1 ) );
    }

    private void checkVersionsOrder( String v1, String v2 )
    {
        Version c1 = newComparable( v1 );
        Version c2 = newComparable( v2 );
        assertTrue( "expected " + v1 + " < " + v2, c1.compareTo( c2 ) < 0 );
        assertTrue( "expected " + v2 + " > " + v1, c2.compareTo( c1 ) > 0 );
    }

    public void testVersionsQualifier()
    {
        checkVersionsOrder( VERSIONS_QUALIFIER );
    }

    public void testVersionsNumber()
    {
        checkVersionsOrder( VERSIONS_NUMBER );
    }

    public void testVersionsEqual()
    {
        checkVersionsEqual( "1", "1" );
        checkVersionsEqual( "1", "1.0" );
        checkVersionsEqual( "1", "1.0.0" );
        checkVersionsEqual( "1.0", "1.0.0" );
        checkVersionsEqual( "1", "1-0" );
        checkVersionsEqual( "1", "1.0-0" );
        checkVersionsEqual( "1.0", "1.0-0" );
        // no separator between number and character
        checkVersionsEqual( "1a", "1.a" );
        checkVersionsEqual( "1a", "1-a" );
        checkVersionsEqual( "1a", "1.0-a" );
        checkVersionsEqual( "1a", "1.0.0-a" );
        checkVersionsEqual( "1.0a", "1.0.a" );
        checkVersionsEqual( "1.0.0a", "1.0.0.a" );
        checkVersionsEqual( "1x", "1.x" );
        checkVersionsEqual( "1x", "1-x" );
        checkVersionsEqual( "1x", "1.0-x" );
        checkVersionsEqual( "1x", "1.0.0-x" );
        checkVersionsEqual( "1.0x", "1.0.x" );
        checkVersionsEqual( "1.0.0x", "1.0.0.x" );

        // aliases
        checkVersionsEqual( "1ga", "1" );
        checkVersionsEqual( "1final", "1" );
        checkVersionsEqual( "1cr", "1rc" );

        // special "aliases" a, b and m for alpha, beta and milestone
        checkVersionsEqual( "1a1", "1alpha1" );
        checkVersionsEqual( "1b2", "1beta2" );
        checkVersionsEqual( "1m3", "1milestone3" );

        // case insensitive
        checkVersionsEqual( "1X", "1x" );
        checkVersionsEqual( "1A", "1a" );
        checkVersionsEqual( "1B", "1b" );
        checkVersionsEqual( "1M", "1m" );
        checkVersionsEqual( "1Ga", "1" );
        checkVersionsEqual( "1GA", "1" );
        checkVersionsEqual( "1Final", "1" );
        checkVersionsEqual( "1FinaL", "1" );
        checkVersionsEqual( "1FINAL", "1" );
        checkVersionsEqual( "1Cr", "1Rc" );
        checkVersionsEqual( "1cR", "1rC" );
        checkVersionsEqual( "1m3", "1Milestone3" );
        checkVersionsEqual( "1m3", "1MileStone3" );
        checkVersionsEqual( "1m3", "1MILESTONE3" );
    }

    public void testVersionComparing()
    {
        checkVersionsOrder( "1", "2" );
        checkVersionsOrder( "1.5", "2" );
        checkVersionsOrder( "1", "2.5" );
        checkVersionsOrder( "1.0", "1.1" );
        checkVersionsOrder( "1.1", "1.2" );
        checkVersionsOrder( "1.0.0", "1.1" );
        checkVersionsOrder( "1.0.1", "1.1" );
        checkVersionsOrder( "1.1", "1.2.0" );

        checkVersionsOrder( "1.0-alpha-1", "1.0" );
        checkVersionsOrder( "1.0-alpha-1", "1.0-alpha-2" );
        checkVersionsOrder( "1.0-alpha-1", "1.0-beta-1" );

        checkVersionsOrder( "1.0-beta-1", "1.0-SNAPSHOT" );
        checkVersionsOrder( "1.0-SNAPSHOT", "1.0" );
        checkVersionsOrder( "1.0-alpha-1-SNAPSHOT", "1.0-alpha-1" );

        checkVersionsOrder( "1.0", "1.0-1" );
        checkVersionsOrder( "1.0-1", "1.0-2" );
        checkVersionsOrder( "1.0.0", "1.0-1" );

        checkVersionsOrder( "2.0-1", "2.0.1" );
        checkVersionsOrder( "2.0.1-klm", "2.0.1-lmn" );
        checkVersionsOrder( "2.0.1", "2.0.1-xyz" );

        checkVersionsOrder( "2.0.1", "2.0.1-123" );
        checkVersionsOrder( "2.0.1-xyz", "2.0.1-123" );

        checkVersionsOrder( "1.0-20101206.111434-1", "1.0-20101206.111435-1" );
        checkVersionsOrder( "1.0-20101206.111434-2", "1.0-20101206.111434-10" );
    }

    public void testLocaleIndependent()
    {
        Locale orig = Locale.getDefault();
        Locale[] locales = { Locale.ENGLISH, new Locale( "tr" ), Locale.getDefault() };
        try
        {
            for ( Locale locale : locales )
            {
                Locale.setDefault( locale );
                checkVersionsEqual( "1-abcdefghijklmnopqrstuvwxyz", "1-ABCDEFGHIJKLMNOPQRSTUVWXYZ" );
            }
        }
        finally
        {
            Locale.setDefault( orig );
        }
    }

}
