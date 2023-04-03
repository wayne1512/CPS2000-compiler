package frontend.parseRules;

import frontend.ParserContext;
import frontend.ast.BinaryOpASTNode;
import frontend.ast.IntegerLiteralASTNode;
import frontend.tokens.Token;
import frontend.tokens.Token.TokenType;

public class TermParseRule implements ParseRule<BinaryOpASTNode>{
    @Override
    public BinaryOpASTNode parse(ParserContext pc){
        Token lookahead = pc.lookahead(1);

        BinaryOpASTNode.OpType op = null;

        if (lookahead.getType() == TokenType.Multiply)
            op = BinaryOpASTNode.OpType.mul;
        else if (lookahead.getType() == TokenType.Divide)
            op = BinaryOpASTNode.OpType.div;

        IntegerLiteralASTNode left = new IntegerLiteralParseRule().parse(pc);
        pc.consumeToken();
        IntegerLiteralASTNode right = new IntegerLiteralParseRule().parse(pc);

        return new BinaryOpASTNode(left.getSourceStart(),right.getSourceEnd(),op,left,right);

    }
}
