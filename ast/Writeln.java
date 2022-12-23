package ast;
import environment.Environment;

/**
 * Executes Writeln statements 
 *
 * @author Amiya Chokhawala 
 * @version March 9, 2022
 */
public class Writeln extends Statement 
{
    // instance variables - replace the example below with your own
    private Expression exp;

    /**
     * Constructor for objects of class Writeln
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }
    
    /**
     * Prints the value of evaluated Writeln statement
     * 
     * @param env the Environment 
     */
    public void exec(Environment env)
    {
        System.out.println(this.exp.eval(env));
    }
}
