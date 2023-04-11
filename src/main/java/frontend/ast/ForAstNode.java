package frontend.ast;

public class ForAstNode extends ASTNode{
    VarDeclAstNode decl;
    ASTNode expr;
    AssignmentAstNode assignment;
    BlockAstNode block;

    public ForAstNode(long sourceStart, long sourceEnd, VarDeclAstNode decl, ASTNode expr, AssignmentAstNode assignment, BlockAstNode block){
        super(sourceStart, sourceEnd);
        this.decl = decl;
        this.expr = expr;
        this.assignment = assignment;
        this.block = block;
    }

    @Override
    public String toString(){
        return String.format("<For>%s%s%s%s</For>", decl,expr,assignment,block);
    }

}
