package ast.nodes;

import ast.ASTNode;
import backend.Visitor;

public class AssignmentAstNode extends ASTNode{
    public IdentifierAstNode identifier;
    public ASTNode expr;

    public AssignmentAstNode(long sourceStart, long sourceEnd, IdentifierAstNode identifier, ASTNode expr) {
        super(sourceStart, sourceEnd);
        this.identifier = identifier;
        this.expr = expr;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitAssignmentAstNode(this);
    }
}
