package parser;

import scanner.Scanner;
import scanner.ScanErrorException;
import environment.Environment;
import ast.Program;
import ast.Statement;
import java.io.*;

/**
 * Tester for the Parser Class
 * 
 *
 * @author Amiya Chokhawala
 * @version April 24, 2022
 */
public class ParserTester
{
    /**
     * Main method for ParserTester class. 
     * 
     * @param   args arguments from the command line
     */
    public static void main(String[] args) throws IOException, IllegalArgumentException, 
    ScanErrorException
    {
        File firstFile = new File
        ("/Users/amarchokhawala/Desktop/Compilers/CompilersLab/parser/parserTest8.txt");
        InputStream i0 = new FileInputStream(firstFile);
        Scanner s0 = new Scanner(i0);
        Parser p0 = new Parser(s0);
        Environment env = new Environment();
        while (s0.hasNext())
        {
            Program p = p0.parseProgram(env);
            p.exec(env);            
        }

        // File secondFile = new File
        // ("/Users/amarchokhawala/Desktop/Compilers/CompilersLab/parser/parserTest1.txt");
        // InputStream i1 = new FileInputStream(secondFile);
        // Scanner s1 = new Scanner(i1);
        // Parser p1 = new Parser(s1);
        // while (s1.hasNext())
        // {
        // p1.parseStatement();
        // }

        // File thirdFile = new File
        // ("/Users/amarchokhawala/Desktop/Compilers/CompilersLab/parser/parserTest2.txt");
        // InputStream i2 = new FileInputStream(thirdFile);
        // Scanner s2 = new Scanner(i2);
        // Parser p2 = new Parser(s2);
        // while (s2.hasNext())
        // {
        // p2.parseStatement();
        // }

        // File fourthFile = new File
        // ("/Users/amarchokhawala/Desktop/Compilers/CompilersLab/parser/parserTest3.txt");
        // InputStream i3 = new FileInputStream(fourthFile);
        // Scanner s3 = new Scanner(i3);
        // Parser p3 = new Parser(s3);
        // p3.parseStatement();
        // while (s3.hasNext())
        // {
        // p3.parseStatement();
        // }

        /*File fifthFile = new File
        ("/Users/amarchokhawala/Desktop/Compilers/CompilersLab/parser/parserTest4.txt");
        InputStream i4 = new FileInputStream(fifthFile);
        Scanner s4 = new Scanner(i4);
        Parser p4 = new Parser(s4);
        while (s4.hasNext())
        {
        p4.parseStatement();
        }*/
        
    }
}
