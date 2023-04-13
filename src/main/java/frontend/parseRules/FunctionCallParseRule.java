package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ActualParamsAstNode;
import frontend.ast.FunctionCallAstNode;
import frontend.ast.IdentifierAstNode;
import frontend.tokens.Token;

public class FunctionCallParseRule implements ParseRule<FunctionCallAstNode>{
    @Override
    public FunctionCallAstNode parse(ParserContext pc) throws SyntaxErrorException{

        IdentifierAstNode identifier = new IdentifierParseRule().parse(pc);

        Token openBrac = pc.consumeTokenSkipComments();
        if (openBrac.getType()!= Token.TokenType.BracOpen)
            pc.throwUnexpectedTokenException(openBrac);

        ActualParamsAstNode params = null;

        Token paramsLookahead = pc.lookaheadSkipComments(0);
        if (paramsLookahead.getType() != Token.TokenType.BracClose)
            //parse the parameters
            params = new ActualParamsParseRule().parse(pc);

        Token closeBrac = pc.consumeTokenSkipComments();
        if (closeBrac.getType()!= Token.TokenType.BracClose)
            pc.throwUnexpectedTokenException(closeBrac);

        return new FunctionCallAstNode(identifier.getSourceStart(),closeBrac.getTokenEnd(),identifier,params);
    }
}
