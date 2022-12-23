package ast;
import environment.Environment;

/**
 * Evaluates Conditions 
 *
 * @author Amiya Chokhawala 
 * @version March 31, 2022
 */
public class Condition
{
    private Expression exp1;
    private Expression exp2; 
    private String operator;

    /**
     * Constructor for objects of class Condition
     */
    public Condition(Expression exp1, Expression exp2, String operator)
    {
        this.exp1 = exp1; 
        this.exp2 = exp2;
        this.operator = operator;
    }

    /**
     * Evaluates the condition based on the type of operator 
     * 
     * @param env the environment 
     * @return 1 if true and -1 if false 
     */
    public Integer eval(Environment env) 
    {
        if (operator.equals("=")) 
        {
            if (exp1.eval(env) == exp2.eval(env)) 
            {
                return 1; 
            }
            else
            {
                return -1;
            }
        }    
        if (operator.equals("<>")) 
        {
            if (exp1.eval(env) != exp2.eval(env)) 
            {
                return 1; 
            }
            else
            {
                return -1;
            }
        }    
        if (operator.equals("<")) 
        {
            if (exp1.eval(env) < exp2.eval(env)) 
            {
                return 1; 
            }
            else
            {
                return -1;
            }
        }    
        if (operator.equals(">")) 
        {
            if (exp1.eval(env) > exp2.eval(env)) 
            {
                return 1; 
            }
            else
            {
                return -1;
            }
        }    
        if (operator.equals("<=")) 
        {
            if (exp1.eval(env) <= exp2.eval(env)) 
            {
                return 1; 
            }
            else
            {
                return -1;
            }
        }    
        if (operator.equals(">=")) 
        {
            if (exp1.eval(env) >= exp2.eval(env)) 
            {
                return 1; 
            }
            else
            {
                return -1;
            }
        }    
        return -1;
    }
}

