package frontend.parseRules;

import ast.ASTNode;
import ast.PadReadAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class PadReadParseRule implements ParseRule<PadReadAstNode>{
    @Override
    public PadReadAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token padReadToken = pc.consumeTokenSkipComments();
        if (padReadToken.getType()!= Token.TokenType.PadRead)
            pc.throwUnexpectedTokenException(padReadToken);

        ASTNode x = new ExprParseRule().parse(pc);

        Token comma = pc.consumeTokenSkipComments();
        if (comma.getType()!= Token.TokenType.Comma)
            pc.throwUnexpectedTokenException(padReadToken);

        ASTNode y = new ExprParseRule().parse(pc);

        return new PadReadAstNode(padReadToken.getTokenStart(),y.getSourceEnd(),x,y);
    }
}
