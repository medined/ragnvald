package com.codebits.ragnvald;

public class GrepPlayground {
    
    public static void main(final String[] args) throws Exception {
        String s = "001 002 002H 002R 003 004EX 005F 006S 007";
        s = s.replaceAll("(\\d{3}) ", "<b>$1</b> ");
        s = s.replaceAll("(\\d{3})$", "<b>$1</b>");
        System.out.println("[" + s + "]");
        System.out.println("Hello World");
    }

}
