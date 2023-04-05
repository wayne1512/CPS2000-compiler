package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ColourLiteralASTNode;
import frontend.ast.IdentifierASTNode;
import frontend.tokens.Token;

public class IdentifierParseRule implements ParseRule<IdentifierASTNode>{

    @Override
    public IdentifierASTNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeToken();
        return new IdentifierASTNode(t.getTokenStart(),t.getTokenEnd(),t.getLexeme());
    }
}
