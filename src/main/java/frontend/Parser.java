package frontend;

import ast.ASTNode;
import exceptions.SyntaxErrorException;
import frontend.parseRules.ParseRule;
import frontend.parseRules.StatementListParseRule;
import frontend.tokens.Token;

import java.util.LinkedList;

public class Parser{
    Lexer lexer;
    LinkedList<Token> lookahead = new LinkedList<>();


    public Parser(Lexer lexer){
        this.lexer = lexer;
    }

    public Token consumeToken() throws SyntaxErrorException{
        if (lookahead.size() == 0)
            lookahead.add(lexer.nextToken());
        return lookahead.pop();
    }

    public Token lookahead(int amount) throws SyntaxErrorException{
        while (lookahead.size() <= amount)
            lookahead.add(lexer.nextToken());
        return lookahead.get(amount);
    }

    public ASTNode parse() throws SyntaxErrorException{
        ParseRule<? extends ASTNode> startRule = new StatementListParseRule();

        return startRule.parse(new ParserContext(this));
    }


}
