package frontend;

import exceptions.SyntaxErrorException;
import frontend.tokens.Token;

public class ParserContext{
    Parser p;

    public ParserContext(Parser p){
        this.p = p;
    }

    public Token consumeToken() throws SyntaxErrorException{
        return p.consumeToken();
    }

    public Token lookahead(int amount) throws SyntaxErrorException{
        return p.lookahead(amount);
    }
}
