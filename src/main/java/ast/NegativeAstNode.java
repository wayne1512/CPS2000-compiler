package ast;

public class NegativeAstNode extends ASTNode{

    ASTNode child;

    public NegativeAstNode(long sourceStart, long sourceEnd, ASTNode child){
        super(sourceStart, sourceEnd);
        this.child = child;
    }

    @Override
    public String toString(){
        return String.format("<Negative>%s</Negative>", child);
    }
}
