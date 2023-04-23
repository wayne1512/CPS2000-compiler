package ast;

import backend.Visitor;

public class SubExprAstNode extends ASTNode{

    public ASTNode child;

    public SubExprAstNode(long sourceStart, long sourceEnd, ASTNode child){
        super(sourceStart, sourceEnd);
        this.child = child;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitSubExprAstNode(this);
    }
}
