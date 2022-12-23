package ast;
import java.util.List;
import ast.ProcedureDeclaration;
import ast.ProcedureCall; 
import ast.Statement;
import environment.Environment;

/**
 * Write a description of class Program here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Program extends Statement 
{
    // instance variables - replace the example below with your own
    private List<ProcedureDeclaration> decls;
    private List<Statement> stmts;

    /**
     * Constructor for objects of class Program
     */
    public Program(List<ProcedureDeclaration> decls, List<Statement> stmts)
    {
        this.decls = decls;
        this.stmts = stmts; 
    }
    
    public void exec(Environment env)
    { 
        int index = 0;
        while(index < stmts.size())
        {
            Statement stmt = stmts.get(index);
            index++;
            stmt.exec(env);
        }
    }
}
