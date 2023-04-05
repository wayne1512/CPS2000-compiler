package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.FloatLiteralASTNode;
import frontend.tokens.Token;

public class FloatLiteralParseRule implements ParseRule<FloatLiteralASTNode>{

    @Override
    public FloatLiteralASTNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeToken();
        return new FloatLiteralASTNode(t.getTokenStart(), t.getTokenEnd(), Float.parseFloat(t.getLexeme()));
    }
}
