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
testInputToPattern( "abs \"*/ :?&\"", "fs" );

	//parse_char
testInputToPattern( ":;(),", ":;()," );

	//parse_backslash
testInputToPattern( "\\N", "1" );
testInputToPattern( "\\NABS", "1f" );
testInputToPattern( "\\A", "?n" );
testInputToPattern( "\\ABS", "?f" );

	//parse_eol_comment
testInputToPattern( "ABS#", "fc" );

	//parse_dash
testInputToPattern( "ABS--", "fc" );
testInputToPattern( "ABS-", "fo" );

	//parse_var
testInputToPattern( "@-", "vo" );

	//is_mysql_comment
testMySqlComment( "/*", 0 );
testMySqlComment( "/*a", 0 );
testMySqlComment( "/*!", 3 );
testMySqlComment( "/*!a", 3 );
testMySqlComment( "/*!1", 4 );
testMySqlComment( "/*!1a", 4 );
testMySqlComment( "/*!12oo", 4 );
testMySqlComment( "/*!12ooo", 3 ); //TODO: why 3?
testMySqlComment( "/*!1234", 4 );
testMySqlComment( "/*!12345", 8 );

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

testParseVar( "@", "v", 1 );
testParseVar( "@@", "v", 2 );
testParseVar( "@@a", "v", 3 );
testParseVar( "@a", "v", 2 );
testParseVar( "@-", "v", 1 );
testParseVar( "@@a-", "v", 3 );
testParseVar( "@@$a-", "v", 4 );
testParseVar( "@@_a-", "v", 4 );
testParseVar( "@@a.a-", "v", 5 );
}
//--------------------------------------------------------------------------------
private static void testMySqlComment( String input, int result )
throws Exception
{
if( result != is_mysql_comment( input ) )
	{
	ex( input + " " + result );
	}
}
//--------------------------------------------------------------------------------
private static void testParseVar( String input, String result, int length )
throws Exception
{
int[] lengthBuf = new int[ 1 ];
if( !parse_var( input, lengthBuf ).equals( result ) )
	{
	ex( input + " " + result + " " + length );
	}
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