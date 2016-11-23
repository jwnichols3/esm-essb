<%@ page contentType="image/jpeg"            %>
<%@ page import="java.awt.*"                 %>
<%@ page import="java.awt.image.*"           %>
<%@ page import="java.io.*"                  %>
<%@ page import="java.util.*"                %>
<%@ page import="javax.imageio.*"            %>
<%@ page import="org.apache.log4j.*"        %>
<%@ page import="com.sun.image.codec.jpeg.*" %>
<%@ page import="com.bgi.esm.monitoring.portlets.MonitoringPlatformAdmin.*" %>
<%
    final int graph_height                  = Constants.QueueActivityGraph_GraphHeight;
    final int hour_width                    = Constants.QueueActivityGraph_HourWidth;
    final int num_hours                     = Constants.QueueActivityGraph_NumHours;
    final int image_left_border             = Constants.QueueActivityGraph_ImageLeftBorder;
    final int image_bottom_border           = Constants.QueueActivityGraph_ImageBottomBorder;
    final int image_width                   = image_left_border + num_hours*hour_width;
    final int image_height                  = graph_height + image_bottom_border;
    final double d_graph_height             = (double) graph_height;
    final int marker_size                   = Constants.QueueActivityGraph_MarkerSize;
    int height1                             = 0;
    int height2                             = 0;
    int num_events1                         = 0;
    Date date1                              = null;
    HashMap <Date, Integer> num_events_list = (HashMap <Date, Integer>) session.getAttribute ( Constants.SESSION_NUM_EVENTS_24_HOURS );
    Integer min_events                      = (Integer) session.getAttribute ( Constants.SESSION_NUM_EVENTS_24_HOURS_MIN );
    Integer max_events                      = (Integer) session.getAttribute ( Constants.SESSION_NUM_EVENTS_24_HOURS_MAX );
    Vector <Date> dates                     = (Vector <Date>) session.getAttribute ( Constants.SESSION_NUM_EVENTS_24_HOURS_DATES );
    double event_range                      = (double) (max_events.intValue() - min_events.intValue());

    ServletOutputStream sos                 = response.getOutputStream();
    JPEGImageEncoder encoder                = JPEGCodec.createJPEGEncoder ( sos );
    BufferedImage image                     = new BufferedImage ( image_width, image_height+1, BufferedImage.TYPE_INT_RGB );
    Graphics g                              = image.getGraphics();

    Vector <Integer> heights                = new Vector <Integer> ();

    //  Calculate the points
    int num_dates    = dates.size();
    int num_segments = dates.size() - 1;
    for ( int counter = 0; counter < num_dates; counter++ )
    {
        date1       = dates.elementAt ( counter );
        num_events1 = num_events_list.get( date1 ).intValue();
        double h    = ((double) ( num_events1 - min_events )) / (double) event_range * d_graph_height;

        heights.add ( new Integer ( (int) h ) );
    }

    //  Clear the image
    g.setColor ( Color.white );
    g.fillRect ( 0, 0, image_width, image_height+1 );

    //  Draw the graph border
    g.setColor ( Color.black );
    g.drawLine ( image_left_border, 0, image_left_border, graph_height );
    g.drawLine ( image_left_border, graph_height+1, image_width, graph_height+1);

    //  Draw the graph line
    int start_x = image_left_border;
    int start_y = image_height - image_bottom_border;
    for ( int counter = 0; counter < num_segments; counter++ )
    {
        height1 = start_y - heights.elementAt( counter   ).intValue();
        height2 = start_y - heights.elementAt( counter+1 ).intValue();

        g.setColor ( Color.red );
        g.drawLine ( start_x, height1, start_x + hour_width, height2 );
        start_x += hour_width;

        g.setColor ( Color.black );
        g.drawLine ( start_x, graph_height-marker_size, start_x, graph_height+marker_size );
    }

    encoder.encode ( (BufferedImage) image );

    out.clear();
    out = pageContext.pushBody();
 %>
