package frontend.parseRules;

import ast.ASTNode;
import ast.SubExprAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class SubExprParseRule implements ParseRule<SubExprAstNode>{
    @Override
    public SubExprAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token a = pc.consumeTokenSkipComments();
        if (a.getType() != Token.TokenType.BracOpen)
            pc.throwUnexpectedTokenException(a);

        ASTNode child = new ExprParseRule().parse(pc);

        Token b = pc.consumeTokenSkipComments();
        if (b.getType() != Token.TokenType.BracClose)
            pc.throwUnexpectedTokenException(a);

        return new SubExprAstNode(a.getTokenStart(), b.getTokenEnd(), child);

    }
}
