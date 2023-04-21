package frontend.parseRules;

import ast.ASTNode;
import ast.NegativeAstNode;
import ast.NotAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class UnaryParseRule implements ParseRule<ASTNode>{

    @Override
    public ASTNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeTokenSkipComments();

        if (t.getType() != Token.TokenType.Subtract && t.getType()!= Token.TokenType.Not)
            //error
            pc.throwUnexpectedTokenException(t);

        ASTNode child = new ExprParseRule().parse(pc);


        if (t.getType() == Token.TokenType.Subtract)
            return new NegativeAstNode(t.getTokenStart(),child.getSourceEnd(),child);
        else
            return new NotAstNode(t.getTokenStart(),child.getSourceEnd(),child);
    }
}
