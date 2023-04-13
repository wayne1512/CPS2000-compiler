package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.BinaryOpAstNode;
import frontend.tokens.Token;

public class Expr_ParseRule implements ParseRule<ASTNode>{

    ASTNode left;

    public Expr_ParseRule(ASTNode left){
        this.left = left;
    }

    @Override
    public ASTNode parse(ParserContext pc) throws SyntaxErrorException{
        Token lookahead = pc.lookaheadSkipComments(0);

        BinaryOpAstNode.OpType op = null;

        //no operand - take the empty string
        if (lookahead == null)
            return left;

        if (lookahead.getType() == Token.TokenType.LT)
            op = BinaryOpAstNode.OpType.LT;
        else if (lookahead.getType() == Token.TokenType.GT)
            op = BinaryOpAstNode.OpType.GT;
        else if (lookahead.getType() == Token.TokenType.EQ)
            op = BinaryOpAstNode.OpType.EQ;
        else if (lookahead.getType() == Token.TokenType.NE)
            op = BinaryOpAstNode.OpType.NE;
        else if (lookahead.getType() == Token.TokenType.GTE)
            op = BinaryOpAstNode.OpType.GTE;
        else if (lookahead.getType() == Token.TokenType.LTE)
            op = BinaryOpAstNode.OpType.LTE;


        //no operand - take the empty string
        if (op == null)
            return left;
        else
            pc.consumeTokenSkipComments();

        ASTNode term = new SimpleExprParseRule().parse(pc);

        ASTNode newLeft = new BinaryOpAstNode(left.getSourceStart(), term.getSourceEnd(), op, left, term);


        return new Expr_ParseRule(newLeft).parse(pc);

    }
}
