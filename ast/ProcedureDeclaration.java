package ast;
import environment.Environment;
import java.util.ArrayList;
import java.util.List;


/**
 * Evaluates Procedure Declarations
 *
 * @author Amiya Chokhawala
 * @version April 7, 2022
 */
public class ProcedureDeclaration extends Statement
{
    private String name; 
    private List<String> params = new ArrayList<String>();
    private Statement stmt;
    
    /**
     * Constructor for objects of class ProcedureDeclaration
     */
    public ProcedureDeclaration(String name, Statement stmt, List<String> params)
    {
        this.name = name;
        this.stmt = stmt;
        this.params = params; 
    }
    
    /**
     * Sets the name 
     * 
     *@param n the value the name is set to
     */
    public void setName(String n)
    {
        name = n;
    }
    
    /**
     * Gets the name 
     * 
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Gets the list of parameters  
     * 
     * @return the list of parameters
     */
    public List<String> getParams()
    {
        return params;
    }
    
    /**
     * Gets the statement 
     * 
     * @return the stmt 
     */
    public Statement getStatement()
    {
        return stmt;
    }
    
    /**
     * Executes the procedure declaration
     */
    public void exec(Environment env)
    {
        env.setProcedure(name, this);        
    }
}

