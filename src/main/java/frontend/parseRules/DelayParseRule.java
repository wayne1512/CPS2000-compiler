package frontend.parseRules;

import ast.ASTNode;
import ast.nodes.DelayAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class DelayParseRule implements ParseRule<DelayAstNode>{
    @Override
    public DelayAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeTokenSkipComments();
        if (t.getType()!= Token.TokenType.Delay)
            pc.throwUnexpectedTokenException(t);

        ASTNode x = new ExprParseRule().parse(pc);

        return new DelayAstNode(t.getTokenStart(),x.getSourceEnd(),x);
    }
}
