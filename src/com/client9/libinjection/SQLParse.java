package com.client9.libinjection;

import java.util.*;
import java.util.regex.*;

public class SQLParse
{
public static final int MAX_TOKENS = 5;
protected static boolean debug = false;

protected static Map sqlKeywords = new HashMap( 500 );
protected static Map multiKeywords = new HashMap( 50 );
protected static List pt2Function = new ArrayList( 130 );
protected static Set multiKeywordsStart;
protected static Set operators2;
protected static Set multikeywordsFirstWordTypeSet;
protected static Set unaryOpSet;
protected static Set arithOpSet;
protected static Set fingerprints;

static
{
/* Automatically generated content. See sqlparse_data_h_to_java */
sqlKeywords.put( "ABS", "f" );
sqlKeywords.put( "ACCESSIBLE", "k" );
sqlKeywords.put( "ACOS", "f" );
sqlKeywords.put( "ADD", "k" );
sqlKeywords.put( "ADDDATE", "f" );
sqlKeywords.put( "ADDTIME", "f" );
sqlKeywords.put( "AES_DECRYPT", "f" );
sqlKeywords.put( "AES_ENCRYPT", "f" );
sqlKeywords.put( "AGAINST", "k" );
sqlKeywords.put( "ALL_USERS", "k" );
sqlKeywords.put( "ALTER", "k" );
sqlKeywords.put( "ANALYZE", "k" );
sqlKeywords.put( "AND", "&" );
sqlKeywords.put( "AS", "k" );
sqlKeywords.put( "ASC", "k" );
sqlKeywords.put( "ASCII", "f" );
sqlKeywords.put( "ASENSITIVE", "k" );
sqlKeywords.put( "ASIN", "f" );
sqlKeywords.put( "ATAN", "f" );
sqlKeywords.put( "ATAN2", "f" );
sqlKeywords.put( "AVG", "f" );
sqlKeywords.put( "BEFORE", "k" );
sqlKeywords.put( "BEGIN", "k" );
sqlKeywords.put( "BENCHMARK", "f" );
sqlKeywords.put( "BETWEEN", "k" );
sqlKeywords.put( "BIGINT", "k" );
sqlKeywords.put( "BIN", "f" );
sqlKeywords.put( "BINARY", "k" );
sqlKeywords.put( "BINARY_DOUBLE_INFINITY", "1" );
sqlKeywords.put( "BINARY_DOUBLE_NAN", "1" );
sqlKeywords.put( "BINARY_FLOAT_INFINITY", "1" );
sqlKeywords.put( "BINARY_FLOAT_NAN", "1" );
sqlKeywords.put( "BINBINARY", "f" );
sqlKeywords.put( "BIT_AND", "f" );
sqlKeywords.put( "BIT_COUNT", "f" );
sqlKeywords.put( "BIT_LENGTH", "f" );
sqlKeywords.put( "BIT_OR", "f" );
sqlKeywords.put( "BIT_XOR", "f" );
sqlKeywords.put( "BLOB", "k" );
sqlKeywords.put( "BOOLEAN", "k" );
sqlKeywords.put( "BOTH", "k" );
sqlKeywords.put( "BY", "n" );
sqlKeywords.put( "CALL", "k" );
sqlKeywords.put( "CASCADE", "k" );
sqlKeywords.put( "CASE", "o" );
sqlKeywords.put( "CAST", "f" );
sqlKeywords.put( "CEIL", "f" );
sqlKeywords.put( "CEILING", "f" );
sqlKeywords.put( "CHANGE", "k" );
sqlKeywords.put( "CHAR", "f" );
sqlKeywords.put( "CHARACTER", "k" );
sqlKeywords.put( "CHARACTER_LENGTH", "f" );
sqlKeywords.put( "CHARSET", "f" );
sqlKeywords.put( "CHAR_LENGTH", "f" );
sqlKeywords.put( "CHECK", "k" );
sqlKeywords.put( "CHR", "f" );
sqlKeywords.put( "COALESCE", "k" );
sqlKeywords.put( "COERCIBILITY", "f" );
sqlKeywords.put( "COLLATE", "k" );
sqlKeywords.put( "COLLATION", "f" );
sqlKeywords.put( "COLUMN", "k" );
sqlKeywords.put( "COMPRESS", "f" );
sqlKeywords.put( "CONCAT", "f" );
sqlKeywords.put( "CONCAT_WS", "f" );
sqlKeywords.put( "CONDITION", "k" );
sqlKeywords.put( "CONNECTION_ID", "f" );
sqlKeywords.put( "CONSTRAINT", "k" );
sqlKeywords.put( "CONTINUE", "k" );
sqlKeywords.put( "CONV", "f" );
sqlKeywords.put( "CONVERT", "f" );
sqlKeywords.put( "CONVERT_TZ", "f" );
sqlKeywords.put( "COS", "f" );
sqlKeywords.put( "COT", "f" );
sqlKeywords.put( "COUNT", "f" );
sqlKeywords.put( "CRC32", "f" );
sqlKeywords.put( "CREATE", "k" );
sqlKeywords.put( "CTXSYS.DRITHSX.SN", "f" );
sqlKeywords.put( "CURDATE", "f" );
sqlKeywords.put( "CURRENT_DATE", "k" );
sqlKeywords.put( "CURRENT_DATECURRENT_TIME", "f" );
sqlKeywords.put( "CURRENT_TIME", "k" );
sqlKeywords.put( "CURRENT_TIMESTAMP", "k" );
sqlKeywords.put( "CURRENT_USER", "k" );
sqlKeywords.put( "CURSOR", "k" );
sqlKeywords.put( "CURTIME", "f" );
sqlKeywords.put( "DATABASE", "k" );
sqlKeywords.put( "DATABASES", "k" );
sqlKeywords.put( "DATE", "f" );
sqlKeywords.put( "DATEDIFF", "f" );
sqlKeywords.put( "DATE_ADD", "f" );
sqlKeywords.put( "DATE_FORMAT", "f" );
sqlKeywords.put( "DATE_SUB", "f" );
sqlKeywords.put( "DAY", "f" );
sqlKeywords.put( "DAYNAME", "f" );
sqlKeywords.put( "DAYOFMONTH", "f" );
sqlKeywords.put( "DAYOFWEEK", "f" );
sqlKeywords.put( "DAYOFYEAR", "f" );
sqlKeywords.put( "DAY_HOUR", "k" );
sqlKeywords.put( "DAY_MICROSECOND", "k" );
sqlKeywords.put( "DAY_MINUTE", "k" );
sqlKeywords.put( "DAY_SECOND", "k" );
sqlKeywords.put( "DBMS_PIPE.RECEIVE_MESSAGE", "f" );
sqlKeywords.put( "DEC", "k" );
sqlKeywords.put( "DECIMAL", "k" );
sqlKeywords.put( "DECLARE", "k" );
sqlKeywords.put( "DECODE", "f" );
sqlKeywords.put( "DEFAULT", "k" );
sqlKeywords.put( "DEGREES", "f" );
sqlKeywords.put( "DELAY", "k" );
sqlKeywords.put( "DELAYED", "k" );
sqlKeywords.put( "DELETE", "k" );
sqlKeywords.put( "DESC", "k" );
sqlKeywords.put( "DESCRIBE", "k" );
sqlKeywords.put( "DES_DECRYPT", "f" );
sqlKeywords.put( "DES_ENCRYPT", "f" );
sqlKeywords.put( "DETERMINISTIC", "k" );
sqlKeywords.put( "DISTINCROW", "k" );
sqlKeywords.put( "DISTINCT", "k" );
sqlKeywords.put( "DIV", "o" );
sqlKeywords.put( "DROP", "k" );
sqlKeywords.put( "DUAL", "k" );
sqlKeywords.put( "EACH", "k" );
sqlKeywords.put( "ELSE", "k" );
sqlKeywords.put( "ELSEIF", "k" );
sqlKeywords.put( "ELT", "f" );
sqlKeywords.put( "ENCLOSED", "k" );
sqlKeywords.put( "ENCODE", "f" );
sqlKeywords.put( "ENCRYPT", "f" );
sqlKeywords.put( "ESCAPED", "k" );
sqlKeywords.put( "EXEC", "k" );
sqlKeywords.put( "EXECUTE", "k" );
sqlKeywords.put( "EXISTS", "k" );
sqlKeywords.put( "EXIT", "k" );
sqlKeywords.put( "EXP", "f" );
sqlKeywords.put( "EXPLAIN", "k" );
sqlKeywords.put( "EXPORT_SET", "f" );
sqlKeywords.put( "EXTRACT", "f" );
sqlKeywords.put( "EXTRACTVALUE", "f" );
sqlKeywords.put( "EXTRACT_VALUE", "f" );
sqlKeywords.put( "FALSE", "1" );
sqlKeywords.put( "FETCH", "k" );
sqlKeywords.put( "FIELD", "f" );
sqlKeywords.put( "FIND_IN_SET", "f" );
sqlKeywords.put( "FLOOR", "f" );
sqlKeywords.put( "FOR", "n" );
sqlKeywords.put( "FORCE", "k" );
sqlKeywords.put( "FOREIGN", "k" );
sqlKeywords.put( "FORMAT", "f" );
sqlKeywords.put( "FOUND_ROWS", "f" );
sqlKeywords.put( "FROM", "k" );
sqlKeywords.put( "FROM_DAYS", "f" );
sqlKeywords.put( "FROM_UNIXTIME", "f" );
sqlKeywords.put( "FULLTEXT", "k" );
sqlKeywords.put( "GENERATE_SERIES", "f" );
sqlKeywords.put( "GET_FORMAT", "f" );
sqlKeywords.put( "GET_LOCK", "f" );
sqlKeywords.put( "GOTO", "k" );
sqlKeywords.put( "GRANT", "k" );
sqlKeywords.put( "GREATEST", "f" );
sqlKeywords.put( "GROUP", "n" );
sqlKeywords.put( "GROUP_CONCAT", "f" );
sqlKeywords.put( "HAVING", "k" );
sqlKeywords.put( "HEX", "f" );
sqlKeywords.put( "HIGH_PRIORITY", "k" );
sqlKeywords.put( "HOST_NAME", "f" );
sqlKeywords.put( "HOUR", "f" );
sqlKeywords.put( "HOUR_MICROSECOND", "k" );
sqlKeywords.put( "HOUR_MINUTE", "k" );
sqlKeywords.put( "HOUR_SECOND", "k" );
sqlKeywords.put( "IF", "k" );
sqlKeywords.put( "IFF", "f" );
sqlKeywords.put( "IFNULL", "f" );
sqlKeywords.put( "IGNORE", "k" );
sqlKeywords.put( "IIF", "f" );
sqlKeywords.put( "IN", "n" );
sqlKeywords.put( "INDEX", "k" );
sqlKeywords.put( "INET_ATON", "f" );
sqlKeywords.put( "INET_NTOA", "f" );
sqlKeywords.put( "INFILE", "k" );
sqlKeywords.put( "INNER", "k" );
sqlKeywords.put( "INOUT", "k" );
sqlKeywords.put( "INSENSITIVE", "k" );
sqlKeywords.put( "INSERT", "k" );
sqlKeywords.put( "INSTR", "f" );
sqlKeywords.put( "INT", "k" );
sqlKeywords.put( "INT1", "k" );
sqlKeywords.put( "INT2", "k" );
sqlKeywords.put( "INT3", "k" );
sqlKeywords.put( "INT4", "k" );
sqlKeywords.put( "INT8", "k" );
sqlKeywords.put( "INTEGER", "k" );
sqlKeywords.put( "INTERVAL", "k" );
sqlKeywords.put( "INTO", "k" );
sqlKeywords.put( "IS", "o" );
sqlKeywords.put( "ISNULL", "f" );
sqlKeywords.put( "IS_FREE_LOCK", "f" );
sqlKeywords.put( "IS_MEMBER", "f" );
sqlKeywords.put( "IS_SRVROLEMEMBER", "f" );
sqlKeywords.put( "IS_USED_LOCK", "f" );
sqlKeywords.put( "ITERATE", "k" );
sqlKeywords.put( "JOIN", "k" );
sqlKeywords.put( "KEYS", "k" );
sqlKeywords.put( "KILL", "k" );
sqlKeywords.put( "LAST_INSERT_ID", "f" );
sqlKeywords.put( "LCASE", "f" );
sqlKeywords.put( "LEADING", "k" );
sqlKeywords.put( "LEAST", "f" );
sqlKeywords.put( "LEAVE", "k" );
sqlKeywords.put( "LEFT", "f" );
sqlKeywords.put( "LENGTH", "f" );
sqlKeywords.put( "LIKE", "o" );
sqlKeywords.put( "LIMIT", "k" );
sqlKeywords.put( "LINEAR", "k" );
sqlKeywords.put( "LINES", "k" );
sqlKeywords.put( "LN", "f" );
sqlKeywords.put( "LOAD", "k" );
sqlKeywords.put( "LOAD_FILE", "f" );
sqlKeywords.put( "LOCALTIME", "k" );
sqlKeywords.put( "LOCALTIMESTAMP", "k" );
sqlKeywords.put( "LOCATE", "f" );
sqlKeywords.put( "LOCK", "n" );
sqlKeywords.put( "LOG", "f" );
sqlKeywords.put( "LOG10", "f" );
sqlKeywords.put( "LOG2", "f" );
sqlKeywords.put( "LONGBLOB", "k" );
sqlKeywords.put( "LONGTEXT", "k" );
sqlKeywords.put( "LOOP", "k" );
sqlKeywords.put( "LOWER", "f" );
sqlKeywords.put( "LOW_PRIORITY", "k" );
sqlKeywords.put( "LPAD", "f" );
sqlKeywords.put( "LTRIM", "f" );
sqlKeywords.put( "MAKEDATE", "f" );
sqlKeywords.put( "MAKE_SET", "f" );
sqlKeywords.put( "MASTER_BIND", "k" );
sqlKeywords.put( "MASTER_POS_WAIT", "f" );
sqlKeywords.put( "MASTER_SSL_VERIFY_SERVER_CERT", "k" );
sqlKeywords.put( "MATCH", "k" );
sqlKeywords.put( "MAX", "f" );
sqlKeywords.put( "MAXVALUE", "k" );
sqlKeywords.put( "MD5", "f" );
sqlKeywords.put( "MEDIUMBLOB", "k" );
sqlKeywords.put( "MEDIUMINT", "k" );
sqlKeywords.put( "MEDIUMTEXT", "k" );
sqlKeywords.put( "MERGE", "k" );
sqlKeywords.put( "MICROSECOND", "f" );
sqlKeywords.put( "MID", "f" );
sqlKeywords.put( "MIDDLEINT", "k" );
sqlKeywords.put( "MIN", "f" );
sqlKeywords.put( "MINUTE", "f" );
sqlKeywords.put( "MINUTE_MICROSECOND", "k" );
sqlKeywords.put( "MINUTE_SECOND", "k" );
sqlKeywords.put( "MOD", "o" );
sqlKeywords.put( "MODE", "n" );
sqlKeywords.put( "MODIFIES", "k" );
sqlKeywords.put( "MONTH", "f" );
sqlKeywords.put( "MONTHNAME", "f" );
sqlKeywords.put( "NAME_CONST", "f" );
sqlKeywords.put( "NOT", "o" );
sqlKeywords.put( "NOW", "f" );
sqlKeywords.put( "NO_WRITE_TO_BINLOG", "k" );
sqlKeywords.put( "NULL", "1" );
sqlKeywords.put( "NULLIF", "f" );
sqlKeywords.put( "NUMERIC", "k" );
sqlKeywords.put( "OCT", "f" );
sqlKeywords.put( "OCTET_LENGTH", "f" );
sqlKeywords.put( "OFFSET", "k" );
sqlKeywords.put( "OLD_PASSWORD", "f" );
sqlKeywords.put( "ONE_SHOT", "k" );
sqlKeywords.put( "OPEN", "k" );
sqlKeywords.put( "OPENDATASOURCE", "f" );
sqlKeywords.put( "OPENQUERY", "f" );
sqlKeywords.put( "OPENROWSET", "f" );
sqlKeywords.put( "OPENXML", "f" );
sqlKeywords.put( "OPTIMIZE", "k" );
sqlKeywords.put( "OPTION", "k" );
sqlKeywords.put( "OPTIONALLY", "k" );
sqlKeywords.put( "OR", "&" );
sqlKeywords.put( "ORD", "f" );
sqlKeywords.put( "ORDER", "n" );
sqlKeywords.put( "OUT", "k" );
sqlKeywords.put( "OUTFILE", "k" );
sqlKeywords.put( "OWN3D", "k" );
sqlKeywords.put( "PARTITION", "k" );
sqlKeywords.put( "PASSWORD", "k" );
sqlKeywords.put( "PERIOD_ADD", "f" );
sqlKeywords.put( "PERIOID_DIFF", "f" );
sqlKeywords.put( "PG_ADVISORY_LOCK", "f" );
sqlKeywords.put( "PG_SLEEP", "f" );
sqlKeywords.put( "PI", "f" );
sqlKeywords.put( "POSITION", "f" );
sqlKeywords.put( "POW", "f" );
sqlKeywords.put( "POWER", "f" );
sqlKeywords.put( "PRECISION", "k" );
sqlKeywords.put( "PRIMARY", "k" );
sqlKeywords.put( "PROCEDURE", "k" );
sqlKeywords.put( "PURGE", "k" );
sqlKeywords.put( "QUARTER", "f" );
sqlKeywords.put( "QUOTE", "f" );
sqlKeywords.put( "RADIANS", "f" );
sqlKeywords.put( "RAND", "f" );
sqlKeywords.put( "RANDOMBLOB", "f" );
sqlKeywords.put( "RANGE", "k" );
sqlKeywords.put( "READ", "k" );
sqlKeywords.put( "READS", "k" );
sqlKeywords.put( "READ_WRITE", "k" );
sqlKeywords.put( "REAL", "n" );
sqlKeywords.put( "REFERENCES", "k" );
sqlKeywords.put( "REGEXP", "o" );
sqlKeywords.put( "RELEASE", "k" );
sqlKeywords.put( "RELEASE_LOCK", "f" );
sqlKeywords.put( "RENAME", "k" );
sqlKeywords.put( "REPEAT", "k" );
sqlKeywords.put( "REPLACE", "k" );
sqlKeywords.put( "REQUIRE", "k" );
sqlKeywords.put( "RESIGNAL", "k" );
sqlKeywords.put( "RESTRICT", "k" );
sqlKeywords.put( "RETURN", "k" );
sqlKeywords.put( "REVERSE", "f" );
sqlKeywords.put( "REVOKE", "k" );
sqlKeywords.put( "RIGHT", "f" );
sqlKeywords.put( "RLIKE", "o" );
sqlKeywords.put( "ROUND", "f" );
sqlKeywords.put( "ROW", "f" );
sqlKeywords.put( "ROW_COUNT", "f" );
sqlKeywords.put( "RPAD", "f" );
sqlKeywords.put( "RTRIM", "f" );
sqlKeywords.put( "SCHEMA", "k" );
sqlKeywords.put( "SCHEMAS", "k" );
sqlKeywords.put( "SECOND_MICROSECOND", "k" );
sqlKeywords.put( "SEC_TO_TIME", "f" );
sqlKeywords.put( "SELECT", "k" );
sqlKeywords.put( "SENSITIVE", "k" );
sqlKeywords.put( "SEPARATOR", "k" );
sqlKeywords.put( "SESSION_USER", "f" );
sqlKeywords.put( "SET", "k" );
sqlKeywords.put( "SHA", "f" );
sqlKeywords.put( "SHA1", "f" );
sqlKeywords.put( "SHA2", "f" );
sqlKeywords.put( "SHOW", "k" );
sqlKeywords.put( "SHUTDOWN", "k" );
sqlKeywords.put( "SIGN", "f" );
sqlKeywords.put( "SIGNAL", "k" );
sqlKeywords.put( "SIMILAR", "k" );
sqlKeywords.put( "SIN", "f" );
sqlKeywords.put( "SLEEP", "f" );
sqlKeywords.put( "SMALLINT", "k" );
sqlKeywords.put( "SOUNDEX", "f" );
sqlKeywords.put( "SOUNDS", "o" );
sqlKeywords.put( "SPACE", "f" );
sqlKeywords.put( "SPATIAL", "k" );
sqlKeywords.put( "SPECIFIC", "k" );
sqlKeywords.put( "SQL", "k" );
sqlKeywords.put( "SQLEXCEPTION", "k" );
sqlKeywords.put( "SQLSTATE", "k" );
sqlKeywords.put( "SQLWARNING", "k" );
sqlKeywords.put( "SQL_BIG_RESULT", "k" );
sqlKeywords.put( "SQL_CALC_FOUND_ROWS", "k" );
sqlKeywords.put( "SQL_SMALL_RESULT", "k" );
sqlKeywords.put( "SQRT", "f" );
sqlKeywords.put( "SSL", "k" );
sqlKeywords.put( "STARTING", "k" );
sqlKeywords.put( "STDDEV", "f" );
sqlKeywords.put( "STDDEV_POP", "f" );
sqlKeywords.put( "STDDEV_SAMP", "f" );
sqlKeywords.put( "STRAIGHT_JOIN", "k" );
sqlKeywords.put( "STRCMP", "f" );
sqlKeywords.put( "STR_TO_DATE", "f" );
sqlKeywords.put( "SUBDATE", "f" );
sqlKeywords.put( "SUBSTR", "f" );
sqlKeywords.put( "SUBSTRING", "f" );
sqlKeywords.put( "SUBSTRING_INDEX", "f" );
sqlKeywords.put( "SUBTIME", "f" );
sqlKeywords.put( "SUM", "f" );
sqlKeywords.put( "SYS.STRAGG", "f" );
sqlKeywords.put( "SYSCOLUMNS", "k" );
sqlKeywords.put( "SYSDATE", "f" );
sqlKeywords.put( "SYSOBJECTS", "k" );
sqlKeywords.put( "SYSTEM_USER", "f" );
sqlKeywords.put( "SYSUSERS", "k" );
sqlKeywords.put( "TABLE", "k" );
sqlKeywords.put( "TAN", "f" );
sqlKeywords.put( "TERMINATED", "k" );
sqlKeywords.put( "THEN", "k" );
sqlKeywords.put( "TIME", "k" );
sqlKeywords.put( "TIMEDIFF", "f" );
sqlKeywords.put( "TIMESTAMP", "f" );
sqlKeywords.put( "TIMESTAMPADD", "f" );
sqlKeywords.put( "TIME_FORMAT", "f" );
sqlKeywords.put( "TIME_TO_SEC", "f" );
sqlKeywords.put( "TINYBLOB", "k" );
sqlKeywords.put( "TINYINT", "k" );
sqlKeywords.put( "TINYTEXT", "k" );
sqlKeywords.put( "TOP", "k" );
sqlKeywords.put( "TO_CHAR", "f" );
sqlKeywords.put( "TO_DAYS", "f" );
sqlKeywords.put( "TO_SECONDS", "f" );
sqlKeywords.put( "TRAILING", "n" );
sqlKeywords.put( "TRIGGER", "k" );
sqlKeywords.put( "TRIM", "f" );
sqlKeywords.put( "TRUE", "1" );
sqlKeywords.put( "TRUNCATE", "f" );
sqlKeywords.put( "UCASE", "f" );
sqlKeywords.put( "UNCOMPRESS", "f" );
sqlKeywords.put( "UNCOMPRESS_LENGTH", "f" );
sqlKeywords.put( "UNDO", "k" );
sqlKeywords.put( "UNHEX", "f" );
sqlKeywords.put( "UNION", "U" );
sqlKeywords.put( "UNIQUE", "n" );
sqlKeywords.put( "UNIX_TIMESTAMP", "f" );
sqlKeywords.put( "UNLOCK", "k" );
sqlKeywords.put( "UNSIGNED", "k" );
sqlKeywords.put( "UPDATE", "k" );
sqlKeywords.put( "UPDATEXML", "f" );
sqlKeywords.put( "UPPER", "f" );
sqlKeywords.put( "USAGE", "k" );
sqlKeywords.put( "USE", "k" );
sqlKeywords.put( "USING", "f" );
sqlKeywords.put( "UTC_DATE", "k" );
sqlKeywords.put( "UTC_TIME", "k" );
sqlKeywords.put( "UTC_TIMESTAMP", "k" );
sqlKeywords.put( "UTL_INADDR.GET_HOST_ADDRESS", "f" );
sqlKeywords.put( "UUID", "f" );
sqlKeywords.put( "UUID_SHORT", "f" );
sqlKeywords.put( "VALUES", "k" );
sqlKeywords.put( "VARBINARY", "k" );
sqlKeywords.put( "VARCHAR", "k" );
sqlKeywords.put( "VARCHARACTER", "k" );
sqlKeywords.put( "VARIANCE", "f" );
sqlKeywords.put( "VARYING", "k" );
sqlKeywords.put( "VAR_POP", "f" );
sqlKeywords.put( "VAR_SAMP", "f" );
sqlKeywords.put( "VERSION", "f" );
sqlKeywords.put( "WAITFOR", "k" );
sqlKeywords.put( "WEEK", "f" );
sqlKeywords.put( "WEEKDAY", "f" );
sqlKeywords.put( "WEEKOFYEAR", "f" );
sqlKeywords.put( "WHEN", "k" );
sqlKeywords.put( "WHERE", "k" );
sqlKeywords.put( "WHILE", "k" );
sqlKeywords.put( "WITH", "k" );
sqlKeywords.put( "XMLELEMENT", "f" );
sqlKeywords.put( "XMLFOREST", "f" );
sqlKeywords.put( "XMLFORMAT", "f" );
sqlKeywords.put( "XMLTYPE", "f" );
sqlKeywords.put( "XOR", "o" );
sqlKeywords.put( "XP_EXECRESULTSET", "k" );
sqlKeywords.put( "YEAR", "f" );
sqlKeywords.put( "YEARWEEK", "f" );
sqlKeywords.put( "YEAR_MONTH", "k" );
sqlKeywords.put( "ZEROFILL", "k" );

multiKeywords.put( "ALTER DOMAIN", "k" );
multiKeywords.put( "ALTER TABLE", "k" );
multiKeywords.put( "CROSS JOIN", "k" );
multiKeywords.put( "FULL OUTER", "k" );
multiKeywords.put( "GROUP BY", "B" );
multiKeywords.put( "IN BOOLEAN", "n" );
multiKeywords.put( "IN BOOLEAN MODE", "k" );
multiKeywords.put( "INTERSECT ALL", "o" );
multiKeywords.put( "IS NOT", "o" );
multiKeywords.put( "LEFT JOIN", "k" );
multiKeywords.put( "LEFT OUTER", "k" );
multiKeywords.put( "LOCK TABLE", "k" );
multiKeywords.put( "LOCK TABLES", "k" );
multiKeywords.put( "NATURAL FULL", "k" );
multiKeywords.put( "NATURAL INNER", "k" );
multiKeywords.put( "NATURAL JOIN", "k" );
multiKeywords.put( "NATURAL LEFT", "k" );
multiKeywords.put( "NATURAL OUTER", "k" );
multiKeywords.put( "NATURAL RIGHT", "k" );
multiKeywords.put( "NOT BETWEEN", "o" );
multiKeywords.put( "NOT IN", "o" );
multiKeywords.put( "NOT LIKE", "o" );
multiKeywords.put( "NOT SIMILAR", "o" );
multiKeywords.put( "NOT SIMILAR TO", "o" );
multiKeywords.put( "ORDER BY", "B" );
multiKeywords.put( "OWN3D BY", "B" );
multiKeywords.put( "READ WRITE", "k" );
multiKeywords.put( "RIGHT JOIN", "k" );
multiKeywords.put( "RIGHT OUTER", "k" );
multiKeywords.put( "SELECT ALL", "k" );
multiKeywords.put( "SIMILAR TO", "o" );
multiKeywords.put( "SOUNDS LIKE", "o" );
multiKeywords.put( "UNION ALL", "U" );

pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");
pt2Function.add( "parse_white");	/*   */
pt2Function.add( "parse_operator2");	/* ! */
pt2Function.add( "parse_string");	/* " */
pt2Function.add( "parse_eol_comment");	/* # */
pt2Function.add( "parse_white");	/* $ */
pt2Function.add( "parse_operator1");	/* % */
pt2Function.add( "parse_operator2");	/* & */
pt2Function.add( "parse_string");	/* ' */
pt2Function.add( "parse_char");	/* ( */
pt2Function.add( "parse_char");	/* ) */
pt2Function.add( "parse_operator2");	/* * */
pt2Function.add( "parse_operator1");	/* + */
pt2Function.add( "parse_char");	/* , */
pt2Function.add( "parse_dash");	/* - */
pt2Function.add( "parse_number");	/* . */
pt2Function.add( "parse_slash");	/* / */
pt2Function.add( "parse_number");	/* 0 */
pt2Function.add( "parse_number");	/* 1 */
pt2Function.add( "parse_number");	/* 2 */
pt2Function.add( "parse_number");	/* 3 */
pt2Function.add( "parse_number");	/* 4 */
pt2Function.add( "parse_number");	/* 5 */
pt2Function.add( "parse_number");	/* 6 */
pt2Function.add( "parse_number");	/* 7 */
pt2Function.add( "parse_number");	/* 8 */
pt2Function.add( "parse_number");	/* 9 */
pt2Function.add( "parse_char");	/* : */
pt2Function.add( "parse_char");	/* ; */
pt2Function.add( "parse_operator2");	/* < */
pt2Function.add( "parse_operator2");	/* = */
pt2Function.add( "parse_operator2");	/* > */
pt2Function.add( "parse_other");	/* ? */
pt2Function.add( "parse_var");	/* @ */
pt2Function.add( "parse_word");	/* A */
pt2Function.add( "parse_word");	/* B */
pt2Function.add( "parse_word");	/* C */
pt2Function.add( "parse_word");	/* D */
pt2Function.add( "parse_word");	/* E */
pt2Function.add( "parse_word");	/* F */
pt2Function.add( "parse_word");	/* G */
pt2Function.add( "parse_word");	/* H */
pt2Function.add( "parse_word");	/* I */
pt2Function.add( "parse_word");	/* J */
pt2Function.add( "parse_word");	/* K */
pt2Function.add( "parse_word");	/* L */
pt2Function.add( "parse_word");	/* M */
pt2Function.add( "parse_word");	/* N */
pt2Function.add( "parse_word");	/* O */
pt2Function.add( "parse_word");	/* P */
pt2Function.add( "parse_word");	/* Q */
pt2Function.add( "parse_word");	/* R */
pt2Function.add( "parse_word");	/* S */
pt2Function.add( "parse_word");	/* T */
pt2Function.add( "parse_word");	/* U */
pt2Function.add( "parse_word");	/* V */
pt2Function.add( "parse_word");	/* W */
pt2Function.add( "parse_word");	/* X */
pt2Function.add( "parse_word");	/* Y */
pt2Function.add( "parse_word");	/* Z */
pt2Function.add( "parse_other");	/* [ */
pt2Function.add( "parse_backslash");	/* \ */
pt2Function.add( "parse_other");	/* ] */
pt2Function.add( "parse_operator1");	/* ^ */
pt2Function.add( "parse_word");	/* _ */
pt2Function.add( "parse_word");	/* ` */
pt2Function.add( "parse_word");	/* a */
pt2Function.add( "parse_word");	/* b */
pt2Function.add( "parse_word");	/* c */
pt2Function.add( "parse_word");	/* d */
pt2Function.add( "parse_word");	/* e */
pt2Function.add( "parse_word");	/* f */
pt2Function.add( "parse_word");	/* g */
pt2Function.add( "parse_word");	/* h */
pt2Function.add( "parse_word");	/* i */
pt2Function.add( "parse_word");	/* j */
pt2Function.add( "parse_word");	/* k */
pt2Function.add( "parse_word");	/* l */
pt2Function.add( "parse_word");	/* m */
pt2Function.add( "parse_word");	/* n */
pt2Function.add( "parse_word");	/* o */
pt2Function.add( "parse_word");	/* p */
pt2Function.add( "parse_word");	/* q */
pt2Function.add( "parse_word");	/* r */
pt2Function.add( "parse_word");	/* s */
pt2Function.add( "parse_word");	/* t */
pt2Function.add( "parse_word");	/* u */
pt2Function.add( "parse_word");	/* v */
pt2Function.add( "parse_word");	/* w */
pt2Function.add( "parse_word");	/* x */
pt2Function.add( "parse_word");	/* y */
pt2Function.add( "parse_word");	/* z */
pt2Function.add( "parse_other");	/* { */
pt2Function.add( "parse_operator2");	/* | */
pt2Function.add( "parse_other");	/* } */
pt2Function.add( "parse_operator1");	/* ~ */
pt2Function.add( "parse_white");

/* Automatically generated content end */

multiKeywordsStart = new HashSet( Arrays.asList( new String[]{
	//copy and paste from sqlparse_data.h
    "ALTER",
    "CROSS",
    "FULL",
    "GROUP",
    "IN",
    "IN BOOLEAN",
    "INTERSECT",
    "IS",
    "LEFT",
    "LOCK",
    "NATURAL",
    "NOT",
    "NOT SIMILAR",
    "ORDER",
    "OWN3D",
    "READ",
    "RIGHT",
    "SELECT",
    "SIMILAR",
    "SOUNDS",
    "UNION",
} ) );

operators2 = new HashSet( Arrays.asList( new String[]{
	//copy and paste from sqlparse_data.h
    "!!",
    "!<",
    "!=",
    "!>",
    "!~",
    "%=",
    "&&",
    "&=",
    "*=",
    "+=",
    "-=",
    "/=",
    ":=",
    "<<",
    "<=",
    "<>",
    "<@",
    ">=",
    ">>",
    "@>",
    "^=",
    "|/",
    "|=",
    "||",
    "~*",
} ) );


multikeywordsFirstWordTypeSet = new HashSet( Arrays.asList( new String[]{
"n","k","U","o"
} ) );

unaryOpSet = new HashSet( Arrays.asList( new String[]{
"+",
"-",
"!",
"!!",
"NOT",
"~"
}) );

arithOpSet = new HashSet( Arrays.asList( new String[]{
"-",
"+",
"~",
"!",
"/",
"%",
"*",
"|",
"&",
"MOD",
"DIV",
} ) );

fingerprints = new HashSet( Arrays.asList( new String[]{
"&1o1U",
"&1osU",
"&1ovU",
"&f()o",
"&f(1)",
"&f(s)",
"&f(v)",
"&so1U",
"&sovU",
"&vo1U",
"&vosU",
"&vovU",
"1&((f",
"1&((k",
"1&(1)",
"1&(1,",
"1&(1o",
"1&(f(",
"1&(k(",
"1&(k1",
"1&(kf",
"1&(kk",
"1&(kn",
"1&(ko",
"1&(ks",
"1&(kv",
"1&(s)",
"1&(s,",
"1&(so",
"1&(v)",
"1&(v,",
"1&(vo",
"1&1Bf",
"1&1Uk",
"1&1f(",
"1&1o(",
"1&1o1",
"1&1of",
"1&1ok",
"1&1on",
"1&1oo",
"1&1os",
"1&1ov",
"1&f((",
"1&f()",
"1&f(1",
"1&f(f",
"1&f(k",
"1&f(n",
"1&f(s",
"1&f(v",
"1&k(1",
"1&k(f",
"1&k(s",
"1&k(v",
"1&k1k",
"1&kUk",
"1&kk1",
"1&kks",
"1&kkv",
"1&ksk",
"1&kvk",
"1&n()",
"1&no1",
"1&nos",
"1&nov",
"1&o(1",
"1&o(s",
"1&o(v",
"1&o1o",
"1&oso",
"1&ovo",
"1&sBf",
"1&sU(",
"1&sUk",
"1&sf(",
"1&so(",
"1&so1",
"1&sof",
"1&sok",
"1&son",
"1&soo",
"1&sos",
"1&sov",
"1&vBf",
"1&vU(",
"1&vUk",
"1&vf(",
"1&vo(",
"1&vo1",
"1&vof",
"1&vok",
"1&von",
"1&voo",
"1&vos",
"1&vov",
"1)&(1",
"1)&(f",
"1)&(k",
"1)&(n",
"1)&(s",
"1)&(v",
"1)&1B",
"1)&1U",
"1)&1f",
"1)&1o",
"1)&f(",
"1)&o(",
"1)&sB",
"1)&sU",
"1)&sf",
"1)&so",
"1)&vB",
"1)&vU",
"1)&vf",
"1)&vo",
"1)()s",
"1)()v",
"1))&(",
"1))&1",
"1))&f",
"1))&o",
"1))&s",
"1))&v",
"1)))&",
"1))))",
"1)));",
"1)))B",
"1)))U",
"1)))k",
"1)))o",
"1));k",
"1))B1",
"1))Bs",
"1))Bv",
"1))Uk",
"1))Un",
"1))k1",
"1))kk",
"1))ks",
"1))kv",
"1))o(",
"1))o1",
"1))of",
"1))ok",
"1))on",
"1))os",
"1))ov",
"1),(1",
"1),(s",
"1),(v",
"1);k&",
"1);k(",
"1);kf",
"1);kk",
"1);kn",
"1);ko",
"1)B1",
"1)B1&",
"1)B1c",
"1)B1o",
"1)Bs",
"1)Bs&",
"1)Bsc",
"1)Bso",
"1)Bv",
"1)Bv&",
"1)Bvc",
"1)Bvo",
"1)U(k",
"1)Uk(",
"1)Uk1",
"1)Ukf",
"1)Ukk",
"1)Ukn",
"1)Uko",
"1)Uks",
"1)Ukv",
"1)Unk",
"1)k1",
"1)k1c",
"1)k1o",
"1)kks",
"1)kkv",
"1)ks",
"1)ksc",
"1)kso",
"1)kv",
"1)kvc",
"1)kvo",
"1)o(1",
"1)o(k",
"1)o(n",
"1)o(s",
"1)o(v",
"1)o1)",
"1)o1B",
"1)o1U",
"1)o1f",
"1)o1k",
"1)o1o",
"1)of(",
"1)ok(",
"1)ok1",
"1)oks",
"1)okv",
"1)on&",
"1)ono",
"1)os)",
"1)osB",
"1)osU",
"1)osf",
"1)osk",
"1)oso",
"1)ov)",
"1)ovB",
"1)ovU",
"1)ovf",
"1)ovk",
"1)ovo",
"1,(f(",
"1,(k(",
"1,(k1",
"1,(kf",
"1,(ks",
"1,(kv",
"1,1),",
"1,1)o",
"1,1B1",
"1,1Bs",
"1,1Bv",
"1,1Uk",
"1,f(1",
"1,f(s",
"1,f(v",
"1,s),",
"1,s)o",
"1,sB1",
"1,sBv",
"1,sUk",
"1,v),",
"1,v)o",
"1,vB1",
"1,vBs",
"1,vBv",
"1,vUk",
"1;k&k",
"1;k((",
"1;k(1",
"1;k(o",
"1;k(s",
"1;k(v",
"1;k1,",
"1;kf(",
"1;kks",
"1;kkv",
"1;kn(",
"1;kn,",
"1;knc",
"1;ko(",
"1;kok",
"1;ks,",
"1;kv,",
"1B1",
"1B1,1",
"1B1,n",
"1B1,s",
"1B1,v",
"1B1Uk",
"1B1c",
"1B1k1",
"1B1ks",
"1B1kv",
"1Bf(1",
"1Bf(f",
"1Bf(s",
"1Bf(v",
"1Bk(1",
"1Bk(s",
"1Bk(v",
"1Bn,n",
"1Bnk1",
"1Bnks",
"1Bnkv",
"1Bs",
"1Bs,1",
"1Bs,n",
"1Bs,v",
"1BsUk",
"1Bsc",
"1Bsk1",
"1Bsks",
"1Bskv",
"1Bv",
"1Bv,1",
"1Bv,n",
"1Bv,s",
"1Bv,v",
"1BvUk",
"1Bvc",
"1Bvk1",
"1Bvks",
"1Bvkv",
"1U((k",
"1U(k1",
"1U(kf",
"1U(kn",
"1U(ks",
"1U(kv",
"1U1,1",
"1U1,s",
"1U1,v",
"1Uc",
"1Uk",
"1Uk(1",
"1Uk(k",
"1Uk(n",
"1Uk(s",
"1Uk(v",
"1Uk1",
"1Uk1,",
"1Uk1c",
"1Uk1f",
"1Uk1k",
"1Uk1n",
"1Uk1o",
"1Ukf",
"1Ukf(",
"1Ukf,",
"1Ukk(",
"1Ukk,",
"1Ukk1",
"1Ukkk",
"1Ukkn",
"1Ukks",
"1Ukkv",
"1Ukn&",
"1Ukn(",
"1Ukn,",
"1Ukn1",
"1Uknc",
"1Uknk",
"1Ukno",
"1Ukns",
"1Uknv",
"1Uko1",
"1Ukok",
"1Ukos",
"1Ukov",
"1Uks",
"1Uks,",
"1Uksc",
"1Uksf",
"1Uksk",
"1Uksn",
"1Ukso",
"1Ukv",
"1Ukv,",
"1Ukvc",
"1Ukvf",
"1Ukvk",
"1Ukvn",
"1Ukvo",
"1Un,1",
"1Un,s",
"1Un,v",
"1Un1,",
"1Unk(",
"1Unk1",
"1Unkf",
"1Unks",
"1Unkv",
"1Uns,",
"1Unv,",
"1Uon1",
"1Uons",
"1Uonv",
"1Us,1",
"1Us,v",
"1Uv,1",
"1Uv,s",
"1Uv,v",
"1f()k",
"1k1U(",
"1k1Uk",
"1k1c",
"1kU1,",
"1kUs,",
"1kUv,",
"1kf(1",
"1kf(s",
"1kf(v",
"1kk(1",
"1kk(s",
"1kk(v",
"1kksc",
"1kkvc",
"1knkn",
"1ksU(",
"1ksUk",
"1ksc",
"1kvU(",
"1kvUk",
"1kvc",
"1n&f(",
"1nUk1",
"1nUkn",
"1nUks",
"1nUkv",
"1nk1c",
"1nkf(",
"1nksc",
"1nkvc",
"1o(((",
"1o((1",
"1o((f",
"1o((s",
"1o((v",
"1o(1)",
"1o(1o",
"1o(f(",
"1o(k(",
"1o(k1",
"1o(kf",
"1o(kn",
"1o(ks",
"1o(kv",
"1o(n)",
"1o(o1",
"1o(os",
"1o(s)",
"1o(so",
"1o(v)",
"1o(vo",
"1o1)&",
"1o1)o",
"1o1Bf",
"1o1Uk",
"1o1f(",
"1o1kf",
"1o1o(",
"1o1o1",
"1o1of",
"1o1oo",
"1o1os",
"1o1ov",
"1of()",
"1of(1",
"1of(f",
"1of(n",
"1of(s",
"1of(v",
"1ok(1",
"1ok(k",
"1ok(s",
"1ok(v",
"1ok)U",
"1ok)o",
"1ok1",
"1ok1,",
"1ok1c",
"1ok1k",
"1okUk",
"1okf(",
"1oks",
"1oks,",
"1oksc",
"1oksk",
"1okv",
"1okv,",
"1okvc",
"1okvk",
"1onos",
"1onov",
"1os)&",
"1os)U",
"1os)o",
"1osBf",
"1osUk",
"1osf(",
"1oskf",
"1oso(",
"1oso1",
"1osof",
"1osoo",
"1osos",
"1osov",
"1ov)&",
"1ov)U",
"1ov)o",
"1ovBf",
"1ovUk",
"1ovf(",
"1ovkf",
"1ovo(",
"1ovo1",
"1ovof",
"1ovoo",
"1ovos",
"1ovov",
";kknc",
"Uk1,1",
"Uk1,f",
"Uk1,n",
"Uk1,s",
"Uk1,v",
"Ukkkn",
"Uks,1",
"Uks,f",
"Uks,n",
"Uks,v",
"Ukv,1",
"Ukv,f",
"Ukv,n",
"Ukv,s",
"Ukv,v",
"f((k(",
"f((kf",
"f()&f",
"f()of",
"f(1)&",
"f(1)U",
"f(1)o",
"f(1,1",
"f(1,f",
"f(1,s",
"f(1,v",
"f(1o1",
"f(1os",
"f(1ov",
"f(f()",
"f(f(1",
"f(f(f",
"f(f(s",
"f(f(v",
"f(k()",
"f(k,(",
"f(k,n",
"f(n()",
"f(s)&",
"f(s)U",
"f(s)o",
"f(s,1",
"f(s,f",
"f(s,v",
"f(so1",
"f(sov",
"f(v)&",
"f(v)U",
"f(v)o",
"f(v,1",
"f(v,f",
"f(v,s",
"f(v,v",
"f(vo1",
"f(vos",
"f(vov",
"k()ok",
"k(1)U",
"k(ok(",
"k(s)U",
"k(sv)",
"k(v)U",
"k(vs)",
"k(vv)",
"k1,1,",
"k1,1c",
"k1,1k",
"k1,f(",
"k1,s,",
"k1,sc",
"k1,sk",
"k1,v,",
"k1,vc",
"k1,vk",
"k1k(k",
"k1o(s",
"k1o(v",
"k;non",
"kf(1)",
"kf(1,",
"kf(f(",
"kf(n,",
"kf(o)",
"kf(s)",
"kf(s,",
"kf(s:",
"kf(v)",
"kf(v,",
"kf(v:",
"kk(f(",
"kk1f(",
"kk1fn",
"kk1kk",
"kk1nk",
"kk1sk",
"kk1sn",
"kk1vk",
"kk1vn",
"kksf(",
"kksfn",
"kkskk",
"kksnk",
"kksvk",
"kksvn",
"kkvf(",
"kkvfn",
"kkvkk",
"kkvnk",
"kkvsk",
"kkvsn",
"kkvvk",
"kkvvn",
"kn1kk",
"kn1sk",
"kn1sn",
"kn1vk",
"kn1vn",
"knk(k",
"knskk",
"knsvk",
"knsvn",
"knvkk",
"knvsk",
"knvsn",
"knvvk",
"knvvn",
"ko(k(",
"ko(kf",
"kok(k",
"ks)",
"ks,1,",
"ks,1c",
"ks,1k",
"ks,f(",
"ks,v,",
"ks,vc",
"ks,vk",
"ksf(1",
"ksf(v",
"ksk(1",
"ksk(k",
"ksk(v",
"kso(s",
"kso(v",
"kv)",
"kv,1,",
"kv,1c",
"kv,1k",
"kv,f(",
"kv,s,",
"kv,sc",
"kv,sk",
"kv,v,",
"kv,vc",
"kv,vk",
"kvf(1",
"kvf(s",
"kvf(v",
"kvk(1",
"kvk(k",
"kvk(s",
"kvk(v",
"kvo(s",
"kvo(v",
"n&(1)",
"n&(1,",
"n&(k1",
"n&(ks",
"n&(kv",
"n&(o1",
"n&(os",
"n&(ov",
"n&(s)",
"n&(s,",
"n&(v)",
"n&(v,",
"n&1f(",
"n&1o(",
"n&1o1",
"n&1of",
"n&1oo",
"n&1os",
"n&1ov",
"n&f(1",
"n&f(f",
"n&f(s",
"n&f(v",
"n&k(1",
"n&k(s",
"n&k(v",
"n&o1o",
"n&oso",
"n&ovo",
"n&sf(",
"n&so(",
"n&so1",
"n&sof",
"n&soo",
"n&sov",
"n&vf(",
"n&vo(",
"n&vo1",
"n&vof",
"n&voo",
"n&vos",
"n&vov",
"n)&(k",
"n)&1f",
"n)&1o",
"n)&f(",
"n)&sf",
"n)&so",
"n)&vf",
"n)&vo",
"n))&(",
"n))&1",
"n))&f",
"n))&s",
"n))&v",
"n)))&",
"n)));",
"n)))k",
"n)))o",
"n));k",
"n))kk",
"n))o(",
"n))o1",
"n))of",
"n))ok",
"n))os",
"n))ov",
"n);k&",
"n);k(",
"n);kf",
"n);kk",
"n);kn",
"n);ko",
"n)k1o",
"n)kks",
"n)kkv",
"n)kso",
"n)kvo",
"n)o(k",
"n)o1&",
"n)o1f",
"n)o1o",
"n)of(",
"n)ok(",
"n)os&",
"n)osf",
"n)oso",
"n)ov&",
"n)ovf",
"n)ovo",
"n,(f(",
"n,(k(",
"n,(k1",
"n,(kf",
"n,(ks",
"n,(kv",
"n,f(1",
"n,f(s",
"n,f(v",
"n:o1U",
"n:osU",
"n:ovU",
"n;k&k",
"n;k((",
"n;k(1",
"n;k(s",
"n;k(v",
"n;kf(",
"n;kks",
"n;kkv",
"n;kn(",
"n;ko(",
"n;kok",
"nUk(k",
"nUk1,",
"nUkn,",
"nUks,",
"nUkv,",
"nUnk(",
"nk1Uk",
"nkf(1",
"nkf(s",
"nkf(v",
"nkksc",
"nkkvc",
"nksUk",
"nkvUk",
"nnn)U",
"nno1U",
"nnosU",
"nnovU",
"no(k1",
"no(ks",
"no(kv",
"no(o1",
"no(os",
"no(ov",
"no1&1",
"no1&s",
"no1&v",
"no1Uk",
"no1f(",
"no1o(",
"no1o1",
"no1of",
"no1oo",
"no1os",
"no1ov",
"nof(1",
"nof(s",
"nof(v",
"nok(1",
"nok(f",
"nok(k",
"nok(s",
"nok(v",
"nos&1",
"nos&v",
"nosUk",
"nosf(",
"noso(",
"noso1",
"nosof",
"nosoo",
"nosov",
"nov&1",
"nov&s",
"nov&v",
"novUk",
"novf(",
"novo(",
"novo1",
"novof",
"novoo",
"novos",
"novov",
"o1kf(",
"oUk1,",
"oUks,",
"oUkv,",
"of()o",
"of(1)",
"of(s)",
"of(v)",
"ok1o1",
"ok1os",
"ok1ov",
"okkkn",
"okso1",
"oksov",
"okvo1",
"okvos",
"okvov",
"ook1,",
"ooks,",
"ookv,",
"oskf(",
"ovkf(",
"s&((f",
"s&((k",
"s&(1)",
"s&(1,",
"s&(1o",
"s&(f(",
"s&(k(",
"s&(k)",
"s&(k1",
"s&(kf",
"s&(kk",
"s&(kn",
"s&(ko",
"s&(ks",
"s&(kv",
"s&(s)",
"s&(s,",
"s&(so",
"s&(v)",
"s&(v,",
"s&(vo",
"s&1Bf",
"s&1Uk",
"s&1c",
"s&1f(",
"s&1o(",
"s&1o1",
"s&1of",
"s&1ok",
"s&1on",
"s&1oo",
"s&1os",
"s&1ov",
"s&f((",
"s&f()",
"s&f(1",
"s&f(f",
"s&f(k",
"s&f(n",
"s&f(s",
"s&f(v",
"s&k&s",
"s&k&v",
"s&k(1",
"s&k(f",
"s&k(o",
"s&k(s",
"s&k(v",
"s&k1k",
"s&k1o",
"s&kUk",
"s&kc",
"s&kk1",
"s&kkv",
"s&knk",
"s&ko(",
"s&ko1",
"s&kok",
"s&kos",
"s&kov",
"s&kso",
"s&kvk",
"s&kvo",
"s&n&s",
"s&n&v",
"s&n()",
"s&no1",
"s&nos",
"s&nov",
"s&o(1",
"s&o(k",
"s&o(s",
"s&o(v",
"s&o1o",
"s&okc",
"s&oko",
"s&os",
"s&oso",
"s&ov",
"s&ovo",
"s&s:o",
"s&sBf",
"s&sU(",
"s&sUk",
"s&sc",
"s&sf(",
"s&so(",
"s&so1",
"s&sof",
"s&son",
"s&soo",
"s&sos",
"s&sov",
"s&sso",
"s&svo",
"s&v:o",
"s&vBf",
"s&vU(",
"s&vUk",
"s&vc",
"s&vf(",
"s&vo(",
"s&vo1",
"s&vof",
"s&vok",
"s&von",
"s&voo",
"s&vos",
"s&vov",
"s&vso",
"s&vvo",
"s)&(1",
"s)&(f",
"s)&(k",
"s)&(n",
"s)&(s",
"s)&(v",
"s)&1B",
"s)&1U",
"s)&1f",
"s)&1o",
"s)&f(",
"s)&o(",
"s)&sB",
"s)&sf",
"s)&so",
"s)&vB",
"s)&vU",
"s)&vf",
"s)&vo",
"s)()s",
"s)()v",
"s))&(",
"s))&1",
"s))&f",
"s))&n",
"s))&o",
"s))&s",
"s))&v",
"s)))&",
"s))))",
"s)));",
"s)))B",
"s)))U",
"s)))k",
"s)))o",
"s));k",
"s))B1",
"s))Bs",
"s))Bv",
"s))Uk",
"s))Un",
"s))k1",
"s))kk",
"s))ks",
"s))kv",
"s))o(",
"s))o1",
"s))of",
"s))ok",
"s))on",
"s))os",
"s))ov",
"s),(1",
"s),(s",
"s),(v",
"s);k&",
"s);k(",
"s);kf",
"s);kk",
"s);kn",
"s);ko",
"s)B1",
"s)B1&",
"s)B1c",
"s)B1o",
"s)Bs",
"s)Bs&",
"s)Bsc",
"s)Bso",
"s)Bv",
"s)Bv&",
"s)Bvc",
"s)Bvo",
"s)U(k",
"s)Uk(",
"s)Uk1",
"s)Ukf",
"s)Ukk",
"s)Ukn",
"s)Uko",
"s)Uks",
"s)Ukv",
"s)Unk",
"s)k1",
"s)k1c",
"s)k1o",
"s)kks",
"s)kkv",
"s)ks",
"s)ksc",
"s)kso",
"s)kv",
"s)kvc",
"s)kvo",
"s)o(1",
"s)o(k",
"s)o(n",
"s)o(s",
"s)o(v",
"s)o1B",
"s)o1f",
"s)o1k",
"s)o1o",
"s)of(",
"s)ok(",
"s)ok1",
"s)oks",
"s)okv",
"s)on&",
"s)ono",
"s)os)",
"s)osB",
"s)osU",
"s)osf",
"s)osk",
"s)oso",
"s)ov)",
"s)ovB",
"s)ovf",
"s)ovk",
"s)ovo",
"s,(f(",
"s,(k(",
"s,(k1",
"s,(kf",
"s,(kv",
"s,1),",
"s,1)o",
"s,1B1",
"s,1Bv",
"s,1Uk",
"s,f(1",
"s,f(v",
"s,s),",
"s,v),",
"s,v)o",
"s,vB1",
"s,vBv",
"s,vUk",
"s:o1)",
"s:ov)",
"s;k&k",
"s;k((",
"s;k(1",
"s;k(o",
"s;k(s",
"s;k(v",
"s;k1,",
"s;k1o",
"s;k;",
"s;k[k",
"s;k[n",
"s;kf(",
"s;kkn",
"s;kks",
"s;kkv",
"s;kn(",
"s;kn,",
"s;knc",
"s;knk",
"s;knn",
"s;ko(",
"s;kok",
"s;ks,",
"s;ksc",
"s;ksk",
"s;kso",
"s;kv,",
"s;kvc",
"s;kvk",
"s;kvo",
"s;n:k",
"sB1",
"sB1&s",
"sB1&v",
"sB1,1",
"sB1,n",
"sB1,v",
"sB1Uk",
"sB1c",
"sB1k1",
"sB1ks",
"sB1kv",
"sB1os",
"sB1ov",
"sBf(1",
"sBf(f",
"sBf(v",
"sBk(1",
"sBk(v",
"sBn,n",
"sBnk1",
"sBnkv",
"sBs",
"sBs&s",
"sBs&v",
"sBsUk",
"sBsc",
"sBsos",
"sBsov",
"sBv",
"sBv&s",
"sBv&v",
"sBv,1",
"sBv,n",
"sBv,v",
"sBvUk",
"sBvc",
"sBvk1",
"sBvks",
"sBvkv",
"sBvos",
"sBvov",
"sU((k",
"sU(k(",
"sU(k1",
"sU(kf",
"sU(kk",
"sU(kn",
"sU(ks",
"sU(kv",
"sU1,1",
"sU1,v",
"sUc",
"sUk",
"sUk(1",
"sUk(k",
"sUk(n",
"sUk(v",
"sUk1",
"sUk1&",
"sUk1,",
"sUk1c",
"sUk1f",
"sUk1k",
"sUk1n",
"sUk1o",
"sUkf",
"sUkf(",
"sUkf,",
"sUkk(",
"sUkk,",
"sUkk1",
"sUkkk",
"sUkkn",
"sUkks",
"sUkkv",
"sUkn&",
"sUkn(",
"sUkn,",
"sUkn1",
"sUknc",
"sUknk",
"sUkno",
"sUkns",
"sUknv",
"sUko1",
"sUkok",
"sUkov",
"sUks",
"sUks&",
"sUks,",
"sUksc",
"sUksk",
"sUkso",
"sUkv",
"sUkv&",
"sUkv,",
"sUkvc",
"sUkvf",
"sUkvk",
"sUkvn",
"sUkvo",
"sUn(k",
"sUn,1",
"sUn,v",
"sUn1,",
"sUnk(",
"sUnk1",
"sUnkf",
"sUnks",
"sUnkv",
"sUno1",
"sUnos",
"sUnov",
"sUnv,",
"sUon1",
"sUonv",
"sUv,1",
"sUv,v",
"sf()k",
"sf(1)",
"sf(n,",
"sf(s)",
"sf(v)",
"sk)&(",
"sk)&1",
"sk)&f",
"sk)&s",
"sk)&v",
"sk);k",
"sk)B1",
"sk)Bs",
"sk)Bv",
"sk)Uk",
"sk)Un",
"sk)k1",
"sk)kk",
"sk)ks",
"sk)kv",
"sk)o(",
"sk)o1",
"sk)of",
"sk)ok",
"sk)os",
"sk)ov",
"sk1&1",
"sk1&s",
"sk1&v",
"sk1U(",
"sk1Uk",
"sk1c",
"sk1o1",
"sk1os",
"sk1ov",
"skU1,",
"skUs,",
"skUv,",
"skf(1",
"skf(v",
"skk(1",
"skk(v",
"skks",
"skksc",
"skkv",
"skkvc",
"sknkn",
"sks&1",
"sks&v",
"sksUk",
"sksc",
"skso1",
"sksos",
"sksov",
"skv&1",
"skv&s",
"skv&v",
"skvU(",
"skvUk",
"skvc",
"skvo1",
"skvos",
"skvov",
"sn&f(",
"sn,f(",
"snUk1",
"snUkn",
"snUkv",
"snk1c",
"snkf(",
"snkvc",
"sno1U",
"snosU",
"snovU",
"so(((",
"so((1",
"so((f",
"so((k",
"so((s",
"so((v",
"so(1)",
"so(1o",
"so(f(",
"so(k(",
"so(k)",
"so(k1",
"so(kf",
"so(kk",
"so(kn",
"so(ko",
"so(ks",
"so(kv",
"so(n)",
"so(o1",
"so(os",
"so(ov",
"so(s)",
"so(so",
"so(v)",
"so(vo",
"so1&1",
"so1&o",
"so1&s",
"so1&v",
"so1)&",
"so1)o",
"so1Bf",
"so1Uk",
"so1c",
"so1f(",
"so1kf",
"so1o(",
"so1o1",
"so1of",
"so1ok",
"so1oo",
"so1os",
"so1ov",
"sof()",
"sof(1",
"sof(f",
"sof(k",
"sof(n",
"sof(s",
"sof(v",
"sok&s",
"sok&v",
"sok(1",
"sok(k",
"sok(o",
"sok(s",
"sok(v",
"sok1",
"sok1,",
"sok1c",
"sok1k",
"sok1o",
"sokUk",
"sokc",
"sokf(",
"sokn,",
"soknk",
"soko(",
"soko1",
"sokok",
"sokos",
"sokov",
"soks",
"soks,",
"soksc",
"sokso",
"sokv",
"sokv,",
"sokvc",
"sokvk",
"sokvo",
"sonk1",
"sonks",
"sonkv",
"sono1",
"sonos",
"sonov",
"sos",
"sos&(",
"sos&1",
"sos&o",
"sos&s",
"sos&v",
"sos:o",
"sosBf",
"sosUk",
"sosc",
"sosf(",
"soskf",
"soso(",
"soso1",
"sosof",
"sosok",
"sosoo",
"sosos",
"sosov",
"sosso",
"sosvo",
"sov",
"sov&(",
"sov&1",
"sov&o",
"sov&s",
"sov&v",
"sov)&",
"sov)o",
"sov:o",
"sovBf",
"sovUk",
"sovc",
"sovf(",
"sovkf",
"sovo(",
"sovo1",
"sovof",
"sovok",
"sovoo",
"sovos",
"sovov",
"sovso",
"sovvo",
"v&((f",
"v&((k",
"v&(1)",
"v&(1,",
"v&(1o",
"v&(f(",
"v&(k(",
"v&(k)",
"v&(k1",
"v&(kf",
"v&(kk",
"v&(kn",
"v&(ko",
"v&(ks",
"v&(kv",
"v&(s)",
"v&(s,",
"v&(so",
"v&(v)",
"v&(v,",
"v&(vo",
"v&1Bf",
"v&1Uk",
"v&1c",
"v&1f(",
"v&1o(",
"v&1o1",
"v&1of",
"v&1ok",
"v&1on",
"v&1oo",
"v&1os",
"v&1ov",
"v&f((",
"v&f()",
"v&f(1",
"v&f(f",
"v&f(k",
"v&f(n",
"v&f(s",
"v&f(v",
"v&k&s",
"v&k&v",
"v&k(1",
"v&k(f",
"v&k(o",
"v&k(s",
"v&k(v",
"v&k1k",
"v&k1o",
"v&kUk",
"v&kc",
"v&kk1",
"v&kks",
"v&kkv",
"v&knk",
"v&ko(",
"v&ko1",
"v&kok",
"v&kos",
"v&kov",
"v&ksk",
"v&kso",
"v&kvk",
"v&kvo",
"v&n&s",
"v&n&v",
"v&n()",
"v&no1",
"v&nos",
"v&nov",
"v&o(1",
"v&o(k",
"v&o(s",
"v&o(v",
"v&o1o",
"v&okc",
"v&oko",
"v&os",
"v&oso",
"v&ov",
"v&ovo",
"v&s:o",
"v&sBf",
"v&sU(",
"v&sUk",
"v&sc",
"v&sf(",
"v&so(",
"v&so1",
"v&sof",
"v&sok",
"v&son",
"v&soo",
"v&sos",
"v&sov",
"v&sso",
"v&svo",
"v&v:o",
"v&vBf",
"v&vU(",
"v&vUk",
"v&vc",
"v&vf(",
"v&vo(",
"v&vo1",
"v&vof",
"v&vok",
"v&von",
"v&voo",
"v&vos",
"v&vov",
"v&vso",
"v&vvo",
"v)&(1",
"v)&(f",
"v)&(k",
"v)&(n",
"v)&(s",
"v)&(v",
"v)&1B",
"v)&1U",
"v)&1f",
"v)&1o",
"v)&f(",
"v)&o(",
"v)&sB",
"v)&sU",
"v)&sf",
"v)&so",
"v)&vB",
"v)&vU",
"v)&vf",
"v)&vo",
"v)()s",
"v)()v",
"v))&(",
"v))&1",
"v))&f",
"v))&n",
"v))&o",
"v))&s",
"v))&v",
"v)))&",
"v))))",
"v)));",
"v)))B",
"v)))U",
"v)))k",
"v)))o",
"v));k",
"v))B1",
"v))Bs",
"v))Bv",
"v))Uk",
"v))Un",
"v))k1",
"v))kk",
"v))ks",
"v))kv",
"v))o(",
"v))o1",
"v))of",
"v))ok",
"v))on",
"v))os",
"v))ov",
"v),(1",
"v),(s",
"v),(v",
"v);k&",
"v);k(",
"v);kf",
"v);kk",
"v);kn",
"v);ko",
"v)B1",
"v)B1&",
"v)B1c",
"v)B1o",
"v)Bs",
"v)Bs&",
"v)Bsc",
"v)Bso",
"v)Bv",
"v)Bv&",
"v)Bvc",
"v)Bvo",
"v)U(k",
"v)Uk(",
"v)Uk1",
"v)Ukf",
"v)Ukk",
"v)Ukn",
"v)Uko",
"v)Uks",
"v)Ukv",
"v)Unk",
"v)k1",
"v)k1c",
"v)k1o",
"v)kks",
"v)kkv",
"v)ks",
"v)ksc",
"v)kso",
"v)kv",
"v)kvc",
"v)kvo",
"v)o(1",
"v)o(k",
"v)o(n",
"v)o(s",
"v)o(v",
"v)o1B",
"v)o1U",
"v)o1f",
"v)o1k",
"v)o1o",
"v)of(",
"v)ok(",
"v)ok1",
"v)oks",
"v)okv",
"v)on&",
"v)ono",
"v)os)",
"v)osB",
"v)osU",
"v)osf",
"v)osk",
"v)oso",
"v)ovB",
"v)ovU",
"v)ovf",
"v)ovk",
"v)ovo",
"v,(f(",
"v,(k(",
"v,(k1",
"v,(kf",
"v,(ks",
"v,(kv",
"v,1),",
"v,1)o",
"v,1B1",
"v,1Bs",
"v,1Bv",
"v,1Uk",
"v,f(1",
"v,f(s",
"v,f(v",
"v,s),",
"v,s)o",
"v,sB1",
"v,sBv",
"v,sUk",
"v,v),",
"v,v)o",
"v,vB1",
"v,vBs",
"v,vBv",
"v,vUk",
"v:o1)",
"v:os)",
"v:ov)",
"v;k&k",
"v;k((",
"v;k(1",
"v;k(o",
"v;k(s",
"v;k(v",
"v;k1,",
"v;k1o",
"v;k;",
"v;k[k",
"v;k[n",
"v;kf(",
"v;kkn",
"v;kks",
"v;kkv",
"v;kn(",
"v;kn,",
"v;knc",
"v;knk",
"v;knn",
"v;ko(",
"v;kok",
"v;ks,",
"v;ksc",
"v;ksk",
"v;kso",
"v;kv,",
"v;kvc",
"v;kvk",
"v;kvo",
"v;n:k",
"vB1",
"vB1&s",
"vB1&v",
"vB1,1",
"vB1,n",
"vB1,s",
"vB1,v",
"vB1Uk",
"vB1c",
"vB1k1",
"vB1ks",
"vB1kv",
"vB1os",
"vB1ov",
"vBf(1",
"vBf(f",
"vBf(s",
"vBf(v",
"vBk(1",
"vBk(s",
"vBk(v",
"vBn,n",
"vBnk1",
"vBnks",
"vBnkv",
"vBs",
"vBs&s",
"vBs&v",
"vBs,1",
"vBs,n",
"vBs,v",
"vBsUk",
"vBsc",
"vBsk1",
"vBsks",
"vBskv",
"vBsos",
"vBsov",
"vBv",
"vBv&s",
"vBv&v",
"vBv,1",
"vBv,n",
"vBv,s",
"vBv,v",
"vBvUk",
"vBvc",
"vBvk1",
"vBvks",
"vBvkv",
"vBvos",
"vBvov",
"vU((k",
"vU(k(",
"vU(k1",
"vU(kf",
"vU(kk",
"vU(kn",
"vU(ks",
"vU(kv",
"vU1,1",
"vU1,s",
"vU1,v",
"vUc",
"vUk",
"vUk(1",
"vUk(k",
"vUk(n",
"vUk(s",
"vUk(v",
"vUk1",
"vUk1&",
"vUk1,",
"vUk1c",
"vUk1f",
"vUk1k",
"vUk1n",
"vUk1o",
"vUkf",
"vUkf(",
"vUkf,",
"vUkk(",
"vUkk,",
"vUkk1",
"vUkkk",
"vUkkn",
"vUkks",
"vUkkv",
"vUkn&",
"vUkn(",
"vUkn,",
"vUkn1",
"vUknc",
"vUknk",
"vUkno",
"vUkns",
"vUknv",
"vUko1",
"vUkok",
"vUkos",
"vUkov",
"vUks",
"vUks&",
"vUks,",
"vUksc",
"vUksf",
"vUksk",
"vUksn",
"vUkso",
"vUkv",
"vUkv&",
"vUkv,",
"vUkvc",
"vUkvf",
"vUkvk",
"vUkvn",
"vUkvo",
"vUn(k",
"vUn,1",
"vUn,s",
"vUn,v",
"vUn1,",
"vUnk(",
"vUnk1",
"vUnkf",
"vUnks",
"vUnkv",
"vUno1",
"vUnos",
"vUnov",
"vUns,",
"vUnv,",
"vUon1",
"vUons",
"vUonv",
"vUs,1",
"vUs,v",
"vUv,1",
"vUv,s",
"vUv,v",
"vf()k",
"vf(1)",
"vf(n,",
"vf(s)",
"vf(v)",
"vk)&(",
"vk)&1",
"vk)&f",
"vk)&s",
"vk)&v",
"vk);k",
"vk)B1",
"vk)Bs",
"vk)Bv",
"vk)Uk",
"vk)Un",
"vk)k1",
"vk)kk",
"vk)ks",
"vk)kv",
"vk)o(",
"vk)o1",
"vk)of",
"vk)ok",
"vk)os",
"vk)ov",
"vk1&1",
"vk1&s",
"vk1&v",
"vk1U(",
"vk1Uk",
"vk1c",
"vk1o1",
"vk1os",
"vk1ov",
"vkU1,",
"vkUs,",
"vkUv,",
"vkf(1",
"vkf(s",
"vkf(v",
"vkk(1",
"vkk(s",
"vkk(v",
"vkks",
"vkksc",
"vkkv",
"vkkvc",
"vknkn",
"vks&1",
"vks&v",
"vksU(",
"vksUk",
"vksc",
"vkso1",
"vksos",
"vksov",
"vkv&1",
"vkv&s",
"vkv&v",
"vkvU(",
"vkvUk",
"vkvc",
"vkvo1",
"vkvos",
"vkvov",
"vn&f(",
"vn,f(",
"vnUk1",
"vnUkn",
"vnUks",
"vnUkv",
"vnk1c",
"vnkf(",
"vnksc",
"vnkvc",
"vno1U",
"vnosU",
"vnovU",
"vo(((",
"vo((1",
"vo((f",
"vo((k",
"vo((s",
"vo((v",
"vo(1)",
"vo(1o",
"vo(f(",
"vo(k(",
"vo(k)",
"vo(k1",
"vo(kf",
"vo(kk",
"vo(kn",
"vo(ko",
"vo(ks",
"vo(kv",
"vo(n)",
"vo(o1",
"vo(os",
"vo(ov",
"vo(s)",
"vo(so",
"vo(v)",
"vo(vo",
"vo1&1",
"vo1&o",
"vo1&s",
"vo1&v",
"vo1)&",
"vo1)o",
"vo1Bf",
"vo1Uk",
"vo1c",
"vo1f(",
"vo1kf",
"vo1o(",
"vo1o1",
"vo1of",
"vo1ok",
"vo1oo",
"vo1os",
"vo1ov",
"vof()",
"vof(1",
"vof(f",
"vof(k",
"vof(n",
"vof(s",
"vof(v",
"vok&s",
"vok&v",
"vok(1",
"vok(k",
"vok(o",
"vok(s",
"vok(v",
"vok1",
"vok1,",
"vok1c",
"vok1k",
"vok1o",
"vokUk",
"vokc",
"vokf(",
"vokn,",
"voknk",
"voko(",
"voko1",
"vokok",
"vokos",
"vokov",
"voks",
"voks,",
"voksc",
"voksk",
"vokso",
"vokv",
"vokv,",
"vokvc",
"vokvk",
"vokvo",
"vonk1",
"vonks",
"vonkv",
"vono1",
"vonos",
"vonov",
"vos",
"vos&(",
"vos&1",
"vos&o",
"vos&s",
"vos&v",
"vos)&",
"vos)o",
"vos:o",
"vosBf",
"vosUk",
"vosc",
"vosf(",
"voskf",
"voso(",
"voso1",
"vosof",
"vosok",
"vosoo",
"vosos",
"vosov",
"vosso",
"vosvo",
"vov",
"vov&(",
"vov&1",
"vov&o",
"vov&s",
"vov&v",
"vov)&",
"vov)o",
"vov:o",
"vovBf",
"vovUk",
"vovc",
"vovf(",
"vovkf",
"vovo(",
"vovo1",
"vovof",
"vovok",
"vovoo",
"vovos",
"vovov",
"vovso",
"vovvo",
} ) );

}
//--------------------------------------------------------------------------------
public static boolean isSQLi( final String input )
{
try
	{
	return isSQLiImpl( input, null );
	}
catch( Exception e )
	{
	e.printStackTrace();
	return false;
	}
}
//--------------------------------------------------------------------------------
private static boolean isSQLiImpl( final String input, String[] tokenBuf )
throws Exception
{
if( tokenBuf == null )
	{
	tokenBuf = new String[ 1 ];
	}

String tokenized = sqli_tokenize( input );
tokenBuf[ 0 ] = tokenized;
if( fingerprints.contains( tokenized ) )
	{
	p( "isSQLi: type1 : " + tokenized );
	return true;
	}

if( input.indexOf( '\'' ) > -1 )
	{
	tokenized = sqli_tokenize( "'" + input );
	tokenBuf[ 0 ] = tokenized;
	if( fingerprints.contains( tokenized ) )
		{
		p( "isSQLi: type2 : " + tokenized );
		return true;
		}
	}

if( input.indexOf( '"' ) > -1 )
	{
	tokenized = sqli_tokenize( "\"" + input );
	tokenBuf[ 0 ] = tokenized;
	if( fingerprints.contains( tokenized ) )
		{
		p( "isSQLi: type3 : " + tokenized );
		return true;
		}
	}

return false;
}
//--------------------------------------------------------------------------------
protected static void p( Object o )
{
if( debug )
	{
	System.out.println( o );
	}
}
//--------------------------------------------------------------------------------
protected static void parse_word( String input, String[] typeBuf, int[] lengthBuf )
{
String word = getMatch( "^[A-Z0-9_\\.$]+", input );
lengthBuf[ 0 ] = word.length();
String value = ( String )sqlKeywords.get( word );
if( value == null )
	{
	typeBuf[ 0 ] = "n";
	}
else
	{
	typeBuf[ 0 ] = value;
	}
}
//--------------------------------------------------------------------------------
protected static void parse_string( String input, char delim, int[] lengthBuf )
{
if( input.length() == 1 )
	{
	lengthBuf[ 0 ] = 1;
	return;
	}
else if( input.equals( "\"\"" ) || input.equals( "''" ) )
	{
	lengthBuf[ 0 ] = 2;
	return;
	}
for( int i = 0; i < input.length(); ++i )
	{
	if( input.charAt( i ) == delim )
		{
		if( i == 0 )
			{
			//ignore
			}
		else
			{
			if( input.charAt( i - 1 ) == '\\' )
				{
				//ignore
				}
			else
				{
				lengthBuf[ 0 ] = i + 1;
				return;
				}
			}
		}
	}
lengthBuf[ 0 ] = input.length();
return;
}
//--------------------------------------------------------------------------------
protected static void parse_number( String input, String[] typeBuf, int[] lengthBuf )
{
if( input.startsWith( "0X" ) )
	{
	String match = getMatch( "^0X[0-9A-F]*", input );
	if( match.length() == 2 )
		{
		lengthBuf[ 0 ] = 2;
		typeBuf[ 0 ] = "n";
		}
	else
		{
		lengthBuf[ 0 ] = match.length();
		typeBuf[ 0 ] = "1";
		}
	}
else if( input.equals( "." ) )
	{
	lengthBuf[ 0 ] = 1;
	typeBuf[ 0 ] = "n";
	}
else if( input.matches( "^\\.[^0-9]+.*" ) ) // .A 
	{
	lengthBuf[ 0 ] = 1;
	typeBuf[ 0 ] = "n";
	}
else
	{
	String match = getMatch( "^[0-9\\.]+", input );
	String rest = input.substring( match.length() );
	if( rest.length() > 0 && rest.charAt( 0 ) == 'E' )
		{
		rest = getMatch( "^(E[\\+\\-]?[0-9]*)", rest );
		lengthBuf[ 0 ] = match.length() + rest.length(); //TODO: 1.2E-1A should be 'n'?
		typeBuf[ 0 ] = "1";
		}
	else if( rest.matches( "^[A-Z]+.*" ) ) //oh no!
		{
		String restAlNum = getMatch( "^([A-Z0-9]+)", rest );
		lengthBuf[ 0 ] = match.length() + restAlNum.length();
		typeBuf[ 0 ] = "n";
		}
	else
		{
		lengthBuf[ 0 ] = match.length();
		typeBuf[ 0 ] = "1";
		}
	}
}
// MySQLのコメント内の場合は */ をスルーする
//  <=> をoとして消化
// コメント内の場合、*/を単にスキップする
// && || を&として消化
// is_operator2で2文字のオペレータかどうかしらべ、その場合はoとして消化する
//--------------------------------------------------------------------------------
protected static void parse_operator2( String input, final boolean[] inCommentBuf, String[] typeBuf, int[] lengthBuf )
{
if( input.startsWith( "<=>" ) )
	{
	lengthBuf[ 0 ] = 3;
	typeBuf[ 0 ] = "o";
	}
else if( input.length() == 1 )
	{
	lengthBuf[ 0 ] = 1;
	typeBuf[ 0 ] = "o";	
	}
else
	{
	String twoByteStr = input.substring( 0, 2 );
	if( twoByteStr.equals( "&&" ) || twoByteStr.equals( "||" ) )
		{
		lengthBuf[ 0 ] = 2;
		typeBuf[ 0 ] = "&";
		}
	else if( inCommentBuf[ 0 ] && twoByteStr.equals( "*/" ) )
		{
		lengthBuf[ 0 ] = 2;
		inCommentBuf[ 0 ] = false;
		typeBuf[ 0 ] = "";
		}
	else
		{
		if( operators2.contains( twoByteStr ) )
			{
			lengthBuf[ 0 ] = 2;
			typeBuf[ 0 ] = "o";
			}
		else
			{
			lengthBuf[ 0 ] = 1;
			typeBuf[ 0 ] = "o";
			}
		}
	}
}
//--------------------------------------------------------------------------------
protected static void parse_backslash( String input, String[] typeBuf, int[] lengthBuf )
{
if( input.startsWith( "\\N" ) )
	{
	lengthBuf[ 0 ] = 2;
	typeBuf[ 0 ] = "1";
	}
else
	{
	parse_other( input, typeBuf, lengthBuf );
	}
}
//--------------------------------------------------------------------------------
protected static void parse_other( String input, String[] typeBuf, int[] lengthBuf )
{
lengthBuf[ 0 ] = 1;
typeBuf[ 0 ] = "?";
}
//--------------------------------------------------------------------------------
protected static void parse_dash( String input, String[] typeBuf, int[] lengthBuf )
{
if( input.startsWith( "--" ) )
	{
	parse_eol_comment( input, typeBuf, lengthBuf );
	}
else
	{
	lengthBuf[ 0 ] = 1;
	typeBuf[ 0 ] = "o";
	}
}
//--------------------------------------------------------------------------------
protected static void parse_eol_comment( String input, String[] typeBuf, int[] lengthBuf )
{
int index = input.indexOf( '\n' );
if( index == -1 )
	{
	lengthBuf[ 0 ] = input.length();
	}
else
	{
	lengthBuf[ 0 ] = index + 1;
	}
typeBuf[ 0 ] = "c";
}
//--------------------------------------------------------------------------------
protected static void parse_var( String input, String[] typeBuf, int[] lengthBuf )
{
//ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_.$
String match = getMatch( "^(@{1,2}[A-Z0-9_\\.\\$]*)", input );
lengthBuf[ 0 ] = match.length();
typeBuf[ 0 ] = "v";
}
//--------------------------------------------------------------------------------
protected static void parse_slash( String input, int[] lengthBuf, String[] typeBuf, boolean[] inCommentBuf )
{
if( !input.startsWith( "/*" ) )
	{
	lengthBuf[ 0 ] = 1;
	typeBuf[ 0 ] = "o";
	}

	//starts with /*
else if( input.length() == 2 )
	{
	//input is '/*'
	lengthBuf[ 0 ] = 2;
	typeBuf[ 0 ] = "c";
	}
else
	{
	//length > 2
	int mysqlResult = is_mysql_comment( input );
	if( mysqlResult == 0 )
		{
		int index = input.indexOf( "*/", 2 );
		if( index == -1 )
			{
			lengthBuf[ 0 ] = input.length();
			typeBuf[ 0 ] = "c";
			}
		else
			{
			lengthBuf[ 0 ] = index + 2;
			typeBuf[ 0 ] = "c";
			}
		}
	else
		{
		//MySQL Comment
		inCommentBuf[ 0 ] = true;
		lengthBuf[ 0 ] = mysqlResult;
		typeBuf[ 0 ] = "";
		}
	}
}
//--------------------------------------------------------------------------------
protected static int is_mysql_comment( String input )
{
//戻り値は 0, 3, 4, 8のどれか
// 8 -> /*!12345 が見つかった場合
// 0 -> 見つからない場合
// 3 -> /*!  だけ見つかった場合
// 4 -> /*!1 まで見つかった場合

	// input starts with /*
if( input.length() <= 2 ) 
	{
	return 0;
	}
else if( input.charAt( 2 ) != '!' )
	{
	return 0;
	}

	// this is a mysql comment
	// got "/*!"

else if( input.length() == 3 )
	{
	return 3;
	}
	
else if( input.length() == 4 )
	{
	if( Character.isDigit( input.charAt( 3 ) ) )
		{
		return 4;
		}
	else
		{
		return 3;
		}
	}
	
		//length > 4
else if( !Character.isDigit( input.charAt( 4 ) ) ) // handle odd case of /*!0SELECT
	{
	return 4;
	}
else if( input.length() < 8 )
	{
	return 4;
	}
else if( input.matches( "^/\\*\\![0-9]{5}.*" ) )
	{
	return 8;
	}
else
	{
	return 3; //TODO: why 3?
	}
}
//--------------------------------------------------------------------------------
// 'や"の内部にいるときは終わりまでパースしてtrueで抜ける
// ch < 0 || ch > 127 の文字は全部無視してループする(いちいち関数を抜けない）
// 最後にたどり着いてしまったらfalseで抜ける
// 何かしらtypeを取得できたらtrueで抜ける
protected static void parse_token( String[] inputBuf, boolean[] inCommentBuf, String[] typeBuf )
{
	//initialize
String input = inputBuf[ 0 ];
int[] lengthBuf = new int[ 1 ];
lengthBuf[ 0 ] = 0;
typeBuf[ 0 ] = "";

char firstChar = input.charAt( 0 );
int i = ( int )( ( byte )firstChar );
if( i < 0 || 127 < i )
	{
	input = input.substring( 1 );
	}
else
	{
	String functionName = ( String )pt2Function.get( i );
	p( functionName );
	
	if( functionName.equals( "parse_word" ) )
		{
		parse_word( input, typeBuf, lengthBuf );
		input = input.substring( lengthBuf[ 0 ] );
		}
	else if( functionName.equals( "parse_white" ) )
		{
		input = input.substring( 1 );
		}
	else if( functionName.equals( "parse_string" ) )
		{
		typeBuf[ 0 ] = "s";
		parse_string( input, firstChar, lengthBuf );
		input = input.substring( lengthBuf[ 0 ] );
		}
	else if( functionName.equals( "parse_number" ) )
		{
		parse_number( input, typeBuf, lengthBuf );
		input = input.substring( lengthBuf[ 0 ] );
		}
	else if( functionName.equals( "parse_operator1" ) )
		{
		typeBuf[ 0 ] = "o";
		input = input.substring( 1 );
		}
	else if( functionName.equals( "parse_operator2" ) )
		{
		parse_operator2( input, inCommentBuf, typeBuf, lengthBuf );
		input = input.substring( lengthBuf[ 0 ] );
		}
	else if( functionName.equals( "parse_char" ) )
		{
		typeBuf[ 0 ] = firstChar + "";
		input = input.substring( 1 );
		}
	else if( functionName.equals( "parse_backslash" ) )
		{
		parse_backslash( input, typeBuf, lengthBuf );
		input = input.substring( lengthBuf[ 0 ] );
		}
	else if( functionName.equals( "parse_other" ) )
		{
		parse_other( input, typeBuf, lengthBuf );
		input = input.substring( lengthBuf[ 0 ] );
		}
	else if( functionName.equals( "parse_eol_comment" ) )
		{
		parse_eol_comment( input, typeBuf, lengthBuf );
		input = input.substring( lengthBuf[ 0 ] );
		}
	else if( functionName.equals( "parse_dash" ) )
		{
		parse_dash( input, typeBuf, lengthBuf );
		input = input.substring( lengthBuf[ 0 ] );
		}
	else if( functionName.equals( "parse_var" ) )
		{
		parse_var( input, typeBuf, lengthBuf );
		input = input.substring( lengthBuf[ 0 ] );		
		}
	else if( functionName.equals( "parse_slash" ) )
		{
		parse_slash( input, lengthBuf, typeBuf, inCommentBuf );
		input = input.substring( lengthBuf[ 0 ] );		
		}	
	}

p( "---" + input + "---" );
p( ":::" + typeBuf[ 0 ] + ":::" );
inputBuf[ 0 ] = input;
}
//--------------------------------------------------------------------------------
public static String sqli_tokenize( String input )
{
input = input.toUpperCase();
String[] typeArray = new String[ MAX_TOKENS + 1 ]; //types. for example, "1", "o", "n", "f"
String[] valueArray = new String[ MAX_TOKENS + 1 ]; //processed values. for example, "or" "select"
Arrays.fill( typeArray, "" );

String[] inputBuf = new String[]{ input };
boolean[] inCommentBuf = new boolean[]{ false };
String[] typeBuf = new String[]{ "" };
String lastInput = input;
String lastProcessed = "";
String currentType = "";

int typeIndex = 0;
while( true )
	{
	final int inputLength = inputBuf[ 0 ].length();
	if( inputLength == 0 )
		{
		break;
		}
	parse_token( inputBuf, inCommentBuf, typeBuf );
	currentType = typeBuf[ 0 ];
		
	String processed = lastInput.substring( 0, lastInput.length() - inputBuf[ 0 ].length() );
	p( "lastProcessed:" + lastProcessed );
	p( "processed:[" + processed + "]" );
	p( "currentType:" + currentType );
	p( "typeIndex:" + typeIndex );
	

		//lastType
	String lastType = "";
	if( typeIndex > 0 )
		{
		lastType = typeArray[ typeIndex - 1 ];
		p( "lastType:" + lastType );
		}

	if( currentType.length() == 1 )
		{
			//remove last 'c' comment
		if( lastType.equals( "c" ) )
			{
			typeIndex --;
			}
		
			//strings
		else if( currentType.equals( "s" ) && lastType.equals( "s" ) )
			{
			typeIndex --;
			}
		
			//fold
		else if( currentType.equals( "1" ) )
			{
			if( lastType.equals( "o" ) && arithOpSet.contains( lastProcessed ) )
				{
				if( typeIndex >= 2 )
					{
					if( typeArray[ typeIndex - 2 ].equals( "1" ) )
						{
						// 1, o, 1
						typeIndex -= 2;
						typeArray[ typeIndex + 1 ] = "";
						}
					else
						{
						//typeIndex --;
						}
					}
				else
					{
					
					}
				}
			}
	
			//fix up for ambigous "IN"
		else if( lastProcessed.equals( "IN" ) && lastType.equals( "n" ) )
			{
			if( !currentType.equals( "s" )
			 && !currentType.equals( "o" )
			 && !currentType.equals( "n" )
			 && !currentType.equals( "k" )
			  )
				{
				p( ">>> currentType:" + currentType );
				typeArray[ typeIndex - 1 ] = "f"; //overwrite n to f
				}
			}

		if( typeIndex == 0 
		 && ( currentType.equals( "(" ) || unaryOpSet.contains( processed ) )
		  )
			{
			//ignore first ( or unary op
			}
		else
			{
			typeArray [ typeIndex ] = currentType;
			valueArray[ typeIndex ] = processed;
			++ typeIndex;
			}
		}
	
		//multikeywords
	boolean multiKeywordsFound = false;
	if( currentType.length() == 1 )
		{
		//if( multikeywordsFirstWordTypeSet.contains( lastType ) )
			{
			if( multiKeywordsStart.contains( lastProcessed ) )
				{
				String _key = lastProcessed + " " + processed;
				String multiKeyType = ( String )multiKeywords.get( _key );
				if( multiKeyType != null )
					{
					p( "multi keywords found" );
					multiKeywordsFound = true;
					lastProcessed = _key;
					
						//reset currentType
					typeIndex --;
					typeArray[ typeIndex ] = "";
					
						//overwrite lastType
					if( typeIndex > 0 )
						{
						typeIndex --;
						}
					typeArray[ typeIndex ] = multiKeyType;
					++typeIndex;
					}
				else
					{
					lastProcessed = processed;
					}
				}
			else
				{
				lastProcessed = processed;
				}
			}
		}
	
	if( multiKeywordsFound == false )
		{
		if( currentType.equals( "o" )
		 && ( lastType.equals( "o" ) || lastType.equals( "&" ) || lastType.equals( "U" ) )
		 && unaryOpSet.contains( processed )
		  )
			{
				//an operator is followed by a unary operator, skip it.
			typeIndex --;
			typeArray[ typeIndex ] = "";
			}
		}
	
	if( typeIndex == MAX_TOKENS )
		{
		if( currentType.equals( "c" ) )
			{
				//remove last 'c' and search next
			-- typeIndex;
			}
		else if( multiKeywordsStart.contains( processed ) || multiKeywordsStart.contains( lastProcessed ) )
			{
				// need to check next
			}
		else
			{
			p( "type is full" );
			break;
			}
		}
	else if( typeIndex == MAX_TOKENS + 1 )
		{
		break;
		}
	
	if( inputBuf[ 0 ].length() == 0 )
		{
		p( "input is consumed" );
		break;
		}
	
	lastInput = inputBuf[ 0 ];
	if( multikeywordsFirstWordTypeSet.contains( currentType ) && multiKeywordsFound == false )
		{
		lastProcessed = processed;
		}
	}
StringBuffer buf = new StringBuffer();
for( int i = 0; i < MAX_TOKENS; ++i )
	{
	buf.append( typeArray[ i ] );
	}

p( "---" );
p( Arrays.asList( valueArray ) );
p( buf.toString() );
p( "---" );

return buf.toString();
}

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
