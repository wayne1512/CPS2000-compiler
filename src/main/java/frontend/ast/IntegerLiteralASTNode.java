package frontend.ast;

public class IntegerLiteralASTNode extends ASTNode{
    private final int val;

    public IntegerLiteralASTNode(long sourceStart, long sourceEnd, String val){
        super(sourceStart, sourceEnd);
        this.val = Integer.parseInt(val);
    }

    public int getVal(){
        return val;
    }

    @Override
    public String toString(){
        return String.format("<Int>%d</Int>",val);
    }
}
