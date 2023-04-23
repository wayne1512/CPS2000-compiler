package ast;

import backend.Visitor;

public class LiteralAstNode extends ASTNode{

    public ASTNode child;

    public LiteralAstNode(long sourceStart, long sourceEnd, ASTNode child){
        super(sourceStart, sourceEnd);
        this.child = child;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitLiteralAstNode(this);
    }
}
