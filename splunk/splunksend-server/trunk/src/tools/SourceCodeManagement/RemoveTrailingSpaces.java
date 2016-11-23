package tools.SourceCodeManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class RemoveTrailingSpaces
{
    private static List <String> JavaSourceFiles = new Vector <String> ();
    private static int tally = 0;

    public static void main2  ( String args[] )
    {
        String s1 = "No Spaces";
        String s2 = "Spaces    ";

        System.out.println ( "Test 1: " );
        System.out.println ( String.format ( "    *%s*", s1 ) );
        System.out.println ( String.format ( "    *%s*", removeTrailingSpaces ( s1 ) ) );

        System.out.println ( "Test 2: " );
        System.out.println ( String.format ( "    *%s*", s2 ) );
        System.out.println ( String.format ( "    *%s*", removeTrailingSpaces ( s2 ) ) );
    }

    public static void main ( String args[] ) throws IOException
    {
        processDirectory ( new File ( args[0] ) );

        for ( int counter = 0; counter < JavaSourceFiles.size(); counter++ )
        {
            removeTrailingWhitespace ( JavaSourceFiles.get ( counter ) );
        }

        System.out.println ( "Space saved: " + tally );
    }

    private static void removeTrailingWhitespace ( String filename ) throws IOException
    {
        FileInputStream infile = new FileInputStream ( filename );
        byte file_contents[]   = new byte[infile.available()];
        infile.read ( file_contents );
        infile.close();

        String contents = new String ( file_contents );
        String lines[] = contents.split ( "\r\n" );
        StringBuilder newContents = new StringBuilder();

        for ( int counter = 0; counter < lines.length; counter++ )
        {
            String line = lines[counter];
            newContents.append ( removeTrailingSpaces ( line ) );

            if ( counter + 1 < lines.length )
            {
                newContents.append ( "\n" );
            }
        }

        FileOutputStream outfile = new FileOutputStream ( filename );
            outfile.write ( newContents.toString().getBytes() );
        outfile.close();

        System.out.println ( String.format ( "Processed '%s' ( OldSize=%d, NewSize=%d )", filename, file_contents.length, newContents.length() ) );

        tally += ( file_contents.length - newContents.length() );
    }

    private static String removeTrailingSpaces ( String s )
    {
        int charIndex = s.length() - 1;
        while (( charIndex > 0 ) &&
                (( s.charAt ( charIndex ) == ' '  ) ||
                 ( s.charAt ( charIndex ) == '\r' )))
        {
            charIndex--;
        }

        return s.substring ( 0, charIndex+1 ).replaceAll ( "\t", "    " );
    }

    private static void processDirectory ( File directory )
    {
        File files[] = directory.listFiles();

        for ( int counter = 0; counter < files.length; counter++ )
        {
            if ( files[counter].isFile() )
            {
                String filename = files[counter].getAbsolutePath();
                if ( filterFilename ( filename ) )
                {
                    JavaSourceFiles.add ( filename );
                }
            }
            else
            {
                processDirectory ( files[counter] );
            }
        }
    }

    private static boolean filterFilename ( String filename )
    {
        return (( filename.indexOf ( ".java" ) > 0 ) && ( filename.indexOf ( "svn-base" ) < 0 ));
    }
};
