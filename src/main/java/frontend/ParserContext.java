package frontend;

import frontend.tokens.Token;

public class ParserContext{
    Parser p;

    public ParserContext(Parser p){
        this.p = p;
    }

    public Token consumeToken(){
        return p.consumeToken();
    }

    public Token lookahead(int amount){
        return p.lookahead(amount);
    }
}
