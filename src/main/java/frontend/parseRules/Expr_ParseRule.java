package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.BinaryOpASTNode;
import frontend.tokens.Token;

public class Expr_ParseRule implements ParseRule<ASTNode>{

    ASTNode left;

    public Expr_ParseRule(ASTNode left){
        this.left = left;
    }

    @Override
    public ASTNode parse(ParserContext pc) throws SyntaxErrorException{
        Token lookahead = pc.lookahead(0);

        BinaryOpASTNode.OpType op = null;

        //no operand - take the empty string
        if (lookahead == null)
            return left;

        if (lookahead.getType() == Token.TokenType.LT)
            op = BinaryOpASTNode.OpType.LT;
        else if (lookahead.getType() == Token.TokenType.GT)
            op = BinaryOpASTNode.OpType.GT;
        else if (lookahead.getType() == Token.TokenType.EQ)
            op = BinaryOpASTNode.OpType.EQ;
        else if (lookahead.getType() == Token.TokenType.NE)
            op = BinaryOpASTNode.OpType.NE;
        else if (lookahead.getType() == Token.TokenType.GTE)
            op = BinaryOpASTNode.OpType.GTE;
        else if (lookahead.getType() == Token.TokenType.LTE)
            op = BinaryOpASTNode.OpType.LTE;


        //no operand - take the empty string
        if (op == null)
            return left;
        else
            pc.consumeToken();

        ASTNode term = new SimpleExprParseRule().parse(pc);

        ASTNode newLeft = new BinaryOpASTNode(left.getSourceStart(), term.getSourceEnd(), op, left, term);


        return new Expr_ParseRule(newLeft).parse(pc);

    }
}
