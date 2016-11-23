package com.bgi.esm.monitoring.portlets.Suppressions.actions;

import java.io.IOException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.Logger;

import com.bgi.esm.monitoring.portlets.Suppressions.Toolkit;
import com.bgi.esm.monitoring.portlets.Suppressions.forms.EditPreferences;

/**
 * <a href="AddEntryAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Dennis Lin
 * @version $Revision: 1.1 $
 *
 */
public class EditPreferencesAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
          throws Exception 
    {
    	_log.debug ( "com.bgi.esm.portlets.Suppression.actions.EditPreferencesAction::execute()" );
    	
        EditPreferences data_form = (EditPreferences) form;
        
		RenderRequest renderRequest = (RenderRequest) request.getAttribute ( "javax.portlet.request" );
		if ( null != renderRequest )
		{
			PortletPreferences prefs = renderRequest.getPreferences();

            /*
			prefs.setValue ( "suppression_database_type",            data_form.getSuppressionDatabaseType()     );
			prefs.setValue ( "suppression_database_server",          data_form.getSuppressionDatabaseServer()   );
			prefs.setValue ( "suppression_database_name",            data_form.getSuppressionDatabaseName()     );
			prefs.setValue ( "suppression_database_port",            data_form.getSuppressionDatabasePort()     );
			prefs.setValue ( "suppression_database_username",        data_form.getSuppressionDatabaseUsername() );
			prefs.setValue ( "suppression_database_password",        data_form.getSuppressionDatabasePassword() );
            //*/

			prefs.setValue ( "vpo_database_type",                    data_form.getVpoDatabaseType()     );
			prefs.setValue ( "vpo_database_server",                  data_form.getVpoDatabaseServer()   );
			prefs.setValue ( "vpo_database_name",                    data_form.getVpoDatabaseName()     );
			prefs.setValue ( "vpo_database_port",                    data_form.getVpoDatabasePort()     );
			prefs.setValue ( "vpo_database_username",                data_form.getVpoDatabaseUsername() );
			prefs.setValue ( "vpo_database_password",                data_form.getVpoDatabasePassword() );

			prefs.setValue ( "inst_database_type",                   data_form.getInstDatabaseType()     );
			prefs.setValue ( "inst_database_server",                 data_form.getInstDatabaseServer()   );
			prefs.setValue ( "inst_database_name",                   data_form.getInstDatabaseName()     );
			prefs.setValue ( "inst_database_port",                   data_form.getInstDatabasePort()     );
			prefs.setValue ( "inst_database_username",               data_form.getInstDatabaseUsername() );
			prefs.setValue ( "inst_database_password",               data_form.getInstDatabasePassword() );

            /*
			prefs.setValue ( "suppression_backup_database_type",     data_form.getSuppressionBackupDatabaseType()     );
			prefs.setValue ( "suppression_backup_database_server",   data_form.getSuppressionBackupDatabaseServer()   );
			prefs.setValue ( "suppression_backup_database_name",     data_form.getSuppressionBackupDatabaseName()     );
			prefs.setValue ( "suppression_backup_database_port",     data_form.getSuppressionBackupDatabasePort()     );
			prefs.setValue ( "suppression_backup_database_username", data_form.getSuppressionBackupDatabaseUsername() );
			prefs.setValue ( "suppression_backup_database_password", data_form.getSuppressionBackupDatabasePassword() );
            //*/

			prefs.setValue ( "vpo_backup_database_type",             data_form.getVpoBackupDatabaseType()     );
			prefs.setValue ( "vpo_backup_database_server",           data_form.getVpoBackupDatabaseServer()   );
			prefs.setValue ( "vpo_backup_database_name",             data_form.getVpoBackupDatabaseName()     );
			prefs.setValue ( "vpo_backup_database_port",             data_form.getVpoBackupDatabasePort()     );
			prefs.setValue ( "vpo_backup_database_username",         data_form.getVpoBackupDatabaseUsername() );
			prefs.setValue ( "vpo_backup_database_password",         data_form.getVpoBackupDatabasePassword() );

			prefs.setValue ( "inst_backup_database_type",            data_form.getInstBackupDatabaseType()     );
			prefs.setValue ( "inst_backup_database_server",          data_form.getInstBackupDatabaseServer()   );
			prefs.setValue ( "inst_backup_database_name",            data_form.getInstBackupDatabaseName()     );
			prefs.setValue ( "inst_backup_database_port",            data_form.getInstBackupDatabasePort()     );
			prefs.setValue ( "inst_backup_database_username",        data_form.getInstBackupDatabaseUsername() );
			prefs.setValue ( "inst_backup_database_password",        data_form.getInstBackupDatabasePassword() );

            /*
            prefs.setValue ( "extract_file_suppression",             data_form.getExtractFileForSuppressions() );
            prefs.setValue ( "extract_file_vpo",                     data_form.getExtractFileForVpo() );
            prefs.setValue ( "extract_file_inst",                    data_form.getExtractFileForInst() );
            //*/

			prefs.store();
        
            Toolkit.refreshDatabaseConnections ( renderRequest );
		}

        return(mapping.findForward("success"));
    }
  
    private static final Logger _log = Logger.getLogger (EditPreferencesAction.class);
}


