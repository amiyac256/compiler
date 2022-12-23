package ast;
import java.util.List;
import environment.Environment;

/**
 * Evaluates Blocks
 *
 * @author Amiya Chokhawala 
 * @version March 11, 2022
 */
public class Block extends Statement 
{
    private List<Statement> stmts; 

    /**
     * Constructor for objects of class Block
     */
    public Block(List<Statement> stmts)
    {
        this.stmts = stmts; 
    }

    /**
     * Evaluates the list of statemnets that make up the block 
     * @param env the Environment 
     */
    public void exec(Environment env)
    {
        for (Statement stmt: stmts) 
        {
            stmt.exec(env);
        }
    }
}
