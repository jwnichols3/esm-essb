package com.bgi.esm.monitoring.portlets.Suppressions.forms;

import javax.servlet.http.HttpServletRequest;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author  Dennis Lin
 * @version $Revision: 1.1 $
 */
public class EditPreferences extends ActionForm {
    /**
     * 
     */
    private static final long serialVersionUID = 3235792719067863713L;
    public void reset ( ActionMapping mapping, HttpServletRequest req ) {
        
        // javax.portlet.config    javax.portlet.PortletConfig
        // javax.portlet.request   javax.portlet.RenderRequest
        // javax.portlet.response  javax.portlet.RenderResponse
        // javax.portlet.request   javax.portlet.PortletRequest
        RenderRequest renderRequest = (RenderRequest) req.getAttribute ( "javax.portlet.request" );

        if ( null != renderRequest )
        {
            PortletPreferences prefs = renderRequest.getPreferences();

            //  Main database connections
            suppression_database_type             = prefs.getValue ( "suppression_database_type",     "" );
            suppression_database_server           = prefs.getValue ( "suppression_database_server",   "" );
            suppression_database_name             = prefs.getValue ( "suppression_database_name",     "" );
            suppression_database_port             = prefs.getValue ( "suppression_database_port",     "" );
            suppression_database_username         = prefs.getValue ( "suppression_database_username", "" );
            suppression_database_password         = prefs.getValue ( "suppression_database_password", "" );

            vpo_database_type                     = prefs.getValue ( "vpo_database_type",     "" );
            vpo_database_server                   = prefs.getValue ( "vpo_database_server",   "" );
            vpo_database_name                     = prefs.getValue ( "vpo_database_name",     "" );
            vpo_database_port                     = prefs.getValue ( "vpo_database_port",     "" );
            vpo_database_username                 = prefs.getValue ( "vpo_database_username", "" );
            vpo_database_password                 = prefs.getValue ( "vpo_database_password", "" );

            inst_database_type                    = prefs.getValue ( "inst_database_type",     "" );
            inst_database_server                  = prefs.getValue ( "inst_database_server",   "" );
            inst_database_name                    = prefs.getValue ( "inst_database_name",     "" );
            inst_database_port                    = prefs.getValue ( "inst_database_port",     "" );
            inst_database_username                = prefs.getValue ( "inst_database_username", "" );
            inst_database_password                = prefs.getValue ( "inst_database_password", "" );

            //  BCP database connections
            suppression_backup_database_type      = prefs.getValue ( "suppression_backup_database_type",     "" );
            suppression_backup_database_server    = prefs.getValue ( "suppression_backup_database_server",   "" );
            suppression_backup_database_name      = prefs.getValue ( "suppression_backup_database_name",     "" );
            suppression_backup_database_port      = prefs.getValue ( "suppression_backup_database_port",     "" );
            suppression_backup_database_username  = prefs.getValue ( "suppression_backup_database_username", "" );
            suppression_backup_database_password  = prefs.getValue ( "suppression_backup_database_password", "" );

            vpo_backup_database_type              = prefs.getValue ( "vpo_backup_database_type",     "" );
            vpo_backup_database_server            = prefs.getValue ( "vpo_backup_database_server",   "" );
            vpo_backup_database_name              = prefs.getValue ( "vpo_backup_database_name",     "" );
            vpo_backup_database_port              = prefs.getValue ( "vpo_backup_database_port",     "" );
            vpo_backup_database_username          = prefs.getValue ( "vpo_backup_database_username", "" );
            vpo_backup_database_password          = prefs.getValue ( "vpo_backup_database_password", "" );

            inst_backup_database_type             = prefs.getValue ( "inst_backup_database_type",     "" );
            inst_backup_database_server           = prefs.getValue ( "inst_backup_database_server",   "" );
            inst_backup_database_name             = prefs.getValue ( "inst_backup_database_name",     "" );
            inst_backup_database_port             = prefs.getValue ( "inst_backup_database_port",     "" );
            inst_backup_database_username         = prefs.getValue ( "inst_backup_database_username", "" );
            inst_backup_database_password         = prefs.getValue ( "inst_backup_database_password", "" );

            //  Full paths to extract files on server
            extract_file_suppressions             = prefs.getValue ( "extract_file_suppression", "" );
            extract_file_vpo                      = prefs.getValue ( "extract_file_vpo",         "" );
            extract_file_inst                     = prefs.getValue ( "extract_file_inst",        "" );
        }
        else
        {
            suppression_database_type      = "";
            suppression_database_server    = "";
            suppression_database_name      = "";
            suppression_database_port      = "";
            suppression_database_username  = "";
            suppression_database_password  = "";

            vpo_database_type              = "";
            vpo_database_server            = "";
            vpo_database_name              = "";
            vpo_database_port              = "";
            vpo_database_username          = "";
            vpo_database_password          = "";

            inst_database_type             = "";
            inst_database_server           = "";
            inst_database_name             = "";
            inst_database_port             = "";
            inst_database_username         = "";
            inst_database_password         = "";

            extract_file_suppressions      = "";
            extract_file_vpo               = "";
            extract_file_inst              = "";
        }
    }

