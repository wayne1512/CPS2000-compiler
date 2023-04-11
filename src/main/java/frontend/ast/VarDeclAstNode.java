package frontend.ast;

public class VarDeclAstNode extends ASTNode{
    IdentifierAstNode identifier;
    ASTNode type;

    ASTNode expr;

    public VarDeclAstNode(long sourceStart, long sourceEnd, IdentifierAstNode identifier, ASTNode type, ASTNode expr) {
        super(sourceStart, sourceEnd);
        this.identifier = identifier;
        this.type = type;
        this.expr = expr;
    }

    @Override
    public String toString(){
        return String.format("<Decl>%s%s%s</Decl>", identifier,type,expr);
    }

}
