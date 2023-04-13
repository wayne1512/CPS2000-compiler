package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.ReturnAstNode;
import frontend.tokens.Token;

public class ReturnParseRule implements ParseRule<ReturnAstNode>{
    @Override
    public ReturnAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token padRandiToken = pc.consumeTokenSkipComments();
        if (padRandiToken.getType()!= Token.TokenType.Retrn)
            pc.throwUnexpectedTokenException(padRandiToken);

        ASTNode x = new ExprParseRule().parse(pc);

        return new ReturnAstNode(padRandiToken.getTokenStart(),x.getSourceEnd(),x);
    }
}
