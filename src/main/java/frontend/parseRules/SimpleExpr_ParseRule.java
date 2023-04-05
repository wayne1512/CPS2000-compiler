package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.BinaryOpASTNode;
import frontend.tokens.Token;

public class SimpleExpr_ParseRule implements ParseRule<ASTNode>{

    ASTNode left;

    public SimpleExpr_ParseRule(ASTNode left){
        this.left = left;
    }

    @Override
    public ASTNode parse(ParserContext pc) throws SyntaxErrorException{
        Token lookahead = pc.lookahead(0);

        BinaryOpASTNode.OpType op = null;

        //no operand - take the empty string
        if (lookahead == null)
            return left;

        if (lookahead.getType() == Token.TokenType.Add)
            op = BinaryOpASTNode.OpType.add;
        else if (lookahead.getType() == Token.TokenType.Subtract)
            op = BinaryOpASTNode.OpType.sub;


        //no operand - take the empty string
        if (op == null)
            return left;
        else
            pc.consumeToken();

        ASTNode term = new TermParseRule().parse(pc);

        ASTNode newLeft = new BinaryOpASTNode(left.getSourceStart(), term.getSourceEnd(), op, left, term);


        return new SimpleExpr_ParseRule(newLeft).parse(pc);

    }
}
