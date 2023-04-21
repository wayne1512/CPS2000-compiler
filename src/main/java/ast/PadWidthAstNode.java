package ast;

public class PadWidthAstNode extends ASTNode {
    public PadWidthAstNode(long sourceStart, long sourceEnd) {
        super(sourceStart, sourceEnd);
    }

    @Override
    public String toString() {
        return "<PadWidth/>";
    }
}
