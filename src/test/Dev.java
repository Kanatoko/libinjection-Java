package test;

import java.util.*;
import java.io.*;
import com.client9.libinjection.*;

public class Dev
extends SQLParse
{
//--------------------------------------------------------------------------------
public static void main( String[] args )
throws Exception
{
debug = true;
initialize();

StringBuffer buf = new StringBuffer();
for( int i = 0; i < 20000; ++i )
	{
	buf.append( "FOOOOO," );
	}
isSQLi( buf.toString() );
p( "OK" );

bruteForce();
bruteForce();
bruteForce();
bruteForce();
bruteForce();

/*
List valueList = new ArrayList();
String[] allTokenBuf = new String[ 1 ];
tokenize( "1+1 UNION", valueList, allTokenBuf, SQL_MYSQL, true );
allTokenBuf[ 0 ] = allTokenBuf[ 0 ].replaceAll( "w+", "" );
p( valueList );
p( allTokenBuf[ 0 ] );
/*
String foldedToken = fold( valueList, allTokenBuf[ 0 ] );
p( "----" );
p( foldedToken );
p( valueList );
/*
bruteForce();
/*
Iterator p = smap.keySet().iterator();
while( p.hasNext() )
	{
	String key = ( String )p.next();
	if( key.length() <= 2 )
		{
		p( key );
		}
	}
*/

//bruteForce();
/*
int i = parseString( "'ho''e'", '\'', true );
p( i );
/*
int flags  = QUOTE_SINGLE | SQL_MYSQL;
p( flags & QUOTE_SINGLE );
p( flags & QUOTE_NONE );
p( flags & QUOTE_DOUBLE );
p( flags & SQL_ANSI );
p( flags & SQL_MYSQL );
/*

String s = "\\\na";
s = replaceAll( s, "\\\\", "" );
p( s );
/*
String input = "SELECT '12\\'3'";
int i = parseString( input, '\'' );
p( input.substring( 0, i ) );

/*

String[] allTokenBuf = new String[ 1 ];
tokenizeWithWhite( "select and", allTokenBuf );
p( allTokenBuf[ 0 ] );
/*
String[] tokenBuf = new String[ 1 ];
String[] processed = new String[ 1 ];
tokenizeOne( " ", processed, tokenBuf );
p( tokenBuf[ 0 ] );
p( processed[ 0 ] );

/*
Iterator p = map.keySet().iterator();
while( p.hasNext() )
	{
	String s = p.next().toString();
	int length = ( s.length() );
	if( length > 26 )
		{
		p( s );
		}
	}
*/
}
//--------------------------------------------------------------------------------
public static void bruteForce()
throws Exception
{
Random r = new Random();
byte[] buf = new byte[ 1024 ];

String[] processed = new String[ 1 ];

debug = false;
int count = 50000;

long start = System.currentTimeMillis();
for( int i = 0; i < count; ++i )
	{
	r.nextBytes( buf );
	String s = new String( buf, "ISO-8859-1" );
	
	try
		{
		List valueList = new ArrayList();
		tokenize( s, valueList, processed, 0, true );
		}
	catch( Exception e )
		{
		FileOutputStream out = new FileOutputStream( "1" );
		out.write( buf );
		out.close();
		e.printStackTrace();
		throw e;
		}
	if( ( i % 1000 ) == 0 )
		{
		p( i + ":" + processed[ 0 ] );
		}
	}

long took = System.currentTimeMillis() - start;

double average = ( double )took / ( double )count;
p( "average: " + average + " ms" );

}
//--------------------------------------------------------------------------------
public static void p( Object o )
{
System.out.println( "--" + o + "--" );
}
//--------------------------------------------------------------------------------
public static void ex()
throws Exception
{
throw new Exception();
}
//--------------------------------------------------------------------------------
public static void ex( Object o )
throws Exception
{
throw new Exception( o + "" );
}
//--------------------------------------------------------------------------------
}