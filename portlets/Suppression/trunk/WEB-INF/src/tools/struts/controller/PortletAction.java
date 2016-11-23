package tools.struts.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Vector;

import tools.struts.Common;
import tools.databases.Table;
import tools.databases.Column;


public class PortletAction
{
    private Vector <String> primary_keys = new Vector <String> ();
    private String portlet_name          = null;
    private String action_name           = null;
    private String form_bean             = null;
    private String render_forward        = null;
    private String action_forward        = null;
    private Properties properties        = null;
    private Properties common_properties = null;
    private String output_directory      = null;
    private DecimalFormat df             = new DecimalFormat ( "###" );
    private Table table                  = null;

    private String package_actions       = null;
    private String package_forms         = null;
    private String package_toolkit       = null;

    private int max_col_name_length      = 0;
    private int max_pk_name_length       = 0;
    private boolean is_edit_action       = false;


    public static void main ( String args[] ) throws IOException, SQLException, ClassNotFoundException
    {
        if ( args.length != 6 )
        {
            System.out.println ( "Usage:\n" );
            System.out.println ( "\tjava PortletAction <table-name> <action-name> <form-bean-name> <render-forward> <action-forward> <primary-key-1> <primary-key-2>... <primary-key-n>\n\n" );
        }
        else
        {
            /*
            String table_name     = args[0];
            String action_name    = args[1];
            String form_bean      = args[2];
            String render_forward = args[3];
            String action_forward = args[4];

			PortletAction portlet_action = 
                new PortletAction ( table_name, action_name, form_bean, render_forward, action_forward );
			//*/
        }
    }

    public PortletAction ( Table new_table ) throws ClassNotFoundException, IOException, SQLException
    {
        // ( String new_table_name, String new_action_name, String new_form_bean, String new_render_forward, String new_action_forward ) 
        table          = new_table;
        /*
        action_name    = new_action_name;
        form_bean      = new_form_bean;
        render_forward = new_render_forward;
        action_forward = new_action_forward;
		//*/

        readProperties();

        //getDatabaseConnection();

        Vector <Column> pk_columns = table.getPrimaryKeyColumns();
        Column column              = null;

        for ( int counter = 0; counter < pk_columns.size(); counter++ )
        {
            column = pk_columns.elementAt ( counter );
            primary_keys.add ( column.getName() );

            if ( column.getName().length() > max_pk_name_length )
            {
                max_pk_name_length = column.getName().length();
            }
        }

        if (( action_name.indexOf ( "Edit" ) >= 0 ) && 
            ( render_forward.indexOf ( "edit" ) >= 0 ) &&
            ( action_forward.indexOf ( "edit" ) >= 0 ))
        {
            is_edit_action = true;
        }

        BuildActionFile ( action_name );
    }

