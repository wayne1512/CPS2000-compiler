package frontend.parseRules;

import ast.nodes.PadHeightAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class PadHeightParseRule implements ParseRule<PadHeightAstNode> {
    @Override
    public PadHeightAstNode parse(ParserContext pc) throws SyntaxErrorException {
        Token t = pc.consumeTokenSkipComments();

        if (t.getType() != Token.TokenType.PadHeight)
            pc.throwUnexpectedTokenException(t);
        
        return new PadHeightAstNode(t.getTokenStart(),t.getTokenEnd());
    }
}
