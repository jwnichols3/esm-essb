import java.util.*;

public class test
{
    public static void main ( String args[] )
    {
        Properties p = System.getProperties();

        Enumeration e = p.propertyNames();
        String prop = null;

        while ( e.hasMoreElements() )
        {
            prop = e.nextElement().toString();

            System.out.print ( prop );
            System.out.print ( " - " );
            System.out.println ( p.getProperty ( prop ) );
        }
    }
}
