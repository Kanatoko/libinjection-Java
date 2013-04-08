package test;

import com.client9.SQLParse;

public class Test
extends SQLParse
{
//--------------------------------------------------------------------------------
public static void main( String[] args )
throws Exception
{
test3();
test1();
test2();

p( "OK." );
}
//--------------------------------------------------------------------------------
private static void test3()
throws Exception
{
testParseToken( "ABS", "", "f", true );
testParseToken( " ABS", "", "f", true );
testParseToken( "123", "", "1", true );
testParseToken( "'aaa'", "", "s", true );

	//operator2
testParseToken( "<=>", "", "o", true );
testParseToken( "&&", "", "&", true );

	//parse_char
testParseToken( "(", "", "(", true );

	//parse_backslash
testParseToken( "\\N", "", "1", true );
testParseToken( "\\NABS", "ABS", "1", true );
testParseToken( "\\A", "A", "?", true );
testParseToken( "\\ABS", "ABS", "?", true );

	//parse_other
testParseToken( "[", "", "?", true );

	//parse_eol_comment
testParseToken( "#hogehoge\na", "a", "c", true );
testParseToken( "#hogehoge", "", "c", true );
testParseToken( "#hogehoge\nhogefuga", "hogefuga", "c", true );


}
//--------------------------------------------------------------------------------
private static void testParseToken( String input, String inputOut, String type, boolean result )
throws Exception
{
String[] inputBuf = new String[ 1 ];
String[] typeBuf = new String[ 1 ];
char delim = ' ';

inputBuf[ 0 ] = input;
boolean _result = parse_token( inputBuf, delim, typeBuf );
if( _result != result
 || !inputBuf[ 0 ].equals( inputOut )
 || !typeBuf[ 0 ].equals( type )
  )
	{
	ex( input + "/" + type + "/" + result  + "/" + inputBuf[ 0 ] + "/" + typeBuf[ 0 ] + "/" + _result );
	}
}
//-------------------------------------------------------------------------------
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

testParseString( "'HOGE'", '\'', "" );
testParseString( "'HOGE'A", '\'', "A" );
testParseString( "'HOGE'AA", '\'', "AA" );
testParseString( "'", '\'', "" );
testParseString( "''", '\'', "" );

testParseVar( "@", "v", 1 );
testParseVar( "@@", "v", 2 );
testParseVar( "@@A", "v", 3 );
testParseVar( "@A", "v", 2 );
testParseVar( "@-", "v", 1 );
testParseVar( "@@A-", "v", 3 );
testParseVar( "@@$A-", "v", 4 );
testParseVar( "@@_A-", "v", 4 );
testParseVar( "@@A.A-", "v", 5 );

testParseOperator2( "*/", "", 2, true );
testParseOperator2( "*/a", "", 2, true );

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

if( System.currentTimeMillis() > 0 )
	{
	return;
	}
testParseToken( "&&", "&" );
testParseToken( "&&ABS", "&f" );
testParseToken( "9&&ABS", "1&f" );
testParseToken( "12345&&ABS", "1&f" );

testParseToken( "&", "o" );
testParseToken( "&ABS", "of" );
testParseToken( "9&ABS", "1of" );
testParseToken( "12345&ABS", "1of" );
testParseToken( "abs \"*/ :?&\"", "fs" );

	//parse_char
testParseToken( ":;(),", ":;()," );

	//parse_backslash
testParseToken( "\\N", "1" );
testParseToken( "\\NABS", "1f" );
testParseToken( "\\A", "?n" );
testParseToken( "\\ABS", "?f" );

	//parse_eol_comment
testParseToken( "ABS#", "fc" );

	//parse_dash
testParseToken( "ABS--", "fc" );
testParseToken( "ABS-", "fo" );

	//parse_var
testParseToken( "@-", "vo" );

testParseToken( "ABS|", "fo" );

testParseToken( "ABS A", "fn" );
testParseToken( "A'HOGE'", "ns" );
testParseToken( "A 'HOGE'", "ns" );
testParseToken( "A 'HOGE", "ns" );
testParseToken( "'", "s" );
//testInputToPattern( "12345-", "1o" );
testParseToken( "0X1", "1"  );
testParseToken( "0X123", "1" );
testParseToken( "0X123V", "1n" );
testParseToken( ".", "n" );
testParseToken( ".A", "nn" );
testParseToken( ".123", "1" );
testParseToken( ".123V", "n" );
testParseToken( ".123 V", "1n" );
testParseToken( ".123V V", "nn" );
testParseToken( "1.2E-1", "1");
testParseToken( "1.2E-1A", "1n" );
testParseToken( "1.2E-12", "1" );
testParseToken( "1.2E+1", "1" );
testParseToken( "1.2E+1A", "1n" );
testParseToken( "1.2E+12", "1" );
testParseToken( "12345X", "n" );

	//parse_slash + mysql
testParseToken( "/", "o" );
testParseToken( "/a", "on" );
testParseToken( "//", "oo" );
testParseToken( "/*", "c" );
testParseToken( "/*a", "c" );
testParseToken( "/*abc*/", "c" );
testParseToken( "/*abc*/a", "cn" ); //TODO: reader returns 'n'
testParseToken( "/*! 123", "1" );
testParseToken( "/*! 123 abs", "1f" );
testParseToken( "/*! 123 abs */", "1f" );
testParseToken( "/*! 123 abs */4", "1f1" );

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
String[] typeBuf = new String[ 1 ];
parse_var( input, typeBuf, lengthBuf );
if( !typeBuf[ 0 ].equals( result )
 || lengthBuf[ 0 ] != length )
	{
	ex( input + "/" + result + "/" + length + "/" + typeBuf[ 0 ] + "/" + lengthBuf[ 0 ] );
	}
}
//--------------------------------------------------------------------------------
private static void testParseOperator2( String input, String result, int length )
throws Exception
{
testParseOperator2( input, result, length, false );
}
//--------------------------------------------------------------------------------
private static void testParseOperator2( String input, String result, int length, boolean inComment )
throws Exception
{
int[] lengthBuf = new int[ 1 ];
String[] typeBuf = new String[ 1 ];
parse_operator2( input, inComment, typeBuf, lengthBuf );

if( lengthBuf[ 0 ] != length
 || !result.equals( typeBuf[ 0 ] )
  )
	{
	ex( input + "/" + result + "/" + length + "/" + typeBuf[ 0 ] + "/" + lengthBuf[ 0 ] );
	}
}
//--------------------------------------------------------------------------------
private static void testParseNumber( String input, String result, int length )
throws Exception
{
int[] lengthBuf = new int[ 1 ];
String[] typeBuf = new String[ 1 ];

parse_number( input, typeBuf, lengthBuf );

if( lengthBuf[ 0 ] != length
 || !result.equals( typeBuf[ 0 ] )
  )
	{
	ex( input + "/" + result + "/" + length + "/" + typeBuf[ 0 ] + "/" + lengthBuf[ 0 ] );
	}

}
//--------------------------------------------------------------------------------
private static void testParseString( String input, char delim, String result )
throws Exception
{
int[] lengthBuf = new int[ 1 ];
parse_string( input, delim, lengthBuf );
String _result = input.substring( lengthBuf[ 0 ] );
if( !_result.equals( result ) )
	{
	ex( _result + "/" + input + "/" + result + "/" + lengthBuf[ 0 ] );
	}
}
//--------------------------------------------------------------------------------
private static void testParseToken( String input, String pattern )
throws Exception
{
if( sqli_tokenize( input ).equals( pattern ) )
	{
	//OK
	}
else
	{
	ex( input + " " + pattern );	
	}
}
//--------------------------------------------------------------------------------
private static void testParseWord( String input, String result )
throws Exception
{
int[] lengthBuf = new int[ 1 ];
String[] typeBuf = new String[ 1 ];

parse_word( input, typeBuf, lengthBuf );

input = input.substring( lengthBuf[ 0 ] );

if( !result.equals( typeBuf[ 0 ] ) )
	{
	ex( input + "/" + typeBuf[ 0 ] + "/" + lengthBuf[ 0 ] );
	}

}
//--------------------------------------------------------------------------------
}