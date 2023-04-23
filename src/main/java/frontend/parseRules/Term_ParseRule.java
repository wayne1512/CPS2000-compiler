package frontend.parseRules;

import ast.ASTNode;
import ast.nodes.BinaryOpAstNode;
import ast.nodes.FactorAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class Term_ParseRule implements ParseRule<ASTNode>{

    ASTNode left;

    public Term_ParseRule(ASTNode left){
        this.left = left;
    }

    @Override
    public ASTNode parse(ParserContext pc) throws SyntaxErrorException{
        Token lookahead = pc.lookaheadSkipComments(0);

        BinaryOpAstNode.OpType op = null;

        //no operand - take the empty string
        if (lookahead == null)
            return left;

        if (lookahead.getType() == Token.TokenType.Multiply)
            op = BinaryOpAstNode.OpType.mul;
        else if (lookahead.getType() == Token.TokenType.Divide)
            op = BinaryOpAstNode.OpType.div;
        else if (lookahead.getType() == Token.TokenType.And)
            op = BinaryOpAstNode.OpType.and;


        //no operand - take the empty string
        if (op == null)
            return left;
        else
            pc.consumeTokenSkipComments();

        FactorAstNode factor = new FactorParseRule().parse(pc);

        ASTNode newLeft = new BinaryOpAstNode(left.getSourceStart(), factor.getSourceEnd(), op, left, factor);


        return new Term_ParseRule(newLeft).parse(pc);

    }
}
