package ast;

import backend.Visitor;

public class VarDeclAstNode extends ASTNode{
    public IdentifierAstNode identifier;
    public ASTNode type;

    public ASTNode expr;

    public VarDeclAstNode(long sourceStart, long sourceEnd, IdentifierAstNode identifier, ASTNode type, ASTNode expr) {
        super(sourceStart, sourceEnd);
        this.identifier = identifier;
        this.type = type;
        this.expr = expr;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitVarDeclAstNode(this);
    }
}
