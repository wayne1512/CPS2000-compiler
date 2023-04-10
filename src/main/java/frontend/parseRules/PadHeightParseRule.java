package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.PadHeightAstNode;
import frontend.tokens.Token;

public class PadHeightParseRule implements ParseRule<PadHeightAstNode> {
    @Override
    public PadHeightAstNode parse(ParserContext pc) throws SyntaxErrorException {
        Token t = pc.consumeToken();

        if (t.getType() != Token.TokenType.PadWidth)
            pc.throwUnexpectedTokenException(t);
        
        return new PadHeightAstNode(t.getTokenStart(),t.getTokenEnd());
    }
}
