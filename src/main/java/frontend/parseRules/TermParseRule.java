package frontend.parseRules;

import ast.ASTNode;
import ast.FactorAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;

public class TermParseRule implements ParseRule<ASTNode>{
    @Override
    public ASTNode parse(ParserContext pc) throws SyntaxErrorException{


        FactorAstNode left = new FactorParseRule().parse(pc);

        return new Term_ParseRule(left).parse(pc);
    }
}
