from com.bgi.esm.ucmdb.discovery import ParseExcel
from java.lang import System

#
#  Obtain the workbook
#     The "0" means that the column values are stored in row 0
#     The "1" means that the data starts in row 1
#  Please note that row/column indices are 0-based
#  Please note that all 3 constructor calls are the same (i.e. overridden with defaults)

excel = ParseExcel ( "RTF.xls" )
#excel = ParseExcel ( "RTF.xls", 0 )
#excel = ParseExcel ( "RTF.xls", 0, 1 )


print System.getProperty ( "java.class.path" )

#  Obtain meta data about worksheet
#     Columns with no values in the specified column row (i.e. 2nd parameter of the
#     constructor call) will be considered blank and ignored
num_columns = excel.getNumColumns();
num_entries = excel.getNumEntries();
columnNames = excel.getColumnNames();

print "Num entries: " + str ( num_entries )
print "Num columns: " + str ( num_columns )

#  Retrieve column information
for c in range ( 0, num_columns ):
    print "Found column: " + excel.getColumnName ( c )

#  Set the column which would be the title row

#  Dump the worksheet
for r in range ( 0, excel.getNumEntries() ):
    output = ""
    for c in range ( 0, num_columns ):
        if ( 0 != c ):
            output = output + ","

        output = output + excel.getData ( c, r )

    print output
