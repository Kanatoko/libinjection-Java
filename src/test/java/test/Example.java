package test;

public class Example {
    //--------------------------------------------------------------------------------
    public static void main(String[] args)
            throws Exception {
        String s = "foo'/**/ or 1=1--";
        boolean isSQLi = com.client9.libinjection.SQLParse.isSQLi(s);

        if (isSQLi) {
            System.out.println("SQLi found : " + s);
        }
    }
//--------------------------------------------------------------------------------
}