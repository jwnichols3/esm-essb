package tools.struts;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;

import tools.databases.Database;
import tools.databases.Table;
import tools.struts.Common;

public class StrutsConfig
{
    //  Common public variables
    public static String STRUTS_FORWARD_SUCCESS      = "success";
    public static String STRUTS_FORWARD_FAILURE      = "failure";

    //  External data 
    private Properties struts_properties             = null;
    private Database database                        = null;
    private Vector <Table> tables                    = null;
	private Vector <String> table_names              = null;

	//  Configuration
	private String directory_forms                   = null;
	private String directory_html_forms_out          = null;

    //  Internal data
    private HashMap <String, String> form_beans_edit = null;
    private HashMap <String, String> form_beans_add  = null;
    private int max_length_form_bean_name            = 0;

    /**
     * The main function - used only for debugging purposes.
     */
    public static void main ( String args[] ) throws SQLException, IOException, ClassNotFoundException
    {
        Database database          = new Database();
        StrutsConfig struts_config = new StrutsConfig ( database );
		struts_config.BuildFile ( "c:\\test\\struts-config.xml" );
    }

    /**
     * Constructor that initializes this object with the necessary information about the
     * relevant database
     *
     * @param new_database the Database object that encapsulates the relevant database
     */
    public StrutsConfig ( Database new_database ) throws IOException
    {
        database = new_database;
        tables   = database.getTables();

        initialize();
    }

    /**
     * Organizes and prepares the data to build the &ldquo;struts-config.xml&rdquo; file
     */
    public void initialize() throws IOException
    {
        String table_name        = null;
        Table table              = null;
        int counter              = 0;

		//  Retrieve properties from properties files	
        struts_properties        = Common.getStrutsToolsProperties();
        
		//  Retrieving the package names
		directory_forms          = struts_properties.getProperty ( "directory.forms" );
		
		//  Retrieving the output directories
		directory_html_forms_out = struts_properties.getProperty ( "directory.html.forms.out" );

        form_beans_edit          = new HashMap <String, String> ();
        form_beans_add           = new HashMap <String, String> ();
		table_names              = new Vector <String> ();
        for ( counter = 0; counter < tables.size(); counter++ )
        {
            table          = tables.elementAt ( counter );
            table_name     = table.getName();
			table_names.add ( table_name );
            
            form_beans_add.put  ( table_name, Common.formatAddFormName ( table )  );
            form_beans_edit.put ( table_name, Common.formatEditFormName ( table ) );
        }
    }

    /**
     * Creates and saves the &ldquo;struts-config.xml&rdquo; file
     *
     * @param filename the relative path from PORTLET_ROOT to the filename
     */
    public void BuildFile ( String filename ) throws IOException
    {
        FileOutputStream outfile = new FileOutputStream ( filename   );
			outfile.write ( createFormBeansSection().getBytes()      );
			outfile.write ( "\n\n".getBytes() );
			outfile.write ( createActionMappings().getBytes()         );
			outfile.write ( "\n\n".getBytes() );
    		outfile.write ( getMessageResources().getBytes()         );
			outfile.write ( "\n\n".getBytes() );
			outfile.write ( getPluginConfigForTiles().getBytes()     );
			outfile.write ( "\n\n".getBytes() );
			outfile.write ( getPluginConfigForValidator().getBytes() );
        outfile.close();
    }

	/**
     * Creates the &lt;action-mappings&gt; section of the &ldquo;struts-config.xml&rdquo; file.
	 *
	 * @return the &lt;action-mappings&gt; section of the &ldquo;struts-config.xml&rdquo; file.
	 */
    private String createActionMappings()
    {
        StringBuilder contents = new StringBuilder();
        String table_name      = null;
		Enumeration <String> e = table_names.elements();
		int length             = database.getMaxLengthTableName() + 5;

        length = (length > max_length_form_bean_name)? length : max_length_form_bean_name;

        contents.append ( "    <action-mappings>\n" );
		while ( e.hasMoreElements() )
		{
			table_name = e.nextElement();

			//  The starter view page for each table
			contents.append ( "        <action path=\"/" );
			contents.append ( table_name );
			contents.append ( "_view\"" );
			contents.append ( Common.formatStringToLength ( "", length-table_name.length() ) );
			contents.append ( " include=\"/" );
            contents.append ( directory_html_forms_out );
			contents.append ( "/" );
			contents.append ( table_name );
			contents.append ( "_view.jsp\"/>\n" );

            //  The add action

		}

        contents.append ( "    </action-mappings>\n" );

        /*
      <action    path="/EditSubscription" type="org.apache.struts.webapp.example.EditSubscriptionAction" name="SubscriptionForm" scope="request" validate="false">
        <forward name="failure"              path="/MainMenu.do" redirect="true"/>
        <forward name="success"              path="/html/subscription.jsp"/>
      </action>
      //*/
      //
        return contents.toString();
    }

