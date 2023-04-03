package frontend.parseRules;

import frontend.ParserContext;
import frontend.ast.FloatLiteralASTNode;
import frontend.ast.IntegerLiteralASTNode;
import frontend.tokens.Token;

public class FloatLiteralParseRule implements ParseRule<FloatLiteralASTNode>{

    @Override
    public FloatLiteralASTNode parse(ParserContext pc){
        Token t = pc.consumeToken();
        return new FloatLiteralASTNode(t.getTokenStart(),t.getTokenEnd(),Float.parseFloat(t.getLexeme()));
    }
}