    public void BuildActionFile ( String filename ) throws IOException, SQLException, ClassNotFoundException
    {
        StringBuilder file = new StringBuilder();
        file.append ( "package " );
        file.append ( package_actions );
        file.append ( ";\n\n" );
        file.append ( "import com.liferay.portal.struts.PortletAction;\n\n" );
        file.append ( "import " );
        file.append ( package_forms );
        file.append ( "." );
        file.append ( form_bean );
        file.append ( ";\n\n" );
        file.append ( "import " );
        file.append ( package_toolkit );
        file.append ( ".Toolkit;\n\n" );
        file.append ( "import java.sql.Connection;\n" );
        file.append ( "import java.sql.PreparedStatement;\n" );
        file.append ( "import java.sql.ResultSet;\n" );
        file.append ( "import java.sql.SQLException;\n" );
        file.append ( "import javax.portlet.ActionRequest;\n" );
        file.append ( "import javax.portlet.ActionResponse;\n" );
        file.append ( "import javax.portlet.PortletConfig;\n" );
        file.append ( "import javax.portlet.RenderRequest;\n" );
        file.append ( "import javax.portlet.RenderResponse;\n\n" );
        file.append ( "import org.apache.commons.logging.Log;\n" );
        file.append ( "import org.apache.commons.logging.LogFactory;\n" );
        file.append ( "import org.apache.struts.action.ActionForm;\n" );
        file.append ( "import org.apache.struts.action.ActionForward;\n" );
        file.append ( "import org.apache.struts.action.ActionMapping;\n\n" );
        file.append ( "public class " );
        file.append ( action_name );
        file.append ( " extends PortletAction\n" );
        file.append ( "{\n" );

        processAction ( file );

        render ( file );

        prepareStatements ( file );

        file.append ( "\tprivate Connection con              = null;\n\n" );
        file.append ( "\tprivate PreparedStatement ps_select = null;\n" );
        file.append ( "\tprivate PreparedStatement ps_insert = null;\n" );
        file.append ( "\tprivate PreparedStatement ps_delete = null;\n" );
        file.append ( "\tprivate PreparedStatement ps_update = null;\n\n" );
        file.append ( "\tprivate final Log _log = LogFactory.getLog ( " );
        file.append ( form_bean );
        file.append ( ".class );\n" );
        file.append ( "}\n" );

        FileOutputStream outfile = new FileOutputStream ( output_directory + "/" + filename + ".java" );
            outfile.write ( file.toString().getBytes() );
        outfile.close();
    }

    private void processAction ( StringBuilder file )
    {
        file.append ( "\tpublic void processAction ( ActionMapping mapping, ActionForm form, \n" );
        file.append ( "\t\t\tPortletConfig config, ActionRequest req, ActionResponse res)\n" );
        file.append ( "\t\tthrows Exception\n" );
        file.append ( "\t{\n" );
        if ( is_edit_action )
        {
            file.append ( "\t\tupdateDatabase ( form );\n\n" );
            file.append ( "\t\tsetForward ( req, \"/" );
            file.append ( portlet_name );
            file.append ( "/" );
            file.append ( action_forward );
            file.append ( "\" );\n" );
        }
        else
        {
            file.append ( "\t\tinsertIntoDatabase ( form );\n\n" );
            file.append ( "\t\tsetForward ( req, \"/" );
            file.append ( portlet_name );
            file.append ( "/" );
            file.append ( action_forward );
            file.append ( "\" );\n" );
        }
        file.append ( "\t}\n\n" );
    }

    private void render ( StringBuilder file )
    {
        file.append ( "\tpublic ActionForward render ( ActionMapping mapping, ActionForm form, PortletConfig config,\n" );
        file.append ( "\t\t\tRenderRequest req, RenderResponse res)\n" );
        file.append ( "\t\tthrows Exception\n" );
        file.append ( "\t{\n" );
        file.append ( "\t\t" );
        file.append ( form_bean );
        file.append ( " data_form = (" );
        file.append ( form_bean );
        file.append ( ") form;\n\n" );
        if ( is_edit_action )
        {
            file.append ( "\t\tselectFromDatabase ( form, req );\n\n" );
        }
        file.append ( "\t\t_log.info ( data_form.toString() );\n\n" );
        file.append ( "\t\treturn mapping.findForward ( \"portlet." );
        file.append ( portlet_name );
        file.append ( "." );
        file.append ( render_forward );
        file.append ( "\" );\n" );
        file.append ( "\t}\n\n" );
    }

