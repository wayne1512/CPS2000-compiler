package frontend.parseRules;

import ast.ASTNode;
import ast.nodes.PixelAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class PixelParseRule implements ParseRule<PixelAstNode>{
    @Override
    public PixelAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token padReadToken = pc.consumeTokenSkipComments();
        if (padReadToken.getType()!= Token.TokenType.Pixel)
            pc.throwUnexpectedTokenException(padReadToken);

        ASTNode x = new ExprParseRule().parse(pc);

        Token comma = pc.consumeTokenSkipComments();
        if (comma.getType()!= Token.TokenType.Comma)
            pc.throwUnexpectedTokenException(padReadToken);

        ASTNode y = new ExprParseRule().parse(pc);

        Token comma2 = pc.consumeTokenSkipComments();
        if (comma2.getType()!= Token.TokenType.Comma)
            pc.throwUnexpectedTokenException(padReadToken);

        ASTNode colour = new ExprParseRule().parse(pc);

        return new PixelAstNode(padReadToken.getTokenStart(),colour.getSourceEnd(),x,y,colour);
    }
}
