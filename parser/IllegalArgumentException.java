package parser;


/**
 * ScanErrorException is a sub class of Exception and is thrown to indicate a 
 * scanning error.  Usually, the scanning error is the result of an illegal 
 * character in the input stream.  The error is also thrown when the expected
 * value of the character stream does not match the actual value.
 * @author Mr. Page
 * @version 062014
 *
 */
public class IllegalArgumentException extends Exception
{
    /**
     * default constructor for ScanErrorObjects
     */
    public IllegalArgumentException()
    {
        super();
    }
    /**
     * Constructor for ScanErrorObjects that includes a reason for the error
     * @param reason the reasoning behind throwing an error
     */
    public IllegalArgumentException(String reason)
    {
        super(reason);
    }
}