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
private static void p( Object o )
{
System.out.println( o );
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
if( !tokenize( "or" ).equals( "&" ) ) { ex(); }
}
//--------------------------------------------------------------------------------
}