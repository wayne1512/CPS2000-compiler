package ast;

import backend.Visitor;

public class PadWidthAstNode extends ASTNode {
    public PadWidthAstNode(long sourceStart, long sourceEnd) {
        super(sourceStart, sourceEnd);
    }

    @Override
    public String toString() {
        return "<PadWidth/>";
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitPadWidthAstNode(this);
    }
}
