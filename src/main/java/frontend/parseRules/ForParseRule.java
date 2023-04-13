package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.*;
import frontend.tokens.Token;

public class ForParseRule implements ParseRule<ForAstNode>{
    @Override
    public ForAstNode parse(ParserContext pc) throws SyntaxErrorException{

        Token forToken = pc.consumeTokenSkipComments();
        if (forToken.getType() != Token.TokenType.For)
            pc.throwUnexpectedTokenException(forToken);

        Token openBracToken = pc.consumeTokenSkipComments();
        if (openBracToken.getType() != Token.TokenType.BracOpen)
            pc.throwUnexpectedTokenException(openBracToken);


        VarDeclAstNode decleration = null;

        Token possibleSemicolon = pc.lookaheadSkipComments(0);
        if (possibleSemicolon.getType() != Token.TokenType.SemiColon){
            decleration = new VarDeclParseRule().parse(pc);
        }


        Token semicolon1 = pc.consumeTokenSkipComments();
        if (semicolon1.getType() != Token.TokenType.SemiColon)
            pc.throwUnexpectedTokenException(semicolon1);

        ASTNode conditionExpr = new ExprParseRule().parse(pc);

        Token semicolon2 = pc.consumeTokenSkipComments();
        if (semicolon2.getType() != Token.TokenType.SemiColon)
            pc.throwUnexpectedTokenException(semicolon2);

        AssignmentAstNode assignment = null;

        Token possibleCloseBracket = pc.lookaheadSkipComments(0);
        if (possibleCloseBracket.getType() != Token.TokenType.BracClose){
            assignment = new AssignmentParseRule().parse(pc);
        }

        Token closeBracToken = pc.consumeTokenSkipComments();
        if (closeBracToken.getType() != Token.TokenType.BracClose)
            pc.throwUnexpectedTokenException(closeBracToken);

        BlockAstNode codeBlock = new BlockParseRule().parse(pc);

        return new ForAstNode(forToken.getTokenStart(),codeBlock.getSourceEnd(),decleration,conditionExpr,assignment,codeBlock);
    }
}
