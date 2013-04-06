package com.client9;

public class SQLParse
{
//--------------------------------------------------------------------------------
public static boolean isSQLi( final String s )
{
try
	{
	return isSQLiImpl( s );
	}
catch( Exception e )
	{
	e.printStackTrace();
	return false;
	}
}
//--------------------------------------------------------------------------------
private static boolean isSQLiImpl( final String s )
throws Exception
{
return false;
}
//--------------------------------------------------------------------------------
protected static String tokenize( final String s )
{
return null;
}
//--------------------------------------------------------------------------------
}