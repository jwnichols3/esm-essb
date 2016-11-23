import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class GenerateFromTemplates
{
	private static String config_template              = null;
	private static Properties properties_common        = null;
	private static Properties properties_template_keys = null;
	private static Properties properties_template_vals = null;
	private static String arguments[]                  = null;
	private static Vector <String> keys                = new Vector <String> ();

	public static void main ( String args[] ) throws IOException
	{
		readPropertiesCommon();

		generateForJBoss();
	}

	private static void generateForJBoss() throws IOException
	{
		readPropertiesJBoss();

		String filenames[] = 
		{
			"database.xml",
			"forums-pages.xml",
			"jboss-portlet.xml",
			"lvb-digester-rules.xml",
			"portlet.xml",
			"portlet-instances.xml",
			"server-types.xml",
			"struts-config.xml",
			"struts-portlet-config.xml",
			"validation.xml",
			"web.xml",
            "deploy.bat"
		};

		for ( int counter = 0; counter < filenames.length; counter++ )
		{
			System.out.println ( "Processing filename: " + filenames[counter] );
			getTemplate ( "WEB-INF/classes/templates/jboss/" + filenames[counter] );
			saveTemplate ( "WEB-INF/" + filenames[counter] );
		}

		String files_to_copy[] =
		{
			"app.tld",
			"struts-config-registration.xml",
			"validator-rules.xml"
		};

		for ( int counter = 0; counter < files_to_copy.length; counter++ )
		{
			System.out.println ( "Copying file: " + files_to_copy[counter] );
			copyFile ( "WEB-INF/classes/templates/jboss/" + files_to_copy[counter], "WEB-INF/" + files_to_copy[counter] );
		}

		for ( int counter = 0; counter < filenames.length; counter++ )
		{
			System.out.print ( "Processing filename: " + filenames[counter] );
			System.out.print ( "...\t" );
			if ( doFilesMatch ( "WEB-INF/classes/correctness/jboss/" + filenames[counter], "WEB-INF/" + filenames[counter] ) )
			{
				System.out.println ( "pass!" );
			}
			else
			{
				System.out.println ( "FAILED!" );
			}
		}

		for ( int counter = 0; counter < files_to_copy.length; counter++ )
		{
			System.out.print ( "Processing filename: " + files_to_copy[counter] );
			System.out.print ( "...\t" );
			if ( doFilesMatch ( "WEB-INF/classes/correctness/jboss/" + files_to_copy[counter], "WEB-INF/" + files_to_copy[counter] ) )
			{
				System.out.println ( "pass!" );
			}
			else
			{
				System.out.println ( "FAILED!" );
			}
		}
        //*/
	}

	private static void readPropertiesCommon() throws IOException
	{
		if ( null == properties_common )
		{
            ClassLoader cl    = new URLClassLoader ( new URL[0] );
            InputStream is    = cl.getResourceAsStream ( "Common.properties" );
            properties_common = new Properties();
            properties_common.load ( is );
		}
	}

	private static void readPropertiesJBoss() throws IOException
	{
		if ( null == properties_template_vals )
		{
            ClassLoader cl           = new URLClassLoader ( new URL[0] );
            InputStream is           = cl.getResourceAsStream ( "templates/jboss_vals.properties" );
            properties_template_vals = new Properties();
            properties_template_vals.load ( is );
		}

		if ( null == properties_template_keys )
		{
            ClassLoader cl           = new URLClassLoader ( new URL[0] );
            InputStream is           = cl.getResourceAsStream ( "templates/jboss_keys.properties" );
            properties_template_keys = new Properties();
            properties_template_keys.load ( is );
		}

		Enumeration property_names = properties_template_keys.propertyNames();
		String temp_string         = null;
		keys.clear();

		while ( property_names.hasMoreElements() )
		{
			temp_string = property_names.nextElement().toString();

			keys.add ( temp_string );
		}

		generateArgumentArray();
	}

	private static void generateArgumentArray()
	{
		String temp_string = null;
		int index          = 0;
		arguments          = new String[keys.size()];

		System.out.println ( "Num arguments: " + keys.size() );
		System.out.println ( "Argument array: " );
		for ( int counter = 0; counter < keys.size(); counter++ )
		{
			temp_string = keys.elementAt ( counter );

			System.out.println ( "\t" + temp_string );

			index = Integer.parseInt ( properties_template_keys.getProperty ( temp_string ) );

			arguments[index] = properties_template_vals.getProperty ( temp_string );
		}
	}

	private static void getTemplate ( String filename ) throws IOException
	{
		FileInputStream infile   = new FileInputStream ( filename );
		byte file_contents[]     = new byte[infile.available()];
		infile.read ( file_contents );
		infile.close();

		config_template = new String ( file_contents );
	}

	private static String replace ( String data, String token, String replacement )
	{
		return data.replaceAll ( token, replacement );
	}

	private static void saveTemplate ( String filename ) throws IOException
	{
		String results = config_template;
		String temp_string1 = null;
		for ( int counter = 0; counter < arguments.length; counter++ )
		{
			temp_string1 = "\\{" + counter + "\\}";

			results = replace ( results, temp_string1, arguments[counter] );
		}

		FileOutputStream outfile = new FileOutputStream ( filename );
			outfile.write ( results.getBytes() );
		outfile.close();
	}

	private static void copyFile ( String file1, String file2 ) throws IOException
	{
		FileInputStream infile   = new FileInputStream ( file1 );
		byte file_contents[]     = new byte[infile.available()];
		infile.read ( file_contents );
		infile.close();

		FileOutputStream outfile = new FileOutputStream ( file2 );
			outfile.write ( file_contents );
		outfile.close();
	}

	private static boolean doFilesMatch ( String file1, String file2 ) throws IOException
	{
		FileInputStream infile   = null;
		byte file_contents[]     = null;
		String contents1         = null;
		String contents2         = null;
        FileOutputStream outfile = null;

        /*
        System.out.print ( "Comparing " );
        System.out.print ( file1 );
        System.out.print ( " to " );
        System.out.println ( file2 );
        //*/

		infile                   = new FileInputStream ( file1 );
		file_contents            = new byte[infile.available()];
		contents1                = new String ( file_contents );
		infile.close();

		infile                   = new FileInputStream ( file2 );
		file_contents            = new byte[infile.available()];
		contents2                = new String ( file_contents );
		infile.close();

        if ( contents1.equals ( contents2 ) )
        {
            return true;
        }
        else
        {

            outfile = new FileOutputStream ( "c:\\test\\test1" );
            outfile.write ( contents1.getBytes() );
            outfile.close();

            outfile = new FileOutputStream ( "c:\\test\\test2" );
            outfile.write ( contents2.getBytes() );
            outfile.close();
            
            return false;
        }
		//return contents1.equals ( contents2 );
	}
};
