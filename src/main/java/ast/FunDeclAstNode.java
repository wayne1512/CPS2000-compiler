package ast;

import backend.Visitor;

public class FunDeclAstNode extends ASTNode{
    IdentifierAstNode identifier;
    FormalParamsAstNode params;
    TypeLiteralAstNode type;
    BlockAstNode codeBlock;

    public FunDeclAstNode(long sourceStart, long sourceEnd, IdentifierAstNode identifier, FormalParamsAstNode params, TypeLiteralAstNode type, BlockAstNode codeBlock){
        super(sourceStart, sourceEnd);
        this.identifier = identifier;
        this.params = params;
        this.type = type;
        this.codeBlock = codeBlock;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitFunDeclAstNode(this);
    }
}
