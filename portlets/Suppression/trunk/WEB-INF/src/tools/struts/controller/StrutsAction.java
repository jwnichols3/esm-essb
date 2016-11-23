package tools.struts.controller;

import java.io.IOException;
import java.io.FileOutputStream;

import tools.databases.Table;

public class StrutsAction
{
    private Table table = null;

    public StrutsAction ( Table new_table )
    {
        table = new_table;
    }

    public void BuildFile ( String filename ) throws IOException
    {
        StringBuilder contents = new StringBuilder();

        contents.append ( "import javax.servlet.http.HttpServletRequest;\n" );
        contents.append ( "import javax.servlet.http.HttpServletResponse;\n" );
        contents.append ( "import org.apache.struts.action.Action;\n" );
        contents.append ( "import org.apache.struts.action.ActionForm;\n" );
        contents.append ( "import org.apache.struts.action.ActionForward;\n" );
        contents.append ( "import org.apache.struts.action.ActionMapping;\n" );
        contents.append ( "import org.apache.commons.logging.Log;\n" );
        contents.append ( "import org.apache.commons.logging.LogFactory;\n" );
        contents.append ( "\n" );
        contents.append ( "import java.io.FileOutputStream;\n" );
        contents.append ( "import java.util.Map;\n" );

        FileOutputStream outfile = new FileOutputStream ( filename );
            outfile.write ( contents.toString().getBytes() );
        outfile.close();
    }
    
    public Table getTable()
    {
        return table;
    }
}
