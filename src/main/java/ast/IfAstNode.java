package ast;

import backend.Visitor;

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
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitIfAstNode(this);
    }
}
