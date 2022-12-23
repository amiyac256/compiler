package ast;
import environment.Environment;

/**
 * Write a description of class Statement here.
 *
 * @author Amiya Chokhawala 
 * @version March 9, 2022 
 */
public abstract class Statement
{
    /**
     * Constructor for objects of class Statement
     */
    public Statement()
    {
       
    }
    
    public abstract void exec(Environment env); 
    
}
