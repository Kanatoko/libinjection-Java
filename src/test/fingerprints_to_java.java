package test;

import java.awt.Font;
import java.io.*;

/*
 * read "fingerprints.txt" and write Java code to System.out
 */
public class fingerprints_to_java
{
//--------------------------------------------------------------------------------
public static void main( String[] args )
throws Exception
{
BufferedReader reader = new BufferedReader( new InputStreamReader( new FileInputStream( "src/fingerprints.txt" ) ) );
String line = null;

loop:
while( true )
	{
	line = reader.readLine();
	if( line == null )
		{
		break loop;
		}
	p( "\"" + line + "\"," );
	}
}
//--------------------------------------------------------------------------------
public static void p( Object o )
{
System.out.println( o );
}
//--------------------------------------------------------------------------------
}