package test;

import java.io.*;
import java.util.*;

import com.client9.libinjection.SQLParse;

/*
 * Test tests/test-sqli-001.txt
 */
public class TestSqli
extends SQLParse
{
//--------------------------------------------------------------------------------
public static void main( String[] args )
throws Exception
{
debug = true;
BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
String stdin;
while( true )
	{
	stdin = reader.readLine();
	if( stdin == null )
		{
		return;
		}
	final byte[] buf = new byte[ 1024 * 20 ]; //20KB
	String fileContent = new String( buf, 0, ( new FileInputStream( stdin ) ).read( buf ) );
	List lines = Arrays.asList( fileContent.split( "[\\r\\n]+" ) );
	//p( lines );
	String cToken = "";
	String javaFoldedToken = "dummy";
	LinkedList valueList = new LinkedList();
	boolean isSqli = false;
	for( int i = 0; i < lines.size(); ++i )
		{
		String line = ( String )lines.get( i );
		if( line.equals( "--INPUT--" ) )
			{
			String input = ( String )lines.get( i + 1 );
			p( "input:" + input );
			isSqli = isSQLi( input );
			}
		else if( line.equals( "--EXPECTED--" ) )
			{
			++i;
			
			p( "isSqli:" + isSqli );
				
			if( lines.size() > i )
				{
					//is sqli
				line = ( String )lines.get( i );
				if( isSqli )
					{
					p( "==== OK ==== 1 " + stdin );
					}
				else
					{
					p( "********  NG ******* 1 " + stdin );
					p( fileContent );
					return;
					}
				}
			else
				{
				if( isSqli )
					{
					p( "****** NG2 ******** " + stdin );
					p( fileContent );
					return;
					}
				else
					{
					p( "==== OK ==== 2 " + stdin );
					}
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