package ast;
import java.util.ArrayList;
import java.util.List;
import ast.Expression;
import environment.Environment;

/**
 * Evaluates Procedure Calls 
 *
 * @author Amiya Chokhawala
 * @version April 19, 2022
 */
public class ProcedureCall extends Expression
{
    private String name;
    private List<Expression> args = new ArrayList<Expression>();

    /**
     * Constructor for objects of class ProcedureCall
     */
    public ProcedureCall(String name, List<Expression> args)
    {
        this.name = name;
        this.args = args;
    }

    /**
     * Sets the name of the procedure call 
     * 
     * @param n what it is being set to
     */
    public void setName(String n)
    {
        name = n;
    }

    /**
     * Returns the name of the procedure call
     * 
     * @return name the name of the procedure call 
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the arguments of the procedure call
     * 
     * @return args the argyments of the procedure call 
     */
    public List<Expression> getArgs()
    {
        return args;
    }
    
    /**
     * Evaluates the Procedure Call 
     * 
     * @return the value after the procedure call is executed
     */
    public Integer eval(Environment env)
    {
        Environment local = new Environment(env);
        ProcedureDeclaration pd = env.getProcedure(getName());
        //local.declareVariable(getName(), );
        List<String> params = pd.getParams();
        int index = 0;
        for (Expression exp : args) {
            Integer returnVal = exp.eval(env);
            local.setVariable(params.get(index), returnVal);
            index++;
        }
        Statement stmt = pd.getStatement();
        stmt.exec(local);
        return local.getVariable(getName());
    }
}
