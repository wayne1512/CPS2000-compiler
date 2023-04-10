package frontend.ast;

public class VarDeclASTNode extends ASTNode{
    IdentifierASTNode identifier;
    ASTNode type;

    ASTNode expr;

    public VarDeclASTNode(long sourceStart, long sourceEnd, IdentifierASTNode identifier, ASTNode type, ASTNode expr) {
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
