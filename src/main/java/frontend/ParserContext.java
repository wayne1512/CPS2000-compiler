package frontend;

import exceptions.SyntaxErrorException;
import frontend.tokens.Token;

public class ParserContext{
    Parser p;

    public ParserContext(Parser p){
        this.p = p;
    }

    public Token consumeTokenSkipComments() throws SyntaxErrorException{
        Token t = null;
        do{
            t = p.consumeToken();
        }while (t.getType() == Token.TokenType.SingleLineComment || t.getType() == Token.TokenType.MultiLineComment);

        return t;
    }

    public Token lookaheadSkipComments(int amount) throws SyntaxErrorException{

        int amountAfterSkippingComments = -1;

        int nonCommentTokensPassed = 0;

        Token t = null;
        while (nonCommentTokensPassed <= amount){

            amountAfterSkippingComments++;
            t = p.lookahead(amountAfterSkippingComments);

            if (t==null)
                return null;

            if (t.getType() != Token.TokenType.SingleLineComment && t.getType() != Token.TokenType.MultiLineComment)
                nonCommentTokensPassed++;
        }



        return p.lookahead(amountAfterSkippingComments);
    }

    public void throwUnexpectedTokenException(Token t) throws SyntaxErrorException{
        throw new SyntaxErrorException("unexpected token \"" + t.getLexeme() + "\"", t.getTokenStart(), t.getTokenEnd());
    }
}
