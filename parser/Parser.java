package parser;

import scanner.Scanner;
import scanner.ScanErrorException;
import java.io.*;
import java.util.*;
import ast.Assignment;
import ast.Condition;
import ast.BinOp;
import ast.If;
import ast.Number;
import ast.Variable;
import ast.Block;
import ast.ProcedureCall;
import ast.ProcedureDeclaration;
import ast.Program;
import ast.Statement;
import ast.Expression;
import ast.While;
import ast.Writeln;
import environment.Environment;

/**
 * Parser that executes Pascal-like statements after it parses them
 *
 * @author Amiya Chokhawala 
 * @version April 25, 2022 
 */
public class Parser
{

    private Scanner scanner; 
    private String currentToken;
    private String nextGotoInst;
    HashMap<String, Expression> map = new HashMap();

    /**
     * Parser Constructor 
     *
     * @param s the scanner
     */
    public Parser(Scanner s) throws ScanErrorException 
    {
        scanner = s; 
        currentToken = scanner.nextToken();
    }

    /**
     * Method: eat
     * Compares the value of the parameter with the value of currentToken. If they are the same,
     * the methods advances in the input by one character. If they are not the same, 
     * the method throws an exception.
     * @postcondition input string is advanced by one character
     * @param expectedToken the expected value of currentToken 
     * @throws IllegalArgumentException whenn expected and currentToken are not the same
     * @throws ScanErrorException whenn expected and currentChar are not the same
     */
    private String eat(String expectedToken) throws IllegalArgumentException, ScanErrorException
    {
        if (currentToken.equals(expectedToken))
        {
            currentToken = scanner.nextToken();
            return currentToken;
        }
        throw new IllegalArgumentException("Expected" + expectedToken + "but found" + currentToken);
    }

    /**
     * Method: parseNumber
     * Parses integers  
     * @precondition: current token is an integer 
     * @postcondition: number token has been eaten 
     * @return the value of the parsed integer 
     */
    private Number parseNumber() throws IllegalArgumentException, ScanErrorException, 
    NumberFormatException 
    {
        String token = currentToken;
        eat(currentToken);
        return new Number(Integer.parseInt(token));
    }

    /**
     * Parses WRITELN statements, BEGIN and END statements, and IF and WHILE statements,
     * and Assignments 
     * 
     * @return Statement depending on the tyep of statement parsed
     * @throws IllegalArgument Exception, ScanErrorException
     */
    public Statement parseStatement() throws IllegalArgumentException, ScanErrorException 
    {
        if(currentToken.equals("BEGIN")) 
        {
            ArrayList<Statement> stmts = new ArrayList<Statement>(); 
            eat("BEGIN");
            while(!currentToken.equals("END")) 
            {
                if (currentToken.equals("ignore")) 
                {
                    eat("ignore");
                    eat(":=");
                }
                stmts.add(parseStatement());
            }
            eat("END");
            eat(";");
            return new Block(stmts);
        }
        else if (currentToken.equals("WRITELN")) 
        {
            eat("WRITELN");
            if (currentToken.equals("("))
            {
                eat("(");
            } 
            Expression exp = parseExpression();
            if (currentToken.equals(")"))
            {
                eat(")");
                if (currentToken.equals(";")) 
                {
                    eat(";");
                }
            }
            return new Writeln(exp); 
        }
        else if (currentToken.equals("IF"))
        {
            eat("IF"); 
            Condition cond = parseCondition(); 
            eat("THEN");
            Statement stmt = parseStatement();
            return new If(cond, stmt);
        }
        else if (currentToken.equals("WHILE"))
        {
            eat("WHILE"); 
            Condition cond = parseCondition();
            eat("DO");
            Statement stmt = parseStatement();
            return new While(cond, stmt);
        }
        else 
        {
            String token = currentToken; 
            eat(currentToken);
            eat(":=");
            Expression r = parseExpression();
            eat(";");
            return new Assignment(token,  r); 
        }
    }

    /**
     * Parses conditions 
     * 
     * @postcondition the condition is parsed 
     * @return a new Condition object
     * @throws IllegalArgument Exception, ScanErrorException
     */
    private Condition parseCondition() throws IllegalArgumentException, ScanErrorException {
        Expression exp1 = parseExpression();
        String term = currentToken;
        String relOp = "";
        if (term.equals("=")) 
        {
            relOp = "=";
            eat("=");
        } else if (term.equals("<>")) 
        {
            relOp = "<>";
            eat("<>");
        } else if (term.equals("<")) 
        {
            relOp = "<";
            eat("<");
        } else if (term.equals(">")) 
        {
            relOp = ">";
            eat(">");
        } else if (term.equals("<=")) 
        {
            relOp = "<=";
            eat("<=");
        }  else  if (term.equals(">=")) 
        {
            relOp = ">=";
            eat(">=");
        } 
        if (relOp.equals("")) {

            throw new IllegalArgumentException();
        }
        Expression exp2 = parseExpression();
        return new Condition(exp1, exp2, relOp);
    }

