package ast;

import backend.Visitor;

public class NegativeAstNode extends ASTNode{

    ASTNode child;

    public NegativeAstNode(long sourceStart, long sourceEnd, ASTNode child){
        super(sourceStart, sourceEnd);
        this.child = child;
    }

    @Override
    public String toString(){
        return String.format("<Negative>%s</Negative>", child);
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitNegativeAstNode(this);
    }
}
