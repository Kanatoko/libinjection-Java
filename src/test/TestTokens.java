package test;

import java.io.*;
import java.util.*;

import com.client9.libinjection.SQLParse;

/*
 * Test tests/test-tokens-*.txt
 * No folding
 */
public class TestTokens
extends SQLParse
{
//--------------------------------------------------------------------------------
public static void main( String[] args )
throws Exception
{
debug = true;
BufferedReader reader = new BufferedReader( new InputStreamReader( System.in, "ISO-8859-1" ) );
String stdin;
while( true )
	{
	stdin = reader.readLine();
	if( stdin == null )
		{
		return;
		}
	final byte[] buf = new byte[ 1024 * 20 ]; //20KB
	String fileContent = new String( buf, 0, ( new FileInputStream( stdin ) ).read( buf ), "ISO-8859-1" );
	List lines = Arrays.asList( fileContent.split( "[\\r\\n]+" ) );
	//p( lines );
	String cToken = "";
	String javaToken = "dummy";
	LinkedList valueList = new LinkedList();
	for( int i = 0; i < lines.size(); ++i )
		{
		String line = ( String )lines.get( i );
		if( line.equals( "--INPUT--" ) )
			{
			StringBuffer inputBuf = new StringBuffer();
			for( int k = i;; ++k )
				{
				String nextLine = ( String )lines.get( k + 1 );
				if( nextLine.equals( "--EXPECTED--" ) )
					{
					break;
					}
				else
					{
					if( inputBuf.length() > 0 )
						{
						inputBuf.append( "\n" );
						}
					inputBuf.append( nextLine );
					}
				}
			String input = inputBuf.toString();
			p( "input:" + input );
			String[] allTokenBuf = new String[ 1 ];
			tokenize( input, valueList, allTokenBuf );
			javaToken = allTokenBuf[ 0 ].replaceAll( "w+", "" );
			p( "Java:\t" + javaToken );
			p( valueList );
			}
		else if( line.equals( "--EXPECTED--" ) )
			{
			StringBuffer strBuf = new StringBuffer();
			++i;
			for( ;i  < lines.size(); ++i  )
				{
				line = ( String )lines.get( i );
				strBuf.append( line.charAt( 0 ) );
				}
			cToken = strBuf.toString();
			p( "C:\t" + cToken );
			if( cToken.equals( javaToken ) )
				{
				p( "==== OK ==== : " + stdin );
				}
			else
				{
				p( "Error: " + stdin );
				p( fileContent );
				System.exit( 1 );
				}
			}
		else
			{
			//p( line );
			}
		}
	}
}
//--------------------------------------------------------------------------------
public static void ex()
throws Exception
{
throw new Exception();
}
//--------------------------------------------------------------------------------
public static void p( Object o )
{
System.out.println( o );
}
//--------------------------------------------------------------------------------
}