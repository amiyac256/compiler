package ast;
import environment.Environment;

/**
 * Executes if statements. 
 *
 * @author Amiya Chokhawala 
 * @version March 31, 2022 
 */
public class If extends Statement 
{
    // instance variables - replace the example below with your own
    private Condition cond; 
    private Statement stmt; 

    /**
     * Constructor for objects of class If
     */
    public If(Condition cond, Statement stmt)
    {
        this.cond = cond; 
        this.stmt = stmt; 
    }
    
    /**
     * Executes if statements 
     */
    public void exec(Environment env) {
        if (cond.eval(env) > 0) 
        {
            stmt.exec(env);
        }
    }
}
