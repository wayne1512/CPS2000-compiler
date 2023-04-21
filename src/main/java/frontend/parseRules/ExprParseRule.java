package frontend.parseRules;

import ast.ASTNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;

public class ExprParseRule implements ParseRule<ASTNode>{
    @Override
    public ASTNode parse(ParserContext pc) throws SyntaxErrorException{


        ASTNode left = new SimpleExprParseRule().parse(pc);

        return new Expr_ParseRule(left).parse(pc);
    }
}
