package com.bgi.esm.monitoring.platform.filters.Archway;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.bgi.esm.monitoring.platform.filters.Archway.threads.ArchwayThread;

public class TrafficModulatorServlet extends HttpServlet 
{
    final private static Logger _log = Logger.getLogger ( TrafficModulatorServlet.class );

    private static ArchwayThread thread = null;

    public void init() throws ServletException
    {
        _log.info ( "Traffic Modulator Servlet has been started" );

        thread = new ArchwayThread();
        thread.run();
    }

    public void destroy()
    {
        _log.warn ( "Traffic Modulator Servlet has been stopped" );
    }

    public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
    	res.setContentType("text/html");
    
    	ServletOutputStream out = res.getOutputStream();
    	out.println("<html>");
    	out.println("<head><title>Service Center Archway Traffic Modulator Servlet</title></head>");
    	out.println("<body>");
    	out.println("<h1>Service Center Archway Traffic Modulator Servlet -- servlet is active</h1>");
    	out.println("</body></html>");
    }
    
    public String getServletInfo() 
    {
	    return "Modulates the traffic going to Service Center Archway";
    }
}
