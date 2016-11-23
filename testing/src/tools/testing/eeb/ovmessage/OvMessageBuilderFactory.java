package tools.testing.eeb.ovmessage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Properties;

public abstract class OvMessageBuilderFactory {
    public abstract OvMessageBuilder newOvMessageBuilder();

    private static String getFactoryClassNameFromPropertiesFile(String fileName, String propertyName) throws IOException 
    {
        String factoryClassName = null;

        InputStream in = OvMessageBuilderFactory.class.getResourceAsStream(fileName);

        if (in != null) {
            Properties properties = new Properties();

            try {
                properties.load(in); // Can throw IOException
            }
            finally {
                in.close(); // Can throw IOException
            }

            factoryClassName = properties.getProperty(propertyName);
        }

        return factoryClassName;
    }

    private static String getFactoryClassNameFromServiceFile(String fileName) throws IOException {
        assert fileName != null;

        String factoryClassName = null;

        InputStream in  = OvMessageBuilderFactory.class.getResourceAsStream("META-INF/services/" + fileName);

        if (in != null) {
            try {
                // InputStreamReader constructor can throw UnsupportedEncodingException, 
                // which derives from IOException
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                factoryClassName = reader.readLine();
            }
            finally {
                in.close();
                // Do I also need to close the two Readers?
            }
        }

        return factoryClassName;
    }

    public static OvMessageBuilderFactory newInstance() {
        final String FACTORY_PROPERTY = "tools.testing.eeb.ovmessage.OvMessageBuilderFactory";
        final String OVMESSAGE_PROPERTIES_FILE = "ovmessage.properties";
        final String DEFAULT_FACTORY = 
            "tools.testing.eeb.ovmessage.impl.jaxb.OvMessageBuilderFactoryJAXBImpl";

        String factoryClassName = System.getProperty(FACTORY_PROPERTY);

        if (factoryClassName == null) {

            try {
                factoryClassName = getFactoryClassNameFromPropertiesFile(OVMESSAGE_PROPERTIES_FILE, FACTORY_PROPERTY);
            }
            catch (IOException e) {
                // log exception
            }

            if (factoryClassName == null) {

                try {
                    factoryClassName = getFactoryClassNameFromServiceFile(FACTORY_PROPERTY);
                }
                catch (IOException e) {
                    // log exception
                }

                if (factoryClassName == null) {
                    factoryClassName = DEFAULT_FACTORY;
                }
            }
        }

        try {
            Class factoryClass = Class.forName(factoryClassName);
            return (OvMessageBuilderFactory) factoryClass.newInstance();
        }
        catch (ClassNotFoundException e) {
            throw new FactoryConfigurationError(e);
        }
        catch (InstantiationException e) {
            throw new FactoryConfigurationError(e);
        }
        catch (IllegalAccessException e) {
            throw new FactoryConfigurationError(e);
        }
    }
}
