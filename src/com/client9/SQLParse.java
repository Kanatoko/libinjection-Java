package com.client9;

import java.util.*;

public class SQLParse
{
private static Map sqlKeywords = new HashMap( 500 );
private static Map multiKeywords = new HashMap( 50 );
private static List pt2Function = new ArrayList( 130 );
private static Set multiKeywordsStart;
private static Set operators2;

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

}
//--------------------------------------------------------------------------------
public static boolean isSQLi( final String input )
{
try
	{
	return isSQLiImpl( input );
	}
catch( Exception e )
	{
	e.printStackTrace();
	return false;
	}
}
//--------------------------------------------------------------------------------
public static void main( String[] args )
throws Exception
{
Set s = new HashSet();
s.addAll( pt2Function );

p( s );
}
//--------------------------------------------------------------------------------
private static boolean isSQLiImpl( final String input )
throws Exception
{
String pattern = sqli_tokenize( input );
return false;
}
//--------------------------------------------------------------------------------
protected static void p( Object o )
{
System.out.println( o );
}
//--------------------------------------------------------------------------------
protected static String parse_word( String input, int[] lengthBuf )
{
String word = Util.getMatch( "^[A-Z0-9_\\.$]+", input );
lengthBuf[ 0 ] = word.length();
String value = ( String )sqlKeywords.get( word );
if( value == null )
	{
	return "n";
	}
else
	{
	return value;
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
				//ignroe
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
protected static String parse_number( String input, int[] lengthBuf )
{
if( input.startsWith( "0X" ) )
	{
	String match = Util.getMatch( "^0X[0-9A-F]*", input );
	if( match.length() == 2 )
		{
		lengthBuf[ 0 ] = 2;
		return "n";
		}
	else
		{
		lengthBuf[ 0 ] = match.length();
		return "1";
		}
	}
else if( input.equals( "." ) )
	{
	lengthBuf[ 0 ] = 1;
	return "n";
	}
else if( input.matches( "^\\.[^0-9]+.*" ) ) // .A 
	{
	lengthBuf[ 0 ] = 1;
	return "n";
	}
else
	{
	String match = Util.getMatch( "^[0-9\\.]+", input );
	String rest = input.substring( match.length() );
	if( rest.startsWith( "E+" )
	 || rest.startsWith( "E-" )
	  )
		{
		rest = Util.getMatch( "^(E.[0-9]+)", rest );
		lengthBuf[ 0 ] = match.length() + rest.length(); //TODO: 1.2E-1A should be 'n'?
		return "1";
		}
	else if( rest.matches( "^[A-Z]+.*" ) ) //oh no!
		{
		String restAlNum = Util.getMatch( "^([A-Z0-9]+)", rest );
		lengthBuf[ 0 ] = match.length() + restAlNum.length();
		return "n";
		}
	else
		{
		lengthBuf[ 0 ] = match.length();
		return "1";
		}
	}
}
//  <=> をoとして消化
// コメント内の場合、*/を単にスキップする
// && || を&として消化
// is_operator2で2文字のオペレータかどうかしらべ、その場合はoとして消化する
// MySQLのコメント内の場合は */ をスルーする
//--------------------------------------------------------------------------------
protected static String parse_operator2( String input, int[] lengthBuf, boolean[] inCommentBuf )
{
if( input.startsWith( "<=>" ) )
	{
	lengthBuf[ 0 ] = 3;
	return "o";
	}
else if( input.length() == 1 )
	{
	lengthBuf[ 0 ] = 1;
	return "o";	
	}
else
	{
	String twoByteStr = input.substring( 0, 2 );
	if( twoByteStr.equals( "&&" ) || twoByteStr.equals( "||" ) )
		{
		lengthBuf[ 0 ] = 2;
		return "&";
		}
	else if( inCommentBuf[ 0 ] == true && twoByteStr.equals( "*/" ) )
		{
		lengthBuf[ 0 ] = 2;
		return "";
		}
	else
		{
		if( operators2.contains( twoByteStr ) )
			{
			lengthBuf[ 0 ] = 2;
			return "o";
			}
		else
			{
			lengthBuf[ 0 ] = 1;
			return "o";
			}
		}
	}
}
//--------------------------------------------------------------------------------
protected static String parse_backslash( String input, int[] lengthBuf )
{
if( input.startsWith( "\\N" ) )
	{
	lengthBuf[ 0 ] = 2;
	return "1";
	}
else
	{
	return parse_other( input, lengthBuf );
	}
}
//--------------------------------------------------------------------------------
protected static String parse_other( String input, int[] lengthBuf )
{
lengthBuf[ 0 ] = 1;
return "?";
}
//--------------------------------------------------------------------------------
protected static String parse_dash( String input, int[] lengthBuf )
{
if( input.startsWith( "--" ) )
	{
	return parse_eol_comment( input, lengthBuf );
	}
else
	{
	lengthBuf[ 0 ] = 1;
	return "o";
	}
}
//--------------------------------------------------------------------------------
protected static String parse_eol_comment( String input, int[] lengthBuf )
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
return "c";
}
//--------------------------------------------------------------------------------
protected static String parse_var( String input, int[] lengthBuf )
{
//ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_.$
String match = Util.getMatch( "^(@{1,2}[A-Z0-9_\\.\\$]*)", input );
lengthBuf[ 0 ] = match.length();
return "v";
}
//--------------------------------------------------------------------------------
protected static String parse_slash( String input, int[] lengthBuf, boolean[] inCommentBuf )
{
if( !input.startsWith( "/*" ) )
	{
	lengthBuf[ 0 ] = 1;
	return "o";
	}

	//starts with /*
else if( input.length() == 2 )
	{
	//input is '/*'
	lengthBuf[ 0 ] = 2;
	return "c";
	}

//length > 2

int mysqlResult = is_mysql_comment( input );
if( mysqlResult == 0 )
	{
	int index = input.indexOf( "*/", 2 );
	if( index == -1 )
		{
		lengthBuf[ 0 ] = input.length();
		return "c";
		}
	else
		{
		lengthBuf[ 0 ] = index + 2;
		return "c";
		}
	}
else
	{
	//MySQL Comment
	inCommentBuf[ 0 ] = true;
	lengthBuf[ 0 ] = mysqlResult;
	return "";
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
// ch < 0 || ch > 127 の文字は全部無視してループする
// 最後にたどり着いてしまったらfalseで抜ける
// 何かしらtypeを取得できたらtrueで抜ける
protected static boolean parse_token( String input, char delim, int[] lengthBuf, String[] typeBuf )
{
	//initialize
lengthBuf[ 0 ] = 0;
typeBuf[ 0 ] = "";

if( input.length() == 0 )
	{
	return false;
	}

if( delim == '\'' || delim == '"' )
	{
		//TODO implement
	return true;
	}

while( true )
	{
	if( input.length() == 0 )
		{
		return false;
		}
	char firstChar = input.charAt( 0 );
	int i = ( int )( ( byte )firstChar );
	if( i < 0 || 127 < i )
		{
		input = input.substring( 1 );
		continue;
		}
	
	String functionName = ( String )pt2Function.get( i );
	
	if( functionName.equals( "parse_word" ) )
		{
		typeBuf[ 0 ] = parse_word( input, lengthBuf );
		//input = input.substring( lengthBuf[ 0 ] );
		}
	else if( functionName.equals( "parse_white" ) )
		{
		input = input.substring( 1 );
		continue;
		}
	else if( functionName.equals( "parse_string" ) )
		{
		typeBuf[ 0 ] = "s";
		parse_string( input, firstChar, lengthBuf );
		}
	/*
	else if( functionName.equals( "parse_number" ) )
		{
		patternBuf.append( parse_number( input, lengthBuf ) );
		input = input.substring( lengthBuf[ 0 ] );
		}
	else if( functionName.equals( "parse_operator1" ) )
		{
		patternBuf.append( "o" );
		input = input.substring( 1 );
		}
	else if( functionName.equals( "parse_operator2" ) )
		{
		patternBuf.append( parse_operator2( input, lengthBuf, inCommentBuf ) );
		input = input.substring( lengthBuf[ 0 ] );
		}
	else if( functionName.equals( "parse_char" ) )
		{
		patternBuf.append( firstChar );
		input = input.substring( 1 );
		}
	else if( functionName.equals( "parse_backslash" ) )
		{
		patternBuf.append( parse_backslash( input, lengthBuf ) );
		input = input.substring( lengthBuf[ 0 ] );
		}
	else if( functionName.equals( "parse_other" ) )
		{
		patternBuf.append( parse_other( input, lengthBuf ) );
		input = input.substring( lengthBuf[ 0 ] );
		}
	else if( functionName.equals( "parse_eol_comment" ) )
		{
		patternBuf.append( parse_eol_comment( input, lengthBuf ) );
		input = input.substring( lengthBuf[ 0 ] );
		}
	else if( functionName.equals( "parse_dash" ) )
		{
		patternBuf.append( parse_dash( input, lengthBuf ) );
		input = input.substring( lengthBuf[ 0 ] );
		}
	else if( functionName.equals( "parse_var" ) )
		{
		patternBuf.append( parse_var( input, lengthBuf ) );
		input = input.substring( lengthBuf[ 0 ] );		
		}
	else if( functionName.equals( "parse_slash" ) )
		{
		patternBuf.append( parse_slash( input, lengthBuf, inCommentBuf ) );
		input = input.substring( lengthBuf[ 0 ] );		
		}
	*/
	return true;
	}
}
//--------------------------------------------------------------------------------
protected static String sqli_tokenize( String input )
{
input = input.toUpperCase();
StringBuffer patternBuf = new StringBuffer();
boolean[] inCommentBuf = new boolean[ 1 ];
inCommentBuf[ 0 ] = false;

String lastType;
String currentType;


return null;
}
//--------------------------------------------------------------------------------
}
