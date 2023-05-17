package frontend.parseRules;

import ast.ASTNode;
import ast.nodes.PixelAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class PixelParseRule implements ParseRule<PixelAstNode>{
    @Override
    public PixelAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token pixelToken = pc.consumeTokenSkipComments();
        if (pixelToken.getType()!= Token.TokenType.Pixel)
            pc.throwUnexpectedTokenException(pixelToken);

        ASTNode x = new ExprParseRule().parse(pc);

        Token comma = pc.consumeTokenSkipComments();
        if (comma.getType()!= Token.TokenType.Comma)
            pc.throwUnexpectedTokenException(comma);

        ASTNode y = new ExprParseRule().parse(pc);

        Token comma2 = pc.consumeTokenSkipComments();
        if (comma2.getType()!= Token.TokenType.Comma)
            pc.throwUnexpectedTokenException(comma2);

        ASTNode colour = new ExprParseRule().parse(pc);

        return new PixelAstNode(pixelToken.getTokenStart(),colour.getSourceEnd(),x,y,colour);
    }
}
