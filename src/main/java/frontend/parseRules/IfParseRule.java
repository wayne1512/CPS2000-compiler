package frontend.parseRules;

import ast.ASTNode;
import ast.nodes.BlockAstNode;
import ast.nodes.IfAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class IfParseRule implements ParseRule<IfAstNode>{
    @Override
    public IfAstNode parse(ParserContext pc) throws SyntaxErrorException{

        Token ifToken = pc.consumeTokenSkipComments();
        if (ifToken.getType() != Token.TokenType.If)
            pc.throwUnexpectedTokenException(ifToken);

        Token openBracToken = pc.consumeTokenSkipComments();
        if (openBracToken.getType() != Token.TokenType.BracOpen)
            pc.throwUnexpectedTokenException(openBracToken);

        ASTNode conditionExpr = new ExprParseRule().parse(pc);

        Token closeBracToken = pc.consumeTokenSkipComments();
        if (closeBracToken.getType() != Token.TokenType.BracClose)
            pc.throwUnexpectedTokenException(closeBracToken);

        BlockAstNode thenBlock = new BlockParseRule().parse(pc);



        BlockAstNode elseBlock = null;
        Token elseToken = pc.lookaheadSkipComments(0);
        if (elseToken != null && elseToken.getType() == Token.TokenType.Else){
            //consume else
            pc.consumeTokenSkipComments();

            elseBlock = new BlockParseRule().parse(pc);
        }

        //get the end of the astnode
        long srsEnd = elseBlock!=null?elseBlock.getSourceEnd():thenBlock.getSourceEnd();
        return new IfAstNode(ifToken.getTokenStart(),srsEnd,conditionExpr,thenBlock,elseBlock);
    }
}
