package frontend.parseRules;

import ast.StatementAstNode;
import ast.StatementListAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StatementList_ParseRule implements ParseRule<StatementListAstNode>{


    //contains a list of tokens that indicate that we have another statement
    public Set<Token.TokenType> statementFirstToken = new HashSet<>(Arrays.asList(
       Token.TokenType.Let,
            Token.TokenType.Identifier,
            Token.TokenType.Print,
            Token.TokenType.Delay,
            Token.TokenType.Pixel,
            Token.TokenType.PixelRange,
            Token.TokenType.If,
            Token.TokenType.For,
            Token.TokenType.While,
            Token.TokenType.Retrn,
            Token.TokenType.Fun,
            Token.TokenType.CurlyBracOpen
    ));

    StatementListAstNode left;

    public StatementList_ParseRule(StatementListAstNode left){
        this.left = left;
    }

    @Override
    public StatementListAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token lookahead = pc.lookaheadSkipComments(0);

        if (lookahead == null || !statementFirstToken.contains(lookahead.getType()))
            return left;


        StatementAstNode stmt = new StatementParseRule().parse(pc);

        ArrayList<StatementAstNode> stmtList = new ArrayList<>(Arrays.asList(left.children));
        stmtList.add(stmt);
        StatementAstNode[] stmtArr = stmtList.toArray(new StatementAstNode[0]);

        StatementListAstNode newLeft = new StatementListAstNode(left.getSourceStart(), stmt.getSourceEnd(), stmtArr);


        return new StatementList_ParseRule(newLeft).parse(pc);

    }
}
