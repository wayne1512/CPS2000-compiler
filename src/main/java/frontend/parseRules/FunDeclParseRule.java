package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.*;
import frontend.tokens.Token;

public class FunDeclParseRule implements ParseRule<FunDeclAstNode>{
    @Override
    public FunDeclAstNode parse(ParserContext pc) throws SyntaxErrorException{

        Token funToken = pc.consumeToken();
        if (funToken.getType() != Token.TokenType.Fun)
            pc.throwUnexpectedTokenException(funToken);

        IdentifierAstNode identifier = new IdentifierParseRule().parse(pc);

        Token openBracToken = pc.consumeToken();
        if (openBracToken.getType() != Token.TokenType.BracOpen)
            pc.throwUnexpectedTokenException(openBracToken);

        FormalParamsAstNode params = null;
        Token possibleCloseBracket = pc.lookahead(0);
        if (possibleCloseBracket.getType() != Token.TokenType.BracClose)
            params = new FormalParamsParseRule().parse(pc);

        Token closeBracToken = pc.consumeToken();
        if (closeBracToken.getType() != Token.TokenType.BracClose)
            pc.throwUnexpectedTokenException(closeBracToken);

        Token arrowToken = pc.consumeToken();
        if (arrowToken.getType() != Token.TokenType.Arrow)
            pc.throwUnexpectedTokenException(arrowToken);

        TypeLiteralAstNode type = new TypeLiteralParseRule().parse(pc);

        BlockAstNode codeBlock = new BlockParseRule().parse(pc);

        return new FunDeclAstNode(funToken.getTokenStart(),codeBlock.getSourceEnd(),identifier,params,type,codeBlock);
    }
}
