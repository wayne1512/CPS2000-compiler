package ast.nodes;

import ast.ASTNode;
import backend.Visitor;

public class ReturnAstNode extends ASTNode{


    public ASTNode x;

    public ReturnAstNode(long sourceStart, long sourceEnd, ASTNode x){
        super(sourceStart, sourceEnd);
        this.x = x;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitReturnAstNode(this);
    }
}
