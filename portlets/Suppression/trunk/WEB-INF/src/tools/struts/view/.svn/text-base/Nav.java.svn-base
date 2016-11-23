package tools.struts.view;

import java.io.FileOutputStream;
import java.io.IOException;

import tools.databases.Database;

public class Nav
{
    @SuppressWarnings("unused")
    private static Database database = null;

    /**
     * Creates the HTML navigational panel for the database.
     *
     * @param new_database The database that this object will create the central navigation panel for
     */
    public Nav ( Database new_database )
    {
        database = new_database;

        initialize();
    }

    /**
     * Prepares and organizes the data necessary to create the HTML navigational panel
     */
    private void initialize()
    {
    }

    /**
     * Create and save the central navigational panel HTML file
     *
     * @param filename the path relative to PORLET_ROOT where the central navigational panel HTML file will reside
     */
    public void BuildFile ( String filename ) throws IOException
    {
        StringBuilder file = new StringBuilder();

        FileOutputStream outfile = new FileOutputStream ( filename );
            outfile.write ( file.toString().getBytes() );
        outfile.close();
    }
}
