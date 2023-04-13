package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.PadRandiAstNode;
import frontend.tokens.Token;

public class PadRandIParseRule implements ParseRule<PadRandiAstNode>{
    @Override
    public PadRandiAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token padRandiToken = pc.consumeTokenSkipComments();
        if (padRandiToken.getType()!= Token.TokenType.PadRandI)
            pc.throwUnexpectedTokenException(padRandiToken);

        ASTNode x = new ExprParseRule().parse(pc);

        return new PadRandiAstNode(padRandiToken.getTokenStart(),x.getSourceEnd(),x);
    }
}
