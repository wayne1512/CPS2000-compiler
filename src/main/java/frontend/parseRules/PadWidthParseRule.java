package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.PadWidthAstNode;
import frontend.tokens.Token;

public class PadWidthParseRule implements ParseRule<PadWidthAstNode> {
    @Override
    public PadWidthAstNode parse(ParserContext pc) throws SyntaxErrorException {
        Token t = pc.consumeToken();

        if (t.getType() != Token.TokenType.PadWidth)
            pc.throwUnexpectedTokenException(t);

        return new PadWidthAstNode(t.getTokenStart(),t.getTokenEnd());
    }
}