    /**
     * Parsers the current factor 
     * a factor can be a factor or term in parentheses, a - factor, a variable, or a number
     * 
     * @postcondition the factor is parsed
     * @return result an Expression that is either a Number, BinOp, or Variable 
     * @throws NumberFormatException, IllegalArgument Exception, ScanErrorException
     */
    private Expression parseFactor() throws IllegalArgumentException, ScanErrorException,
    NumberFormatException 
    {
        Expression result = new Number(1); 
        if(currentToken.equals("(")) 
        {
            eat("("); 
            result = parseExpression(); 
            eat(")");
        }
        else if (currentToken.equals("-"))
        {
            eat("-");
            result = new BinOp("*", new Number(-1), parseFactor());
        }
        else {
            try 
            {
                int val = Integer.parseInt(currentToken);     
                eat(currentToken); 
                result = new Number(val); 
            }
            catch( NumberFormatException e)
            {
                String token = currentToken;
                eat(currentToken); 
                if(currentToken.equals("(")) {
                    List<Expression> args = new ArrayList<Expression>(); 
                    eat("(");
                    while(!currentToken.equals(")")) 
                    {
                        args.add(parseExpression());
                        if (currentToken.equals(","))
                        {
                            eat(",");
                        }
                    }
                    eat(")");
                    Statement stmt = parseStatement();
                    return new ProcedureCall(token, args); 
                } else 
                {
                    result = new Variable(token); 
                } 
            }
        }
        return result; 
    }

    /**
     * Parses the term, any expression that can be added or subtracted 
     * which could be a term * factor, term / factor, or just a factor 
     * Parses the first factor and multiplies and divides until it reaches the end of the input 
     * 
     * @postcondition the term is parsed
     * @return result the BinOp or an Expression
     * @throws IllegalArgument Exception, ScanErrorException
     */
    private Expression parseTerm() throws IllegalArgumentException, ScanErrorException 
    {
        Expression result = parseFactor(); 
        while (currentToken.equals("*") || currentToken.equals("/"))
        {
            if (currentToken.equals("*"))
            {
                eat("*");
                result = new BinOp("*", result, parseFactor());
            }
            if (currentToken.equals("/"))
            {
                eat("/");
                result = new BinOp("/", result, parseFactor());
            }
        }
        return result; 
    }

    /**
     * Parses any expressions which can contain addition and subtraction 
     * 
     * @postcondition the expression is parsed 
     * @return result the BinOp or an Expression
     * @throws IllegalArgument Exception, ScanErrorException
     */
    private Expression parseExpression() throws IllegalArgumentException, ScanErrorException 
    {
        Expression result = parseTerm();
        while (currentToken.equals("+") || currentToken.equals("-"))
        {
            if (currentToken.equals("+"))
            {
                eat("+");
                result = new BinOp("+", result, parseTerm()); 
            }
            if (currentToken.equals("-"))
            {
                eat("-");
                result = new BinOp("-", result, parseTerm());;
            }
        }
        return result; 
    }

    /**
     * Parses procedure declarations until the current token is not PROCEDURE 
     * 
     * @postcondition the program is parsed 
     * @return a new Program 
     */
    public Program parseProgram(Environment global)
    {
        List<ProcedureDeclaration> pdList = new ArrayList<ProcedureDeclaration>();
        List<ProcedureCall> pdCalls = new ArrayList<ProcedureCall>();
        List<Statement> statements = new ArrayList<Statement>();
        while(scanner.hasNext()) 
        {
            try {
                while (currentToken.equals("PROCEDURE")) 
                {
                    ProcedureDeclaration pd = parseProcedure(global);
                    eat(currentToken);
                    global.setProcedure(pd.getName(), pd);
                    pdList.add(pd);
                }
                Statement stmt = parseStatement();
                statements.add(stmt);
            } catch(IllegalArgumentException e) 
            {

            } 
            catch(ScanErrorException e) 
            {

            }
        }
        return new Program(pdList, statements);
    }

    /**
     * Parses procedures 
     * 
     * @postcondition the procedure is parsed 
     * @return a new ProcedureDeclaration
     * @throws IllegalArgument Exception, ScanErrorException
     */
    public ProcedureDeclaration parseProcedure(Environment global) throws IllegalArgumentException, ScanErrorException 
    {
        List<Statement> statements = new ArrayList<Statement>();
        List<String> params = new ArrayList<String>();
        eat(currentToken);
        String token = currentToken; 
        eat(currentToken);
        eat("(");
        while(!currentToken.equals(")")) 
        {
            params.add(currentToken);
            eat(currentToken);
            if (currentToken.equals(","))
            {
                eat(",");
            }
        }
        eat(")");
        eat(";");
        Statement stmt = parseStatement();
        return new ProcedureDeclaration(token, stmt, params); 
    }
}

