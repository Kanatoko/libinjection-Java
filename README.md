libinjection-Java
============

libinjection-Java is a Java porting of the libinjection([http://www.client9.com/projects/libinjection/](http://www.client9.com/projects/libinjection/)) 

Compatibility
-------------------------
It is not 100% compatible with the original C implementation at this time.

How to use
-------------------------
Call static method com.client9.libinjection.SQLParse.isSQLi( String s ).
It is thread safe.

```Java
package test;

public class Example
{
//--------------------------------------------------------------------------------
public static void main( String[] args )
throws Exception
{
String s = "foo' or 1=1--";
boolean isSQLi = com.client9.libinjection.SQLParse.isSQLi( s );

if( isSQLi )
	{
	System.out.println( "SQLi found : " + s );
	}
}
//--------------------------------------------------------------------------------
}
```

LICENSE
-------------------------
libinjection-Java is distributed under the same license as libinjection.

Copyright (c) 2012, 2013 Nick Galbreath
Licensed under standard BSD open source license
See /COPYING.txt -- commercial licenses available.
Send requests to nickg@client9.com
