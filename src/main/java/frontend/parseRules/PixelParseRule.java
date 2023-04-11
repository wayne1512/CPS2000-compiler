package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.PadReadAstNode;
import frontend.ast.PixelAstNode;
import frontend.tokens.Token;

public class PixelParseRule implements ParseRule<PixelAstNode>{
    @Override
    public PixelAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token padReadToken = pc.consumeToken();
        if (padReadToken.getType()!= Token.TokenType.Pixel)
            pc.throwUnexpectedTokenException(padReadToken);

        ASTNode x = new ExprParseRule().parse(pc);

        Token comma = pc.consumeToken();
        if (comma.getType()!= Token.TokenType.Comma)
            pc.throwUnexpectedTokenException(padReadToken);

        ASTNode y = new ExprParseRule().parse(pc);

        Token comma2 = pc.consumeToken();
        if (comma2.getType()!= Token.TokenType.Comma)
            pc.throwUnexpectedTokenException(padReadToken);

        ASTNode colour = new ExprParseRule().parse(pc);

        return new PixelAstNode(padReadToken.getTokenStart(),colour.getSourceEnd(),x,y,colour);
    }
}