package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.DelayAstNode;
import frontend.tokens.Token;

public class DelayParseRule implements ParseRule<DelayAstNode>{
    @Override
    public DelayAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeToken();
        if (t.getType()!= Token.TokenType.Delay)
            pc.throwUnexpectedTokenException(t);

        ASTNode x = new ExprParseRule().parse(pc);

        return new DelayAstNode(t.getTokenStart(),x.getSourceEnd(),x);
    }
}
