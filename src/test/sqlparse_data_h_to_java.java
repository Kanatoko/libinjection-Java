package test;

import java.awt.Font;
import java.io.*;

import com.client9.Util;

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

String mapName = null;
final String pattern = "^\\s*\\{\"[^\"]+\",\\s+'.'\\}.*$";
int k = 0;
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
		mapName = "sqlKeywords";
		//p( "Map sqlKeywords = new HashMap( 500 );" );
		}
	else if( line.indexOf( "multikeywords[]" ) > -1 )
		{
		mapName = "multiKeywords";
		p( "" );
		//p( "Map multiKeywords = new HashMap( 50 );" );
		}
	else if( line.matches( pattern ) )
		{
		String key   = Util.getMatch( "\"([^\"]+)\"", line );
		String value = Util.getMatch( "'([^\"]+)'", line );
		p( mapName + ".put( \"" + key + "\", \"" + value + "\" );" );
		}
	else if( line.indexOf( "char_parse_map[]" ) > -1 )
		{
		p( "" );
		}
	else if( line.indexOf( "&parse" ) > -1 )
		{
		char ch = ( char )k;
		String s = "";
		if( Character.isLetterOrDigit( ch ) || ( ch >= 32 && ch <= 126 ) ) 
			{
			s = "	/* " + ( char )k + " */";
			}
		p( "pt2Function.add( \"" + Util.getMatch( "(parse[^,]+),", line ) + "\");" + s );
		++k;
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