package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ColourLiteralAstNode;
import frontend.tokens.Token;

public class ColourLiteralParseRule implements ParseRule<ColourLiteralAstNode>{

    @Override
    public ColourLiteralAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeToken();
        return new ColourLiteralAstNode(t.getTokenStart(), t.getTokenEnd(), t.getLexeme());
    }
}