    /**
     * Creates the &lt;form-bean&gt; section of the &ldquo;struts-config.xml&rdquo; file.
     *
     * @return the &lt;form-bean&gt; section of the &ldquo;struts-config.xml&rdquo; file.
     */
    private String createFormBeansSection()
    {
        StringBuilder content     = new StringBuilder();
		String form_name          = null;
		String table_name         = null;
		Enumeration <String> e    = table_names.elements();
		max_length_form_bean_name = database.getMaxLengthTableName() + 8;
		
        content.append ( "    <form-beans>\n" );
		while ( e.hasMoreElements() )
		{
			table_name = e.nextElement();

			form_name  = form_beans_add.get ( table_name );
			content.append ( "        <form-bean name=\"" );
			content.append ( form_name );
			content.append ( "\"" );
			content.append ( Common.formatStringToLength ( "", max_length_form_bean_name-form_name.length() ) );
			content.append ( " type=\"" );
			content.append ( directory_forms );
			content.append ( "." );
			content.append ( form_name );
			content.append ( "\">\n" );

			form_name  = form_beans_edit.get ( table_name );
			content.append ( "        <form-bean name=\"" );
			content.append ( form_name );
			content.append ( "\"" );
			content.append ( Common.formatStringToLength ( "", max_length_form_bean_name-form_name.length() ) );
			content.append ( " type=\"" );
			content.append ( directory_forms );
			content.append ( "." );
			content.append ( form_name );
			content.append ( "\">\n" );

			if ( e.hasMoreElements() )
			{
				content.append ( "\n" );
			}
		}
        content.append ( "    </form-beans>\n" );

        return content.toString();
    }

    /**
     * Creates the part of the &ldquo;struts-config.xml&rdquo; file that reads in the message resources
     *
     * @return the part of the &ldquo;struts-config.xml&rdquo; file that reads in the message resources
     */
    private static String getMessageResources()
    {
        StringBuilder content = new StringBuilder();
        content.append ( "      <!-- Message Resources -->\n" );
        content.append ( "	    <message-resources key=\"databases\" parameter=\"content.databases\" />\n" );
        content.append ( "	    <message-resources key=\"messages\"  parameter=\"content.Messages\" />\n" );

        return content.toString();
    }

    /**
     * Creates the part of the &ldquo;struts-config.xml&rdquo; file that configures the Tiles plugin
     *
     * @return the part of the &ldquo;struts-config.xml&rdquo; file that configures the Tiles plugin
     */
    private static String getPluginConfigForTiles()
    {
        StringBuilder content = new StringBuilder();
        content.append ( "    <!-- Tiles Plugin -->\n" );
        content.append ( "    <plug-in className=\"org.apache.struts.tiles.TilesPlugin\" >\n" );
        content.append ( "        <set-property property=\"definitions-config\" value=\"/WEB-INF/tiles-defs.xml\" />\n" );
        content.append ( "        <set-property property=\"moduleAware\" value=\"true\" />\n" );
        content.append ( "        <set-property property=\"definitions-parser-validate\" value=\"true\" />\n" );
        content.append ( "    </plug-in>\n" );

        return content.toString();
    }

    /**
     * Creates the part of the &ldquo;struts-config.xml&rdquo; file that configures the Struts Validator plugin
     *
     * @return the part of the &ldquo;struts-config.xml&rdquo; file that configures the Struts Validator plugin
     */
    private static String getPluginConfigForValidator()
    {
        StringBuilder content = new StringBuilder();
        content.append ( "    <!-- Validator Plugin -->\n" );
        content.append ( "    <plug-in className=\"org.apache.struts.validator.ValidatorPlugIn\">\n" );
        content.append ( "        <set-property property=\"pathnames\" value=\"/WEB-INF/validator-rules.xml,/WEB-INF/validation.xml\" />\n" );
        content.append ( "    </plug-in>\n" );

        return content.toString();
    }


    
}
