package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.FloatLiteralAstNode;
import frontend.tokens.Token;

public class FloatLiteralParseRule implements ParseRule<FloatLiteralAstNode>{

    @Override
    public FloatLiteralAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeToken();
        return new FloatLiteralAstNode(t.getTokenStart(), t.getTokenEnd(), Float.parseFloat(t.getLexeme()));
    }
}
