package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.PixelAstNode;
import frontend.ast.PixelRangeAstNode;
import frontend.tokens.Token;

public class PixelRangeParseRule implements ParseRule<PixelRangeAstNode>{
    @Override
    public PixelRangeAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token padReadToken = pc.consumeToken();
        if (padReadToken.getType()!= Token.TokenType.PixelRange)
            pc.throwUnexpectedTokenException(padReadToken);

        ASTNode x = new ExprParseRule().parse(pc);

        Token comma = pc.consumeToken();
        if (comma.getType()!= Token.TokenType.Comma)
            pc.throwUnexpectedTokenException(padReadToken);

        ASTNode y = new ExprParseRule().parse(pc);

        Token comma2 = pc.consumeToken();
        if (comma2.getType()!= Token.TokenType.Comma)
            pc.throwUnexpectedTokenException(padReadToken);

        ASTNode width = new ExprParseRule().parse(pc);

        Token comma3 = pc.consumeToken();
        if (comma3.getType()!= Token.TokenType.Comma)
            pc.throwUnexpectedTokenException(padReadToken);

        ASTNode height = new ExprParseRule().parse(pc);

        Token comma4 = pc.consumeToken();
        if (comma4.getType()!= Token.TokenType.Comma)
            pc.throwUnexpectedTokenException(padReadToken);

        ASTNode colour = new ExprParseRule().parse(pc);

        return new PixelRangeAstNode(padReadToken.getTokenStart(),colour.getSourceEnd(),x,y,width,height,colour);
    }
}
