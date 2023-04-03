package frontend.ast;

public class FloatLiteralASTNode extends ASTNode{
    private final float val;

    public FloatLiteralASTNode(long sourceStart, long sourceEnd, float val){
        super(sourceStart, sourceEnd);
        this.val = val;
    }

    public float getVal(){
        return val;
    }


    @Override
    public String toString(){
        return String.format("<Float>%f</Float>",val);
    }
}
