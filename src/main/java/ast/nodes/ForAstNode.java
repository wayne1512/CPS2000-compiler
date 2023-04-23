package ast.nodes;

import ast.ASTNode;
import backend.Visitor;

public class ForAstNode extends ASTNode{
    public VarDeclAstNode decl;
    public ASTNode expr;
    public AssignmentAstNode assignment;
    public BlockAstNode block;

    public ForAstNode(long sourceStart, long sourceEnd, VarDeclAstNode decl, ASTNode expr, AssignmentAstNode assignment, BlockAstNode block){
        super(sourceStart, sourceEnd);
        this.decl = decl;
        this.expr = expr;
        this.assignment = assignment;
        this.block = block;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitForAstNode(this);
    }
}
