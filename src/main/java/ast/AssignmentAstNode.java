package ast;

import backend.Visitor;

public class AssignmentAstNode extends ASTNode{
    IdentifierAstNode identifier;
    ASTNode expr;

    public AssignmentAstNode(long sourceStart, long sourceEnd, IdentifierAstNode identifier, ASTNode expr) {
        super(sourceStart, sourceEnd);
        this.identifier = identifier;
        this.expr = expr;
    }

    @Override
    public String toString(){
        return String.format("<Assign>%s%s</Assign>", identifier,expr);
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitAssignmentAstNode(this);
    }
}
