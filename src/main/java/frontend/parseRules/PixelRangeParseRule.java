package frontend.parseRules;

import ast.ASTNode;
import ast.nodes.PixelRangeAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class PixelRangeParseRule implements ParseRule<PixelRangeAstNode>{
    @Override
    public PixelRangeAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token pixelToken = pc.consumeTokenSkipComments();
        if (pixelToken.getType()!= Token.TokenType.PixelRange)
            pc.throwUnexpectedTokenException(pixelToken);

        ASTNode x = new ExprParseRule().parse(pc);

        Token comma = pc.consumeTokenSkipComments();
        if (comma.getType()!= Token.TokenType.Comma)
            pc.throwUnexpectedTokenException(comma);

        ASTNode y = new ExprParseRule().parse(pc);

        Token comma2 = pc.consumeTokenSkipComments();
        if (comma2.getType()!= Token.TokenType.Comma)
            pc.throwUnexpectedTokenException(comma2);

        ASTNode width = new ExprParseRule().parse(pc);

        Token comma3 = pc.consumeTokenSkipComments();
        if (comma3.getType()!= Token.TokenType.Comma)
            pc.throwUnexpectedTokenException(comma3);

        ASTNode height = new ExprParseRule().parse(pc);

        Token comma4 = pc.consumeTokenSkipComments();
        if (comma4.getType()!= Token.TokenType.Comma)
            pc.throwUnexpectedTokenException(comma4);

        ASTNode colour = new ExprParseRule().parse(pc);

        return new PixelRangeAstNode(pixelToken.getTokenStart(),colour.getSourceEnd(),x,y,width,height,colour);
    }
}
