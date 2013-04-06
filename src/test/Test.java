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

p( "OK." );
}
//--------------------------------------------------------------------------------
private static void ex()
throws Exception
{
throw new Exception();
}
//--------------------------------------------------------------------------------
private static void test1()
throws Exception
{
testParseWord( "a", "n" );
testParseWord( "ab", "n" );
testParseWord( "abs", "f" );

//if( !inputToPattern( "abs add" ).equals( "fk" ) ) { ex(); }
}
//--------------------------------------------------------------------------------
private static void testParseWord( String input, String pattern )
throws Exception
{
if( parse_word( input ).equals( pattern ) )
	{
	//OK
	}
else
	{
	ex();
	}
}
//--------------------------------------------------------------------------------
}