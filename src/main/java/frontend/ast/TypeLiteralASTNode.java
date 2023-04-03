package frontend.ast;

public class TypeLiteralASTNode extends ASTNode{
    private final Type val;

    public TypeLiteralASTNode(long sourceStart, long sourceEnd, Type val){
        super(sourceStart, sourceEnd);
        this.val = val;
    }

    public Type getVal(){
        return val;
    }

    @Override
    public String toString(){
        return String.format("<Int>%b</Int>",val);
    }

    public enum Type{
        Float,Int,Bool,Colour
    }
}
