package frontend.parseRules;

import ast.StatementAstNode;
import ast.StatementListAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;

public class StatementListParseRule implements ParseRule<StatementListAstNode>{
    @Override
    public StatementListAstNode parse(ParserContext pc) throws SyntaxErrorException{


        StatementAstNode left = new StatementParseRule().parse(pc);
        StatementListAstNode leftAsStatementList = new StatementListAstNode(left.getSourceStart(), left.getSourceEnd(), new StatementAstNode[]{left});


        return new StatementList_ParseRule(leftAsStatementList).parse(pc);
    }
}
