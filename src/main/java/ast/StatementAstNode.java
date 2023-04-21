package ast;

import backend.Visitor;

public class StatementAstNode extends ASTNode{

    ASTNode child;

    public StatementAstNode(long sourceStart, long sourceEnd, ASTNode child){
        super(sourceStart, sourceEnd);
        this.child = child;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitStatementAstNode(this);
    }
}
