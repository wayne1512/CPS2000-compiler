package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.ActualParamsAstNode;

public class ActualParamsParseRule implements ParseRule<ActualParamsAstNode>{
    @Override
    public ActualParamsAstNode parse(ParserContext pc) throws SyntaxErrorException{


        ASTNode left = new ExprParseRule().parse(pc);
        ActualParamsAstNode leftAsActualParams = new ActualParamsAstNode(left.getSourceStart(), left.getSourceEnd(), new ASTNode[]{left});


        return new ActualParams_ParseRule(leftAsActualParams).parse(pc);
    }
}
