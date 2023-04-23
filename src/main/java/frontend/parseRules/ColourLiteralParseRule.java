package frontend.parseRules;

import ast.nodes.ColourLiteralAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class ColourLiteralParseRule implements ParseRule<ColourLiteralAstNode>{

    @Override
    public ColourLiteralAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeTokenSkipComments();
        return new ColourLiteralAstNode(t.getTokenStart(), t.getTokenEnd(), t.getLexeme());
    }
}
