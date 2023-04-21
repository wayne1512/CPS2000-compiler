package ast;

import backend.Visitor;

public class FunctionCallAstNode extends ASTNode{
    IdentifierAstNode identifier;
    ActualParamsAstNode params;

    public FunctionCallAstNode(long sourceStart, long sourceEnd, IdentifierAstNode identifier, ActualParamsAstNode params){
        super(sourceStart, sourceEnd);
        this.identifier = identifier;
        this.params = params;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitFunctionCallAstNode(this);
    }
}
