package frontend.ast;

public class ReturnAstNode extends ASTNode{


    public ASTNode x;

    public ReturnAstNode(long sourceStart, long sourceEnd, ASTNode x){
        super(sourceStart, sourceEnd);
        this.x = x;
    }

    @Override
    public String toString(){
        return String.format("<Return>%s</Return>", x);
    }
}
