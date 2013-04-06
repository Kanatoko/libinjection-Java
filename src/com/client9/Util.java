package com.client9;

import java.util.regex.*;

public class Util
{
//------------------------------------------------------------------------------------------
public static String getMatch( String patternStr, String target )
{
Pattern pattern = Pattern.compile( patternStr, Pattern.DOTALL );
Matcher matcher = pattern.matcher( target );
if( matcher.find() )
	{
	if( matcher.groupCount() > 0 )
		{
		return matcher.group( 1 );
		}
	else
		{
		return target.substring( matcher.start(), matcher.end() );
		}
	}
else
	{
	return "";
	}
}
//------------------------------------------------------------------------------------------
public static String getMatchIgnoreCase( String patternStr, String target )
{
Pattern pattern = Pattern.compile( patternStr, Pattern.DOTALL | Pattern.CASE_INSENSITIVE );
Matcher matcher = pattern.matcher( target );
if( matcher.find() )
	{
	if( matcher.groupCount() > 0 )
		{
		return matcher.group( 1 );
		}
	else
		{
		return target.substring( matcher.start(), matcher.end() );
		}
	}
else
	{
	return "";
	}
}
//--------------------------------------------------------------------------------
}