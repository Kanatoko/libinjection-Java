package test;

import java.io.*;
import java.util.*;

import com.client9.libinjection.SQLParse;

public class Test
extends SQLParse
{
//--------------------------------------------------------------------------------
public static void main( String[] args )
throws Exception
{
debug = true;
p( sqli_tokenize( "SELECT" ) );
/*
testParseToken( "A00-00A-00A", "nno1n" );

/* Bug in C impl? #25
testParseToken( "FOO & 1", "n" );
testParseToken( "FOO& 1", "n" );
testParseToken( "FOO&1", "n" );
*/

/*
testParseToken( "LIKE & 1", "oo1" );
testParseToken( "LIKE&1", "oo1" );
testParseToken( "1&1", "1" );
testParseToken( "1 & 1", "1" );
/*
testParseToken( "a&a", "n" );
/*
testParseToken( "a&a1", "n" );
testParseToken( "A - A", "n" );
testParseToken( "A-A", "n" );
*/
//testParseToken( "HAVING a a @a - a", "knnvo" );
//testParseToken( "COS MEDIUMINT IN #", "fknc" );
//testParseToken( "LEFT OUTER CROSS JOIN", "fnk" );
/*
test3();
test1();
test2();
*/

p( "OK." );
}
//--------------------------------------------------------------------------------
private static void test3()
throws Exception
{
testParseToken( "ABS", "", "f" );
//testParseToken( " ABS", "", "f" );
testParseToken( "123", "", "1" );
testParseToken( "'aaa'", "", "s" );

	//operator2
testParseToken( "<=>", "", "o" );
testParseToken( "&&", "", "&" );

	//parse_char
testParseToken( "(", "", "(" );

	//parse_backslash
testParseToken( "\\N", "", "1" );
testParseToken( "\\NABS", "ABS", "1" );
testParseToken( "\\A", "A", "?" );
testParseToken( "\\ABS", "ABS", "?" );

	//parse_other
testParseToken( "[", "", "?" );

	//parse_eol_comment
testParseToken( "#hogehoge\na", "a", "c" );
testParseToken( "#hogehoge", "", "c" );
testParseToken( "#hogehoge\nhogefuga", "hogefuga", "c" );

	//parse_dash
testParseToken( "-a", "a", "o" );
testParseToken( "--a", "", "c" );
testParseToken( "--a\nhoge", "hoge", "c" );

	//parse_white
testParseToken( " ", "", "" );

	//parse_char
testParseToken( ",", "", "," );

}
//--------------------------------------------------------------------------------
private static void testParseToken( String input, String inputOut, String type )
throws Exception
{
String[] inputBuf = new String[ 1 ];
String[] typeBuf = new String[ 1 ];
boolean[] inCommentBuf = new boolean[ 1 ];
inCommentBuf[ 0 ] = false;
char delim = ' ';

inputBuf[ 0 ] = input;
parse_token( inputBuf, inCommentBuf, typeBuf );
if( false
 || !inputBuf[ 0 ].equals( inputOut )
 || !typeBuf[ 0 ].equals( type )
  )
	{
	ex( input + "/" + type + "/" + inputBuf[ 0 ] + "/" + typeBuf[ 0 ] );
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
testParseNumber( "12345X", "1", 5 );
testParseNumber( ".123", "1", 4 );
testParseNumber( ".123V", "1", 4 );

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
//testParseToken( "ABS#", "fc" );

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
testParseToken( ".123V", "1n" );
testParseToken( ".123 V", "1n" );
testParseToken( ".123V V", "1nn" );
testParseToken( "1.2E-1", "1");
testParseToken( "1.2E-1A", "1n" );
testParseToken( "1.2E-12", "1" );
testParseToken( "1.2E+1", "1" );
testParseToken( "1.2E+1A", "1n" );
testParseToken( "1.2E+12", "1" );
testParseToken( "12345X", "1n" );

	//parse_slash + mysql
testParseToken( "/", "o" );
testParseToken( "/a", "on" );
testParseToken( "//", "oo" );
testParseToken( "/*", "c" );
testParseToken( "/*a", "c" );
testParseToken( "/*abc*/", "c" );
testParseToken( "/*abc*/a", "n" );
testParseToken( "/*! 123", "1" );
testParseToken( "/*! 123 abs", "1f" );
testParseToken( "/*! 123 abs */", "1f" );
testParseToken( "/*! 123 abs */4", "1f1" );
testParseToken( "'189'))/**/or/**/1=@@version------snip----", "s))&1" );

testParseToken( "111 abs 222 abs 333 abs 444 abs 555 abs", "1f1f1" );

	//comment
testParseToken( "/*hoge*/abs/*fuga*/1", "f1" );
testParseToken( "/*hoge*/abs/*fuga*/1/*gyoe*/", "f1c" );

	//multikeywords
testParseToken( "1 alter table", "1k" );
testParseToken( "1 in boolean mode", "1k" );
testParseToken( "in boolean abs", "nf" );
testParseToken( "abs abs abs abs GROUP BY 1", "ffffB" );
testParseToken( "NOT IN CURRENT_DATECURRENT_TIME ~ ALTER 1&nov WITH ~", "ofok1" );
testParseToken( "NOT IN", "o" );

	//string
testParseToken( "1'hoge' 'hoge'1","1s1" );

	//operator is followed by a unary operator
testParseToken( "1 case 1", "1o1" );
testParseToken( "1 case not 1", "1o1" );

testParseToken( "SELECT 1 IN BOOLEAN MODE;", "k1k;" );

	//fold
testParseToken( "abs abs abs abs -1", "ffffo" );
testParseToken( "abs abs abs ( -1", "fff(1" );
testParseToken( "1001 RLIKE ((-1))", "1o((1" );
testParseToken( "1001 RLIKE ((-1)) UNION SELECT 1 FROM CREDIT_CARDS", "1o((1" );
testParseToken( "1=-1", "1o1" );
testParseToken( "1-1", "1" );
testParseToken( "1-1-1-1-1-1-1-1-1 OR 1=1--", "1&1o1" );
testParseToken( "-select", "k" );
testParseToken( "-1", "1" );
testParseToken( "(1", "1" );
testParseToken( "(abs", "f" );
testParseToken( "((abs", "f" );
testParseToken( "(((abs", "f" );

testParseToken( "SELECT 1/2;--", "k1;c" );
testParseToken( "SELECT 1/2;", "k1;" );

testParseToken( "SELECT \\N;", "k1;" );

	// fix up for ambigous "IN"
testParseToken( "in abs", "ff" );
testParseToken( "in aaa", "nn" );

testParseToken( "SELECT 123E", "k1" );
testParseToken( "SELECT 123E abs", "k1f" );
testParseToken( "SELECT 1.2E34;", "k1;" );
testParseToken( "SELECT 1 / 2;", "k1;" );
testParseToken( "SELECT 123456789.12345678912345678 + 1;", "k1;" );

//testParseToken( "SELECT ~ 1;", "k1;" ); //TODO: c version returns ko1;
testParseToken( "SELECT ~ 1;", "ko1;" );
//testParseToken( "abs -1", "f1" ); //TODO: c version returns fo1
testParseToken( "abs -1", "fo1" );

testParseToken( "SELECT 1E2;", "k1;" );
testParseToken( "SELECT 1E;", "k1;" );
testParseToken( "SELECT .123e1;", "k1;" );

	// is SQLi?
testIsSQLi( "1 or 1=1--", true );
testIsSQLi( "1 or 'a'='a';--hogefuga", true );
testIsSQLi( "foo' or 1=1--", true );
testIsSQLi( "foo\" or 1=1--", true );

testParseToken( "FOO FOO FOO UNION && NOT BETWEEN", "nnnU&" );
testParseToken( "FOO FOO IN BOOLEAN MODE", "nnk" );
testParseToken( "FOO FOO IN BOOLEAN MODE 1", "nnk1" );
testParseToken( "FOO FOO IN BOOLEAN MODE UNION &&", "nnkU&" );

	//* o v o 
testIsSQLi( "1 & a = 1", false );


testParseToken( "n k << o IN BOOLEAN SOUNDS LIKE", "nnonn" );

	//libinjection issue#15
testParseToken( "LEFT JOIN", "k" );
testParseToken( "LEFT", "n" );

testParseToken( "SELECT 1 /*!12XXXXXXXXXXXX", "k11n" );

testParseToken( "0/*!unIoN seLeCt 1,concAt(0x4040,optiOn_vAlue,0x4040),3,4,5,6,7 frOm wp_options where option_name=0x617574685f73616c74*/--", "1Uk1," );
testParseToken( "select/*!32302 1, */1", "k1,1" );

testIsSQLi( "true#false", false );
testIsSQLi( "1--", true );
testIsSQLi( "1--1", false );

testParseToken( "LIKE.", "on" );
testParseToken( "LIKE", "o" );
testParseToken( "''AND 1.-1LIKE.1'", "s&1o1" );
testParseToken( "1001 RLIKE ((-\"1\")) UNION SELECT 1 FROM CREDIT_CARDS", "1o((s" );
testParseToken( "abs(-abs", "f(f" );
testIsSQLi( "/* /* */", true );

}
//--------------------------------------------------------------------------------
private static void testIsSQLi( String input, boolean isSqli )
throws Exception
{
if( isSQLi( input ) != isSqli )
	{
	ex( input );
	}
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
boolean[] inCommentBuf = new boolean[ 1 ];
inCommentBuf[ 0 ] = inComment;
parse_operator2( input, inCommentBuf, typeBuf, lengthBuf );

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
private static void testParseToken( String input, String expected )
throws Exception
{
String result = sqli_tokenize( input );
if( expected.equals( result ) )
	{
	//OK
	}
else
	{
	ex( input + "/" + expected + "/" + result );	
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
