package ast;

import backend.Visitor;

public class NotAstNode extends ASTNode{

    public ASTNode child;

    public NotAstNode(long sourceStart, long sourceEnd, ASTNode child){
        super(sourceStart, sourceEnd);
        this.child = child;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitNotAstNode(this);
    }
}
