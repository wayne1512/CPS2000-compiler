package frontend.parseRules;

import ast.ASTNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;

public class SimpleExprParseRule implements ParseRule<ASTNode>{
    @Override
    public ASTNode parse(ParserContext pc) throws SyntaxErrorException{


        ASTNode left = new TermParseRule().parse(pc);

        return new SimpleExpr_ParseRule(left).parse(pc);
    }
}
