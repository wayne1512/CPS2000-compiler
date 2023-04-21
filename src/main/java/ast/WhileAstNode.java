package ast;

import backend.Visitor;

public class WhileAstNode extends ASTNode{
    ASTNode expr;
    BlockAstNode block;

    public WhileAstNode(long sourceStart, long sourceEnd, ASTNode expr, BlockAstNode block){
        super(sourceStart, sourceEnd);
        this.expr = expr;
        this.block = block;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitWhileAstNode(this);
    }
}
