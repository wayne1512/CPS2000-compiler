package ast;

public class PadReadAstNode extends ASTNode{


    public ASTNode x;
    public ASTNode y;

    public PadReadAstNode(long sourceStart, long sourceEnd, ASTNode x, ASTNode y){
        super(sourceStart, sourceEnd);
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return String.format("<PadRead>%s%s</PadRead>", x,y);
    }
}
