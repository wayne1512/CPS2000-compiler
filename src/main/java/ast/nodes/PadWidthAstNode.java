package ast.nodes;

import ast.ASTNode;
import backend.Visitor;

public class PadWidthAstNode extends ASTNode{
    public PadWidthAstNode(long sourceStart, long sourceEnd) {
        super(sourceStart, sourceEnd);
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitPadWidthAstNode(this);
    }
}
