package ast;

public class PadRandiAstNode extends ASTNode{


    public ASTNode x;

    public PadRandiAstNode(long sourceStart, long sourceEnd, ASTNode x){
        super(sourceStart, sourceEnd);
        this.x = x;
    }

    @Override
    public String toString(){
        return String.format("<PadRandI>%s</PadRandI>", x);
    }
}
