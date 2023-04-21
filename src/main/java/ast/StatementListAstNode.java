package ast;

import backend.Visitor;

public class StatementListAstNode extends ASTNode{


    public StatementAstNode[] children;

    public StatementListAstNode(long sourceStart, long sourceEnd, StatementAstNode[] children){
        super(sourceStart, sourceEnd);
        this.children = children;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitStatementListAstNode(this);
    }
}
