package ast;
import environment.Environment;


/**
 * Assigns a value to a variable and puts it in a map 
 *
 * @author Amiya Chokahwala 
 * @version March 19, 2022
 */
public class Assignment extends Statement 
{
    // instance variables - replace the example below with your own
    private String var; 
    private Expression exp; 

    /**
     * Constructor for objects of class Assignment
     */
    public Assignment(String var, Expression exp)
    {
        this.var = var; 
        this.exp = exp;      
    }
    
    /**
     * Evaluates Asssignments 
     * 
     * @param env the Environment class with the map 
     */
    public void exec(Environment env)
    {
        // after executing the assignment, put var and exp into the map 
        env.setVariable(var, this.exp.eval(env));         
    }
}
