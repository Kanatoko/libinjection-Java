libinjection-Java
============

libinjection-Java is a Java porting of the libinjection([http://www.client9.com/projects/libinjection/](http://www.client9.com/projects/libinjection/)) 
Completely rewritten from scratch at Mar/2014.

Compatibility
-------------------------
The Java implementation is based on the original C implementation version 3.9.1
Currently imcompatible on some rare cases.

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

Copyright (c) 2012, 2013, 2014 Nick Galbreath
Licensed under standard BSD open source license
See /COPYING.txt -- commercial licenses available.
Send requests to nickg@client9.com
