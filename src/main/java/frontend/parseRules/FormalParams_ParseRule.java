package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.ActualParamsAstNode;
import frontend.ast.FormalParameterAstNode;
import frontend.ast.FormalParamsAstNode;
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
        Token lookahead = pc.lookahead(0);

        if (lookahead == null || lookahead.getType() != Token.TokenType.Comma)
            return left;

        //consume comma
        pc.consumeToken();

        FormalParameterAstNode param = new FormalParameterParseRule().parse(pc);

        ArrayList<FormalParameterAstNode> paramList = new ArrayList<>(Arrays.asList(left.children));
        paramList.add(param);
        FormalParameterAstNode[] paramArr = paramList.toArray(new FormalParameterAstNode[0]);

        FormalParamsAstNode newLeft = new FormalParamsAstNode(left.getSourceStart(), param.getSourceEnd(), paramArr);


        return new FormalParams_ParseRule(newLeft).parse(pc);

    }
}
