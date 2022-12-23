package environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ast.ProcedureCall;
import ast.ProcedureDeclaration;

/**
 * The Environment class which which remembers the values of variables. 
 *
 * @author Amiya Chokhawala 
 * @version March 17, 2022 
 */
public class Environment
{
    private HashMap<String, Integer> variables;
    private HashMap<String, ProcedureDeclaration> procedures;
    private Environment parent; 

    /**
     * Constructor for objects of class Environment
     */
    public Environment()
    {
        this.variables = new HashMap<String, Integer>();
        this.procedures = new HashMap<String, ProcedureDeclaration>();
        this.parent = null; 
    }
    
    /**
     * Constructor for objects of class Environment which creates a parent 
     */
    public Environment(Environment parent)
    {
        this.variables = new HashMap<String, Integer>();
        this.parent = parent;
    }

    /**
     * Declare the variable by putting it into the map 
     * 
     * @param variable name of the variable  
     * @param value value of the variable 
     */
    public void declareVariable(String variable, int value)
    {
        this.variables.put(variable, value);
    }

    /**
     * Sets the variable by putting it into the map 
     * 
     * @param variable name of the variable  
     * @param value value of the variable 
     */
    public void setVariable(String variable, int value)
    {
        if (variables.containsKey(variable))
        {
            this.variables.put(variable, value);
        }
        else if (parent != null && parent.getVarMap().containsKey(variable)) 
        {
            parent.getVarMap().put(variable, value);
        }
        else
        {
            declareVariable(variable, value);
        }
    }

    /**
     * Gets the value of the variable 
     * 
     * @param variable 
     * @return the value of the variable 
     */
    public int getVariable(String variable)
    {
        if (variables.containsKey(variable))
        {
            return this.variables.get(variable);
        }
        else 
        {
            return parent.getVariable(variable);
        }
    }
    
    /**
     * Gets the value of the variable 
     * 
     * @param name of the procedure
     * @param p the procedure declaration
     * @return the value of the variable 
     */
    public void setProcedure(String name, ProcedureDeclaration p)
    {
        this.procedures.put(name, p);
    }
    
    /**
     * Gets the name of the procedure declaration 
     * 
     * @param name of the procedure
     * @return the name of the procedure
     */
    public ProcedureDeclaration getProcedure(String name)
    {
        return this.procedures.get(name);
    }   
    
    /**
     * Gets the map of variables 
     * 
     * @return the map of variables
     */
    public HashMap<String, Integer> getVarMap()
    {
        return variables;
    }
}
