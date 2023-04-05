package frontend.ast;

public class ColourLiteralASTNode extends ASTNode{
    private final String val;

    public ColourLiteralASTNode(long sourceStart, long sourceEnd, String val){
        super(sourceStart, sourceEnd);
        this.val = val;
    }

    public String getVal(){
        return val;
    }

    @Override
    public String toString(){
        return String.format("<Colour>%s</Colour>", val);
    }
}
