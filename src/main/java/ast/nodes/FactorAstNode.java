package ast.nodes;

import ast.ASTNode;
import backend.Visitor;

public class FactorAstNode extends ASTNode{
    public ASTNode child;

    public FactorAstNode(long sourceStart, long sourceEnd, ASTNode child){
        super(sourceStart, sourceEnd);
        this.child = child;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitFactorAstNode(this);
    }

}
