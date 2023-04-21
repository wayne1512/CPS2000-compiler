package ast;

import backend.Visitor;

public class BlockAstNode extends ASTNode{
    StatementListAstNode child;

    public BlockAstNode(long sourceStart, long sourceEnd, StatementListAstNode child){
        super(sourceStart, sourceEnd);
        this.child = child;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitBlockAstNode(this);
    }

    @Override
    public String toString(){
        return "<Block>"+child+"</Block>";
    }
}
