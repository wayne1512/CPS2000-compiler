package ast.nodes;

import ast.ASTNode;
import backend.Visitor;

public class IfAstNode extends ASTNode{
    public ASTNode condition;
    public BlockAstNode thenBlock;

    public BlockAstNode elseBlock;

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
