package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.*;
import frontend.tokens.Token;

public class ForParseRule implements ParseRule<ForAstNode>{
    @Override
    public ForAstNode parse(ParserContext pc) throws SyntaxErrorException{

        Token forToken = pc.consumeToken();
        if (forToken.getType() != Token.TokenType.For)
            pc.throwUnexpectedTokenException(forToken);

        Token openBracToken = pc.consumeToken();
        if (openBracToken.getType() != Token.TokenType.BracOpen)
            pc.throwUnexpectedTokenException(openBracToken);


        VarDeclAstNode decleration = null;

        Token possibleSemicolon = pc.lookahead(0);
        if (possibleSemicolon.getType() != Token.TokenType.SemiColon){
            decleration = new VarDeclParseRule().parse(pc);
        }


        Token semicolon1 = pc.consumeToken();
        if (semicolon1.getType() != Token.TokenType.SemiColon)
            pc.throwUnexpectedTokenException(semicolon1);

        ASTNode conditionExpr = new ExprParseRule().parse(pc);

        Token semicolon2 = pc.consumeToken();
        if (semicolon2.getType() != Token.TokenType.SemiColon)
            pc.throwUnexpectedTokenException(semicolon2);

        AssignmentAstNode assignment = null;

        Token possibleCloseBracket = pc.lookahead(0);
        if (possibleCloseBracket.getType() != Token.TokenType.BracClose){
            assignment = new AssignmentParseRule().parse(pc);
        }

        Token closeBracToken = pc.consumeToken();
        if (closeBracToken.getType() != Token.TokenType.BracClose)
            pc.throwUnexpectedTokenException(closeBracToken);

        BlockAstNode codeBlock = new BlockParseRule().parse(pc);

        return new ForAstNode(forToken.getTokenStart(),codeBlock.getSourceEnd(),decleration,conditionExpr,assignment,codeBlock);
    }
}
