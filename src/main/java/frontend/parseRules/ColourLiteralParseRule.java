package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ColourLiteralASTNode;
import frontend.tokens.Token;

public class ColourLiteralParseRule implements ParseRule<ColourLiteralASTNode>{

    @Override
    public ColourLiteralASTNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeToken();
        return new ColourLiteralASTNode(t.getTokenStart(), t.getTokenEnd(), t.getLexeme());
    }
}
