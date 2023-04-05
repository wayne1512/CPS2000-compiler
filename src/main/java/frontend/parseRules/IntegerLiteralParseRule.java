package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.IntegerLiteralASTNode;
import frontend.tokens.Token;

public class IntegerLiteralParseRule implements ParseRule<IntegerLiteralASTNode>{

    @Override
    public IntegerLiteralASTNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeToken();
        return new IntegerLiteralASTNode(t.getTokenStart(), t.getTokenEnd(), Integer.parseInt(t.getLexeme()));
    }
}
