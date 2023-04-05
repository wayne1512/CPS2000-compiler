package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;

public class SimpleExprParseRule implements ParseRule<ASTNode>{
    @Override
    public ASTNode parse(ParserContext pc) throws SyntaxErrorException{


        ASTNode left = new TermParseRule().parse(pc);

        return new SimpleExpr_ParseRule(left).parse(pc);
    }
}
