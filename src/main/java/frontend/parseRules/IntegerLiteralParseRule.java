package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.IntegerLiteralAstNode;
import frontend.tokens.Token;

public class IntegerLiteralParseRule implements ParseRule<IntegerLiteralAstNode>{

    @Override
    public IntegerLiteralAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeToken();
        return new IntegerLiteralAstNode(t.getTokenStart(), t.getTokenEnd(), Integer.parseInt(t.getLexeme()));
    }
}
