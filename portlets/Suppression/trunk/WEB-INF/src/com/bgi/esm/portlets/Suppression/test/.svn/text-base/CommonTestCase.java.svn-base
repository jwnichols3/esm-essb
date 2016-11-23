package com.bgi.esm.portlets.Suppression.test;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

import com.bgi.esm.portlets.Suppression.Toolkit;

import junit.framework.TestCase;

public class CommonTestCase extends TestCase
{
    public CommonTestCase ( String param )
    {
        super ( param );
    }

    public Properties readCommonProperties() throws IOException
    {
        Properties common_properties = new Properties(); 
        Class c        = (new Toolkit()).getClass();
        ClassLoader cl = c.getClassLoader();
        InputStream is = cl.getResourceAsStream ( "portlet-common.properties" );
        common_properties.load ( is );

        return common_properties;
    }
};
