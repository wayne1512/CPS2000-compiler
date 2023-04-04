package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;

public class ExprParseRule implements ParseRule<ASTNode>{
    @Override
    public ASTNode parse(ParserContext pc) throws SyntaxErrorException{


        ASTNode left = new SimpleExprParseRule().parse(pc);

        return new Expr_ParseRule(left).parse(pc);
    }
}
