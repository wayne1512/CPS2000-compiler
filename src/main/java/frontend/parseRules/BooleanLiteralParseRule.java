package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.BooleanLiteralASTNode;
import frontend.ast.FloatLiteralASTNode;
import frontend.tokens.Token;

public class BooleanLiteralParseRule implements ParseRule<BooleanLiteralASTNode>{
    @Override
    public BooleanLiteralASTNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeToken();
        return new BooleanLiteralASTNode(t.getTokenStart(),t.getTokenEnd(), Boolean.parseBoolean(t.getLexeme()));
    }
}