    public ActionErrors validate(
        ActionMapping mapping, HttpServletRequest req) {

        ActionErrors errors = new ActionErrors();

        return errors;
    }

    public String getSuppressionDatabaseType() {
        return suppression_database_type;
    }

    public void setSuppressionDatabaseType ( String param ) {
        suppression_database_type = param;
    }

    public String getSuppressionDatabaseServer() {
        return suppression_database_server;
    }

    public void setSuppressionDatabaseServer ( String param ) {
        suppression_database_server = param;
    }

    public String getSuppressionDatabaseName() {
        return suppression_database_name;
    }

    public void setSuppressionDatabaseName ( String param ) {
        suppression_database_name = param;
    }

    public String getSuppressionDatabasePort() {
        return suppression_database_port;
    }

    public void setSuppressionDatabasePort ( String param ) {
        suppression_database_port = param;
    }

    public String getSuppressionDatabaseUsername() {
        return suppression_database_username;
    }

    public void setSuppressionDatabaseUsername ( String param ) {
        suppression_database_username = param;
    }

    public String getSuppressionDatabasePassword() {
        return suppression_database_password;
    }

    public void setSuppressionDatabasePassword ( String param ) {
        suppression_database_password = param;
    }

    public String getVpoDatabaseType() {
        return vpo_database_type;
    }

    public void setVpoDatabaseType ( String param ) {
        vpo_database_type = param;
    }

    public String getVpoDatabaseServer() {
        return vpo_database_server;
    }

    public void setVpoDatabaseServer ( String param ) {
        vpo_database_server = param;
    }

    public String getVpoDatabaseName() {
        return vpo_database_name;
    }

    public void setVpoDatabaseName ( String param ) {
        vpo_database_name = param;
    }

    public String getVpoDatabasePort() {
        return vpo_database_port;
    }

    public void setVpoDatabasePort ( String param ) {
        vpo_database_port = param;
    }

    public String getVpoDatabaseUsername() {
        return vpo_database_username;
    }

    public void setVpoDatabaseUsername ( String param ) {
        vpo_database_username = param;
    }

    public String getVpoDatabasePassword() {
        return vpo_database_password;
    }

    public void setVpoDatabasePassword ( String param ) {
        vpo_database_password = param;
    }

    public String getInstDatabaseType() {
        return inst_database_type;
    }

    public void setInstDatabaseType ( String param ) {
        inst_database_type = param;
    }

    public String getInstDatabaseServer() {
        return inst_database_server;
    }

    public void setInstDatabaseServer ( String param ) {
        inst_database_server = param;
    }

    public String getInstDatabaseName() {
        return inst_database_name;
    }

    public void setInstDatabaseName ( String param ) {
        inst_database_name = param;
    }

    public String getInstDatabasePort() {
        return inst_database_port;
    }

    public void setInstDatabasePort ( String param ) {
        inst_database_port = param;
    }

    public String getInstDatabaseUsername() {
        return inst_database_username;
    }

    public void setInstDatabaseUsername ( String param ) {
        inst_database_username = param;
    }

    public String getInstDatabasePassword() {
        return inst_database_password;
    }

    public void setInstDatabasePassword ( String param ) {
        inst_database_password = param;
    }

    public String getSuppressionBackupDatabaseType() {
        return suppression_backup_database_type;
    }

    public void setSuppressionBackupDatabaseType ( String param ) {
        suppression_backup_database_type = param;
    }

    public String getSuppressionBackupDatabaseServer() {
        return suppression_backup_database_server;
    }

    public void setSuppressionBackupDatabaseServer ( String param ) {
        suppression_backup_database_server = param;
    }

    public String getSuppressionBackupDatabaseName() {
        return suppression_backup_database_name;
    }

    public void setSuppressionBackupDatabaseName ( String param ) {
        suppression_backup_database_name = param;
    }

    public String getSuppressionBackupDatabasePort() {
        return suppression_backup_database_port;
    }

    public void setSuppressionBackupDatabasePort ( String param ) {
        suppression_backup_database_port = param;
    }

    public String getSuppressionBackupDatabaseUsername() {
        return suppression_backup_database_username;
    }

    public void setSuppressionBackupDatabaseUsername ( String param ) {
        suppression_backup_database_username = param;
    }

