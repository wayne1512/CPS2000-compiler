package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.SubExprASTNode;
import frontend.tokens.Token;

public class SubExprParseRule implements ParseRule<SubExprASTNode>{
    @Override
    public SubExprASTNode parse(ParserContext pc) throws SyntaxErrorException{
        Token a = pc.consumeToken();
        if (a.getType() != Token.TokenType.BracOpen)
            pc.throwUnexpectedTokenException(a);

        ASTNode child = new ExprParseRule().parse(pc);

        Token b = pc.consumeToken();
        if (b.getType() != Token.TokenType.BracClose)
            pc.throwUnexpectedTokenException(a);

        return new SubExprASTNode(a.getTokenStart(), b.getTokenEnd(), child);

    }
}