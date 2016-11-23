package tools.struts.view;

import java.io.FileOutputStream;
import java.io.IOException;

import tools.databases.Database;

public class HTMLView
{
    @SuppressWarnings("unused")
    private static Database database = null;

    public HTMLView ( Database new_database )
    {
        database = new_database;

        initialize();
    }

    public void initialize()
    {
    }

    public void BuildFile ( String filename ) throws IOException
    {
        StringBuilder file = new StringBuilder();

        FileOutputStream outfile = new FileOutputStream ( filename );
            outfile.write ( file.toString().getBytes() );
        outfile.close();
    }
}
