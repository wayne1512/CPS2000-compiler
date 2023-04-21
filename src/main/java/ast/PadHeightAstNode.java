package ast;

public class PadHeightAstNode extends ASTNode {
    public PadHeightAstNode(long sourceStart, long sourceEnd) {
        super(sourceStart, sourceEnd);
    }

    @Override
    public String toString() {
        return "<PadHeight/>";
    }
}
