package ast;

public class BlockAstNode extends ASTNode{
    StatementListAstNode child;

    public BlockAstNode(long sourceStart, long sourceEnd, StatementListAstNode child){
        super(sourceStart, sourceEnd);
        this.child = child;
    }

    @Override
    public String toString(){
        return "<Block>"+child+"</Block>";
    }
}
