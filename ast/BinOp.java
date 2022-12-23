package ast;
import environment.Environment;

/**
 * Evaluates BinOps
 *
 * @author Amiya Chokhawala 
 * @version March 19, 2022
 */
public class BinOp extends Expression
{
    private String binOp;
    private Expression exp1;
    private Expression exp2; 

    /**
     * Constructor for objects of class BinOp
     */
    public BinOp(String binOp, Expression exp1, Expression exp2)
    {
        this.binOp = binOp; 
        this.exp1 = exp1; 
        this.exp2 = exp2;
    }
    
    /**
     * Evaluates the BinOp 
     * 
     * @return the evaluated BinOp 
     * @param env the Environment 
     */
    public Integer eval(Environment env) 
    {
        if (binOp.equals("+")) 
        {
            return exp1.eval(env) + exp2.eval(env);
        } 
        else if (binOp.equals("-")) 
        {
            return exp1.eval(env) - exp2.eval(env);
        } 
        else if (binOp.equals("*"))
        {
            return exp1.eval(env) * exp2.eval(env);
        }
        else if (binOp.equals("/")) 
        {
            return exp1.eval(env) / exp2.eval(env);
        }
        throw new IllegalArgumentException("Not a BinOp");
    }
}
