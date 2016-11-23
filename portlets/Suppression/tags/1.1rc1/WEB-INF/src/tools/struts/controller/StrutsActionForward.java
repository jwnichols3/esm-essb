package tools.struts.controller;


public class StrutsActionForward
{
    private String name    = null;
    private String path    = null;
    private String output  = null;
    private int max_length = 0;

    /**
     * Standard constructor for the ActionForward
     */
    public StrutsActionForward()
    {
    }

    /**
     * Sets the name for the ActionForward object
     *
     * @param new_name the name for the ActionForward object
     */
    public void setName ( String new_name )
    {
        name = new_name;
    }

    /**
     * Returns the name for the ActionForward object
     *
     * @return the name for the ActionForward object
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the forward path for the ActionForward object
     *
     * @param new_path the forward path for the ActionForward object
     */
    public void setPath ( String new_path )
    {
        path = new_path;
    }

    /**
     * Returns the forward path for the ActionForward object
     *
     * @return the forward path for the ActionForward object
     */
    public String getPath()
    {
        return path;
    }

    /**
     * Returns the ActionForward String for the Action to use in struts-config.xml.
     *
     * @return the ActionForward String for the Action to use in struts-config.xml.
     */
    public String toString()
    {
        if ( null == output )
        {
            int counter          = 0;
            StringBuilder result = new StringBuilder ( "        " );
            result.append ( "<forward name=\"" );
            result.append ( name );
            result.append ( "\"" );
            for ( counter = name.length(); counter < max_length; counter++ )
            {
                result.append ( " " );
            }
            result.append ( " path=\"" );
            result.append ( path );
            result.append ( "\"/>" );

            output = result.toString();
        }

        return output;
    }
}
