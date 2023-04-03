package frontend;

import frontend.ast.ASTNode;
import frontend.parseRules.ParseRule;
import frontend.parseRules.TermParseRule;
import frontend.tokens.Token;

import java.util.LinkedList;
import java.util.Queue;

public class Parser{
    Lexer lexer;
    LinkedList<Token> lookahead = new LinkedList<>();


    public Parser(Lexer lexer){
        this.lexer = lexer;
    }

    public Token consumeToken(){
        if (lookahead.size() == 0)
            lookahead.add(lexer.nextToken());
        return lookahead.pop();
    }

    public Token lookahead(int amount){
        while (lookahead.size() <= amount)
            lookahead.add(lexer.nextToken());
        return lookahead.get(amount);
    }

    public ASTNode parse(){
        ParseRule<? extends ASTNode> startRule = new TermParseRule();

        return startRule.parse(new ParserContext(this));
    }


}
