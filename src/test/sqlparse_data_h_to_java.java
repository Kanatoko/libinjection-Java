package test;

import java.io.*;

/*
 * read "sqlparse_data.h" and write Java code to System.out
 */
public class sqlparse_data_h_to_java
{
//--------------------------------------------------------------------------------
public static void main( String[] args )
throws Exception
{
BufferedReader reader = new BufferedReader( new InputStreamReader( new FileInputStream( "src/sqlparse_data.h" ) ) );
String line = null;

loop:
while( true )
	{
	line = reader.readLine();
	if( line == null )
		{
		break loop;
		}
	
	if( line.indexOf( "sql_keywords[]" ) > -1 )
		{
		while( true )
			{
			line = reader.readLine();
			if( line == null )
				{
				break loop;
				}
			//    {"ABS", 'f'},
			String pattern = "^\\s*\\{\"[^\"]+\",\\s+'.'\\}.*$";
			//p( pattern );
			if( line.matches( pattern ) )
				{
				p( line );
				}
			else if( line.indexOf( "static" ) == 0 )
				{
				break;
				}
			}
		}
	}
}
//--------------------------------------------------------------------------------
public static void p( Object o )
{
System.out.println( o );
}
//--------------------------------------------------------------------------------
}