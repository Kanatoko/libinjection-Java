package test;

import java.util.*;
import com.client9.libinjection.*;

@SuppressWarnings("rawtypes")
public class TestJava
extends SQLParse
{
//--------------------------------------------------------------------------------
public static void main( String[] args )
{
initialize();
debug = true;
try
	{
	//testIsSqli( "1+1 UNION", true );
	//if( System.currentTimeMillis() > 0 ){ return; }

	testIsSqli();
	testFold();
	testTokenizeWithWhite();
	testWordIndex();
	testDollar();
	testParseString();
	testParseSingleQuoteString();
	testTokenizeOne();
	testParseComment();
	p( "================ OK ================" );
	}
catch( Exception e )
	{
	e.printStackTrace();
	System.exit( 1 );
	}
}
//--------------------------------------------------------------------------------
public static void testWordIndex()
throws Exception
{
testWordIndex( "", 0 );
testWordIndex( "a", 1 );
testWordIndex( "@", 0 );
testWordIndex( "foo", 3 );
testWordIndex( "foo ", 3 );
testWordIndex( "foo;", 3 );
testWordIndex( "foo'", 3 );
}
//--------------------------------------------------------------------------------
public static void testWordIndex( final String input, int expected )
throws Exception
{
int result = getWordLength( input );
p( "input:" + input );
p( "result:" + result );
p( "expected:" + expected );

if( result!= expected ){ ex(); }
}
//--------------------------------------------------------------------------------
public static void testFold()
throws Exception
{
testFold( "F331802,F375984,F191692,F247942,F413537", "n" );
testFold( "curl/7.15+ (x64-criteo) libcurl/7.15+ OpenSSL zlib libidn", "nnnn" );
testFold( "/foo/", "o" );
testFold( "- - - foo", "n" );
testFold( "foo/bar/as/123", "n" );
testFold( "foo/bar/as/1a", "nn" );
testFold( "foo/bar", "n" );
testFold( "foo/bar/as", "nok" );
testFold( "foo/1.1", "n" );
testFold( "foo/1.1.1", "n1" );
testFold( "1.1 - (DeleGate/9.9.8-pre20)", "1o(n1" );
testFold( "image/png, image/svg+xml, image/*;q=0.8, */*;q=0.5", "no;n" );
testFold( "foo, bar + BZ", "n" );
testFold( "\"remind\" and not 3=9-- a", "s&1c" );
testFold( "1+1 UNION", "1U" );
testFold( "foo,foo", "n" );
testFold( "SELECT '1'::money,", "Es," );
testFold( "SELECT '1'::money", "Es" );
testFold( " '1'::money", "s" );
testFold( ",-sin(5)", ",f(1)" );
testFold( "-sin(5)", "f(1)" );
testFold( "foo + foo", "n" );
//testFold( "foo == foo", "n" );
//testFold( "'a' foo === 1", "sn" );
//testFold( "'1' == -1 OR 1", "so1&1" );
testFold( "'a' == 1", "soo1" );
testFold( "'a' + = 1", "soo1" );
testFold( "'a' + + 1", "so1" );
testFold( "91),(2", "1" );
testFold( "foo  + ( bar ) 1", "n1" );
testFold( "foo  + ( 9 ) 1", "n1" );
}
//--------------------------------------------------------------------------------
public static void testFold( final String input, final String expectedToken )
throws Exception
{
List valueList = new ArrayList();
String[] allTokenBuf = new String[ 1 ];
tokenize( input, valueList, allTokenBuf );
allTokenBuf[ 0 ] = allTokenBuf[ 0 ].replaceAll( "w+", "" );
String foldedToken = fold( valueList, allTokenBuf[ 0 ] );
p( valueList );

if(! foldedToken.equals( expectedToken ) )
	{
	p( foldedToken );
	p( expectedToken );
	ex();
	}
}
//--------------------------------------------------------------------------------
public static void testIsSqli()
throws Exception
{
testIsSqli( "+/zXWqkSECEqv+2l/VBqizw0iLx/j29GoQJqKhgD8m0Ri6jYsrEAJ0xy3Vnl8WGu/S8YFcz2uYpwbtjb8c5UH9JTzw0e6kOEQcjtgp54OUIUXUBbv5xHEy6XziawyB6kC6lWTzVoPXVBhcg2bhfvtseD63YVjc8rlV2dYk6LlY0yQeDS9Sq2Qk21Vp1vmQnejnE9D+YLCRhJgZqGlhekaLyXPzpEPnOpQlPKKG6mkkU8BhEzjX1UPUVz1FhS+hsdWKhopkAhd3tg8XjET8obfp8y+OG9QtcgsgB7HS62XKQ8zgW0ItBLBSubX4ctna7nTqZLL3Al2XRunnxi6v1/+GhGoBtJ7AyKpIeH9N/1ys7iVPuc3IQIH4XnaTPnSKWstBZurkFDxO7BIJXjuaNnfd//UfYSsb+4n8Nyh9x243cJ9BeoYuWr4gNLM+LENRT85/isPPqPUo=", false );
testIsSqli( "\"+/zXWqkSECEqv+2l/VBqizw0iLx/j29GoQJqKhgD8m0Ri6jYsrEAJ0xy3Vnl8WGu/S8YFcz2uYpwbtjb8c5UH9JTzw0e6kOEQcjtgp54OUIUXUBbv5xHEy6XziawyB6kC6lWTzVoPXVBhcg2bhfvtseD63YVjc8rlV2dYk6LlY0yQeDS9Sq2Qk21Vp1vmQnejnE9D+YLCRhJgZqGlhekaLyXPzpEPnOpQlPKKG6mkkU8BhEzjX1UPUVz1FhS+hsdWKhopkAhd3tg8XjET8obfp8y+OG9QtcgsgB7HS62XKQ8zgW0ItBLBSubX4ctna7nTqZLL3Al2XRunnxi6v1/+GhGoBtJ7AyKpIeH9N/1ys7iVPuc3IQIH4XnaTPnSKWstBZurkFDxO7BIJXjuaNnfd//UfYSsb+4n8Nyh9x243cJ9BeoYuWr4gNLM+LENRT85/isPPqPUo=\"", false );
testIsSqli( "foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo foo", false );
testIsSqli( "curl/7.15+ (x64-criteo) libcurl/7.15+ OpenSSL zlib libidn", false );
testIsSqli( "1.1 - (DeleGate/9.9.8-pre20)", false );

	//MySQL String
testIsSqli( "foo\\'bar' or 1=1-- baz", true );

testIsSqli( "1+1 UNION", true );
testIsSqli( "1 UNION", false );
testIsSqli( "1 INTO OUTFILE'foo'", true );
testIsSqli( "1 INTO 'foo'", true );
testIsSqli( "1 TOP 'foo'", false );
testIsSqli( "1 and @version", false );
testIsSqli( "' or \"u", false );
testIsSqli( "' or \"b", false );
testIsSqli( "f' or \"", false );
testIsSqli( "foo' or \"bar", false );
testIsSqli( "foo\" or 'bar", false );
testIsSqli( "\" or \"b", true );
testIsSqli( "a\" or \"", true );
testIsSqli( "\" or \"", true );
testIsSqli( "foo\" or \"bar", true );
testIsSqli( "\" or true#foo", true );
testIsSqli( "\" or 1>0--", true );
testIsSqli( "' or true#", true );
testIsSqli( "' or '", true );
testIsSqli( "a' or '", true );
testIsSqli( "a' or 'b", true );
testIsSqli( "' or 'b", true );
testIsSqli( "foo' or 'bar", true );
testIsSqli( "foo' + 'bar", true );
testIsSqli( "foo' or 1=1-- bar", true );
testIsSqli( "1 or true#", true );
testIsSqli( "1 and 1*100>0-- foo", true );
}
//--------------------------------------------------------------------------------
public static void testIsSqli( final String input, final boolean expected )
throws Exception
{
if( isSQLi( input ) != expected ){ ex(); }
}
//--------------------------------------------------------------------------------
public static void testParseString( final String input, char delimiter, final String expected )
throws Exception
{
int index = parseString( input, delimiter );
if( input.substring( 0, index ).equals( expected ) )
	{
	
	}
else
	{
	ex();
	}
}
//--------------------------------------------------------------------------------
public static void testParseString()
throws Exception
{
testTokenizeWithWhite( "select 1,'\\\\\\'',2;", "Ew1,s,1;", SQL_MYSQL );
testTokenizeWithWhite( "select 1,'\\\\\\'',2;", "Ew1,s" );

testTokenizeWithWhite( "SELECT 'FOO\\'BAR';", "Ewsns" ); // incompatible with libinjection
testTokenizeWithWhite( "SELECT 'FOO\\'BAR';", "Ews;", SQL_MYSQL ); // compatible with libinjection

testTokenizeWithWhite( "'foo''bar'", "s" ); // ''->' one char

testTokenizeWithWhite( "foo'\\''bar", "nsn", SQL_MYSQL );
testTokenizeWithWhite( "foo''''bar", "nsn", SQL_MYSQL );
testTokenizeWithWhite( "foo'1'bar", "nsn", SQL_MYSQL );
testTokenizeWithWhite( "foo''bar", "nsn", SQL_MYSQL );

testTokenizeWithWhite( "foo '\\''' bar", "nwswn"  ); // \ does not escape the delimiter
testTokenizeWithWhite( "foo '\\'' ' bar", "nwsws", SQL_MYSQL  ); // \' escapes the delimiter

testParseString( "'hoge'", '\'', "'hoge'" );
testParseString( "'hog''e'", '\'', "'hog''e'" );
testParseString( "'hog'''a", '\'', "'hog'''" );
/*
if( parseString( "'hoge'", '\'', true ) != 6 ) { ex(); }
if( parseString( "'ho''e'", '\'', true ) != 6 ) { ex(); }
if( parseString( "'ho\\'ge'", '\'', true ) != 8 ) { ex(); }
if( parseString( "'ho\\'ge", '\'', false ) != 5 ) { ex(); }
*/
}
//--------------------------------------------------------------------------------
public static void testParseSingleQuoteString()
throws Exception
{
	//Binary literal B'01'
testTokenizeOne( "B'01'", "B'01'", "1" );

	//PostgreSQL U&'123'
testTokenizeOne( "U&'123'", "U&'123'", "s" );

	//single quote escape( MySQL )
testTokenizeOne( "'123'", "'123'", "s" );
testTokenizeOne( "'12''3'", "'12''3'", "s" );
testTokenizeOne( "'12\\'3'", "'12\\'3'", "s", SQL_MYSQL ); //OK on MySQL, Error on other RDBMS TODO: 

testTokenizeWithWhite( "'FOO'", "s" );
testTokenizeWithWhite( "'FOO", "s" );
testTokenizeWithWhite( "'FOO''BAR'", "s" );
testTokenizeWithWhite( "'FOO' 'BAR'", "sws" );

if( parseSingleQuoteString( "'A''B'" ) != 6 ){ ex(); } 
if( parseSingleQuoteString( "''" ) != 2 ){ ex(); } 
if( parseSingleQuoteString( "'AA'" ) != 4 ){ ex(); } 
if( parseSingleQuoteString( "'" ) != 1 ){ ex(); } 
if( parseSingleQuoteString( "'A" ) != 2 ){ ex(); } 
}
//--------------------------------------------------------------------------------
public static void testParseComment()
throws Exception
{
testTokenizeWithWhite( "SELECT/*!*/foo", "EXn" );
testTokenizeWithWhite( "SELECT/*!*/", "EX" );
testTokenizeWithWhite( "SELECT/*!", "EX" );
testTokenizeWithWhite( "SELECT/*/* foo */1", "EX1" );
testTokenizeWithWhite( "SELECT/*foo*/1", "Ec1" );
testTokenizeOne( "/*FOO/*BAR*/", "/*FOO/*BAR*/", "X" );
testTokenizeOne( "/*FOO*/", "/*FOO*/", "c" );
if( parseCStyleComment( "/*bar*/baz", false, null ) != 7 ){ ex(); }
testTokenizeWithWhite( "/*foo", "c" );
testTokenizeWithWhite( "/*foo*", "c" );
testTokenizeWithWhite( "/*foo*/", "c" );
testTokenizeWithWhite( "/*foo/*bar*/", "X" );
testTokenizeWithWhite( "SELECT 1 /* 2 */;", "Ew1wc;" );
testTokenizeWithWhite( "SELECT/*bar", "Ec" );
}
//--------------------------------------------------------------------------------
public static void testDollar()
throws Exception
{
testTokenizeOne( "$foo$bar$foo$", "$foo$bar$foo$", "s" );
testTokenizeOne( "$Foo$bar$foo$Foo$", "$Foo$bar$foo$Foo$", "s" );
testTokenizeWithWhite( "$$foo$$", "s" );
testTokenizeWithWhite( "$$foo bar baz", "s" );
testTokenizeWithWhite( "$a", "nn" );
testTokenizeWithWhite( "$-$", "non" );
testTokenizeWithWhite( "$a$", "s" );
testTokenizeWithWhite( "$a$ 1 2 3", "s" );
testTokenizeWithWhite( "$foo$bar$foo$", "s" );

testTokenizeWithWhite( "$.", "n" );
testTokenizeWithWhite( "$,", "1" );
testTokenizeWithWhite( "$..", "1" );
testTokenizeWithWhite( "$1", "1" );
testTokenizeWithWhite( "$123.45", "1" );
testTokenizeWithWhite( "$123.45 1", "1w1" );
}
//--------------------------------------------------------------------------------
public static void testTokenizeWithWhite()
throws Exception
{
// nc
//foo/*bar/* nc
//foo/*bar"'-%$*** nc
//SELECT 1 or 2 /3  /*foo/*bar*/baz*/select 1; X
//@`a``b` select vE

//E'ab\'c' SELECT sE

//testTokenizeWithWhite( "select 1,'\\\'',2;", "Ew1,s,1;" );


testTokenizeWithWhite( "1.1.", "1." );
testTokenizeWithWhite( "1.1.1", "11" );
testTokenizeWithWhite( "01Xa", "1n" );
testTokenizeWithWhite( "foo" + '\u00a0' + "bar", "nwn" );
testTokenizeWithWhite( "foo\tbar", "nwn" );
testTokenizeWithWhite( "1foo", "1n" );
testTokenizeWithWhite( "SELECT[foo]", "En" );
testTokenizeWithWhite( "SELECT [foo]", "Ewn" );
testTokenizeWithWhite( "1[foo]1", "1n1" );
testTokenizeWithWhite( "[foo]1", "n1" );
testTokenizeWithWhite( "[foo]", "n" );
testTokenizeWithWhite( "from`bar", "kn" );
testTokenizeWithWhite( "SELECT.1", "E1" );
testTokenizeWithWhite( "foo SELECT.1", "nwE1" );
testTokenizeWithWhite( "foo foo.1", "nwn" );
testTokenizeWithWhite( "SELECT @foo.`select`;", "Ewvn;" );

testTokenizeWithWhite( "SELECT x'1234';", "Ew1;" );

testTokenizeWithWhite( "0x1E", "1" );
testTokenizeWithWhite( "1E2", "1" );

testTokenizeWithWhite( "0b10", "1" );
testTokenizeWithWhite( "0B10", "1" );
testTokenizeWithWhite( "0B", "n" );
testTokenizeWithWhite( "0B2", "n1" );

testTokenizeWithWhite( "foo,foo", "n,n" );
testTokenizeWithWhite( "foo foo", "nwn" );

testTokenizeWithWhite( "SELECT foo.`select`;", "Ewn;" );

testTokenizeWithWhite( ".e", ".n" );
testTokenizeWithWhite( "10.10e", "n" );
testTokenizeWithWhite( "1234.e", "n" );
testTokenizeWithWhite( "1e+1", "1" );
testTokenizeWithWhite( "1e+1F", "1" );

testTokenizeWithWhite( "2.f;", "1;" );
testTokenizeWithWhite( ".", "." );
testTokenizeWithWhite( ".123", "1" );
testTokenizeWithWhite( "2.f", "1" );
testTokenizeWithWhite( "1234.f", "1" );
testTokenizeWithWhite( "123from", "1k" );
testTokenizeWithWhite( "2.f ", "1w" );
testTokenizeWithWhite( "2.f\t", "1w" );
testTokenizeWithWhite( "2.fUNION", "1U" );

testTokenizeWithWhite( "2.d", "1" );
testTokenizeWithWhite( "1a", "1n" );
testTokenizeWithWhite( "2.", "1" );
testTokenizeWithWhite( "2.a", "1n" );
testTokenizeWithWhite( "money", "t" );
testTokenizeWithWhite( "::", "o" );

testTokenizeWithWhite( "SELECT \"first\" \"second\";", "Ewsws;" );
testTokenizeWithWhite( "{`", "{n" );

testTokenizeWithWhite( new String( new byte[]{ ( byte )0x81 }, "ISO-8859-1" ), "n" );
testTokenizeWithWhite( new String( new byte[]{ ( byte )0x81, ( byte )0x82 }, "ISO-8859-1" ), "n" );

testTokenizeWithWhite( "\"foo\" 1", "sw1" );
testTokenizeWithWhite( "\"foo\"", "s" );
testTokenizeWithWhite( "\"foo", "s" );

testTokenizeWithWhite( "B'9", "ns" );

testTokenizeWithWhite( "INTo OUTFILE", "kwk" );
testTokenizeWithWhite( "INTO OUTFILE", "kwk" );
testTokenizeWithWhite( "INTO\tOUTFILE", "kwk" );
testTokenizeWithWhite( "INTO\t OUTFILE", "kwwk" );
testTokenizeWithWhite( "INTO \tOUTFILE", "kwwk" );
testTokenizeWithWhite( "INTO  OUTFILE", "kwwk" );
testTokenizeWithWhite( "INTO  OUTFILE FOO", "kwwkwn" );

testTokenizeWithWhite( "SELECT `abs", "Ewf" );
testTokenizeWithWhite( "SELECT `absa", "Ewn" );

testTokenizeWithWhite( "SELECT-- ", "Ec" );
testTokenizeWithWhite( "SELECT-- a", "Ec" );
testTokenizeWithWhite( "SELECT--\t", "Ec" );
testTokenizeWithWhite( "SELECT--\ta", "Ec" );
testTokenizeWithWhite( "SELECT--", "Ec" );
testTokenizeWithWhite( "SELECT--a", "Ec" );
testTokenizeWithWhite( "SELECT--a", "Eoon", SQL_MYSQL );
testTokenizeWithWhite( "SELECT-", "Eo" );
testTokenizeWithWhite( "SELECT--\nSELECT", "EcE" );

testTokenizeWithWhite( "#hoge", "c", SQL_MYSQL );
testTokenizeWithWhite( "#hoge\nSELECT", "cE", SQL_MYSQL );

testTokenizeWithWhite( "#hoge", "on" );
testTokenizeWithWhite( "#hoge\nSELECT", "onwE" );

testTokenizeWithWhite( "[foo]", "n" ); //MSSQL

testTokenizeWithWhite( "select E'123'", "Ews" ); //PostrgeSQL Escape String
testTokenizeWithWhite( "select E'123", "Ews" ); //PostrgeSQL Escape String unclosed

testTokenizeWithWhite( "select nq'<123>' foo", "Ewswn" ); //unclosed
testTokenizeWithWhite( "select nq'{123}' foo", "Ewswn" ); //unclosed
testTokenizeWithWhite( "select nq'(123)' foo", "Ewswn" ); //unclosed
testTokenizeWithWhite( "select nq'[123]' foo", "Ewswn" ); //unclosed
testTokenizeWithWhite( "select nq'[123", "Ews" ); //unclosed
testTokenizeWithWhite( "select nq'123", "Ews" ); //unclosed
testTokenizeWithWhite( "select nq'12341' select", "EwswE" );
testTokenizeWithWhite( "select nq'12341", "Ews" );
testTokenizeWithWhite( "select nq'1", "Ews" );
testTokenizeWithWhite( "select nq'", "Ewns" );

testTokenizeWithWhite( "select q'[123", "Ews" ); //unclosed
testTokenizeWithWhite( "select q'123", "Ews" ); //unclosed
testTokenizeWithWhite( "select q'12341' select", "EwswE" );
testTokenizeWithWhite( "select q'12341", "Ews" );
testTokenizeWithWhite( "select q'1", "Ews" );
testTokenizeWithWhite( "select q'", "Ewns" );

testTokenizeWithWhite( "select Q'[123", "Ews" ); //unclosed
testTokenizeWithWhite( "select Q'123", "Ews" ); //unclosed
testTokenizeWithWhite( "select Q'12341' select", "EwswE" );
testTokenizeWithWhite( "select Q'12341", "Ews" );
testTokenizeWithWhite( "select Q'1", "Ews" );
testTokenizeWithWhite( "select Q'", "Ewns" );


testTokenizeWithWhite( "SELECT n'123", "Ews" ); //unclosed
testTokenizeWithWhite( "SELECT n'12''3'", "Ews" );
testTokenizeWithWhite( "SELECT n'123'", "Ews" );
testTokenizeWithWhite( "_MACROMAN", "t" );

testTokenizeWithWhite( "select nq'[123]'", "Ews" );
testTokenizeWithWhite( "select q'[123]'", "Ews" );

testTokenizeWithWhite( "select X'AAA---", "Ewns" ); //unclosed
testTokenizeWithWhite( "select X'Z'", "Ewns" );
testTokenizeWithWhite( "SELECT X''", "Ew1" );
testTokenizeWithWhite( "SELECT X'41'", "Ew1" );
testTokenizeWithWhite( "X01a", "n" );

testTokenizeWithWhite( "?", "?" );
testTokenizeWithWhite( "]", "?" );
testTokenizeWithWhite( "@`a`", "v" );
testTokenizeWithWhite( "@`aa`", "v" );
testTokenizeWithWhite( "@@`aa`", "v" );
testTokenizeWithWhite( "@@`a``a`", "v" );
testTokenizeWithWhite( "@", "v" );
testTokenizeWithWhite( "@@", "v" );
testTokenizeWithWhite( "@@abc", "v" );
testTokenizeWithWhite( "@@`a`", "v" );
testTokenizeWithWhite( "@@a", "v" );
testTokenizeWithWhite( "@'a'", "v" );
testTokenizeWithWhite( "@@'a'", "v" );
testTokenizeWithWhite( "@@\"a\"", "v" );
/*
*/

//testTokenizeWithWhite( "[]", "n" );
//testTokenizeWithWhite( "あ", "n" );
testTokenizeWithWhite( "\\N", "1" );
testTokenizeWithWhite( "\\", "\\" );
testTokenizeWithWhite( "COLLATE", "A" );
testTokenizeWithWhite( "AND", "&" );
testTokenizeWithWhite( "&&", "&" );
testTokenizeWithWhite( "LOCALTIME", "v" );
testTokenizeWithWhite( "ANYARRAY", "t" );
testTokenizeWithWhite( "GROUP BY", "nwn" );
testTokenizeWithWhite( "UNION SELECT", "UwE" );
testTokenizeWithWhite( "UNION", "U" );
testTokenizeWithWhite( "EXCEPT", "U" );
testTokenizeWithWhite( "ADD ADD", "kwk" );
testTokenizeWithWhite( "ADD", "k" );
testTokenizeWithWhite( "//", "oo" );
testTokenizeWithWhite( "SELECT 1 / 2;", "Ew1wow1;" );
testTokenizeWithWhite( "1<<=1", "1oo1" );
testTokenizeWithWhite( "|=", "o" );
testTokenizeWithWhite( "^=", "o" );
testTokenizeWithWhite( "~*", "o" );
testTokenizeWithWhite( "<=", "o" );
testTokenizeWithWhite( "<=>", "o" );
testTokenizeWithWhite( "SELECT 1 : = 2;", "Ew1w:wow1;" );
testTokenizeWithWhite( "SELECT 1 := 2;", "Ew1wow1;" );
testTokenizeWithWhite( "19019", "1" );
testTokenizeWithWhite( "1", "1" );
testTokenizeWithWhite( "12345", "1" );

testTokenizeWithWhite( ":=", "o" );
testTokenizeWithWhite( ":SELECT", ":E" );

testTokenizeWithWhite( "((", "((" );

	//E and white
testTokenizeWithWhite( "SELECT", "E" );
testTokenizeWithWhite( "SELECT ", "Ew" );
testTokenizeWithWhite( " SELECT ", "wEw" );
testTokenizeWithWhite( "  SELECT ", "wwEw" );

	//E and k
testTokenizeWithWhite( "SELECT ADD", "Ewk" );

	//E and f
testTokenizeWithWhite( "SELECT ACOS", "Ewf" );

	//E and &
testTokenizeWithWhite( "SELECT AND", "Ew&" );

	//E and t
testTokenizeWithWhite( "SELECT ANYARRAY", "Ewt" );

}
//--------------------------------------------------------------------------------
public static void testTokenizeWithWhite( final String input, final String expectedToken  )
throws Exception
{
testTokenizeWithWhite( input, expectedToken, 0 );
}
//--------------------------------------------------------------------------------
public static void testTokenizeWithWhite( final String input, final String expectedToken, int flags )
throws Exception
{
String[] allTokenBuf = new String[ 1 ];
List valueList = new ArrayList();
tokenize( input, valueList, allTokenBuf, flags, false );
p( allTokenBuf[ 0 ] );
if( !allTokenBuf[ 0 ].equals( expectedToken ) )
	{
	ex();
	}
}
//--------------------------------------------------------------------------------
public static void testTokenizeOne()
throws Exception
{
testTokenizeOne( "\"FOO", "\"FOO", "s" );
testTokenizeOne( "'FOO", "'FOO", "s" );
testTokenizeOne( "'FOO'", "'FOO'", "s" );
testTokenizeOne( "/a", "/", "o" );
testTokenizeOne( "/", "/", "o" );
testTokenizeOne( "DIV", "DIV", "o" );
testTokenizeOne( "<=>", "<=>", "o" );
testTokenizeOne( "=", "=", "o" );
testTokenizeOne( ";", ";", ";" );
testTokenizeOne( "1", "1", "1" );
testTokenizeOne( "2", "2", "1" );

testTokenizeOne( ",", ",", "," );
testTokenizeOne( ".", ".", "." );
testTokenizeOne( "{", "{", "{" );
testTokenizeOne( "}", "}", "}" );
testTokenizeOne( ")", ")", ")" );
testTokenizeOne( "(", "(", "(" );

testTokenizeOne( "SELECT", "SELECT", "E" );
testTokenizeOne( "UTL_INADDR.GET_HOST_ADDRESS", "UTL_INADDR.GET_HOST_ADDRESS", "f" );
testTokenizeOne( "ADD", "ADD", "k" );

	//space after select
testTokenizeOne( "SELECT ", "SELECT", "E" );

	//k
testTokenizeOne( "ADD", "ADD", "k" );

	//t
testTokenizeOne( "ANYARRAY", "ANYARRAY", "t" );

}
//--------------------------------------------------------------------------------
public static void testTokenizeOne( final String input, final String expectedProcessed, final String expectedToken )
throws Exception
{
String[] processed = new String[ 1 ];
String[] tokenBuf = new String[ 1 ];
tokenizeOne( input, processed, tokenBuf, 0 );
if( !processed[ 0 ].equals( expectedProcessed ) ){ ex(); }
if( !tokenBuf[ 0 ].equals( expectedToken ) ){ ex(); }
}
//--------------------------------------------------------------------------------
public static void testTokenizeOne( final String input, final String expectedProcessed, final String expectedToken, int flags )
throws Exception
{
String[] processed = new String[ 1 ];
String[] tokenBuf = new String[ 1 ];
tokenizeOne( input, processed, tokenBuf, flags );
if( !processed[ 0 ].equals( expectedProcessed ) ){ ex(); }
if( !tokenBuf[ 0 ].equals( expectedToken ) ){ ex(); }
}
//--------------------------------------------------------------------------------
public static void p( Object o )
{
System.out.println( o );
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