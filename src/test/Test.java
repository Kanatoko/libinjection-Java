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
testParseWord( "A", "n" );
testParseWord( "AB", "n" );
testParseWord( "ABS", "f" );
testParseWord( "ABSA", "n" );
}
//--------------------------------------------------------------------------------
private static void test2()
throws Exception
{
testParseOperator2( "<=>", "o", 3 );
testParseOperator2( "*=", "o", 2 );
testParseOperator2( "!!", "o", 2 );
testParseOperator2( "!<", "o", 2 );
testParseOperator2( "!=", "o", 2 );
testParseOperator2( "!>", "o", 2 );
testParseOperator2( "!~", "o", 2 );
testParseOperator2( "%=", "o", 2 );
testParseOperator2( "&&", "&", 2 );
testParseOperator2( "&=", "o", 2 );
testParseOperator2( "*=", "o", 2 );
testParseOperator2( "+=", "o", 2 );
testParseOperator2( "-=", "o", 2 );
testParseOperator2( "/=", "o", 2 );
testParseOperator2( ":=", "o", 2 );
testParseOperator2( "<<", "o", 2 );
testParseOperator2( "<=", "o", 2 );
testParseOperator2( "<>", "o", 2 );
testParseOperator2( "<@", "o", 2 );
testParseOperator2( ">=", "o", 2 );
testParseOperator2( ">>", "o", 2 );
testParseOperator2( "@>", "o", 2 );
testParseOperator2( "^=", "o", 2 );
testParseOperator2( "|/", "o", 2 );
testParseOperator2( "|=", "o", 2 );
testParseOperator2( "||", "&", 2 );
testParseOperator2( "~*", "o", 2 );

testParseNumber( "12345-", "1", 5 );
testParseNumber( "0X1", "1", 3 );
testParseNumber( "0X123", "1", 5 );
testParseNumber( "0X123V", "1", 5 );
testParseNumber( ".", "n", 1 );
testParseNumber( ".A", "n", 1 );
testParseNumber( "1.2E-1", "1", 6 );
testParseNumber( "1.2E-1A", "1", 6 );
testParseNumber( "1.2E-12", "1", 7 );
testParseNumber( "1.2E+1", "1", 6 );
testParseNumber( "1.2E+1A", "1", 6 );
testParseNumber( "1.2E+12", "1", 7 );
testParseNumber( "12345X", "n", 6 );
testParseNumber( ".123", "1", 4 );
testParseNumber( ".123V", "n", 5 );

testInputToPattern( "&&", "&" );
testInputToPattern( "&&ABS", "&f" );
testInputToPattern( "9&&ABS", "1&f" );
testInputToPattern( "12345&&ABS", "1&f" );

testInputToPattern( "&", "o" );
testInputToPattern( "&ABS", "of" );
testInputToPattern( "9&ABS", "1of" );
testInputToPattern( "12345&ABS", "1of" );

testInputToPattern( "ABS|", "fo" );

testInputToPattern( "ABS A", "fn" );
testInputToPattern( "A'HOGE'", "ns" );
testInputToPattern( "A 'HOGE'", "ns" );
testInputToPattern( "A 'HOGE", "ns" );
testInputToPattern( "'", "s" );
//testInputToPattern( "12345-", "1o" );
testInputToPattern( "0X1", "1"  );
testInputToPattern( "0X123", "1" );
testInputToPattern( "0X123V", "1n" );
testInputToPattern( ".", "n" );
testInputToPattern( ".A", "nn" );
testInputToPattern( ".123", "1" );
testInputToPattern( ".123V", "n" );
testInputToPattern( ".123 V", "1n" );
testInputToPattern( ".123V V", "nn" );
testInputToPattern( "1.2E-1", "1");
testInputToPattern( "1.2E-1A", "1n" );
testInputToPattern( "1.2E-12", "1" );
testInputToPattern( "1.2E+1", "1" );
testInputToPattern( "1.2E+1A", "1n" );
testInputToPattern( "1.2E+12", "1" );
testInputToPattern( "12345X", "n" );

testParseString( "'HOGE'", '\'', "" );
testParseString( "'HOGE'A", '\'', "A" );
testParseString( "'HOGE'AA", '\'', "AA" );
testParseString( "'", '\'', "" );
testParseString( "''", '\'', "" );
}
//--------------------------------------------------------------------------------
private static void testParseOperator2( String input, String result, int length )
throws Exception
{
int[] lengthBuf = new int[ 1 ];
if( !parse_operator2( input, lengthBuf ).equals( result ) )
	{
	ex( input + " " + result + " " + length );
	}
}
//--------------------------------------------------------------------------------
private static void testParseNumber( String input, String result, int length )
throws Exception
{
int[] lengthBuf = new int[ 1 ];
if( !parse_number( input, lengthBuf ).equals( result )
 || lengthBuf[ 0 ] != length
 )
	{
	ex( input + " " + result + " " + length );
	}
}
//--------------------------------------------------------------------------------
private static void testParseString( String input, char delim, String result )
throws Exception
{
if( !parse_string( input, delim ).equals( result ) )
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