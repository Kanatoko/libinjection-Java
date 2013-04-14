package test;

import java.io.*;
import java.util.*;

/**
 * Test compatibility with C implementation.
 * First generating random string, then compare the tokenized string of C impl and Java impl.
 */
public class Fuzzer
extends Test
{
//--------------------------------------------------------------------------------
public static void main( String[] args )
throws Exception
{
debug = true;

while( true )
	{
	if( !randomCheck() )
		{
		break;
		}
	}
}
//--------------------------------------------------------------------------------
private static boolean randomCheck()
throws Exception
{
debug = false;
String randomStr = genRandomStr();
String result = getCImplResult( randomStr );

String[] array = result.split( "\n" );
StringBuffer buf = new StringBuffer();
for( int i = 0; i < array.length; ++i )
	{
	buf.append( array[ i ].charAt( 0 ) );
	if( i == 4 )
		{
		break;
		}
	}

String cResult= null, jResult = null;
try
	{
	cResult = buf.toString();
	jResult = sqli_tokenize( randomStr );
	debug = true;
	if( !cResult.equals( jResult ) )
		{
		p( "******** NOT MATCH ********" );
		p( randomStr );
		p( "C: " + cResult );
		p( "J: " + jResult );
		return false;
		}
	return true;	
	}
catch( Exception e )
	{
	debug = true;
	p( randomStr );
	p( cResult );
	e.printStackTrace();
	return false;
	}
}
// --------------------------------------------------------------------------------
public static String getCommandResult( String[] cmdArray )
throws Exception
{
Process process = Runtime.getRuntime().exec( cmdArray );
InputStream in = process.getInputStream();
byte[] buf = new byte[ 1024 * 8 ];
int r = in.read( buf );

process.destroy();

return new String( buf, 0, r );
}
//--------------------------------------------------------------------------------
private static String getCImplResult( String s )
throws Exception
{
final String sqliLocation = "/usr/local/bin/sqli"; // compiled sqlii_cli.c location.
String result = getCommandResult( new String[]{
	sqliLocation,
	"-f",
	s
});

return result;
}
//--------------------------------------------------------------------------------
private static String genRandomStr()
throws Exception
{
Collection[] collArray = new Collection[]{
sqlKeywords.keySet(),
multiKeywords.keySet(),
multiKeywordsStart,
operators2,
multikeywordsFirstWordTypeSet,
unaryOpSet,
arithOpSet,
fingerprints,
Arrays.asList( new String[]{ "\"", "'", "foo", "bar", "@a", "(", ")", "/", "/*", "*/", "//", "#", "--", "\n" } )
};

StringBuffer buf = new StringBuffer();
Random r = new Random();
int length = 3 + r.nextInt( 10 );
for( int i = 0; i < length; ++i )
	{
	Collection coll = collArray[ r.nextInt( collArray.length ) ];
	int index = r.nextInt( coll.size() );
	if( i > 0 )
		{
		buf.append( " " );
		}
	buf.append( coll.toArray()[ index ] );
	}

return buf.toString();
}
//--------------------------------------------------------------------------------
}