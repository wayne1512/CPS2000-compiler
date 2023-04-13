package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.BooleanLiteralAstNode;
import frontend.tokens.Token;

public class BooleanLiteralParseRule implements ParseRule<BooleanLiteralAstNode>{
    @Override
    public BooleanLiteralAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeTokenSkipComments();
        return new BooleanLiteralAstNode(t.getTokenStart(), t.getTokenEnd(), Boolean.parseBoolean(t.getLexeme()));
    }
}