    public String getSuppressionBackupDatabasePassword() {
        return suppression_backup_database_password;
    }

    public void setSuppressionBackupDatabasePassword ( String param ) {
        suppression_backup_database_password = param;
    }

    public String getVpoBackupDatabaseType() {
        return vpo_backup_database_type;
    }

    public void setVpoBackupDatabaseType ( String param ) {
        vpo_backup_database_type = param;
    }

    public String getVpoBackupDatabaseServer() {
        return vpo_backup_database_server;
    }

    public void setVpoBackupDatabaseServer ( String param ) {
        vpo_backup_database_server = param;
    }

    public String getVpoBackupDatabaseName() {
        return vpo_backup_database_name;
    }

    public void setVpoBackupDatabaseName ( String param ) {
        vpo_backup_database_name = param;
    }

    public String getVpoBackupDatabasePort() {
        return vpo_backup_database_port;
    }

    public void setVpoBackupDatabasePort ( String param ) {
        vpo_backup_database_port = param;
    }

    public String getVpoBackupDatabaseUsername() {
        return vpo_backup_database_username;
    }

    public void setVpoBackupDatabaseUsername ( String param ) {
        vpo_backup_database_username = param;
    }

    public String getVpoBackupDatabasePassword() {
        return vpo_backup_database_password;
    }

    public void setVpoBackupDatabasePassword ( String param ) {
        vpo_backup_database_password = param;
    }

    public String getInstBackupDatabaseType() {
        return inst_backup_database_type;
    }

    public void setInstBackupDatabaseType ( String param ) {
        inst_backup_database_type = param;
    }

    public String getInstBackupDatabaseServer() {
        return inst_backup_database_server;
    }

    public void setInstBackupDatabaseServer ( String param ) {
        inst_backup_database_server = param;
    }

    public String getInstBackupDatabaseName() {
        return inst_backup_database_name;
    }

    public void setInstBackupDatabaseName ( String param ) {
        inst_backup_database_name = param;
    }

    public String getInstBackupDatabasePort() {
        return inst_backup_database_port;
    }

    public void setInstBackupDatabasePort ( String param ) {
        inst_backup_database_port = param;
    }

    public String getInstBackupDatabaseUsername() {
        return inst_backup_database_username;
    }

    public void setInstBackupDatabaseUsername ( String param ) {
        inst_backup_database_username = param;
    }

    public String getInstBackupDatabasePassword() {
        return inst_backup_database_password;
    }

    public void setInstBackupDatabasePassword ( String param ) {
        inst_backup_database_password = param;
    }

    public void setExtractFileForSuppressions ( String param ) {
        extract_file_suppressions = param;
    }

    public String getExtractFileForSuppressions () {
        return extract_file_suppressions;
    }

    public void setExtractFileForVpo ( String param ) {
        extract_file_vpo = param;
    }

    public String getExtractFileForVpo () {
        return extract_file_vpo ;
    }

    public void setExtractFileForInst ( String param ) {
        extract_file_inst = param;
    }

    public String getExtractFileForInst () {
        return extract_file_inst;
    }

    private String extract_file_suppressions            = "";
    private String extract_file_vpo                     = "";
    private String extract_file_inst                    = "";

    private String suppression_database_type            = "";
    private String suppression_database_server          = "";
    private String suppression_database_name            = "";
    private String suppression_database_port            = "";
    private String suppression_database_username        = "";
    private String suppression_database_password        = "";

    private String vpo_database_type                    = "";
    private String vpo_database_server                  = "";
    private String vpo_database_name                    = "";
    private String vpo_database_port                    = "";
    private String vpo_database_username                = "";
    private String vpo_database_password                = "";

    private String inst_database_type                   = "";
    private String inst_database_server                 = "";
    private String inst_database_name                   = "";
    private String inst_database_port                   = "";
    private String inst_database_username               = "";
    private String inst_database_password               = "";

    private String suppression_backup_database_type     = "";
    private String suppression_backup_database_server   = "";
    private String suppression_backup_database_name     = "";
    private String suppression_backup_database_port     = "";
    private String suppression_backup_database_username = "";
    private String suppression_backup_database_password = "";

    private String vpo_backup_database_type             = "";
    private String vpo_backup_database_server           = "";
    private String vpo_backup_database_name             = "";
    private String vpo_backup_database_port             = "";
    private String vpo_backup_database_username         = "";
    private String vpo_backup_database_password         = "";

    private String inst_backup_database_type            = "";
    private String inst_backup_database_server          = "";
    private String inst_backup_database_name            = "";
    private String inst_backup_database_port            = "";
    private String inst_backup_database_username        = "";
    private String inst_backup_database_password        = "";
}


