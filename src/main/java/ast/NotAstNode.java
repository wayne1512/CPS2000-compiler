package ast;

import backend.Visitor;

public class NotAstNode extends ASTNode{

    ASTNode child;

    public NotAstNode(long sourceStart, long sourceEnd, ASTNode child){
        super(sourceStart, sourceEnd);
        this.child = child;
    }

    @Override
    public String toString(){
        return String.format("<Not>%s</Not>", child);
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitNotAstNode(this);
    }
}
