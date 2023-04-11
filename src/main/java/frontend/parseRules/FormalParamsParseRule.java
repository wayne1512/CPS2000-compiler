package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.ActualParamsAstNode;
import frontend.ast.FormalParameterAstNode;
import frontend.ast.FormalParamsAstNode;

public class FormalParamsParseRule implements ParseRule<FormalParamsAstNode>{
    @Override
    public FormalParamsAstNode parse(ParserContext pc) throws SyntaxErrorException{


        FormalParameterAstNode left = new FormalParameterParseRule().parse(pc);
        FormalParamsAstNode leftAsFormalParams = new FormalParamsAstNode(left.getSourceStart(), left.getSourceEnd(), new FormalParameterAstNode[]{left});


        return new FormalParams_ParseRule(leftAsFormalParams).parse(pc);
    }
}
