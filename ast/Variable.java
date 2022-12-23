package ast;
import environment.Environment;

/**
 * Evaluates Variable objects 
 *
 * @author Amiya Chokhawala 
 * @version March 19, 2022
 */
public class Variable extends Expression
{
    private String name;

    /**
     * Constructor for objects of class Variable
     */
    public Variable(String name)
    {
       this.name = name; 
    }
    
    /**
     * Evaluates the Variable 
     * 
     * @return the variable's name
     * @param env the Environment 
     */
    public Integer eval(Environment env) {
      return env.getVariable(name);
    }
}
