package frontend.ast;

public class AssignmentAstNode extends ASTNode{
    IdentifierASTNode identifier;
    ASTNode expr;

    public AssignmentAstNode(long sourceStart, long sourceEnd, IdentifierASTNode identifier, ASTNode expr) {
        super(sourceStart, sourceEnd);
        this.identifier = identifier;
        this.expr = expr;
    }

    @Override
    public String toString(){
        return String.format("<Assign>%s%s</Assign>", identifier,expr);
    }

}
