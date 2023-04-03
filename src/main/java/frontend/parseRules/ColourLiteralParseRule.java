package frontend.parseRules;

import frontend.ParserContext;
import frontend.ast.ColourLiteralASTNode;
import frontend.ast.FloatLiteralASTNode;
import frontend.tokens.Token;

public class ColourLiteralParseRule implements ParseRule<ColourLiteralASTNode>{

    @Override
    public ColourLiteralASTNode parse(ParserContext pc){
        Token t = pc.consumeToken();
        return new ColourLiteralASTNode(t.getTokenStart(),t.getTokenEnd(),t.getLexeme());
    }
}
