package frontend.ast;

public class BooleanLiteralASTNode extends ASTNode{
    private final boolean val;

    public BooleanLiteralASTNode(long sourceStart, long sourceEnd, boolean val){
        super(sourceStart, sourceEnd);
        this.val = val;
    }

    public boolean getVal(){
        return val;
    }

    @Override
    public String toString(){
        return String.format("<Bool>%b</Bool>", val);
    }
}
