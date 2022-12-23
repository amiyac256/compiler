package ast;
import environment.Environment;

/**
 * Expression class with subclasses Number, Variable, and BinOp 
 *
 * @author Amiya Chokhawala
 * @version March 9, 2022
 */
public abstract class Expression
{    
    public abstract Integer eval(Environment env); 
}
