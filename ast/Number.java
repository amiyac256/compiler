package ast;
import environment.Environment;

/**
 * Evaluates Number objects 
 *
 * @author Amiya Chokhawala 
 * @version March 11, 2022 
 */
public class Number extends Expression 
{
    private int val;

    /**
     * Constructor for objects of class Number
     * 
     */
    public Number(int val)
    {
        this.val = val; 
    }

    /**
     * Returns the value of the number 
     * 
     * @return val value of the number
     */
    public int intValue() 
    { 
        return this.val; 
    }

    /**
     * Sets the value of val 
     * 
     * @param val what is being set 
     */
    public void setNumber(int val) 
    {
        this.val = val;
    }

    /**
     * Evaluates the Number
     * 
     * @return val the value of the number
     * @param env the Environment 
     */
    public Integer eval(Environment env) 
    {
        return val;
    }
}
