package tools.struts.view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import tools.databases.Column;
import tools.databases.Database;
import tools.databases.Table;
import tools.struts.Common;

public class HTMLForm
{
    private Table table                  = null;
	private String prop_header           = null;
	private String table_name            = null;
	private String database_name         = null;
	private String portlet_name          = null;
	private String output_directory      = null;
	private String db_name               = null;
	private String db_driver             = null;
	private String db_connection_url     = null;
	private String db_username           = null;
	private String db_password           = null;
	private String html_form_header      = null;
	private String html_form_footer      = null;
	private Properties properties        = null;
	private Properties form_properties   = null;
	private Properties common_properties = null;
    
    public String getPortletName()
    {
        return portlet_name;
    }
    
    public String getDBName()
    {
        return db_name;
    }
    
    public String getDBDriver()
    {
        return db_driver;
    }
    
    public String getDBConnectionURL()
    {
        return db_connection_url;
    }
    
    public String getDBUsername()
    {
        return db_username;
    }
    
    public String getDBPassword()
    {
        return db_password;
    }

    /**
     * The main function - used only for debugging purposes.
     */
    public static void main ( String args[] ) throws IOException, SQLException, ClassNotFoundException
    {
        Database database     = new Database();
        Vector <Table> tables = database.getTables();
        Table table           = tables.elementAt ( 1 );
        HTMLForm html_form    = new HTMLForm ( table );

        html_form.BuildAddForm();
        html_form.BuildEditForm();
    }

	/**
	 * Initializes the HTMLForm object with the Table object that a form will be created for.
	 *
	 * @param new_table The table object that a form will be created for
	 */
    public HTMLForm ( Table new_table )
    {
		table         = new_table;
        database_name = table.getDBName();
		table_name    = table.getName();

        initialize();
    }

	/**
	 * Organizes and prepares the data to build the HTML form.
	 */
    private void initialize()
    {
    }
	/**
	 * Create and save the "Add" version of the form
	 *
	 * @param filename the path relative to PORLET_ROOT where the HTML form file will reside
	 */
    public void BuildAddForm() throws IOException, SQLException, ClassNotFoundException
    {
        BuildForm ( table.getName() + "_add_success", Common.formatAddFormName ( table ), false );
    }

	/**
	 * Create and save the "Edit" version of the form
	 *
	 * @param filename the path relative to PORLET_ROOT where the HTML form file will reside
	 */
    public void BuildEditForm() throws IOException, SQLException, ClassNotFoundException
    {
        BuildForm ( table.getName() + "_edit_success", Common.formatEditFormName ( table ), true );
    }

