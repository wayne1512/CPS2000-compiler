package frontend.ast;

public class IdentifierASTNode extends ASTNode{
    private final String val;

    public IdentifierASTNode(long sourceStart, long sourceEnd, String val){
        super(sourceStart, sourceEnd);
        this.val = val;
    }

    public String getVal(){
        return val;
    }

    @Override
    public String toString(){
        return String.format("<Id>%s</Id>",val);
    }
}
