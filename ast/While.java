package ast;
import environment.Environment;

/**
 * Executes while statements 
 *
 * @author Amiya Chokhawala 
 * @version March 31, 2022 
 */
public class While extends Statement 
{
    private Condition cond; 
    private Statement stmt; 

    /**
     * Constructor for objects of class If
     */
    public While(Condition cond, Statement stmt)
    {
        this.cond = cond; 
        this.stmt = stmt; 
    }
    
    /**
     * Executes while statements 
     */
    public void exec(Environment env) {
        while (cond.eval(env) > 0) 
        {
            stmt.exec(env);
        }
    }
}
