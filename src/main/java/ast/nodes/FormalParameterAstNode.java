package ast.nodes;

import ast.ASTNode;
import backend.Visitor;

public class FormalParameterAstNode extends ASTNode{
    public IdentifierAstNode identifier;
    public TypeLiteralAstNode type;
    public FormalParameterAstNode(long sourceStart, long sourceEnd, IdentifierAstNode identifier, TypeLiteralAstNode type) {
        super(sourceStart, sourceEnd);
        this.identifier = identifier;
        this.type = type;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitFormalParameterAstNode(this);
    }
}
