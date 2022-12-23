
package scanner;
import java.io.*;

/**
 * Scanner is a simple scanner for Compilers and Interpreters
 * @author Amiya Chokhawala 
 * @version February 1, 2022 
 *  
 * Usage:
 * <The Scanner reads the input string and returns lexemes as a set of tokens.>
 *
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;
    /**
     * Scanner constructor for construction of a scanner that 
     * uses an InputStream object for input.  
     * Usage: 
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * Scanner lex = new Scanner(inStream);
     * @param inStream the input stream to use
     */
    public Scanner(InputStream inStream)
    {
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false;
        getNextChar();
    }

    /**
     * Scanner constructor for constructing a scanner that 
     * scans a given input string.  It sets the end-of-file flag an then reads
     * the first character of the input string into the instance field currentChar.
     * Usage: Scanner lex = new Scanner(input_string);
     * @param inString the string to scan
     */
    public Scanner(String inString)
    {
        in = new BufferedReader(new StringReader(inString));
        eof = false;
        getNextChar();
    }

    /**
     * Method: getNextChar
     * Sets currentChar to the value read from the input string. If the read method returns -1 
     * when the input string is at the end of the file, the variable eof should be set to true.
     * @postcondition currentChar is set to the value read from the input string
     * 
     */
    private void getNextChar() 
    {
        try 
        {
            int c = in.read();
            currentChar = (char) c;
            if (c == -1) 
            {
                eof = true;
            }
            if(currentChar == '.')
            {  
                eof = true;  
            }
        } 
        catch(IOException e)
        {
            try 
            { 
                in.close();
            } 
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Method: hasNext
     * @return false if the input string is at the end of file 
     */
    public boolean hasNext()
    {
        return !eof;
    }

    /**
     * Method: eat
     * Compares the value of the parameter with the value of currentChar. If they are the same,
     * the methods advances in the input string by one character. If they are not the same, 
     * the method throws an exception.
     * @postcondition input string is advanced by one character
     * @param expected the expected value of currentChar 
     * @throws ScanErrorException whenn expected and currentChar are not the same
     */

    private void eat(char expected) throws ScanErrorException
    {
        if (currentChar == expected) 
        {
            getNextChar();
        } 
        else 
        {
            throw new ScanErrorException("Expected expected but found currentChar");
        }
    }

    /**
     * Method: isDigit 
     * Determines where the input character is a digit (a number 0 through 9 inclusive). 
     * @param c character being checked 
     * @return true if the character is a digit; else,
     *         false 
     */
    public static boolean isDigit(char c)
    {
        return c >= '0' && c <= '9';
    }

    /**
     * Method: isLetter
     * Determines where the input character is a letter (a letter a/A through z/Z inclusive). 
     * @param c character being checked 
     * @return true if the character is a letter; else,
     *         false
     */
    public static boolean isLetter(char c)
    {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    /**
     * Method: isWhiteSpace 
     * Determines where the input character is a empty space(a space, newline, tab, carriage 
     * return). 
     * @param c character being checked 
     * @return true if the character is a space; else,
     *         false
     */
    public static boolean isWhiteSpace(char c)
    { 
        switch(c) 
        {
            case ' ': 
            return true;
            case '\t':
            return  true;
            case '\n':
            return true;
            case '\r':
            return true;
            default:
            return false;
        }
    }

    /**
     * Method: isOperator  
     * Determines where the input character is an operator
     * (a semicolon, equals sign, plus sign, etc). 
     * @param c character being checked 
     * @return true if the character is an operator; else,
     *         false
     */
    public static boolean isOperator(char c)
    { 
        switch(c) 
        {
            case ';': 
            return true;
            case '=':
            return  true;
            case '+':
            return true;
            case '-':
            return true;
            case '*':
            return true;
            case '/':
            return true;
            case '%':
            return true;
            case '(':
            return true;
            case ')':
            return true;
            case '.':
            return true; 
            case ',':
            return true; 
            default:
            return false;
        }
    }

    /**
     * Scans the input string and returns a String for a 
     * lexeme of digits found in the input string. 
     * Throws an exception if there is no lexeme found.
     * @return String representing a lexeme of digits
     * @throws ScanErrorException when a lexeme is not found
     */
    private String scanNumber() throws ScanErrorException
    {
        String s = ""; 
        while (hasNext() && isDigit(currentChar))
        {
            s += currentChar;
            eat(currentChar); 
        }
        if (s.length() == 0)
        {
            throw new ScanErrorException("no lexeme found");
        }
        return s; 
    }

    /**
     * Scans the input string and returns a String for 
     * a lexeme of letters found in the input string. 
     * Throws an exception if there is no lexeme found.
     * @return String representing a lexeme of letters
     * @throws ScanErrorException when a lexeme is not found
     */
    private String scanIndentifier() throws ScanErrorException
    {
        String s = ""; 
        while (hasNext() && isLetter(currentChar))
        {
            s = s + currentChar;
            eat(currentChar); 
        }

        if (s.length() == 0)
        {
            throw new ScanErrorException("no lexeme found");
        }
        return s; 
    }

    /**
     * Scans the input string and returns a String 
     * for an operand found in the input string. 
     * Throws an exception if there is no lexeme found.
     * @return String representing a lexeme for an operand
     * @throws ScanErrorException when a lexeme is not found
     */
    private String scanOperand() throws ScanErrorException
    {
        String s = "";

        if (hasNext()  && currentChar == ':') 
        {
            s += currentChar;
            eat(currentChar);
            if (hasNext() && currentChar == '=')
            {
                s += currentChar;
                eat(currentChar);
                return s;
            }
        }

        if (hasNext()  && currentChar == '<') 
        {
            s += currentChar;
            eat(currentChar);
            if (hasNext()  && currentChar == '=')
            {
                s += currentChar;
                eat(currentChar);
                return s;
            }
       }

       if (hasNext()  && currentChar == '>') 
        {
            s += currentChar;
            eat(currentChar);
            if (hasNext() && currentChar == '=')
            {
                s += currentChar;
                eat(currentChar);
                return s;
            }
        }

        if (hasNext()  && currentChar == '<') 
       {
            s += currentChar;
            eat(currentChar);
            if (currentChar == '>')
            {
                s += currentChar; 
                eat(currentChar);
                return s;
            }
       }
        
        if (hasNext()  && currentChar == '/') 
        {
            s += currentChar;
            eat(currentChar);
            if (hasNext() && currentChar == '/')
            {
                s += currentChar;
                eat(currentChar);
                return s;
            }
        }

       if (hasNext() && isOperator(currentChar))
        {
            s += currentChar;
            eat(currentChar); 
        }                

        if (s.length() == 0)
        {
            throw new ScanErrorException("no lexeme found");
        }
        return s;
    }

    /**
     * Method: nextToken
     * Skips the white space in the inputString and scans the tokens by calling the methods that are needed. 
     * Also skips one-line comments.  
     * @return a String representing a lexeme or "END" when the input stream is at end of file
     */
    public String nextToken() throws ScanErrorException
    {  

        while(hasNext() && isWhiteSpace(currentChar)) 
        {
            eat(currentChar);
        }
        if (isDigit(currentChar))
        {                
            return scanNumber();
        }
        else if (isLetter(currentChar))
        {
            String s1 = scanIndentifier();
            return s1;
        } else if (currentChar == '.') {
           return "END";   
        }  
        else if ( isOperator(currentChar) || currentChar == ':' || currentChar == '<' || 
        currentChar == '>')
        {
            String s2 = scanOperand();
            if (s2.equals("//")) {
               while(currentChar != '\n') {
                 eat(currentChar);
               }    
            }
            return s2;
        }        
        else if (!hasNext()) 
        {
            return "END"; 
        }
        else
        {
            throw new ScanErrorException("Character is not recognized: " + currentChar);
        }

    }    
}
