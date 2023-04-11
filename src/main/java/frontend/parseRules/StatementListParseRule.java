package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.FormalParameterAstNode;
import frontend.ast.StatementAstNode;
import frontend.ast.StatementListAstNode;

public class StatementListParseRule implements ParseRule<StatementListAstNode>{
    @Override
    public StatementListAstNode parse(ParserContext pc) throws SyntaxErrorException{


        StatementAstNode left = new StatementParseRule().parse(pc);
        StatementListAstNode leftAsStatementList = new StatementListAstNode(left.getSourceStart(), left.getSourceEnd(), new StatementAstNode[]{left});


        return new StatementList_ParseRule(leftAsStatementList).parse(pc);
    }
}
