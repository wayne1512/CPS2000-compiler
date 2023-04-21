package frontend.parseRules;

import ast.ASTNode;
import ast.BinaryOpAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class SimpleExpr_ParseRule implements ParseRule<ASTNode>{

    ASTNode left;

    public SimpleExpr_ParseRule(ASTNode left){
        this.left = left;
    }

    @Override
    public ASTNode parse(ParserContext pc) throws SyntaxErrorException{
        Token lookahead = pc.lookaheadSkipComments(0);

        BinaryOpAstNode.OpType op = null;

        //no operand - take the empty string
        if (lookahead == null)
            return left;

        if (lookahead.getType() == Token.TokenType.Add)
            op = BinaryOpAstNode.OpType.add;
        else if (lookahead.getType() == Token.TokenType.Subtract)
            op = BinaryOpAstNode.OpType.sub;
        else if (lookahead.getType() == Token.TokenType.Or)
            op = BinaryOpAstNode.OpType.or;


        //no operand - take the empty string
        if (op == null)
            return left;
        else
            pc.consumeTokenSkipComments();

        ASTNode term = new TermParseRule().parse(pc);

        ASTNode newLeft = new BinaryOpAstNode(left.getSourceStart(), term.getSourceEnd(), op, left, term);


        return new SimpleExpr_ParseRule(newLeft).parse(pc);

    }
}
