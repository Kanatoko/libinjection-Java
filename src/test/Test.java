package test;

import com.client9.SQLParse;

public class Test
extends SQLParse
{
//--------------------------------------------------------------------------------
public static void main( String[] args )
throws Exception
{
test1();
test2();

p( "OK." );
}
//------------------------------------------0-------------------------------------
private static void ex( String message )
throws Exception
{
throw new Exception( message );
}
//--------------------------------------------------------------------------------
private static void test1()
throws Exception
{
testParseWord( "a", "n" );
testParseWord( "ab", "n" );
testParseWord( "abs", "f" );
testParseWord( "absa", "n" );
}
//--------------------------------------------------------------------------------
private static void test2()
throws Exception
{
testInputToPattern( "abs a", "fn" );
testParseString( "'hoge'", '\'', 5 );
testParseString( "''", '\'', 1 );
testParseString( "'", '\'', 0 );
}
//--------------------------------------------------------------------------------
private static void testParseString( String input, char delim, int result )
throws Exception
{
if( parse_string( input, delim ) != result )
	{
	ex( input + " " + result );
	}
}
//--------------------------------------------------------------------------------
private static void testInputToPattern( String input, String pattern )
throws Exception
{
if( inputToPattern( input ).equals( pattern ) )
	{
	//OK
	}
else
	{
	ex( input + " " + pattern );	
	}
}
//--------------------------------------------------------------------------------
private static void testParseWord( String input, String pattern )
throws Exception
{
int[] lengthBuf = new int[ 1 ];
if( parse_word( input, lengthBuf ).equals( pattern ) )
	{
	//OK
	}
else
	{
	ex( input + " " + pattern );
	}
}
//--------------------------------------------------------------------------------
}