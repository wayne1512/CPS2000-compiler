package ast.nodes;

import ast.ASTNode;
import backend.Visitor;

public class BlockAstNode extends ASTNode{
    public StatementListAstNode child;

    public BlockAstNode(long sourceStart, long sourceEnd, StatementListAstNode child){
        super(sourceStart, sourceEnd);
        this.child = child;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitBlockAstNode(this);
    }

}
