package frontend.parseRules;

import ast.nodes.IntegerLiteralAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class IntegerLiteralParseRule implements ParseRule<IntegerLiteralAstNode>{

    @Override
    public IntegerLiteralAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeTokenSkipComments();
        return new IntegerLiteralAstNode(t.getTokenStart(), t.getTokenEnd(), Integer.parseInt(t.getLexeme()));
    }
}