    private void prepareStatements ( StringBuilder file ) throws SQLException, IOException, ClassNotFoundException
    {
        String query_select          = table.getQuerySelect();
        String query_insert          = table.getQueryInsert();
        String query_update          = table.getQueryUpdate();
        String query_delete          = table.getQueryDelete();

        Vector <Column> all_columns  = table.getAllColumns();
        Vector <Column> data_columns = table.getDataColumns();
        Vector <Column> pk_columns   = table.getPrimaryKeyColumns();
        String column_name           = null;
        Column column                = null;
        int num_columns              = table.getNumAllColumns();
        int counter                  = 0;
        int col_counter              = 0;

        for ( counter = 0; counter < num_columns; counter++ )
        {
            column = all_columns.elementAt ( counter );

            max_col_name_length = (max_col_name_length > column.getName().length() )? max_col_name_length : column.getName().length();
        }

        file.append ( "\tprivate void prepareStatements() throws SQLException, ClassNotFoundException\n" );
        file.append ( "\t{\n" );
        file.append ( "\t\tif ( null == ps_select )\n" );
        file.append ( "\t\t{\n" );
        file.append ( "\t\t\tps_select = Toolkit.createPreparedStatement ( \"" );
        file.append ( query_select.toString() );
        file.append ( "\" );\n" );
        file.append ( "\t\t\tps_insert = Toolkit.createPreparedStatement ( \"" );
        file.append ( query_insert.toString() );
        file.append ( "\" );\n" );
        file.append ( "\t\t\tps_update = Toolkit.createPreparedStatement ( \"" );
        file.append ( query_update.toString() );
        file.append ( "\" );\n" );
        file.append ( "\t\t\tps_delete = Toolkit.createPreparedStatement ( \"" );
        file.append ( query_delete.toString() );
        file.append ( "\" );\n" );
        file.append ( "\t\t}\n" );
        file.append ( "\t}\n\n" );

        file.append ( "\tprivate void selectFromDatabase ( ActionForm form, RenderRequest request ) throws SQLException, ClassNotFoundException\n" );
        file.append ( "\t{\n" );
        file.append ( "\t\tprepareStatements();\n\n" );
        file.append ( "\t\t//  Retrieving request parameters\n" );
        for ( counter = 0; counter < pk_columns.size(); counter++ )
        {
            column = pk_columns.elementAt ( counter );

            file.append ( "\t\tString pk_" );
            file.append ( Common.formatStringToLength ( column.getName(), max_pk_name_length ) );
            file.append ( " = request.getParameter ( \"" );
            file.append ( column.getName() );
            file.append ( "\" );\n" );
        }
        file.append ( "\t\t" );
        file.append ( form_bean );
        file.append ( " data_form = (" );
        file.append ( form_bean );
        file.append ( ") form;\n\n" );

        file.append ( "\t\t//  Put the primary key values into the following statements\n" );
        for ( counter = 0; counter < pk_columns.size(); counter++ )
        {
            column = pk_columns.elementAt ( counter );

            file.append ( "\t\tps_select." );
            file.append ( column.generatePSSetValueCall() );
            file.append ( " ( " );
            file.append ( 1 + counter );
            file.append ( ", " );
            file.append ( column.generatePSSetDataWrapper ( "pk_" + column.getName() ) );
            file.append ( " );\n" );
        }
        file.append ( "\n" );
        file.append ( "\t\tResultSet results = ps_select.executeQuery();\n\n" );
        file.append ( "\t\tif ( results.next() )\n" );
        file.append ( "\t\t{\n" );
        for ( counter = 0; counter < data_columns.size(); counter++ )
        {
            column = data_columns.elementAt(counter);
            column_name = Common.formatStringToLength ( column.getFunctionName(), max_col_name_length );
            file.append ( "\t\t\tdata_form.set" );
            file.append ( column_name );
            file.append ( " ( " );
            file.append ( column.generateDatabaseRetrieveType ( counter+1 ) );
            file.append ( " );\n" );
        }
        for ( counter = 0; counter < pk_columns.size(); counter++ )
        {
            column = pk_columns.elementAt(counter);
            column_name = Common.formatStringToLength ( column.getFunctionName(), max_col_name_length );
            file.append ( "\t\t\tdata_form.set" );
            file.append ( column_name );
            file.append ( " ( pk_" );
            file.append ( Common.formatStringToLength ( column.getName(), max_col_name_length ) );
            file.append ( " );\n" );
        }
        file.append ( "\t\t}\n" );
        file.append ( "\t}\n\n" );

        file.append ( "\tprivate void updateDatabase ( ActionForm form ) throws SQLException, ClassNotFoundException\n" );
        file.append ( "\t{\n" );
        file.append ( "\t\tprepareStatements();\n\n" );
        file.append ( "\t\t" );
        file.append ( form_bean );
        file.append ( " data_form = (" );
        file.append ( form_bean );
        file.append ( ") form;\n\n" );
        col_counter = 1;
        for ( counter = 0; counter < data_columns.size(); counter++ )
        {
            column = data_columns.elementAt ( counter );
            file.append ( "\t\tps_update." );
            file.append ( column.generatePSSetValueCall() );
            file.append ( " ( " );
            if ( col_counter < 10 )
            {
                file.append ( " " );
            }
            file.append ( df.format ( col_counter++ ) );
            file.append ( ", " );
            file.append ( column.generatePSSetDataWrapper ( "data_form.get"+column.getFunctionName()+"()" ) );
            file.append ( " );\n" );
        }
        for ( counter = 0; counter < pk_columns.size(); counter++ )
        {
            column = pk_columns.elementAt ( counter );
            file.append ( "\t\tps_update." );
            file.append ( column.generatePSSetValueCall() );
            file.append ( " ( " );
            if ( col_counter < 10 )
            {
                file.append ( " " );
            }
            file.append ( df.format ( col_counter++ ) );
            file.append ( ", " );
            file.append ( column.generatePSSetDataWrapper ( "data_form.get"+column.getFunctionName()+"()" ) );
            file.append ( " );\n" );
        }
        file.append ( "\n\t\tps_update.executeUpdate();\n" );
        file.append ( "\t}\n\n" );

        file.append ( "\tprivate void insertIntoDatabase ( ActionForm form ) throws SQLException, ClassNotFoundException\n" );
        file.append ( "\t{\n" );
        file.append ( "\t\tprepareStatements();\n\n" );
        file.append ( "\t\t" );
        file.append ( form_bean );
        file.append ( " data_form = (" );
        file.append ( form_bean );
        file.append ( ") form;\n\n" );
        col_counter = 1;
        for ( counter = 0; counter < data_columns.size(); counter++ )
        {
            column = data_columns.elementAt ( counter );
            file.append ( "\t\tps_insert." );
            file.append ( column.generatePSSetValueCall() );
            file.append ( " ( " );
            if ( col_counter < 10 )
            {
                file.append ( " " );
            }
            file.append ( df.format ( col_counter++ ) );
            file.append ( ", " );
            file.append ( column.generatePSSetDataWrapper ( "data_form.get"+column.getFunctionName()+"()" ) );
            file.append ( " );\n" );
        }
        for ( counter = 0; counter < pk_columns.size(); counter++ )
        {
            column = pk_columns.elementAt ( counter );
            if ( false == column.isAutoIncrement() )
            {
                file.append ( "\t\tps_insert." );
                file.append ( column.generatePSSetValueCall() );
                file.append ( " ( " );
                if ( col_counter < 10 )
                {
                    file.append ( " " );
                }
                file.append ( df.format ( col_counter++ ) );
                file.append ( ", " );
                file.append ( column.generatePSSetDataWrapper ( "data_form.get"+column.getFunctionName()+"()" ) );
                file.append ( " );\n" );
            }
        }
        file.append ( "\n\t\tps_insert.executeUpdate();\n" );

        file.append ( "\t}\n\n" );
    }

    private void readProperties() throws IOException
    {
        if ( null == properties ) 
        {
			properties = Common.getStrutsToolsProperties();
            output_directory  = properties.getProperty ( "directory.actions.out" );
            package_actions   = properties.getProperty ( "directory.actions" );
            package_forms     = properties.getProperty ( "directory.forms" );
            package_toolkit   = properties.getProperty ( "directory.toolkit" );
        }

        if ( null == common_properties )
        {
			common_properties = Common.getCommonProperties();
            
            portlet_name      = common_properties.getProperty ( "portlet.name" );
        }
    }
};
