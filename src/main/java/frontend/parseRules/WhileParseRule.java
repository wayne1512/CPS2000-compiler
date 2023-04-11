package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.*;
import frontend.tokens.Token;

public class WhileParseRule implements ParseRule<WhileAstNode>{
    @Override
    public WhileAstNode parse(ParserContext pc) throws SyntaxErrorException{

        Token whileToken = pc.consumeToken();
        if (whileToken.getType() != Token.TokenType.While)
            pc.throwUnexpectedTokenException(whileToken);

        Token openBracToken = pc.consumeToken();
        if (openBracToken.getType() != Token.TokenType.BracOpen)
            pc.throwUnexpectedTokenException(openBracToken);


        ASTNode conditionExpr = new ExprParseRule().parse(pc);

        Token closeBracToken = pc.consumeToken();
        if (closeBracToken.getType() != Token.TokenType.BracClose)
            pc.throwUnexpectedTokenException(closeBracToken);

        BlockAstNode codeBlock = new BlockParseRule().parse(pc);

        return new WhileAstNode(whileToken.getTokenStart(),codeBlock.getSourceEnd(),conditionExpr,codeBlock);
    }
}
