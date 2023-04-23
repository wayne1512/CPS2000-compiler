package frontend.parseRules;

import ast.ASTNode;
import ast.nodes.ActualParamsAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

import java.util.ArrayList;
import java.util.Arrays;

public class ActualParams_ParseRule implements ParseRule<ActualParamsAstNode>{

    ActualParamsAstNode left;

    public ActualParams_ParseRule(ActualParamsAstNode left){
        this.left = left;
    }

    @Override
    public ActualParamsAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token lookahead = pc.lookaheadSkipComments(0);

        if (lookahead == null || lookahead.getType() != Token.TokenType.Comma)
            return left;

        //consume comma
        pc.consumeTokenSkipComments();

        ASTNode expr = new ExprParseRule().parse(pc);

        ArrayList<ASTNode> paramList = new ArrayList<>(Arrays.asList(left.children));
        paramList.add(expr);
        ASTNode[] paramArr = paramList.toArray(new ASTNode[0]);

        ActualParamsAstNode newLeft = new ActualParamsAstNode(left.getSourceStart(), expr.getSourceEnd(), paramArr);


        return new ActualParams_ParseRule(newLeft).parse(pc);

    }
}
