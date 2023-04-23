package frontend.parseRules;

import ast.nodes.FloatLiteralAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class FloatLiteralParseRule implements ParseRule<FloatLiteralAstNode>{

    @Override
    public FloatLiteralAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeTokenSkipComments();
        return new FloatLiteralAstNode(t.getTokenStart(), t.getTokenEnd(), Float.parseFloat(t.getLexeme()));
    }
}
