package tools.SourceCodeManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectStreamClass;
import java.util.List;
import java.util.Vector;
import org.apache.log4j.Logger;

public class GenerateSerialVersionUID
{
    final private static Logger _log = Logger.getLogger ( GenerateSerialVersionUID.class );

    public static void main ( String args[] ) throws IOException, ClassNotFoundException
    {
        //if ( args.length < 1 )
        if ( false )
        {
            System.err.println ( "Usage:" );
            System.err.println ( "" );
            System.err.println ( "    java tools.SourceCodeManagement.GenerateSerialVersionUID <src-dir>" );
            System.err.println ( "" );
            System.err.println ( "" );
        }
        else
        {
            collectSourceCodeFiles ( args[0] );
        }
    }

    private static List <String> collectSourceCodeFiles ( String path ) throws IOException, ClassNotFoundException
    {
        List <String> returnValue = new Vector <String> ();

        collectFiles ( returnValue, new File ( path ) );

        for ( int counter = 0; counter < returnValue.size(); counter++ )
        {
            System.out.println ( "Found file: " + returnValue.get ( counter ) );

            processFile ( returnValue.get ( counter ), path );
        }

        return returnValue;
    }

    private static void collectFiles ( List <String> sourceCodeFileNames, File directory )
    {
        if ( directory.isDirectory() )
        {
            File files[] = directory.listFiles();
            for ( int counter = 0; counter < files.length; counter++ )
            {
                if ( files[counter].isDirectory() )
                {
                    collectFiles ( sourceCodeFileNames, files[counter] );
                }
                else
                {
                    try
                    {
                        if ( files[counter].getCanonicalPath().indexOf ( ".java" ) > 1 )
                        {
                            sourceCodeFileNames.add ( files[counter].getPath() );
                        }
                    }
                    catch ( IOException exception )
                    {
                        _log.error ( exception );
                    }
                }
            }
        }
    }

    private static void processFile ( String filename, String start_path ) throws IOException, ClassNotFoundException
    {
        FileInputStream infile = new FileInputStream ( filename );
        byte file_contents[] = new byte[infile.available()];
        infile.read ( file_contents );
        infile.close();

        String paths[] = filename.split ( "\\." );
        if ( paths[paths.length-1].equals ( "java" ) == false )
        {
            _log.info ( "Skipping processing of file: " + filename );
            return;
        }

        String fqnClassName = filename.replaceAll ( "\\.java", "" );
        fqnClassName = fqnClassName.substring ( start_path.length() + 1 );
        fqnClassName = fqnClassName.replace ( '\\', '.' );
        fqnClassName = fqnClassName.replace ( '/', '.' );

        ObjectStreamClass objectStreamClass = null;

        try
        {
            objectStreamClass = ObjectStreamClass.lookup ( Class.forName ( fqnClassName ) );
        }
        catch ( Exception exception )
        {
            _log.error ( "Error when processing file=" + filename + ": " + exception.getMessage() );
            return;
        }

        if ( null == objectStreamClass ) return;

        _log.info ( "Processing class: " + fqnClassName );
        long serialVer = objectStreamClass.getSerialVersionUID();
        StringBuilder replaceString = new StringBuilder();

        replaceString.append ( "final private static long serialVersionUID = " );
        replaceString.append ( serialVer );
        replaceString.append ( "L;" );

        String contents = new String ( file_contents );
        contents = contents.replaceAll ( "//  XXX___SERIALVER__XXX", replaceString.toString() );

        FileOutputStream outfile = new FileOutputStream ( filename );
        outfile.write ( contents.getBytes() );
        outfile.close();
    }
}
