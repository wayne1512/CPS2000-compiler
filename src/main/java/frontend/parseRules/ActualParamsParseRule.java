package frontend.parseRules;

import ast.ASTNode;
import ast.ActualParamsAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;

public class ActualParamsParseRule implements ParseRule<ActualParamsAstNode>{
    @Override
    public ActualParamsAstNode parse(ParserContext pc) throws SyntaxErrorException{


        ASTNode left = new ExprParseRule().parse(pc);
        ActualParamsAstNode leftAsActualParams = new ActualParamsAstNode(left.getSourceStart(), left.getSourceEnd(), new ASTNode[]{left});


        return new ActualParams_ParseRule(leftAsActualParams).parse(pc);
    }
}
