package ast;

import backend.Visitor;

public class ProgramAstNode extends ASTNode{
    StatementListAstNode child;

    public ProgramAstNode(long sourceStart, long sourceEnd, StatementListAstNode child){
        super(sourceStart, sourceEnd);
        this.child = child;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitProgramAstNode(this);
    }
}
