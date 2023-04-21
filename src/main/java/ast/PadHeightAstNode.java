package ast;

import backend.Visitor;

public class PadHeightAstNode extends ASTNode {
    public PadHeightAstNode(long sourceStart, long sourceEnd) {
        super(sourceStart, sourceEnd);
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitPadHeightAstNode(this);
    }
}
