package frontend.parseRules;

import ast.FormalParameterAstNode;
import ast.FormalParamsAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

import java.util.ArrayList;
import java.util.Arrays;

public class FormalParams_ParseRule implements ParseRule<FormalParamsAstNode>{

    FormalParamsAstNode left;

    public FormalParams_ParseRule(FormalParamsAstNode left){
        this.left = left;
    }

    @Override
    public FormalParamsAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token lookahead = pc.lookaheadSkipComments(0);

        if (lookahead == null || lookahead.getType() != Token.TokenType.Comma)
            return left;

        //consume comma
        pc.consumeTokenSkipComments();

        FormalParameterAstNode param = new FormalParameterParseRule().parse(pc);

        ArrayList<FormalParameterAstNode> paramList = new ArrayList<>(Arrays.asList(left.children));
        paramList.add(param);
        FormalParameterAstNode[] paramArr = paramList.toArray(new FormalParameterAstNode[0]);

        FormalParamsAstNode newLeft = new FormalParamsAstNode(left.getSourceStart(), param.getSourceEnd(), paramArr);


        return new FormalParams_ParseRule(newLeft).parse(pc);

    }
}
