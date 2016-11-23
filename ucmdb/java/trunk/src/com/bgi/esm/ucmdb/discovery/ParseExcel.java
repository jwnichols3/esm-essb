package com.bgi.esm.ucmdb.discovery;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Vector;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ParseExcel
{
    private Workbook workbook                   = null;
    private Map <String, List <String>> Data    = null;
    private Map <String, Integer> ColumnIndices = null;
    private List <String> ColumnNames           = null;
    private int ColumnTitleRowNum               = 0;
    private int DataStartRowIndex               = 1;

    public static void main ( String args[] )
    {
        //ParseExcel p = new ParseExcel ( "RTF Consolidated-application  infrastructure311008.xls" );
        ParseExcel p = new ParseExcel ( "RTF.xls", 2 );

        List <String> ColumnNames = p.getColumnNames();
        System.out.println ( "Num columns found 1: " + ColumnNames.size() );
        System.out.println ( "Num columns found 2: " + p.getNumColumns() );
        for ( int counter = 0; counter < ColumnNames.size(); counter++ )
        {
            System.out.println ( "Found column: "  + ColumnNames.get ( counter ) );
        }

        int num_entries = p.getNumEntries();
        int num_columns = p.getNumColumns();
        System.out.println ( "Num entries: " + num_entries );

        /*
        for (  int counter = 0; counter < num_entries; counter++ )
        {
            for ( int c = 0; c < ColumnNames.size(); c++ )
            {
                if ( c != 0 )
                {
                    System.out.print ( "," );
                }
                System.out.print ( p.getData ( ColumnNames.get ( c ), counter ) );
            }
            System.out.print ( "\n" );
        }
        //*/

        for (  int counter = 0; counter < num_entries; counter++ )
        {
            for ( int c = 0; c < ColumnNames.size(); c++ )
            {
                if ( c != 0 )
                {
                    System.out.print ( "," );
                }
                System.out.print ( p.getData ( c, counter ) );
            }
            System.out.print ( "\n" );
        }
    }

    public ParseExcel ( String filename, int column_title_row_num, int data_start_row_index )
    {
        ColumnTitleRowNum = column_title_row_num;
        DataStartRowIndex = data_start_row_index;

        init ( filename );
    }

    public ParseExcel ( String filename, int column_title_row_num )
    {
        ColumnTitleRowNum = column_title_row_num;
        DataStartRowIndex = column_title_row_num + 1;

        init ( filename );
    }

    public ParseExcel ( String filename )
    {
        init ( filename );
    }

    private void init ( String filename )
    {
        try
        {
            File file = new File ( filename );
            workbook = Workbook.getWorkbook ( file );

            Sheet sheets[] = workbook.getSheets();
            Data           = new HashMap <String, List<String>>  ();
            ColumnIndices  = new HashMap <String, Integer> ();
            ColumnNames    = new Vector <String> ();

            Sheet sheet    = sheets[0];
            int num_rows   = sheet.getRows();
            int num_cols   = sheet.getColumns();
            for ( int counter = 0;  counter < num_cols; counter++ )
            {
                String ColumnName = sheet.getCell( counter, ColumnTitleRowNum ).getContents();
                if (( null == ColumnName ) || ( ColumnName.trim().length() == 0 ))
                {
                    continue;
                }

                //  Remove new lines
                ColumnName = ColumnName.replaceAll ( "\n", " " );

                //  Remove double spaces
                /*
                String NewColumnName = null;
                do
                {
                    NewColumnName = ColumnName.replaceAll ( "  ", " " );
                }
                while ( false == ColumnName.equals ( NewColumnName ) );
                //*/

                ColumnNames.add   ( ColumnName );
                ColumnIndices.put ( ColumnName, new Integer ( counter ) );
            }

            //  Create the data placeholders
            for ( int counter = 0; counter < ColumnNames.size(); counter++ )
            {
                String ColumnName = ColumnNames.get ( counter );
                Data.put ( ColumnName, new Vector <String> () );
            }

            //  Retrieve the data
            for ( int counter = DataStartRowIndex; counter < num_rows; counter++ )
            {
                for ( int c = 0; c < ColumnNames.size(); c++ )
                {
                    String ColumnName = ColumnNames.get ( c );
                    String data       = sheets[0].getCell ( c, counter ).getContents();
                    Data.get ( ColumnName ).add ( data );;
                }
            }
        }
        catch ( IOException exception )
        {
            exception.printStackTrace();
        }
        catch ( BiffException exception )
        {
            exception.printStackTrace();
        }
    }

    public List <String> getColumnNames()
    {
        return ColumnNames;
    }

    public String getColumnName ( int index )
    {
        try
        {
            return ColumnNames.get ( index );
        }
        catch ( IndexOutOfBoundsException exception )
        {
            return null;
        }
    }

    public int getNumColumns()
    {
        return ColumnNames.size();
    }

    public String getData ( int column_index, int data_row_number )
    {
        return getData ( ColumnNames.get ( column_index ), data_row_number );
    }

    public String getData ( String column_name, int data_row_number )
    {
        List <String> dataColumn = Data.get ( column_name );
        if ( null == dataColumn )
        {
            return null;
        }

        int dataRowNum = data_row_number;
        if (( dataRowNum < 0 ) || ( dataRowNum >= dataColumn.size() ))
        {
            return null;
        }

        return dataColumn.get ( dataRowNum );
    }

    public int getColumnTitleRowNum ()
    {
        return ColumnTitleRowNum;
    }

    public int getDataStartRowIndex()
    {
        return DataStartRowIndex;
    }

    public int getNumEntries()
    {
        return Data.get( ColumnNames.get( 0 ) ).size();
    }
}
