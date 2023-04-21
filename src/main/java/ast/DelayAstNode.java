package ast;

import backend.Visitor;

public class DelayAstNode extends ASTNode{


    public ASTNode x;

    public DelayAstNode(long sourceStart, long sourceEnd, ASTNode x){
        super(sourceStart, sourceEnd);
        this.x = x;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitDelayAstNode(this);
    }
}
