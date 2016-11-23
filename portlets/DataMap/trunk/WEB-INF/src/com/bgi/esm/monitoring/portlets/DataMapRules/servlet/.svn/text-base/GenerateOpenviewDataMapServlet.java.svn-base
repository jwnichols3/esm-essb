package com.bgi.esm.monitoring.portlets.DataMapRules.servlet;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.portlets.DataMapRules.DataMapGenerator;

public class GenerateOpenviewDataMapServlet extends BaseServlet
{
    final private static Logger _log = Logger.getLogger ( GenerateOpenviewDataMapServlet.class );

    public void doGet ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        HashMap <String, String> parameters = retrieveHttpRequestParameters ( request );

        response.setContentType ( "text/plain" );

        ServletOutputStream out = response.getOutputStream();
        out.println ( DataMapGenerator.generateOpenviewDataMap ( parameters.get ( "filename" ) ) );
    }
};
