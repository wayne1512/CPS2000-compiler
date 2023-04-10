package frontend.ast;

public class PrintAstNode extends ASTNode{


    public ASTNode x;

    public PrintAstNode(long sourceStart, long sourceEnd, ASTNode x){
        super(sourceStart, sourceEnd);
        this.x = x;
    }

    @Override
    public String toString(){
        return String.format("<PadPrint>%s</PadPrint>", x);
    }
}
