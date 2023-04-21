package ast;

import backend.Visitor;

public class IdentifierAstNode extends ASTNode{
    private final String val;

    public IdentifierAstNode(long sourceStart, long sourceEnd, String val){
        super(sourceStart, sourceEnd);
        this.val = val;
    }

    public String getVal(){
        return val;
    }

    @Override
    public String toString(){
        return String.format("<Id>%s</Id>", val);
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitIdentifierAstNode(this);
    }
}