	/**
	 * Create and save the form
	 *
	 * @param forward_name the forwarding Struts path for successful entry of the form
     * @param form_name    the name of the Struts form
     * @param is_edit      whether or not to build the edit version of the form.
	 */
    private void BuildForm ( String forward_name, String form_name, boolean is_edit )
        throws IOException, SQLException, ClassNotFoundException
    {
        Enumeration <Column> e = null;
        Column column          = null;
        String token           = null;
        
        readProperties();

		table.initializeFromDatabase();

        StringBuilder file = new StringBuilder ();

        file.append ( html_form_header );
        file.append ( "    <font class=\"portlet-font\" style=\"font-size: x-small;\">\n" );
        file.append ( "        <html:form action=\"/" );
        file.append ( output_directory );
        file.append ( "/" );
        file.append ( forward_name );
        file.append ( "?actionURL=true\" method=\"post\" focus=\"\" onsubmit=\"submitForm(this); return false;\">\n" );
        file.append ( "            <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\"/>\n" );

        e = table.getAllColumns().elements();
        while ( e.hasMoreElements() )
        {
            column = e.nextElement();
            token  = column.getName();

            file.append ( "                <tr>\n" );
            file.append ( "                    <td><font class=\"portlet-font\" style=\"font-size: x-small;\">" );
            file.append ( "<bean:message bundle=\"databases\" key=\"databases." );
            file.append ( database_name );
            file.append ( "." );
            file.append ( table_name );
            file.append ( "." );
            file.append ( token );
            file.append ( ".name\"/>" );
            file.append ( ":</font></td>\n" );
            file.append ( "                    <td>" );


            if (( token.indexOf ( "is" ) == 0 ) || ( token.indexOf ( "has" ) == 0 ))
            {
                file.append ( "<html:checkbox name=\"" );
                file.append ( form_name );
                file.append ( "\" property=\"" );
                file.append ( column.getPropertyName() );
                file.append ( "\" />" );
            }
            else
            {
                file.append ( "<html:text name=\"" );
                file.append ( form_name );
                file.append ( "\" property=\"" );
                file.append ( column.getPropertyName() );
                file.append ( "\" size=\"20\"/>" );
            }
            file.append ( "</td>\n" );
            file.append ( "                </tr>\n" );
        }

        //  Submit button
        file.append ( "                <tr>\n" );
        if ( is_edit )
        {
            file.append ( "                    <td colspan=\"2\"><html:submit>Update Current <bean:message bundle=\"databases\" key=\"databases." );
        }
        else
        {
            file.append ( "                    <td colspan=\"2\"><html:submit>Add New <bean:message bundle=\"databases\" key=\"databases." );
        }
        file.append ( table.getDBName() );
        file.append ( "." );
        file.append ( table.getName() );
        file.append ( ".table.data_name\"/>" );
        file.append ( "</html:submit></center></td>\n" );
        file.append ( "                </tr>\n" );

        file.append ( "            </table>\n" );
        file.append ( "        </html:form>\n" );
        file.append ( "    </font>\n" );

        file.append ( html_form_footer );

        FileOutputStream outfile = null;

        if ( true == is_edit )
        {
            outfile = new FileOutputStream ( output_directory + "/" + table.getName()+ "_edit.jsp" );
                outfile.write ( file.toString().getBytes() );
            outfile.close();
        }
        else
        {
            outfile = new FileOutputStream ( output_directory + "/" + table.getName()+ "_add.jsp" );
                outfile.write ( file.toString().getBytes() );
            outfile.close();
        }
    }

	@SuppressWarnings("unused")
    private String getFieldName ( String column_name )
	{
		String token = (String) form_properties.get ( prop_header + column_name + ".name" );

		return ( null != token )? token : column_name;
	}

    private void readProperties() throws IOException
    {
		if ( null == form_properties )
		{
			form_properties = Common.getDatabaseProperties();
			/*
            ClassLoader cl    = new URLClassLoader ( new URL[0] );
            InputStream is    = cl.getResourceAsStream ( "content/databases.properties" );
            form_properties   = new Properties();
            form_properties.load ( is );
			//*/
			MessageFormat fp  = new MessageFormat ( "databases.{0}.{1}." );
            String args[]     = new String[2];
            args[0] = database_name;
            args[1] = table_name;
			prop_header       = fp.format ( args );
		}
        if ( null == properties ) 
        {
			properties = Common.getStrutsToolsProperties();
            html_form_header  = properties.getProperty ( "html.forms.header" );
            html_form_footer  = properties.getProperty ( "html.forms.footer" );
            output_directory  = properties.getProperty ( "directory.html.forms.out" );

            FileInputStream infile = null;
            byte file_contents[]   = null;
            
            infile = new FileInputStream ( html_form_header );
            file_contents = new byte[infile.available()];
            infile.read ( file_contents );
            infile.close();
            html_form_header = new String ( file_contents );
            html_form_header = html_form_header.replaceAll ( "\r", "" );

            infile = new FileInputStream ( html_form_footer );
            file_contents = new byte[infile.available()];
            infile.read ( file_contents );
            infile.close();
            html_form_footer = new String ( file_contents );
            html_form_footer = html_form_footer.replaceAll ( "\r", "" );
			//*/
        }

        if ( null == common_properties )
        {
			common_properties = Common.getCommonProperties();
            db_driver         = common_properties.getProperty ( "database.driver" );
            db_connection_url = common_properties.getProperty ( "database.connection_url" );
            db_username       = common_properties.getProperty ( "database.username" );
            db_password       = common_properties.getProperty ( "database.password" );
            portlet_name      = common_properties.getProperty ( "portlet.name" );
        }
    }
}
