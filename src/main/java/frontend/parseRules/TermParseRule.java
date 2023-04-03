package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.BinaryOpASTNode;
import frontend.ast.FactorAstNode;
import frontend.ast.IntegerLiteralASTNode;
import frontend.tokens.Token;
import frontend.tokens.Token.TokenType;

public class TermParseRule implements ParseRule<ASTNode>{
    @Override
    public ASTNode parse(ParserContext pc) throws SyntaxErrorException{


        FactorAstNode left = new FactorParseRule().parse(pc);

        return new Term_ParseRule(left).parse(pc);
    }
}
