package ast;

import backend.Visitor;

public class FormalParameterAstNode extends ASTNode{
    IdentifierAstNode identifier;
    ASTNode type;
    public FormalParameterAstNode(long sourceStart, long sourceEnd, IdentifierAstNode identifier, ASTNode type) {
        super(sourceStart, sourceEnd);
        this.identifier = identifier;
        this.type = type;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitFormalParameterAstNode(this);
    }
}
