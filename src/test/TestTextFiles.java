package test;

import java.io.*;

import com.client9.libinjection.SQLParse;

public class TestTextFiles
extends SQLParse
{
//--------------------------------------------------------------------------------
public static void main( String[] args )
throws Exception
{
BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
String line = null;
while( true )
	{
	line = reader.readLine();
	if( line == null )
		{
		break;
		}
	String fileName = line;
	byte[] buf = new byte[ 1024 ];
	int length = ( new FileInputStream( fileName ) ).read( buf );
	String content = new String( buf, 0, length, "US-ASCII" );
	String[] array = content.split( "(\\r|\\n)+" );
	
	String test	= null;
	String input	= null;
	StringBuffer expectedBuf = new StringBuffer();
	
	for( int i = 0; i < array.length; ++i )
		{
		if( array[ i ].equals( "--TEST--" ) )
			{
			test = array[ i + 1 ];
			++i;
			}
		else if( array[ i ].equals( "--INPUT--" ) )
			{
			input = array[ i + 1 ];
			}
		else if( array[ i ].equals( "--EXPECTED--" ) )
			{
			++i;
			for( ; i < array.length; ++i )
				{
				expectedBuf.append( array[ i ].charAt( 0 ) );
				}
			}
		}
	
	String expected = expectedBuf.toString();
	
	p( "test:" + test );
	p( "input:" + input + "<<" );
	
	String parsed = sqli_tokenize( input );
	p( "parsed   : " + parsed );
	p( "expected : " + expected );
	
	if( parsed.equals( expected ) )
		{
		p( "=== MATCH ===:" + fileName );
		}
	else
		{
		p( "*** NOT MATCH ****: " + fileName );
		}
	p( "" );
	}
}
//--------------------------------------------------------------------------------
public static void p( Object o )
{
System.out.println( o );
}
//--------------------------------------------------------------------------------
}