package frontend.ast;

public class IfAstNode extends ASTNode{
    ASTNode condition;
    BlockAstNode thenBlock;

    BlockAstNode elseBlock;

    public IfAstNode(long sourceStart, long sourceEnd, ASTNode condition, BlockAstNode thenBlock, BlockAstNode elseBlock){
        super(sourceStart, sourceEnd);
        this.condition = condition;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
    }

    @Override
    public String toString(){
        return String.format("<If>%s%s%s</If>", condition,thenBlock,elseBlock);
    }

}
