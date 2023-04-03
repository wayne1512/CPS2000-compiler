package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.BinaryOpASTNode;
import frontend.ast.FactorAstNode;
import frontend.ast.IntegerLiteralASTNode;
import frontend.tokens.Token;

public class Term_ParseRule implements ParseRule<ASTNode>{

    ASTNode left;

    public Term_ParseRule(ASTNode left){
        this.left = left;
    }

    @Override
    public ASTNode parse(ParserContext pc) throws SyntaxErrorException{
        Token lookahead = pc.lookahead(0);

        BinaryOpASTNode.OpType op = null;

        //no operand - take the empty string
        if (lookahead == null)
            return left;

        if (lookahead.getType() == Token.TokenType.Multiply)
            op = BinaryOpASTNode.OpType.mul;
        else if (lookahead.getType() == Token.TokenType.Divide)
            op = BinaryOpASTNode.OpType.div;


        //no operand - take the empty string
        if (op == null)
            return left;
        else
            pc.consumeToken();

        FactorAstNode factor = new FactorParseRule().parse(pc);

        ASTNode newLeft = new BinaryOpASTNode(left.getSourceStart(),factor.getSourceEnd(),op,left,factor);



        return new Term_ParseRule(newLeft).parse(pc);

    }
}
