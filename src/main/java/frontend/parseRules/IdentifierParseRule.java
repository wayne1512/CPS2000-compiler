package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.IdentifierAstNode;
import frontend.tokens.Token;

public class IdentifierParseRule implements ParseRule<IdentifierAstNode>{

    @Override
    public IdentifierAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeTokenSkipComments();
        if (t.getType() != Token.TokenType.Identifier)
            pc.throwUnexpectedTokenException(t);
        return new IdentifierAstNode(t.getTokenStart(), t.getTokenEnd(), t.getLexeme());
    }
}
