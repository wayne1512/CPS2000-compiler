package ast.nodes;

import ast.ASTNode;
import backend.Visitor;

public class FormalParamsAstNode extends ASTNode{


    public FormalParameterAstNode[] children;

    public FormalParamsAstNode(long sourceStart, long sourceEnd, FormalParameterAstNode[] children){
        super(sourceStart, sourceEnd);
        this.children = children;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitFormalParamsAstNode(this);
    }
}
