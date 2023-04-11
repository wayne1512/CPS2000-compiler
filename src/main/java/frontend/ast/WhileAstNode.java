package frontend.ast;

public class WhileAstNode extends ASTNode{
    ASTNode expr;
    BlockAstNode block;

    public WhileAstNode(long sourceStart, long sourceEnd, ASTNode expr, BlockAstNode block){
        super(sourceStart, sourceEnd);
        this.expr = expr;
        this.block = block;
    }

    @Override
    public String toString(){
        return String.format("<For>%s%s</For>", expr,block);
    }

}
