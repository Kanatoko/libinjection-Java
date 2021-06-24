package test;

import com.client9.libinjection.SQLParse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TestFolding
        extends SQLParse {
    //--------------------------------------------------------------------------------
    public static void main(String[] args)
            throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String stdin;
        while (true) {
            stdin = reader.readLine();
            if (stdin == null) {
                return;
            }
            final byte[] buf = new byte[1024 * 20]; //20KB
            String fileContent = new String(buf, 0, (new FileInputStream(stdin)).read(buf));
            List lines = Arrays.asList(fileContent.split("[\\r\\n]+"));
            //p( lines );
            String cToken = "";
            String javaFolded = "dummy";
            LinkedList valueList = new LinkedList();
            for (int i = 0; i < lines.size(); ++i) {
                String line = (String) lines.get(i);
                if (line.equals("--INPUT--")) {
                    String input = (String) lines.get(i + 1);
                    p("input:" + input);
                    String[] allTokenBuf = new String[1];
                    tokenize(input, valueList, allTokenBuf);
                    String javaToken = allTokenBuf[0].replaceAll("w+", "");
                    p("Java:\t" + javaToken);
                    p(valueList);
                    javaFolded = fold(valueList, javaToken);
                    p("javafList: " + valueList);
                    p("Javaf:\t" + javaFolded);
                } else if (line.equals("--EXPECTED--")) {
                    StringBuffer strBuf = new StringBuffer();
                    ++i;
                    for (; i < lines.size(); ++i) {
                        line = (String) lines.get(i);
                        strBuf.append(line.charAt(0));
                    }
                    cToken = strBuf.toString();
                    p("C:\t" + cToken);
                    if (cToken.equals(javaFolded)) {
                        p("==== OK ==== : " + stdin);
                    } else {
                        p("Error: " + stdin);
                        p(fileContent);
                        System.exit(1);
                    }
                } else {
                    //p( line );
                }
            }
        }
    }

    //--------------------------------------------------------------------------------
    public static void ex()
            throws Exception {
        throw new Exception();
    }

    //--------------------------------------------------------------------------------
    public static void p(Object o) {
        System.out.println(o);
    }
//--------------------------------------------------------------------------------
}